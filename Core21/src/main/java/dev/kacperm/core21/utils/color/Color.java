package dev.kacperm.core21.utils.color;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Color {

    public static Component translate(String vanillaMessage) {
        if (vanillaMessage.trim().contains("&")) {
            return LegacyComponentSerializer.legacyAmpersand().deserialize(vanillaMessage).decoration(TextDecoration.ITALIC, false);
        } else if (vanillaMessage.trim().startsWith("§")) {
            return LegacyComponentSerializer.legacySection().deserialize(vanillaMessage).decoration(TextDecoration.ITALIC, false);
        } else {
            return MiniMessage.miniMessage().deserialize(vanillaMessage).decoration(TextDecoration.ITALIC, false);
        }
    }

    public static List<Component> translateList(List<String> vanillaMessages) {
        List<Component> components = new ArrayList<>();
        for (String vanillaMessage : vanillaMessages) {
            components.add(translate(vanillaMessage));
        }

        return components;
    }

    @SuppressWarnings("deprecation")
    public static String translateLegacy(String vanillaMessage) {
        return ChatColor.translateAlternateColorCodes('&', vanillaMessage);
    }

    public static String legacy(Component message) {
        return LegacyComponentSerializer.legacyAmpersand().serialize(message);
    }
}