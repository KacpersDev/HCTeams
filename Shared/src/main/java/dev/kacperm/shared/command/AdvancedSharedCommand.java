package dev.kacperm.shared.command;

import org.bukkit.command.CommandExecutor;

import java.util.Map;

public abstract class AdvancedSharedCommand implements CommandExecutor {

    public abstract String name();
    public abstract String[] aliases();
    public abstract String description();

    public abstract Map<String[], AdvancedSubCommand> advancedSubCommands();
}
