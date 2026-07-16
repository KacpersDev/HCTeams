package dev.kacperm.shared.command.messages;

import org.bukkit.command.CommandSender;

public interface SharedFactionCommandMessages {

    void factionUsage(CommandSender sender);
    void requirePlayer(CommandSender sender);
    void noPermissions(CommandSender sender);
    void factionExists(String teamName);
    void hasFaction();
    void factionCreated(String name, String teamName);
}
