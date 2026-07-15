package dev.kacperm.shared.command;

import org.bukkit.command.CommandExecutor;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class AdvancedSharedCommand implements CommandExecutor {

    public abstract String name();
    public abstract String[] aliases();
    public abstract String description();

    public abstract Set<AdvancedSubCommand> advancedSubCommands();

    public Optional<AdvancedSubCommand> getByArgument(String argument) {
        for (AdvancedSubCommand advancedSubCommand : this.advancedSubCommands()) {
            if (advancedSubCommand.arguments().contains(argument)) {
                return Optional.of(advancedSubCommand);
            }
        }

        return Optional.empty();
    }
}
