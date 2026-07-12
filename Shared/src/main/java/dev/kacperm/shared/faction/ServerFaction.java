package dev.kacperm.shared.faction;

import dev.kacperm.shared.faction.type.FactionType;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.UUID;

@Getter
@Setter
public class ServerFaction extends Faction {

    private boolean safeZone;
    private Location[] claimCorners;

    public ServerFaction(UUID uuid, String name, FactionType type, boolean safeZone, Location[] claimCorners) {
        super(uuid, name, type);

        this.safeZone = safeZone;
        this.claimCorners = claimCorners;
    }
}
