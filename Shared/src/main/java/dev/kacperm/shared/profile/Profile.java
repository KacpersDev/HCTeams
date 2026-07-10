package dev.kacperm.shared.profile;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Profile {

    private UUID uuid;
    private UUID team;
    private int balance, kills, deaths;

    private int lives;
    private long lastDeath;
    private long lastLogout;

    private boolean frozen;
    private boolean staff;

    private long firstJoin;
    private long playTime;
}
