package net.shadowrain.essentialsplus;


import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class TeleportUtils {

    public static Location generateLocation(Player p) {

        Random random = new Random();

        int x = random.nextInt(500);
        int y = 0;
        int z = random.nextInt(500);

        Location randomLocation = new Location(p.getWorld(), x, y, z);

        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation);

        return randomLocation;

    }
}
