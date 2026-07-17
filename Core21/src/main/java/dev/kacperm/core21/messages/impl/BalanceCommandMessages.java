package dev.kacperm.core21.messages.impl;

import dev.kacperm.core21.messages.Messages;
import dev.kacperm.shared.command.messages.SharedBalanceCommandMessages;
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommandMessages implements SharedBalanceCommandMessages {

    @Override
    public void noPermissionsMessage(Player player) {
        player.sendMessage(Messages.NO_PERMISSIONS);
    }

    @Override
    public void requiredPlayerMessage(CommandSender sender) {
        sender.sendMessage(Messages.REQUIRED_PLAYER);
    }

    @Override
    public void balanceMessage(Player player, long balance) {
        player.sendMessage(Messages.BALANCE.replaceText(
                TextReplacementConfig.builder().matchLiteral("{balance}")
                        .replacement(String.valueOf(balance)).build()
        ));
    }
}
