package dev.kacperm.shared.listener;

import dev.kacperm.shared.scoreboard.SharedScoreboardManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ScoreboardListener implements Listener {

    private final SharedScoreboardManager<?> sharedScoreboardManager;

    public ScoreboardListener(SharedScoreboardManager<?> sharedScoreboardManager) {
        this.sharedScoreboardManager = sharedScoreboardManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        sharedScoreboardManager.create(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        sharedScoreboardManager.remove(event.getPlayer());
    }
}
