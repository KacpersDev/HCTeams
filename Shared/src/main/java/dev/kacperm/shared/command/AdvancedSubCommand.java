package dev.kacperm.shared.command;

public abstract class AdvancedSubCommand {

    public abstract String argument();
    public abstract String permission();
    public abstract boolean requirePlayer();
}
