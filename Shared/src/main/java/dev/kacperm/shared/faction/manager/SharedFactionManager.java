package dev.kacperm.shared.faction.manager;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import dev.kacperm.shared.faction.Faction;
import dev.kacperm.shared.faction.PlayerFaction;
import dev.kacperm.shared.faction.role.FactionRole;
import dev.kacperm.shared.faction.type.FactionType;
import dev.kacperm.shared.utils.location.FastLocation;
import org.bson.Document;
import org.bukkit.Location;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public interface SharedFactionManager<T extends Faction> {

    Map<UUID, T> factions();
    MongoCollection<Document> factionsCollection();

    default Optional<T> loadFaction(String name, FactionType type) {
        Document document = factionsCollection().find(Filters.eq("name", name)).first();
        if (document == null) return Optional.empty();

        if (type.equals(FactionType.PLAYER)) {
            UUID uuid = UUID.fromString(document.getString("uuid"));
            Map<UUID, FactionRole> members = new HashMap<>();
            document.getList("members", String.class).forEach(s ->
                    members.put(UUID.fromString(s.split(":")[0]), FactionRole.valueOf(s.split(":")[1].toUpperCase())));
            int balance = document.getInteger("balance");
            int points = document.getInteger("points");
            double deathsTillRaidable = document.getDouble("deathsTillRaidable");
            Location home = FastLocation.fromString(document.getString("hq"));
            Location[] claimCorners = new Location[]{
                    FastLocation.fromString(document.getString("claimCornerFirst")),
                    FastLocation.fromString(document.getString("claimCornerSecond"))
            };

            PlayerFaction playerFaction = new PlayerFaction(uuid, name, FactionType.PLAYER, members, balance, points, deathsTillRaidable, home, claimCorners);
            return Optional.of((T) playerFaction);
        }

        return Optional.empty();
    }

    default void saveFaction(T faction) {
        Document document = new Document();
        document.put("uuid", faction.getUuid().toString());
        document.put("name", faction.getName());
        document.put("type", faction.getType());

        if (faction instanceof PlayerFaction) {
            PlayerFaction playerFaction = (PlayerFaction) faction;
            List<String> members = new ArrayList<>();
            playerFaction.getMembers().forEach((k,v)
                    -> members.add(k.toString() + ":" + v.name()));

            document.put("members", members);
            document.put("balance", playerFaction.getBalance());
            document.put("points", playerFaction.getPoints());
            document.put("deathsTillRaidable", playerFaction.getDeathsTillRaidable());
            document.put("hq", FastLocation.toString(playerFaction.getHq()));
            document.put("claimCornerFirst", FastLocation.toString(playerFaction.getClaimCorners()[0]));
            document.put("claimCornerSecond", FastLocation.toString(playerFaction.getClaimCorners()[1]));
        }

        factionsCollection().replaceOne(Filters.eq("uuid", faction.getUuid().toString()), document,
                new ReplaceOptions().upsert(true));
    }

    default CompletableFuture<Optional<T>> getFaction(String name, FactionType type) {
        return CompletableFuture.supplyAsync(() -> loadFaction(name, type));
    }

    default void onEnable() {
        FindIterable<Document> iterable = factionsCollection().find();
        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                FactionType type = FactionType.valueOf(document.getString("type"));
                Optional<T> optionalFaction = loadFaction(document.getString("name"), type);
                optionalFaction.ifPresent(faction -> factions().put(faction.getUuid(), faction));
            }
        }
    }

    default void onDisable() {
        factions().values().forEach(this::saveFaction);
    }
}