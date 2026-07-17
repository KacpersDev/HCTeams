package dev.kacperm.shared.faction;

import dev.kacperm.shared.faction.role.FactionRole;
import dev.kacperm.shared.faction.type.FactionType;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class PlayerFaction extends Faction {

    private Map<UUID, FactionRole> members;
    private int balance;
    private int points;
    private double deathsTillRaidable;
    private Location[] claimCorners;

    private Set<UUID> invitedPlayers = new HashSet<>();

    public PlayerFaction(UUID uuid, String name, FactionType type, Location location, Map<UUID, FactionRole> members, int balance, int points, double deathsTillRaidable, Location[] claimCorners) {
        super(uuid, name, type, location);

        this.members = members;
        this.balance = balance;
        this.points = points;
        this.deathsTillRaidable = deathsTillRaidable;
        this.claimCorners = claimCorners;
    }
}
