package dev.kacperm.core21.messages.impl;

import dev.kacperm.core21.Core;
import dev.kacperm.core21.messages.Messages;
import dev.kacperm.core21.utils.color.Color;
import dev.kacperm.shared.command.messages.SharedFactionCommandMessages;
import dev.kacperm.shared.faction.Faction;
import dev.kacperm.shared.faction.PlayerFaction;
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FactionCommandMessages implements SharedFactionCommandMessages {

    private final Core plugin = Core.getInstance();

    @Override
    public void factionUsage(CommandSender sender) {
        plugin.getLanguage().getConfiguration().getStringList("faction.usage")
                .forEach(m -> sender.sendMessage(Color.translate(m)));
    }

    @Override
    public void requirePlayer(CommandSender sender) {
        sender.sendMessage(Color.translate(plugin.getLanguage().getConfiguration().getString("required-player")));
    }

    @Override
    public void noPermissions(CommandSender sender) {
        sender.sendMessage(Color.translate(plugin.getLanguage().getConfiguration().getString("no-permissions")));
    }

    @Override
    public void factionExists(CommandSender player, String teamName) {
        player.sendMessage(Color.translate(plugin.getLanguage().getConfiguration().getString("faction.already-exists")
                .replace("{faction}", teamName)));
    }

    @Override
    public void hasFaction(CommandSender player) {
        player.sendMessage(Color.translate(plugin.getLanguage().getConfiguration().getString("faction.in-faction")));
    }

    @Override
    public void factionCreated(String name, String teamName) {
        Bukkit.broadcast(
                Color.translate(plugin.getLanguage().getConfiguration().getString("faction.created"))
                        .replaceText(TextReplacementConfig.builder()
                                .matchLiteral("{leader}")
                                .replacement(name)
                                .build())
                        .replaceText(TextReplacementConfig.builder()
                                .matchLiteral("{faction}")
                                .replacement(teamName)
                                .build())
        );
    }

    @Override
    public void noFaction(CommandSender sender, String teamName) {
        sender.sendMessage(Color.translate(plugin.getLanguage().getConfiguration()
                .getString("faction.not-exists").replace("{faction}", teamName)));
    }

    @Override
    public void noPlayerFaction(CommandSender sender) {
        sender.sendMessage(Color.translate(plugin.getLanguage().getConfiguration().getString("faction.no-faction")));
    }

    @Override
    public void notLeader(CommandSender sender) {
        sender.sendMessage(Color.translate(plugin.getLanguage().getConfiguration().getString("faction.not-faction")));
    }

    @Override
    public void factionDisbanded(String leader, String factionName) {
        Bukkit.broadcast(
                Color.translate(plugin.getLanguage().getConfiguration().getString("faction.disbanded"))
                        .replaceText(TextReplacementConfig.builder()
                                .matchLiteral("{leader}")
                                .replacement(leader)
                                .build())
                        .replaceText(TextReplacementConfig.builder()
                                .matchLiteral("{faction}")
                                .replacement(factionName)
                                .build())
        );
    }

    @Override
    public void playerFactionShow(CommandSender sender, PlayerFaction playerFaction) {
        int onlineMembers = 0;

        for (UUID uuid : playerFaction.getMembers().keySet()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && player.isOnline()) {
                onlineMembers++;
            }
        }

        String hq = "None";
        if (playerFaction.getLocation().getWorld() != null) {
            hq = playerFaction.getLocation().getBlockX() + "," + playerFaction.getLocation().getBlockY() + "," + playerFaction.getLocation().getBlockZ();
        }

        for (final String s : plugin.getLanguage().getConfiguration().getStringList("faction.player-faction-show")) {
            sender.sendMessage(Color.translate(s
                    .replace("{faction}", playerFaction.getName())
                    .replace("{online}", String.valueOf(onlineMembers))
                    .replace("{size}", String.valueOf(playerFaction.getMembers().size()))
                    .replace("{hq}", hq)
                    .replace("{balance}", String.valueOf(playerFaction.getBalance()))
                    .replace("{dtr}", String.valueOf(playerFaction.getDeathsTillRaidable()))));
        }
    }

    @Override
    public void factionShow(CommandSender sender, Faction faction) {
        String location = "None";
        if (faction.getLocation().getWorld() != null) {
            location = faction.getLocation().getBlockX() + "," + faction.getLocation().getBlockY() + "," + faction.getLocation().getBlockZ();
        }
        for (final String s : plugin.getLanguage().getConfiguration().getStringList("faction.faction-show")) {
            sender.sendMessage(Color.translate(s
                    .replace("{faction}", faction.getName())
                    .replace("{location}", location)));
        }
    }
}
