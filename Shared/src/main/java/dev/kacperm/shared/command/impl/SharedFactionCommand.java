package dev.kacperm.shared.command.impl;

import dev.kacperm.shared.command.AdvancedSharedCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SharedFactionCommand extends AdvancedSharedCommand {

    public abstract void usageMessage(Player player);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        return true;
    }
}
