package dev.kacperm.core21.messages.impl;

import dev.kacperm.shared.command.messages.SharedBalanceCommandMessages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommandMessages implements SharedBalanceCommandMessages {

    @Override
    public void noPermissionsMessage(Player player) {

    }

    @Override
    public void requiredPlayerMessage(CommandSender sender) {

    }

    @Override
    public void balanceMessage(Player player, long balance) {

    }
}
