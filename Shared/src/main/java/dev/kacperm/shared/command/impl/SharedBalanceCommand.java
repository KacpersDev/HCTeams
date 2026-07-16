package dev.kacperm.shared.command.impl;

import dev.kacperm.shared.command.SimpleSharedCommand;
import dev.kacperm.shared.command.messages.SharedBalanceCommandMessages;
import dev.kacperm.shared.profile.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SharedBalanceCommand extends SimpleSharedCommand {

    private final SharedBalanceCommandMessages balanceCommandMessages;

    public abstract Profile profile(Player player);

    public SharedBalanceCommand(SharedBalanceCommandMessages balanceCommandMessages) {
        this.balanceCommandMessages = balanceCommandMessages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (requirePlayer() && (!(sender instanceof Player))) {
            balanceCommandMessages.requiredPlayerMessage(sender);
            return false;
        }

        Player player = (Player) sender;

        if (!sender.hasPermission(permission())) {
            balanceCommandMessages.noPermissionsMessage(player);
            return false;
        }

        long balance = profile(player).getBalance();
        balanceCommandMessages.balanceMessage(player, balance);

        return true;
    }
}
