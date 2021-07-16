package me.reed.miniboosters.commands;

import me.reed.miniboosters.MiniBoosters;
import me.reed.miniboosters.utilities.MessageUtils;
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

            if (player.isOp() || player.hasPermission("miniboosters.command.booster")) {

                if (args.length == 0) {
                    player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                    player.sendMessage(MessageUtils.incorrectUsageMsg("action", 0, "actions"));
                }

                if (args.length > 0) {
                    switch (args[0].toLowerCase()) {
                        // SET
                        case "set":
                            // Checking args length -> 1 = too short, 2 = default booster, 3 = booster with specified multiValue
                            switch (args.length) {
                                case 1:
                                    player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                                    player.sendMessage(MessageUtils.incorrectUsageMsg("set", 2, "list"));
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
                                            } else {
                                                plugin.setExpMultiEnabled(true);
                                                plugin.setExpMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier is now " + ChatColor.GREEN + "active " + ChatColor.DARK_AQUA + "at " + ChatColor.AQUA + "default " + plugin.getDefaultMultiValue() + "x" + ChatColor.DARK_AQUA + "!");
                                            }
                                            break;
                                        // future cases for future booster types
                                        // when type is non-existent
                                        default:
                                            player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                                            player.sendMessage(MessageUtils.incorrectUsageMsg("set", 1, "list"));
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
                                                } else {
                                                    plugin.setExpMultiEnabled(true);
                                                    plugin.setExpMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(ChatColor.DARK_AQUA + "Exp multiplier is now " + ChatColor.GREEN + "active " + ChatColor.DARK_AQUA + "at " + ChatColor.AQUA + plugin.getExpMultiValue() + "x" + ChatColor.DARK_AQUA + "!");
                                                }
                                            }
                                            break;
                                        // future cases for future booster types
                                        // when type is non-existent
                                        default:
                                            player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                                            player.sendMessage(MessageUtils.incorrectUsageMsg("set", 2, "list"));
                                    }
                                    break;
                                    // end of case 3
                                default:
                                    player.sendMessage(MessageUtils.errorMsgTooManyArgs);
                                    player.sendMessage(MessageUtils.incorrectUsageMsg("set", 2, null));
                            }
                            break;
                        // REMOVE
                        case "remove":
                            switch (args.length) {
                                case 1:
                                    player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                                    player.sendMessage(MessageUtils.incorrectUsageMsg("remove", 1, "list"));
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
                                            player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                                            player.sendMessage(MessageUtils.incorrectUsageMsg("remove", 1, "list"));
                                    }
                                    break;
                                default:
                                    player.sendMessage(MessageUtils.errorMsgTooManyArgs);
                                    player.sendMessage(MessageUtils.incorrectUsageMsg("remove", 1, "list"));
                            }
                            break;
                        // LIST
                        case "list":
                            if (args.length > 1) {
                                player.sendMessage(MessageUtils.errorMsgTooManyArgs);
                                player.sendMessage(ChatColor.GRAY + "(Usage: /booster list)");
                            } else {
                                player.sendMessage(ChatColor.DARK_AQUA + "Available boosters: " + MessageUtils.typeList);
                            }
                            break;
                        // INFO
                        case "info":
                            if (args.length > 1) {
                                player.sendMessage(MessageUtils.errorMsgTooManyArgs);
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
                            player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                            player.sendMessage(MessageUtils.incorrectUsageMsg("action", 0, "actions"));
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command! " + ChatColor.DARK_RED + "miniboosters.command.booster");
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
            p.sendMessage(MessageUtils.errorMsgIncorrectArgs);
            p.sendMessage(MessageUtils.incorrectUsageMsg("set", 2, null) + ChatColor.UNDERLINE + "<multiValue> must be an integer!");
            return false;
        }
        return true;
    }
}
