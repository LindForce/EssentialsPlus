package net.shadowrain.essentialsplus;

import net.shadowrain.essentialsplus.commands.RandomTP;
import net.shadowrain.essentialsplus.commands.Slap;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class EssentialsPlus extends JavaPlugin {

    public final String PREFIX = ChatColor.translateAlternateColorCodes('&', getConfig().getString("plugin-prefix"));

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("slap").setExecutor(new Slap());
        getCommand("rtp").setExecutor(new RandomTP(this));

    }
}
