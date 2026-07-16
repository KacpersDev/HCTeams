package dev.kacperm.shared.command.messages;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface SharedBalanceCommandMessages {

    void noPermissionsMessage(Player player);
    void requiredPlayerMessage(CommandSender sender);
    void balanceMessage(Player player, long balance);
}
