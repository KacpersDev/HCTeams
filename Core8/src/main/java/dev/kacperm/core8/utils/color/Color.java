package dev.kacperm.core8.utils.color;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class Color {

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> translateList(List<String> list) {
        return list.stream().map(Color::translate).collect(Collectors.toList());
    }
}
