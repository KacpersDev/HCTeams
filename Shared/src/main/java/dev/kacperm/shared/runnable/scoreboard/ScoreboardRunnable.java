package dev.kacperm.shared.runnable.scoreboard;

import dev.kacperm.shared.scoreboard.SharedScoreboardManager;
import org.bukkit.Bukkit;

import java.util.UUID;

public class ScoreboardRunnable implements Runnable {

    private final SharedScoreboardManager<?> sharedScoreboardManager;

    public ScoreboardRunnable(SharedScoreboardManager<?> sharedScoreboardManager) {
        this.sharedScoreboardManager = sharedScoreboardManager;
    }

    @Override
    public void run() {
        for (UUID uuid : sharedScoreboardManager.boards().keySet()) {
            sharedScoreboardManager.update(Bukkit.getPlayer(uuid));
        }
    }
}
