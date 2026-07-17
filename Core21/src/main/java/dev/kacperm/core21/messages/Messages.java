package dev.kacperm.core21.messages;

import dev.kacperm.core21.Core;
import dev.kacperm.core21.utils.color.Color;
import net.kyori.adventure.text.Component;

import java.util.List;

public class Messages {

    /* GLOBAL */
    public static Component NO_PERMISSIONS = Color.translate(Core.getInstance().getLanguage().getConfiguration().getString("no-permissions"));
    public static Component REQUIRED_PLAYER = Color.translate(Core.getInstance().getLanguage().getConfiguration().getString("required-player"));

    /* BALANCE */
    public static Component BALANCE = Color.translate(Core.getInstance().getLanguage().getConfiguration().getString("balance.balance"));

    /* FACTION */
    public static List<Component> FACTION_USAGE = Color.translateList(Core.getInstance().getLanguage().getConfiguration().getStringList("faction.usage"));
    public static Component FACTION_CREATED =  Color.translate(Core.getInstance().getLanguage().getConfiguration().getString("faction.created"));
    public static Component FACTION_DISBANDED =  Color.translate(Core.getInstance().getLanguage().getConfiguration().getString("faction.disbanded"));
}
