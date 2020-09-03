package net.shadowrain.essentialsplus.commands;

import net.shadowrain.essentialsplus.EssentialsPlus;
import net.shadowrain.essentialsplus.TeleportUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class RandomTP implements CommandExecutor {
    public HashMap<String, Long> cooldowns = new HashMap<String, Long>();

    EssentialsPlus plugin;

    public RandomTP(EssentialsPlus plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            System.out.println(plugin.PREFIX + "You can only use this command in-game");
            return true;
        }

        int cd = plugin.getConfig().getInt("cooldown");

        Player p = (Player) commandSender;

        if(cooldowns.containsKey(commandSender.getName())) {
            long secondsLeft = ((cooldowns.get(commandSender.getName()) / 1000) + cd) - (System.currentTimeMillis() / 1000);
            if (secondsLeft > 0) {
                // Still cooling down
                commandSender.sendMessage(plugin.PREFIX + "You cant use that commands for another "+ secondsLeft +" seconds!");
                return true;
            }
        }

        // No cooldown found or cooldown has expired, save new cooldown
        cooldowns.put(commandSender.getName(), System.currentTimeMillis());

        p.teleport(TeleportUtils.generateLocation(p));


        return true;
    }
}
