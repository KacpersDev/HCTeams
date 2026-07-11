package dev.kacperm.shared.utils.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class FastLocation {

    public static Location fromString(String string) {
        String[] split = string.split(":");

        World world = Bukkit.getWorld(split[0]);
        int x = Integer.parseInt(split[1]);
        int y = Integer.parseInt(split[2]);
        int z = Integer.parseInt(split[3]);
        float yaw = Float.parseFloat(split[4]);
        float pitch = Float.parseFloat(split[5]);

        return new Location(world, x,y,z,yaw,pitch);
    }

    public static String toString(Location location) {
        return location.getWorld().getName() + ":"
                + location.getBlockX() + ":" + location.getBlockY() + ":" + location.getBlockZ()
                + location.getYaw() + ":" + location.getPitch();
    }
}
