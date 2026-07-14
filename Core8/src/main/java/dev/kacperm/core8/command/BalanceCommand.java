package dev.kacperm.core8.command;

import dev.kacperm.core8.Core;
import dev.kacperm.core8.messages.Messages;
import dev.kacperm.shared.command.impl.SharedBalanceCommand;
import dev.kacperm.shared.profile.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand extends SharedBalanceCommand {

    private final Core plugin = Core.getInstance();

    @Override
    public String name() {
        return "balance";
    }

    @Override
    public String[] aliases() {
        return new String[]{"bal", "eco", "money"};
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public String permission() {
        return "hcf.command.default";
    }

    @Override
    public boolean requirePlayer() {
        return true;
    }

    @Override
    public Profile profile(Player player) {
        return plugin.getProfileManager().getProfiles().get(player.getUniqueId());
    }

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
        player.sendMessage(Messages.BALANCE.replace("{balance}", String.valueOf(balance)));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }
}
