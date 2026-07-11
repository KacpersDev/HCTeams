package dev.kacperm.core21.faction;

import com.mongodb.client.MongoCollection;
import dev.kacperm.core21.Core;
import dev.kacperm.shared.faction.PlayerFaction;
import dev.kacperm.shared.faction.manager.SharedFactionManager;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FactionManager implements SharedFactionManager<PlayerFaction> {

    private final Core plugin = Core.getInstance();
    private final Map<UUID, PlayerFaction> factions = new HashMap<>();

    @Override
    public Map<UUID, PlayerFaction> factions() {
        return factions;
    }

    @Override
    public MongoCollection<Document> factionsCollection() {
        return plugin.getMongoManager().getFactions();
    }
}
