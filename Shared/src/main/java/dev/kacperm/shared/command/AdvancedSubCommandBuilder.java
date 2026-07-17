package dev.kacperm.shared.command;

import lombok.Getter;

@Getter
public class AdvancedSubCommandBuilder {

    private final String arguments;
    private final String permission;
    private final boolean requiredPlayer;

    private final AdvancedSubCommand advancedSubCommand;

    public AdvancedSubCommandBuilder(String arguments, String permission, boolean requiredPlayer) {
        this.arguments = arguments;
        this.permission = permission;
        this.requiredPlayer = requiredPlayer;

        this.advancedSubCommand = new AdvancedSubCommand() {
            @Override
            public String argument() {
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

    public AdvancedSubCommand build() {
        return advancedSubCommand;
    }
}
