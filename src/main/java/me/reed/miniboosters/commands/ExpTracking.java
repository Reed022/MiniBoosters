package me.reed.miniboosters.commands;

import me.reed.miniboosters.MiniBoosters;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExpTracking implements CommandExecutor {
    private final MiniBoosters main;

    public ExpTracking(MiniBoosters main) {this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player)  {
            Player player = (Player) sender;
            if (player.isOp() || player.hasPermission("exptracking")) {
                String statusMessage;
                ChatColor statusColor;
                if (main.getExpToggled(player.getUniqueId())) { // set false
                    main.setExpToggled(player.getUniqueId(), false);
                    statusMessage = "inactive";
                    statusColor = ChatColor.RED;
                } else { // set true
                    main.setExpToggled(player.getUniqueId(), true);
                    statusMessage = "active";
                    statusColor = ChatColor.GREEN;
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
}

