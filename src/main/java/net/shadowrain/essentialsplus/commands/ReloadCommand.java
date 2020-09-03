package net.shadowrain.skillunlock.commands;

import net.shadowrain.essentialsplus.EssentialsPlus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {

    EssentialsPlus plugin;

    public ReloadCommand(EssentialsPlus plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        String reloadMessage = plugin.getConfig().getString("reload-message");

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;

            if (p.hasPermission("su.reload")) {
                plugin.reloadConfig();
                p.sendMessage(plugin.PREFIX + ChatColor.translateAlternateColorCodes('&', reloadMessage));

            } else {
                p.sendMessage(plugin.PREFIX + ChatColor.RED + "You do not have permission to execute that command");
            }

        } else if (commandSender instanceof ConsoleCommandSender) {
            plugin.reloadConfig();
            System.out.println("[EssPlus] Config reloaded successfully");
        }

        return true;
    }
}
