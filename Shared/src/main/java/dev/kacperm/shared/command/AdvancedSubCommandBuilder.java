package dev.kacperm.shared.command;

import lombok.Getter;

import java.util.Set;

@Getter
public class AdvancedSubCommandBuilder {

    private final Set<String> arguments;
    private final String permission;
    private final boolean requiredPlayer;

    private final AdvancedSubCommand advancedSubCommand;

    public AdvancedSubCommandBuilder(Set<String> arguments, String permission, boolean requiredPlayer) {
        this.arguments = arguments;
        this.permission = permission;
        this.requiredPlayer = requiredPlayer;

        this.advancedSubCommand = new AdvancedSubCommand() {
            @Override
            public Set<String> arguments() {
                return arguments;
            }

            @Override
            public String permission() {
                return permission;
            }

            @Override
            public boolean requirePlayer() {
                return requiredPlayer;
            }
        };
    }
}
