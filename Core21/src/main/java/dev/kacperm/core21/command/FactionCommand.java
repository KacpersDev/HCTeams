package dev.kacperm.core21.command;

import dev.kacperm.core21.Core;
import dev.kacperm.shared.command.AdvancedSubCommand;
import dev.kacperm.shared.command.AdvancedSubCommandBuilder;
import dev.kacperm.shared.command.impl.SharedFactionCommand;
import dev.kacperm.shared.command.messages.SharedFactionCommandMessages;
import dev.kacperm.shared.faction.PlayerFaction;
import dev.kacperm.shared.faction.manager.SharedFactionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.HashSet;
import java.util.Set;

public class FactionCommand extends SharedFactionCommand {

    private final Core plugin = Core.getInstance();
    private final Set<AdvancedSubCommand> subCommands = new HashSet<>();

    public FactionCommand(SharedFactionCommandMessages factionCommandMessages) {
        super(factionCommandMessages);

        AdvancedSubCommandBuilder advancedSubCommandBuilder = new AdvancedSubCommandBuilder("create", "", true);
        subCommands.add(advancedSubCommandBuilder.build());
    }

    @Override
    public String name() {
        return "faction";
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
    public Set<AdvancedSubCommand> advancedSubCommands() {
        return subCommands;
    }

    @Override
    public SharedFactionManager<PlayerFaction> sharedFactionManager() {
        return plugin.getFactionManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }
}
