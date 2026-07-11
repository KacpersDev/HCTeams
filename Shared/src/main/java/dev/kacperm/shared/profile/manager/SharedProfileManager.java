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

        Profile profile = Profile.builder()
                .uuid(uuid)
                .team(document.getString("team") == null ? null : UUID.fromString(document.getString("team")))
                .balance(document.getInteger("balance"))
                .kills(document.getInteger("kills"))
                .deaths(document.getInteger("deaths"))
                .lives(document.getInteger("lives"))
                .lastDeath(document.getLong("lastDeath"))
                .lastLogout(document.getLong("lastLogout"))
                .frozen(document.getBoolean("frozen"))
                .staff(document.getBoolean("staff"))
                .reclaimed(document.getBoolean("reclaimed"))
                .firstJoin(document.getLong("firstJoin"))
                .playTime(document.getLong("playTime"))
                .startingTimer(document.getLong("startingTimer"))
                .pvpTimer(document.getLong("pvpTimer")).build();

        return Optional.of(profile);
    }

    default void saveProfile(Profile profile) {
        Document document = new Document();
        document.put("uuid", profile.getUuid().toString());
        document.put("team", profile.getTeam() == null ? null : profile.getTeam().toString());
        document.put("balance", profile.getBalance());
        document.put("kills", profile.getKills());
        document.put("deaths", profile.getDeaths());
        document.put("lives", profile.getLives());
        document.put("lastDeath", profile.getLastDeath());
        document.put("lastLogout", profile.getLastLogout());
        document.put("frozen", profile.isFrozen());
        document.put("staff", profile.isStaff());
        document.put("reclaimed", profile.isReclaimed());
        document.put("firstJoin", profile.getFirstJoin());
        document.put("playTime", profile.getPlayTime());
        document.put("startingTimer", profile.getStartingTimer());
        document.put("pvpTimer", profile.getPvpTimer());

        profilesCollection().replaceOne(Filters.eq("uuid", profile.getUuid().toString()),
                document, new ReplaceOptions().upsert(true));
    }

    default CompletableFuture<Optional<Profile>> getProfile(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> loadProfile(uuid));
    }

    default void onDisable() {
        profiles().values().forEach(this::saveProfile);
    }
}