package net.shadowrain.essentialsplus;



import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class TeleportUtils {

    static EssentialsPlus plugin;

    public TeleportUtils(EssentialsPlus plugin) {
        this.plugin = plugin;
    }

    public static HashSet<Material> badBlocks = new HashSet<>();

    static {
        badBlocks.add(Material.LAVA);
        badBlocks.add(Material.FIRE);
        badBlocks.add(Material.WATER);
    }

    public static Location generateLocation(Player p) {

        Random random = new Random();

        int x = random.nextInt(plugin.getConfig().getInt("border"));
        int y = 0;
        int z = random.nextInt(plugin.getConfig().getInt("border"));

        Location randomLocation = new Location(p.getWorld(), x, y, z);

        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation);
        randomLocation.setY(y);

        while (!isLocationSafe(randomLocation)) {
            randomLocation = generateLocation(p);
        }

        return randomLocation;


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
