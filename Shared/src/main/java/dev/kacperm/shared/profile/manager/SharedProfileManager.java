package dev.kacperm.shared.profile.manager;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import dev.kacperm.shared.profile.Profile;
import org.bson.Document;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface SharedProfileManager {

    Map<UUID, Profile> profiles();
    MongoCollection<Document> profilesCollection();

    default Optional<Profile> loadProfile(UUID uuid) {
        Document document = profilesCollection().find(Filters.eq("uuid", uuid.toString())).first();

        if (document == null) {
            return Optional.empty();
        }

        Profile profile = new Profile(
                uuid,
                UUID.fromString(document.getString("team")),
                document.getInteger("balance"),
                document.getInteger("kills"),
                document.getInteger("deaths"),
                document.getInteger("lives"),
                document.getLong("lastDeath"),
                document.getLong("lastLogout"),
                document.getBoolean("frozen"),
                document.getBoolean("staff"),
                document.getLong("firstJoin"),
                document.getLong("playTime")
        );

        return Optional.of(profile);
    }

    default void saveProfile(Profile profile) {
        Document document = new Document();
        document.put("uuid", profile.getUuid().toString());
        document.put("team", profile.getTeam().toString());
        document.put("balance", profile.getBalance());
        document.put("kills", profile.getKills());
        document.put("deaths", profile.getDeaths());
        document.put("lives", profile.getLives());
        document.put("lastDeath", profile.getLastDeath());
        document.put("lastLogout", profile.getLastLogout());
        document.put("frozen", profile.isFrozen());
        document.put("staff", profile.isStaff());
        document.put("firstJoin", profile.getFirstJoin());
        document.put("playTime", profile.getPlayTime());

        profilesCollection().replaceOne(Filters.eq("uuid", profile.getUuid().toString()),
                document, new ReplaceOptions().upsert(true));
    }

    default CompletableFuture<Optional<Profile>> getProfile(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> loadProfile(uuid));
    }
}