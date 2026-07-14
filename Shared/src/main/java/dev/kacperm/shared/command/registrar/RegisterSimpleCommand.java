package dev.kacperm.shared.command.registrar;

import dev.kacperm.shared.command.SimpleSharedCommand;
import lombok.Getter;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

@Getter
public class RegisterSimpleCommand<T extends SimpleSharedCommand> {

    private final JavaPlugin javaPlugin;
    private final T command;

    public RegisterSimpleCommand(JavaPlugin javaPlugin, T command) {
        this.javaPlugin = javaPlugin;
        this.command = command;
    }

    public void register() {
        PluginCommand pluginCommand = this.javaPlugin.getCommand(command.name());
        pluginCommand.setAliases(Arrays.asList(command.aliases()));
        pluginCommand.setPermission(command.permission());
        pluginCommand.setExecutor(command);
    }
}
