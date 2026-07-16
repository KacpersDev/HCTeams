package dev.kacperm.shared.command.impl;

import dev.kacperm.shared.command.AdvancedSharedCommand;
import dev.kacperm.shared.command.AdvancedSubCommand;
import dev.kacperm.shared.command.impl.faction.FactionCreateArgument;
import dev.kacperm.shared.command.messages.SharedFactionCommandMessages;
import dev.kacperm.shared.faction.PlayerFaction;
import dev.kacperm.shared.faction.manager.SharedFactionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public abstract class SharedFactionCommand extends AdvancedSharedCommand {

    private final SharedFactionCommandMessages factionCommandMessages;
    public abstract SharedFactionManager<PlayerFaction> sharedFactionManager();

    public SharedFactionCommand(SharedFactionCommandMessages factionCommandMessages) {
        this.factionCommandMessages = factionCommandMessages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            factionCommandMessages.factionUsage(sender);
        } else {
            String firstArgument = args[0].toLowerCase();
            Optional<AdvancedSubCommand> advancedSubCommand = getByArgument(firstArgument);

            if (!advancedSubCommand.isPresent()) {
                factionCommandMessages.factionUsage(sender);
                return false;
            }

            AdvancedSubCommand subCommand = advancedSubCommand.get();

            if (subCommand.requirePlayer() && (!(sender instanceof Player))) {
                factionCommandMessages.requirePlayer(sender);
                return false;
            }

            if (!sender.hasPermission(subCommand.permission())) {
                factionCommandMessages.noPermissions(sender);
                return false;
            }

            switch (firstArgument) {
                case "create": {
                    new FactionCreateArgument(factionCommandMessages, sharedFactionManager()).onCommand(sender, args);
                    break;
                }

                default: {
                    factionCommandMessages.factionUsage(sender);
                }
            }
        }

        return true;
    }
}
