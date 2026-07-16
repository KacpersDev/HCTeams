package dev.kacperm.shared.command.impl.faction;

import dev.kacperm.shared.command.CommandArgument;
import dev.kacperm.shared.command.messages.SharedFactionCommandMessages;
import dev.kacperm.shared.faction.PlayerFaction;
import dev.kacperm.shared.faction.manager.SharedFactionManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FactionCreateArgument implements CommandArgument {

    private final SharedFactionCommandMessages factionCommandMessages;
    private final SharedFactionManager<PlayerFaction> sharedFactionManager;

    public FactionCreateArgument(SharedFactionCommandMessages factionCommandMessages, SharedFactionManager<PlayerFaction> sharedFactionManager) {
        this.factionCommandMessages = factionCommandMessages;
        this.sharedFactionManager = sharedFactionManager;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length == 1) {
            factionCommandMessages.factionUsage(player);
        } else {
            String teamName = args[1];

            if (this.sharedFactionManager.factionExists(teamName)) {
                factionCommandMessages.factionExists(teamName);
                return;
            }

            if (this.sharedFactionManager.hasFaction(player.getUniqueId())) {
                factionCommandMessages.hasFaction();
                return;
            }

            this.sharedFactionManager.createFaction(player.getUniqueId(), teamName);
            factionCommandMessages.factionCreated(player.getName(), teamName);
        }
    }
}
