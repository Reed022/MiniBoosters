package me.reed.reedsplugin.commands;

import me.reed.reedsplugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExpTracking implements CommandExecutor {
    private final Main main;
    private static boolean expTracking = false;

    public ExpTracking(Main main) {this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        if (sender instanceof Player)  {
            player = (Player) sender;
            if (player.isOp() || player.hasPermission("exptracking")) {
                expTracking = !expTracking;
                String statusMessage;
                ChatColor statusColor;
                if (expTracking) {
                    statusMessage = "active";
                    statusColor = ChatColor.GREEN;
                } else {
                    statusMessage = "inactive";
                    statusColor = ChatColor.RED;
                }
                player.sendMessage(ChatColor.DARK_AQUA + "Exp pickup tracking now " + statusColor + statusMessage + "!");
            } else {
                player.sendMessage(ChatColor.RED + "You do not have the required permission to use this command!");
            }
        } else {
            main.getLogger().info("You must be in game to activate exp pickup tracking!");
        }
        return true;
    }

    public static boolean getExpTrackingStatus() {return expTracking;}
}
