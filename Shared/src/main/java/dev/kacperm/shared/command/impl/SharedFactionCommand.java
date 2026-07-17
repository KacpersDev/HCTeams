package dev.kacperm.shared.command.impl;

import dev.kacperm.shared.command.AdvancedSharedCommand;
import dev.kacperm.shared.command.AdvancedSubCommand;
import dev.kacperm.shared.command.CommandArgument;
import dev.kacperm.shared.command.impl.faction.FactionCreateArgument;
import dev.kacperm.shared.command.impl.faction.FactionDisbandArgument;
import dev.kacperm.shared.command.impl.faction.FactionShowArgument;
import dev.kacperm.shared.command.messages.SharedFactionCommandMessages;
import dev.kacperm.shared.faction.PlayerFaction;
import dev.kacperm.shared.faction.manager.SharedFactionManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public abstract class SharedFactionCommand extends AdvancedSharedCommand {

    private final SharedFactionCommandMessages factionCommandMessages;
    public abstract SharedFactionManager<PlayerFaction> sharedFactionManager();

    private final CommandArgument factionCreate, factionDisband, factionShow;

    public SharedFactionCommand(SharedFactionCommandMessages factionCommandMessages) {
        this.factionCommandMessages = factionCommandMessages;

        this.factionCreate = new FactionCreateArgument(factionCommandMessages, sharedFactionManager());
        this.factionDisband = new FactionDisbandArgument(factionCommandMessages, sharedFactionManager());
        this.factionShow = new FactionShowArgument(factionCommandMessages, sharedFactionManager());
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
                    factionCreate.onCommand(sender, args);
                    break;
                }
                case "disband": {
                    factionDisband.onCommand(sender, args);
                }
                case "show": {
                    factionShow.onCommand(sender, args);
                }

                default: {
                    factionCommandMessages.factionUsage(sender);
                }
            }
        }

        return true;
    }
}
