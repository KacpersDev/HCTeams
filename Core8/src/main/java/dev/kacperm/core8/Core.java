package dev.kacperm.core8;

import dev.kacperm.core8.profile.ProfileManager;
import dev.kacperm.shared.SharedPlugin;
import dev.kacperm.shared.config.ProfileConfig;
import dev.kacperm.shared.listener.ProfileListener;
import dev.kacperm.shared.mongo.MongoManager;
import dev.kacperm.shared.utils.config.Config;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

@Getter
public final class Core extends JavaPlugin implements SharedPlugin {

    @Getter
    private static Core instance;

    private Config configuration, language;

    private MongoManager mongoManager;
    private ProfileManager profileManager;

    @Override
    public void onEnable() {
        instance = this;

        this.loadConfigurations();
        this.loadListeners();
        this.loadCommands();

        this.mongoManager = new MongoManager(
                getConfiguration().getConfiguration().getString("mongo.uri"),
                getConfiguration().getConfiguration().getString("mongo.database"));
        this.profileManager = new ProfileManager();
    }

    @Override
    public void onDisable() {
        this.profileManager.onDisable();

        instance = null;
    }

    @Override
    public void loadConfigurations() {
        this.configuration = new Config(this, new File(getDataFolder(), "configuration.yml"),
                new YamlConfiguration(), "configuration.yml");
        this.language = new Config(this, new File(getDataFolder(), "language.yml"),
                new YamlConfiguration(), "language.yml");

        this.configuration.create();
        this.language.create();
    }

    @Override
    public void loadCommands() {

    }

    @Override
    public void loadListeners() {
        Arrays.asList(
                new ProfileListener(this, profileManager, configuration)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }
}
