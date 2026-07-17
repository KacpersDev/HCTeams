package dev.kacperm.shared.command.messages;

import dev.kacperm.shared.faction.Faction;
import dev.kacperm.shared.faction.PlayerFaction;
import org.bukkit.command.CommandSender;

public interface SharedFactionCommandMessages {

    void factionUsage(CommandSender sender);
    void requirePlayer(CommandSender sender);
    void noPermissions(CommandSender sender);
    void factionExists(String teamName);
    void hasFaction();
    void factionCreated(String leader, String factionName);
    void noFaction(String teamName);
    void noPlayerFaction();
    void notLeader();
    void factionDisbanded(String leader, String factionName);
    void playerFactionShow(PlayerFaction playerFaction);
    void factionShow(Faction faction);
}
