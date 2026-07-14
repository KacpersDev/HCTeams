package dev.kacperm.core21.messages;

import dev.kacperm.core21.Core;
import dev.kacperm.core21.utils.color.Color;
import net.kyori.adventure.text.Component;

public class Messages {

    /* GLOBAL */
    public static Component NO_PERMISSIONS = Color.translate(Core.getInstance().getLanguage().getConfiguration().getString("no-permissions"));
    public static Component REQUIRED_PLAYER = Color.translate(Core.getInstance().getLanguage().getConfiguration().getString("required-player"));

    /* BALANCE */
    public static Component BALANCE = Color.translate(Core.getInstance().getLanguage().getConfiguration().getString("balance.balance"));
}
