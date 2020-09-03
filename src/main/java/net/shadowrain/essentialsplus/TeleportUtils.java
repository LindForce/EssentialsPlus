package net.shadowrain.essentialsplus;



import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class TeleportUtils {

    public static HashSet<Material> badBlocks = new HashSet<>();

    static {
        badBlocks.add(Material.LAVA);
        badBlocks.add(Material.GRASS_BLOCK);
    }

    public static Location generateLocation(Player p) {

        Random random = new Random();

        int x = random.nextInt(500);
        int y = 0;
        int z = random.nextInt(500);

        Location randomLocation = new Location(p.getWorld(), x, y, z);

        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation);
        randomLocation.setY(y);

        if (isLocationSafe(randomLocation)) {
            return randomLocation;
        } else {
            p.sendMessage("Location was not safe, relocating");
            return generateLocation(p);
        }


    }

    public static boolean isLocationSafe(Location location) {

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y - 1, z);


        if (badBlocks.contains(below.getType())) {
            return false;
        } else if (block.getType().isSolid()) {
            return false;
        } else if (above.getType().isSolid()) {
            return false;
        } else {
            return true;
        }

    }
}
