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

public class FactionCommandMessages implements SharedFactionCommandMessages {

    @Override
    public void factionUsage(CommandSender sender) {
        Core.getInstance().getLanguage().getConfiguration().getStringList("faction.usage")
                .forEach(m -> sender.sendMessage(Color.translate(m)));
    }

    @Override
    public void requirePlayer(CommandSender sender) {
        sender.sendMessage(Messages.REQUIRED_PLAYER);
    }

    @Override
    public void noPermissions(CommandSender sender) {
        sender.sendMessage(Messages.NO_PERMISSIONS);
    }

    @Override
    public void factionExists(String teamName) {

    }

    @Override
    public void hasFaction() {

    }

    @Override
    public void factionCreated(String name, String teamName) {
        Bukkit.broadcast(
                Color.translate(Core.getInstance().getLanguage().getConfiguration().getString("faction.created"))
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
    public void noFaction(String teamName) {

    }

    @Override
    public void noPlayerFaction() {

    }

    @Override
    public void notLeader() {

    }

    @Override
    public void factionDisbanded(String leader, String factionName) {
        Bukkit.broadcast(Messages.FACTION_CREATED
                .replaceText(TextReplacementConfig.builder()
                        .matchLiteral("{leader}").replacement(leader)
                        .matchLiteral("{faction}").replacement(factionName).build()));
    }

    @Override
    public void playerFactionShow(PlayerFaction playerFaction) {

    }

    @Override
    public void factionShow(Faction faction) {

    }
}
