package net.shadowrain.essentialsplus.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Slap implements CommandExecutor {
    static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        String wrongSyntaxMsg = ChatColor.RED + "Wrong syntax. Use syntax: /slap [player] (damage).";

        if (commandSender instanceof Player) {

            if (!p.hasPermission("essplus.slap")) {
                p.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
                return true;
            }

            if (strings.length == 1) {
                Player target = Bukkit.getPlayerExact(strings[0]);
                if (target != null) {

                    if (target.getHealth() > 1) {
                        target.setHealth(target.getHealth() - 1);
                        if (target.getDisplayName() != p.getDisplayName()) {
                            p.sendMessage("You slapped " + target.getDisplayName());
                            target.sendMessage(p.getDisplayName() + " just slapped you!");
                        } else {
                            p.sendMessage("You slapped yourself! Ouch!");
                        }
                    } else {
                        target.setHealth(0.0);

                        if (target.getDisplayName() != p.getDisplayName()) {
                            p.sendMessage("You slapped " + target.getDisplayName() + " to death.");
                            target.sendMessage(p.getDisplayName() + " just slapped you to death!");
                        } else {
                            p.sendMessage("You just slapped yourself to death! Oof!");
                        }
                    }
                } else {
                    p.sendMessage(wrongSyntaxMsg);
                }

                return true;

            }

            if (strings.length == 2) {
                Player target = Bukkit.getPlayerExact(strings[0]);

                if (target != null) {
                    if (isInteger(strings[1])) {
                        int damage = Integer.parseInt(strings[1]);

                        if (target.getHealth() > damage) {
                            target.setHealth(target.getHealth() - damage);


                            if (target.getDisplayName() != p.getDisplayName()) {
                                p.sendMessage("You slapped " + target.getDisplayName() + "for " + damage + " damage!");
                                target.sendMessage(p.getDisplayName() + " just slapped you for " + damage + " damage!");
                            } else {
                                p.sendMessage("You slapped yourself for " + damage + " damage! Ouch!");
                            }
                        } else {
                            target.setHealth(0.0);

                            if (target.getDisplayName() != p.getDisplayName()) {
                                p.sendMessage("You slapped " + target.getDisplayName() + " to death.");
                                target.sendMessage(p.getDisplayName() + " just slapped you to death!");
                            } else {
                                p.sendMessage("You just slapped yourself to death! Oof!");
                            }
                        }

                    } else {
                        p.sendMessage(wrongSyntaxMsg);
                    }
                } else {
                    p.sendMessage(wrongSyntaxMsg);
                }
            } else {
                p.sendMessage(wrongSyntaxMsg);
            }
        } else {
            System.out.println("You need to be in-game to use this command.");
        }

        return true;
    }
}
