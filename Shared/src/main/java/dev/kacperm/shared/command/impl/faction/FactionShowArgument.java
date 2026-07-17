package dev.kacperm.shared.command.impl.faction;

import dev.kacperm.shared.command.AdvancedSubCommand;
import dev.kacperm.shared.command.AdvancedSubCommandBuilder;
import dev.kacperm.shared.command.CommandArgument;
import dev.kacperm.shared.command.messages.SharedFactionCommandMessages;
import dev.kacperm.shared.faction.Faction;
import dev.kacperm.shared.faction.PlayerFaction;
import dev.kacperm.shared.faction.manager.SharedFactionManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.Set;

public class FactionShowArgument implements CommandArgument {

    private final SharedFactionCommandMessages sharedFactionCommandMessages;
    private final SharedFactionManager<?> sharedFactionManager;

    public FactionShowArgument(SharedFactionCommandMessages sharedFactionCommandMessages, SharedFactionManager<?> sharedFactionManager) {
        this.sharedFactionCommandMessages = sharedFactionCommandMessages;
        this.sharedFactionManager = sharedFactionManager;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length == 1) {
            sharedFactionCommandMessages.factionUsage(player);
            return;
        }

        String factionName = args[1];
        Optional<Faction> optionalFaction = sharedFactionManager.getUnknownFaction(factionName);

        if (!optionalFaction.isPresent()) {
            sharedFactionCommandMessages.noFaction(sender, factionName);
            return;
        }

        Faction faction = optionalFaction.get();
        if (faction instanceof PlayerFaction) {
            sharedFactionCommandMessages.playerFactionShow(sender, (PlayerFaction) faction);
            return;
        }

        sharedFactionCommandMessages.factionShow(sender, faction);
    }
}
