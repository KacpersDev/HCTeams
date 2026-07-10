package dev.kacperm.shared.profile.manager;

import dev.kacperm.shared.profile.Profile;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ProfileManager {

    default Optional<Profile> loadProfile() {
        return Optional.of(null);
    }

    default void saveProfile(Profile profile) {

    }

    default CompletableFuture<Profile> getProfile(UUID uuid) {
        return new CompletableFuture<>();
    }
}
