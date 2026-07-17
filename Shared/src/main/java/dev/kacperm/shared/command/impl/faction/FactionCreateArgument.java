package dev.kacperm.shared.command.impl.faction;

import dev.kacperm.shared.command.AdvancedSubCommand;
import dev.kacperm.shared.command.AdvancedSubCommandBuilder;
import dev.kacperm.shared.command.CommandArgument;
import dev.kacperm.shared.command.messages.SharedFactionCommandMessages;
import dev.kacperm.shared.faction.PlayerFaction;
import dev.kacperm.shared.faction.manager.SharedFactionManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
                factionCommandMessages.factionExists(player, teamName);
                return;
            }

            if (this.sharedFactionManager.hasFaction(player.getUniqueId())) {
                factionCommandMessages.hasFaction(player);
                return;
            }

            this.sharedFactionManager.createFaction(player.getUniqueId(), teamName);
            factionCommandMessages.factionCreated(player.getName(), teamName);
        }
    }
}
