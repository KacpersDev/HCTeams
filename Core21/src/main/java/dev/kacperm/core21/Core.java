package dev.kacperm.core21;

import dev.kacperm.shared.SharedPlugin;
import dev.kacperm.shared.utils.config.Config;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Core extends JavaPlugin implements SharedPlugin {

    @Getter
    private static Core instance;

    private Config configuration, language;

    @Override
    public void onEnable() {
        instance = this;

        this.loadConfigurations();
        this.loadListeners();
        this.loadCommands();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    @Override
    public void loadConfigurations() {

    }

    @Override
    public void loadCommands() {

    }

    @Override
    public void loadListeners() {

    }
}
