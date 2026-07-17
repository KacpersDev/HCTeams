package dev.kacperm.shared.command.impl.faction;

import dev.kacperm.shared.command.AdvancedSubCommand;
import dev.kacperm.shared.command.AdvancedSubCommandBuilder;
import dev.kacperm.shared.command.CommandArgument;
import dev.kacperm.shared.command.messages.SharedFactionCommandMessages;
import dev.kacperm.shared.faction.PlayerFaction;
import dev.kacperm.shared.faction.manager.SharedFactionManager;
import dev.kacperm.shared.faction.role.FactionRole;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.Set;

public class FactionDisbandArgument implements CommandArgument {

    private final SharedFactionCommandMessages factionCommandMessages;
    private final SharedFactionManager<PlayerFaction> sharedFactionManager;

    public FactionDisbandArgument(SharedFactionCommandMessages sharedFactionCommandMessages, SharedFactionManager<PlayerFaction> sharedFactionManager) {
        this.factionCommandMessages = sharedFactionCommandMessages;
        this.sharedFactionManager = sharedFactionManager;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Optional<PlayerFaction> optionalPlayerFaction = sharedFactionManager.getFactionByPlayer(player.getUniqueId());

        if (!optionalPlayerFaction.isPresent()) {
            factionCommandMessages.noPlayerFaction();
            return;
        }

        PlayerFaction playerFaction = optionalPlayerFaction.get();

        if (!playerFaction.getMembers().get(player.getUniqueId()).equals(FactionRole.LEADER)) {
            factionCommandMessages.notLeader(sender);
            return;
        }

        factionCommandMessages.factionDisbanded(player.getName(), playerFaction.getName());
        sharedFactionManager.disbandFaction(playerFaction);
    }
}
