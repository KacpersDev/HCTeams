package dev.kacperm.shared.command.impl;

import dev.kacperm.shared.command.SimpleSharedCommand;
import dev.kacperm.shared.profile.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SharedBalanceCommand extends SimpleSharedCommand {

    public abstract Profile profile(Player player);

    public abstract void noPermissionsMessage(Player player);
    public abstract void requiredPlayerMessage(CommandSender sender);
    public abstract void balanceMessage(Player player, long balance);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (requirePlayer() && (!(sender instanceof Player))) {
            requiredPlayerMessage(sender);
            return false;
        }

        Player player = (Player) sender;

        if (!sender.hasPermission(permission())) {
            noPermissionsMessage(player);
            return false;
        }

        long balance = profile(player).getBalance();
        balanceMessage(player, balance);

        return true;
    }
}
