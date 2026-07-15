package dev.kacperm.shared.command.impl;

import dev.kacperm.shared.command.AdvancedSharedCommand;
import dev.kacperm.shared.command.AdvancedSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public abstract class SharedFactionCommand extends AdvancedSharedCommand {

    public abstract void usageMessage(CommandSender sender);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            usageMessage(sender);
        } else {
            String argument = args[0].toLowerCase();
            Optional<AdvancedSubCommand> advancedSubCommand = getByArgument(argument);

            if (!advancedSubCommand.isPresent()) {
                usageMessage(sender);
                return false;
            }

            AdvancedSubCommand subCommand = advancedSubCommand.get();

        }

        return true;
    }
}
