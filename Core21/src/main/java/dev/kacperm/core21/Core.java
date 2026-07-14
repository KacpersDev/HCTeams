package dev.kacperm.core21;

import dev.kacperm.core21.command.BalanceCommand;
import dev.kacperm.core21.faction.FactionManager;
import dev.kacperm.core21.profile.ProfileManager;
import dev.kacperm.core21.scoreboard.ScoreboardManager;
import dev.kacperm.shared.SharedPlugin;
import dev.kacperm.shared.command.registrar.RegisterSimpleCommand;
import dev.kacperm.shared.listener.ProfileListener;
import dev.kacperm.shared.listener.ScoreboardListener;
import dev.kacperm.shared.mongo.MongoManager;
import dev.kacperm.shared.runnable.scoreboard.ScoreboardRunnable;
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
    private ScoreboardManager scoreboardManager;
    private FactionManager factionManager;

    @Override
    public void onEnable() {
        instance = this;

        this.loadConfigurations();

        this.mongoManager = new MongoManager(
                getConfiguration().getConfiguration().getString("mongo.uri"),
                getConfiguration().getConfiguration().getString("mongo.database"));
        this.profileManager = new ProfileManager();
        this.scoreboardManager = new ScoreboardManager();
        this.factionManager = new FactionManager();

        this.factionManager.onEnable();

        this.loadListeners();
        this.loadCommands();
        this.loadRunnables();
    }

    @Override
    public void onDisable() {
        this.profileManager.onDisable();
        this.factionManager.onDisable();

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
        new RegisterSimpleCommand<>(this, new BalanceCommand()).register();
    }

    @Override
    public void loadListeners() {
        Arrays.asList(
                new ProfileListener(this, profileManager, configuration),
                new ScoreboardListener(scoreboardManager)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    @Override
    public void loadRunnables() {
        Bukkit.getScheduler().runTaskTimer(this, new ScoreboardRunnable(scoreboardManager), 0L, 20L);
    }
}
