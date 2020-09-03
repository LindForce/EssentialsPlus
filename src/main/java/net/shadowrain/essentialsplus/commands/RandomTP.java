package net.shadowrain.essentialsplus.commands;

import net.shadowrain.essentialsplus.TeleportUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomTP implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;

            p.teleport(TeleportUtils.generateLocation(p));
        }

        return true;
    }
}
