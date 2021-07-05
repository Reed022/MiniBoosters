package me.reed.reedsplugin.commands;

import me.reed.reedsplugin.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DoubleExp implements CommandExecutor {
    private final Main main;
    private static boolean doubleExpStatus = false;
    private static Player player;

    public DoubleExp(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            player = (Player) sender;

            if (player.isOp() || player.hasPermission("doubleexp")) {
                // Sets variables to appropriate values based on doubleExpStatus and outputs messages based on permissions
                doubleExpStatus = !doubleExpStatus;
                String statusMessage;
                ChatColor statusColor;
                if (doubleExpStatus) {
                    statusColor = ChatColor.GREEN;
                    statusMessage = "active";
                } else {
                    statusColor = ChatColor.RED;
                    statusMessage = "inactive";
                }
                player.sendMessage(ChatColor.DARK_AQUA + "Double exp now " + statusColor + statusMessage + ChatColor.DARK_AQUA + "!");
            } else {
                player.sendMessage(ChatColor.RED + "You do not have the required permission to use this command!");
            }
        } else {
            main.getLogger().info("You must be in game to activate double exp!");
        }
        return true;
    }

    public static boolean getDoubleExpStatus() { return doubleExpStatus;}

    public static Player getSender() { return player;}
}
