package dev.kacperm.shared.faction.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FactionRole {

    LEADER("***"),
    CO_LEADER("**"),
    CAPTAIN("*"),
    MEMBER("");

    final String prefix;
}