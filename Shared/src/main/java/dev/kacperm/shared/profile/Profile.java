package dev.kacperm.shared.profile;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Profile {

    private UUID uuid;
    private UUID team;
    private int balance, kills, deaths;

    private int lives;
    private long lastDeath;
    private long lastLogout;

    private boolean frozen;
    private boolean staff;
    private boolean reclaimed;

    private long firstJoin;
    private long playTime;
    private long currentSession;
}
