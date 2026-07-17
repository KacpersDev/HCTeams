package dev.kacperm.shared.command;

import java.util.Set;

public abstract class AdvancedSubCommand {

    public abstract String argument();
    public abstract String permission();
    public abstract boolean requirePlayer();
}
