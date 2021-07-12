package me.reed.miniboosters.commands;

import me.reed.miniboosters.MiniBoosters;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Booster implements CommandExecutor {
    private final MiniBoosters plugin;

    public Booster(MiniBoosters plugin) {
        this.plugin = plugin;
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
                        // SET
                        case "set":
                            // Checking args length -> 1 = too short, 2 = default booster, 3 = booster with specified multiValue
                            switch (args.length) {
                                case 1:
                                    player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Please provide correct arguments!");
                                    player.sendMessage(ChatColor.GRAY + "(Usage: /booster set <type> <multiValue>) | Use " + ChatColor.UNDERLINE + "/booster list" + ChatColor.RESET + ChatColor.GRAY + " for the list of booster types.");
                                    break;
                                case 2:
                                    // Checking type
                                    switch (args[1].toLowerCase()) {
                                        case "exp":
                                            if (plugin.getExpMultiValue() == plugin.getDefaultMultiValue() && plugin.isExpMultiEnabled()) {
                                                player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier is " + ChatColor.AQUA + "already active " + ChatColor.DARK_AQUA + "at " + ChatColor.AQUA + "default " + plugin.getDefaultMultiValue() + "x" + ChatColor.DARK_AQUA + "!");
                                            } else if (plugin.getExpMultiValue() != plugin.getDefaultMultiValue() && plugin.isExpMultiEnabled()) {
                                                plugin.setExpMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier has been " + ChatColor.YELLOW + "changed " + ChatColor.DARK_AQUA + "to " + ChatColor.AQUA + "default " + plugin.getDefaultMultiValue() + "x" + ChatColor.DARK_AQUA + "!");
                                            } else if (!plugin.isExpMultiEnabled()) {
                                                plugin.setExpMultiEnabled(true);
                                                plugin.setExpMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier is now " + ChatColor.GREEN + "active " + ChatColor.DARK_AQUA + "at " + ChatColor.AQUA + "default " + plugin.getDefaultMultiValue() + "x" + ChatColor.DARK_AQUA + "!");
                                            }
                                            break;
                                        // future cases for future booster types
                                        // when type is non-existent
                                        default:
                                            player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Please provide correct arguments!");
                                            player.sendMessage(ChatColor.GRAY + "(Usage: /booster set <type>) | Use " + ChatColor.UNDERLINE + "/booster list" + ChatColor.RESET + ChatColor.GRAY + " for the list of booster types.");
                                    }
                                    break;
                                case 3:
                                    // Checking type
                                    switch (args[1].toLowerCase()) {
                                        case "exp":
                                            // tests if args[2] is parsable
                                            if (isArgInt(args[2], player)) {
                                                if (Integer.parseInt(args[2]) == plugin.getExpMultiValue() && plugin.isExpMultiEnabled()) {
                                                    player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier is " + ChatColor.AQUA + "already active " + ChatColor.DARK_AQUA + "at " + ChatColor.AQUA + plugin.getExpMultiValue() + "x" + ChatColor.DARK_AQUA + "!");
                                                } else if (Integer.parseInt(args[2]) != plugin.getExpMultiValue() && plugin.isExpMultiEnabled()) {
                                                    plugin.setExpMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier has been " + ChatColor.YELLOW + "changed " + ChatColor.DARK_AQUA + "to " + ChatColor.AQUA + plugin.getExpMultiValue() + "x" + ChatColor.DARK_AQUA + "!");
                                                } else if (!plugin.isExpMultiEnabled()) {
                                                    plugin.setExpMultiEnabled(true);
                                                    plugin.setExpMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier is now " + ChatColor.GREEN + "active " + ChatColor.DARK_AQUA + "at " + ChatColor.AQUA + plugin.getExpMultiValue() + "x" + ChatColor.DARK_AQUA + "!");
                                                }
                                            }
                                            break;
                                        // future cases for future booster types
                                        // when type is non-existent
                                        default:
                                            player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Please provide correct arguments!");
                                            player.sendMessage(ChatColor.GRAY + "(Usage: /booster set <type> <multiValue>) | Use " + ChatColor.UNDERLINE + "/booster list" + ChatColor.RESET + ChatColor.GRAY + " for the list of booster types.");
                                    }
                                    break;
                                    // end of case 3
                                default:
                                    player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Too many arguments!");
                                    player.sendMessage(ChatColor.GRAY + "(Usage: /booster set <type> <multiValue>)");
                            }
                            break;
                        // REMOVE
                        case "remove":
                            switch (args.length) {
                                case 1:
                                    player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Please provide correct arguments!");
                                    player.sendMessage(ChatColor.GRAY + "(Usage: /booster remove <type>) | Use " + ChatColor.UNDERLINE + "/booster list" + ChatColor.RESET + ChatColor.GRAY + " for the list of booster types.");
                                    break;
                                case 2:
                                    switch (args[1].toLowerCase()) {
                                        case "exp":
                                            if (!plugin.isExpMultiEnabled()) {
                                                player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier is " + ChatColor.AQUA + "already inactive" + ChatColor.DARK_AQUA + "!");
                                            } else {
                                                plugin.setExpMultiEnabled(false);
                                                player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier is now " + ChatColor.RED + "inactive" + ChatColor.DARK_AQUA + "!");
                                            }
                                            break;
                                        // future cases for future booster types
                                        // when type is non-existent
                                        default:
                                            player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Please provide correct arguments!");
                                            player.sendMessage(ChatColor.GRAY + "(Usage: /booster remove <type>) | Use " + ChatColor.UNDERLINE + "/booster list" + ChatColor.RESET + ChatColor.GRAY + " for the list of booster types.");
                                    }
                                    break;
                                default:
                                    player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Too many arguments!");
                                    player.sendMessage(ChatColor.GRAY + "(Usage: /booster remove <type>)" + ChatColor.BOLD + " Types: " + ChatColor.RESET + ChatColor.GRAY + "exp");
                            }
                            break;
                        // LIST
                        case "list":
                            if (args.length > 1) {
                                player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Too many arguments!");
                                player.sendMessage(ChatColor.GRAY + "(Usage: /booster list)");
                            } else {
                                player.sendMessage(ChatColor.DARK_AQUA + "Available boosters: " + ChatColor.AQUA + "exp");
                            }
                            break;
                        // INFO
                        case "info":
                            if (args.length > 1) {
                                player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Too many arguments!");
                                player.sendMessage(ChatColor.GRAY + "(Usage: /booster info)");
                            } else {
                                player.sendMessage(ChatColor.DARK_AQUA + "-" + ChatColor.AQUA + "=" + ChatColor.DARK_AQUA + "- " + ChatColor.WHITE + "Active Boosters: " + ChatColor.DARK_AQUA + "-" + ChatColor.AQUA + "=" + ChatColor.DARK_AQUA + "-");
                                player.sendMessage(ChatColor.AQUA + plugin.getActiveBoosters());
                            }
                            break;
                        /*
                        case "tt":
                            break;
                            */
                        default:
                            player.sendMessage(ChatColor.RED + "Incorrect usage of this command! Please provide correct arguments!");
                            player.sendMessage(ChatColor.GRAY + "(Usage: /booster <action>)" + ChatColor.BOLD + " Actions: " + ChatColor.RESET + ChatColor.GRAY + "set, remove, list, info");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have the required permission to use this command!");
            }
        } else {
            plugin.getLogger().info("You must be in game to activate double exp!");
        }
        return true;
    }

    public static boolean isArgInt(String s, Player p) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            p.sendMessage(ChatColor.RED + "Incorrect usage of this command! Please provide correct arguments!");
            p.sendMessage(ChatColor.GRAY + "(Usage: /booster <set> <type> <multiValue>) " + ChatColor.UNDERLINE + "<multiValue> must be an integer!");
            return false;
        }
        return true;
    }
}
