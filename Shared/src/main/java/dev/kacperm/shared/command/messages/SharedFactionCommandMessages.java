package dev.kacperm.shared.command.messages;

import dev.kacperm.shared.faction.Faction;
import dev.kacperm.shared.faction.PlayerFaction;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface SharedFactionCommandMessages {

    void factionUsage(CommandSender sender);
    void requirePlayer(CommandSender sender);
    void noPermissions(CommandSender sender);
    void factionExists(CommandSender sender, String teamName);
    void hasFaction(CommandSender sender);
    void factionCreated(String leader, String factionName);
    void noFaction(CommandSender sender, String teamName);
    void noPlayerFaction(CommandSender sender);
    void notLeader(CommandSender sender);
    void factionDisbanded(String leader, String factionName);
    void playerFactionShow(CommandSender sender, PlayerFaction playerFaction);
    void factionShow(CommandSender sender, Faction faction);
}
