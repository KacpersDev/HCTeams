package dev.kacperm.shared.faction;

import dev.kacperm.shared.faction.role.FactionRole;
import dev.kacperm.shared.faction.type.FactionType;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class PlayerFaction extends Faction {

    private Map<UUID, FactionRole> members;
    private int balance;
    private int points;
    private double deathsTillRaidable;
    private Location hq;
    private Location[] claimCorners;

    public PlayerFaction(UUID uuid, String name, FactionType type, Map<UUID, FactionRole> members, int balance, int points, double deathsTillRaidable, Location hq, Location[] claimCorners) {
        super(uuid, name, type);

        this.members = members;
        this.balance = balance;
        this.points = points;
        this.deathsTillRaidable = deathsTillRaidable;
        this.hq = hq;
        this.claimCorners = claimCorners;
    }
}
