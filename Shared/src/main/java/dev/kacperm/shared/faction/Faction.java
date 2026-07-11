package dev.kacperm.shared.faction;

import dev.kacperm.shared.faction.type.FactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Faction {

    private UUID uuid;
    private String name;
    private FactionType type;
}