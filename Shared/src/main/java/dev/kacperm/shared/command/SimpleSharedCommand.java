package dev.kacperm.shared.command;

import org.bukkit.command.CommandExecutor;

public abstract class SimpleSharedCommand implements CommandExecutor {

    public abstract String name();
    public abstract String[] aliases();
    public abstract String description();
    public abstract String permission();
    public abstract boolean requirePlayer();
}
