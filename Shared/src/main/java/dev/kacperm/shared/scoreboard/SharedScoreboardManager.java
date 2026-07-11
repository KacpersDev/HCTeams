package dev.kacperm.shared.scoreboard;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface SharedScoreboardManager<T> {

    Map<UUID, T> boards();

    void create(Player player);
    void remove(Player player);
    void update(Player player);
}