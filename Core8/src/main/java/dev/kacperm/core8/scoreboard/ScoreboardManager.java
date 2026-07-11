package dev.kacperm.core8.scoreboard;

import dev.kacperm.core8.Core;
import dev.kacperm.core8.utils.color.Color;
import dev.kacperm.shared.scoreboard.SharedScoreboardManager;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;

import java.util.*;

public class ScoreboardManager implements SharedScoreboardManager<FastBoard> {

    private final Core plugin = Core.getInstance();
    private final Map<UUID, FastBoard> boards = new HashMap<>();

    @Override
    public Map<UUID, FastBoard> boards() {
        return boards;
    }

    @Override
    public void create(Player player) {
        FastBoard fastBoard = new FastBoard(player);
        fastBoard.updateTitle(Color.translate(plugin.getConfiguration()
                .getConfiguration().getString("scoreboard.title")));
        boards.put(player.getUniqueId(), fastBoard);
    }

    @Override
    public void remove(Player player) {
        FastBoard fastBoard = boards.get(player.getUniqueId());
        if (fastBoard != null) {
            fastBoard.delete();
        }
    }

    @Override
    public void update(Player player) {
        FastBoard fastBoard = boards.get(player.getUniqueId());

        List<String> lines = new ArrayList<>();
        for (final String line : plugin.getConfiguration().getConfiguration().getStringList("scoreboard.lines")) {
            lines.add(Color.translate(line));
        }

        fastBoard.updateLines(lines);
    }
}
