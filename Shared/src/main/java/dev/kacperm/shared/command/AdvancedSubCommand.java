package dev.kacperm.shared.command;

import java.util.Set;

public abstract class AdvancedSubCommand {

    public abstract Set<String> arguments();
    public abstract String permission();
    public abstract boolean requirePlayer();
}
