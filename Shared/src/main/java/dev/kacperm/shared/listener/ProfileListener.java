package dev.kacperm.shared.listener;

import dev.kacperm.shared.profile.Profile;
import dev.kacperm.shared.profile.manager.SharedProfileManager;
import dev.kacperm.shared.utils.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class ProfileListener implements Listener {

    private final Plugin plugin;
    private final SharedProfileManager profileManager;
    private final Config profileConfig;

    public ProfileListener(Plugin plugin, SharedProfileManager profileManager, Config profileConfig) {
        this.plugin = plugin;
        this.profileManager = profileManager;
        this.profileConfig = profileConfig;
    }

    @EventHandler
    public void onAsyncJoin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();

        Optional<Profile> optionalProfile = profileManager.loadProfile(event.getUniqueId());
        if (!optionalProfile.isPresent()) {
            profileManager.profiles().put(uuid, Profile.builder()
                    .uuid(uuid)
                    .team(null)
                    .balance(profileConfig.getConfiguration().getInt("starting-balance"))
                    .kills(0)
                    .deaths(0)
                    .lives(0)
                    .lastDeath(0)
                    .lastLogout(0)
                    .frozen(false)
                    .staff(false)
                    .reclaimed(false)
                    .firstJoin(new Date().getTime())
                    .playTime(0)
                    .pvpTimer(0)
                    .startingTimer(profileConfig.getConfiguration().getLong("timers.starting-timer") * 1000).build());
        } else {
            Profile profile = optionalProfile.get();
            profile.setCurrentSession(new Date().getTime());
            profileManager.profiles().put(uuid, profile);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Profile profile = profileManager.profiles().get(player.getUniqueId());
        profile.setPvpTimer(profileConfig.getConfiguration().getLong("timers.pvp-timer") * 1000);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        profileManager.profiles().get(player.getUniqueId()).increaseDeaths();
        profileManager.profiles().get(killer.getUniqueId()).increaseKills();
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
