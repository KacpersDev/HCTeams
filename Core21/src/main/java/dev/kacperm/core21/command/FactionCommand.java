package dev.kacperm.core21.command;

import dev.kacperm.core21.Core;
import dev.kacperm.shared.command.AdvancedSubCommand;
import dev.kacperm.shared.command.impl.SharedFactionCommand;
import dev.kacperm.shared.command.messages.SharedFactionCommandMessages;
import dev.kacperm.shared.faction.PlayerFaction;
import dev.kacperm.shared.faction.manager.SharedFactionManager;
import org.bukkit.command.CommandSender;

import java.util.Set;

public class FactionCommand extends SharedFactionCommand {

    private final Core plugin = Core.getInstance();

    public FactionCommand(SharedFactionCommandMessages factionCommandMessages) {
        super(factionCommandMessages);
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
    public Set<AdvancedSubCommand> advancedSubCommands() {
        return Set.of();
    }

    @Override
    public SharedFactionManager<PlayerFaction> sharedFactionManager() {
        return plugin.getFactionManager();
    }

    @Override
    public void usageMessage(CommandSender sender) {

    }

    @Override
    public void noPermissionMessage(CommandSender sender) {

    }

    @Override
    public void requiredPlayerMessage(CommandSender sender) {

    }
}
