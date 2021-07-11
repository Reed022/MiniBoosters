package me.reed.miniboosters.commands;

import me.reed.miniboosters.MiniBoosters;
import me.reed.miniboosters.events.Experience;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Booster implements CommandExecutor {
    private final MiniBoosters main;
    private final int defaultMulti = 2;

    public Booster(MiniBoosters main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp() || player.hasPermission("miniboosters.booster")) {

                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Please provide correct arguments!");
                    player.sendMessage(ChatColor.GRAY + "(Usage: /booster <action>)" + ChatColor.BOLD + " Actions: " + ChatColor.RESET + ChatColor.GRAY + "set, remove, list, info");
                }

                if (args.length > 0) {
                    switch (args[0].toLowerCase()) {
                        case "set":
                            if (args.length == 1) {
                                player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Please provide arguments!");
                                player.sendMessage(ChatColor.GRAY + "(Usage: /booster set <type>)" + ChatColor.BOLD + " Types: " + ChatColor.RESET + ChatColor.GRAY + "exp");
                            } else if (args.length == 2 && args[1].equalsIgnoreCase("exp")) {
                                if (main.getExpMultiToggled()) {
                                    player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier is " + ChatColor.AQUA + "already active" + ChatColor.DARK_AQUA + "!");
                                } else {
                                    main.setExpMultiToggled(true);
                                    Experience.setExpMulti(defaultMulti);
                                    player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier is now " + ChatColor.GREEN + "active " + ChatColor.DARK_AQUA + "at " + ChatColor.AQUA + "default " + defaultMulti + "x" + ChatColor.DARK_AQUA + "!");
                                }
                            }
                            break;
                        case "remove":
                            if (args.length == 1) {
                                player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Please provide arguments!");
                                player.sendMessage(ChatColor.GRAY + "(Usage: /booster remove <type>)" + ChatColor.BOLD + " Types: " + ChatColor.RESET + ChatColor.GRAY + "exp");
                            } else if (args.length == 2 && args[1].equalsIgnoreCase("exp")) {
                                if (!main.getExpMultiToggled()) {
                                    player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier is " + ChatColor.AQUA + "already inactive" + ChatColor.DARK_AQUA + "!");
                                } else {
                                    main.setExpMultiToggled(false);
                                    player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier is now " + ChatColor.RED + "inactive" + ChatColor.DARK_AQUA + "!");
                                }
                            }
                            break;
                        case "list":
                            player.sendMessage(ChatColor.DARK_AQUA + "Available boosters: " + ChatColor.AQUA + "exp");
                            break;
                        case "info":
                            player.sendMessage(ChatColor.DARK_AQUA + "-" + ChatColor.AQUA + "=" + ChatColor.DARK_AQUA + "- " + ChatColor.WHITE + "Active Boosters: " + ChatColor.DARK_AQUA + "-" + ChatColor.AQUA + "=" + ChatColor.DARK_AQUA + "-");
                            player.sendMessage(ChatColor.AQUA + main.getActiveBoosters());
                            break;
                        default:
                            player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Please provide correct arguments!");
                            player.sendMessage(ChatColor.GRAY + "(Usage: /booster <action>)" + ChatColor.BOLD + " Actions: " + ChatColor.RESET + ChatColor.GRAY + "set, remove, list, info");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have the required permission to use this command!");
            }
        } else {
            main.getLogger().info("You must be in game to activate double exp!");
        }
        return true;
    }
}
