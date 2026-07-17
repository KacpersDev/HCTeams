package dev.kacperm.shared.command.registrar;

import dev.kacperm.shared.command.AdvancedSharedCommand;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class RegisterAdvancedCommand<T extends AdvancedSharedCommand> {

    private final JavaPlugin javaPlugin;
    private final T command;

    public RegisterAdvancedCommand(JavaPlugin javaPlugin, T command) {
        this.javaPlugin = javaPlugin;
        this.command = command;
    }

    public void register() {
        PluginCommand pluginCommand = this.javaPlugin.getCommand(command.name());
        pluginCommand.setAliases(Arrays.asList(command.aliases()));
        pluginCommand.setExecutor(command);
    }
}
