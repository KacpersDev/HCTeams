package dev.kacperm.core21.profile;

import com.mongodb.client.MongoCollection;
import dev.kacperm.core21.Core;
import dev.kacperm.shared.profile.Profile;
import dev.kacperm.shared.profile.manager.SharedProfileManager;
import lombok.Getter;
import org.bson.Document;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ProfileManager implements SharedProfileManager {

    private final Core plugin = Core.getInstance();
    private final Map<UUID, Profile> profiles = new ConcurrentHashMap<>();

    @Override
    public Map<UUID, Profile> profiles() {
        return profiles;
    }

    @Override
    public MongoCollection<Document> profilesCollection() {
        return plugin.getMongoManager().getProfiles();
    }
}
