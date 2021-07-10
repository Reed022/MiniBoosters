package me.reed.miniboosters.commands;

import me.reed.miniboosters.MiniBoosters;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DoubleExp implements CommandExecutor {
    private final MiniBoosters main;

    public DoubleExp(MiniBoosters main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp() || player.hasPermission("doubleexp")) {
                String statusMessage;
                ChatColor statusColor;
                if (main.getDoubleToggled()) {
                    main.setDoubleToggled(false);
                    statusColor = ChatColor.RED;
                    statusMessage = "inactive";
                } else {
                    main.setDoubleToggled(true);
                    statusColor = ChatColor.GREEN;
                    statusMessage = "active";
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
}
