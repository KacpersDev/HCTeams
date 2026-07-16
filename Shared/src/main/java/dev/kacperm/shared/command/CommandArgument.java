package dev.kacperm.shared.command;

import org.bukkit.command.CommandSender;

public interface CommandArgument {

    void onCommand(CommandSender sender, String[] args);
}
