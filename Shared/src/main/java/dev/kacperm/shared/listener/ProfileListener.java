package dev.kacperm.shared.listener;

import dev.kacperm.shared.profile.Profile;
import dev.kacperm.shared.profile.manager.SharedProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class ProfileListener implements Listener {

    private final Plugin plugin;
    private final SharedProfileManager profileManager;

    public ProfileListener(Plugin plugin, SharedProfileManager profileManager) {
        this.plugin = plugin;
        this.profileManager = profileManager;
    }

    @EventHandler
    public void onAsyncJoin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();

        Optional<Profile> optionalProfile = profileManager.loadProfile(event.getUniqueId());
        if (!optionalProfile.isPresent()) {
            profileManager.profiles().put(uuid, Profile.builder()
                    .uuid(uuid)
                    .team(null)
                    .balance(0)
                    .kills(0)
                    .deaths(0)
                    .lives(0)
                    .lastDeath(0)
                    .lastLogout(0)
                    .frozen(false)
                    .staff(false)
                    .firstJoin(new Date().getTime())
                    .playTime(0).build());
        } else {
            Profile profile = optionalProfile.get();
            profile.setCurrentSession(new Date().getTime());
            profileManager.profiles().put(uuid, profile);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            Profile profile = profileManager.profiles().remove(event.getPlayer().getUniqueId());
            long session = profile.getCurrentSession();
            profile.setPlayTime(profile.getPlayTime() + session);
            profile.setLastLogout(new Date().getTime());
            profileManager.saveProfile(profile);
        });
    }
}
