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
    private Location location;

    public ServerFaction(UUID uuid, String name, FactionType type, Location location, boolean safeZone, Location[] claimCorners) {
        super(uuid, name, type, location);

        this.safeZone = safeZone;
        this.claimCorners = claimCorners;
    }
}
