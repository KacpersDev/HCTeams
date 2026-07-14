package dev.kacperm.core8.messages;

import dev.kacperm.core8.Core;
import dev.kacperm.core8.utils.color.Color;

public class Messages {

    /* GLOBAL */
    public static String NO_PERMISSIONS = Color.translate(Core.getInstance().getLanguage().getConfiguration().getString("no-permissions"));
    public static String REQUIRED_PLAYER = Color.translate(Core.getInstance().getLanguage().getConfiguration().getString("required-player"));

    /* BALANCE */
    public static String BALANCE = Color.translate(Core.getInstance().getLanguage().getConfiguration().getString("balance.balance"));
}
