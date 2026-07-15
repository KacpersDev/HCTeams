package dev.kacperm.core21.command;

import dev.kacperm.shared.command.AdvancedSubCommand;
import dev.kacperm.shared.command.AdvancedSubCommandBuilder;
import dev.kacperm.shared.command.impl.SharedFactionCommand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FactionCommand extends SharedFactionCommand {

    @Override
    public void usageMessage(Player player) {

    }

    @Override
    public String name() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public Map<Set<String>, AdvancedSubCommand> advancedSubCommands() {
        Map<String[], AdvancedSubCommand> map = new HashMap<>();

        AdvancedSubCommand advancedSubCommand = new AdvancedSubCommandBuilder(null, null, false).getAdvancedSubCommand();

        map.put(new HashSet<>(Set.of("")), advancedSubCommand);

        return map;
    }
}
