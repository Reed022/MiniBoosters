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
                    player.sendMessage(MessageUtils.errorMsgUsageAction);
                }

                if (args.length > 0) {
                    switch (args[0].toLowerCase()) {
                        // SET
                        case "set":
                            // Checking args length -> 1 = too short, 2 = default booster, 3 = booster with specified multiValue
                            switch (args.length) {
                                case 1:
                                    player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                                    player.sendMessage(MessageUtils.errorMsgUsageSet);
                                    break;
                                case 2:
                                    // Checking type
                                    switch (args[1].toLowerCase()) {
                                        case "exp":
                                            if (plugin.getExpMultiValue() == plugin.getDefaultMultiValue() && plugin.isExpMultiEnabled()) {
                                                player.sendMessage(MessageUtils.msgMultiAlreadyActive("Exp", plugin.getDefaultMultiValue(), plugin.getExpMultiValue(), true));

                                            } else if (plugin.getExpMultiValue() != plugin.getDefaultMultiValue() && plugin.isExpMultiEnabled()) {
                                                plugin.setExpMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(MessageUtils.msgChangeMulti("Exp", plugin.getDefaultMultiValue(), plugin.getExpMultiValue(), true));
                                            } else {
                                                plugin.setExpMultiEnabled(true);
                                                plugin.setExpMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(MessageUtils.msgEnableMulti("Exp", plugin.getDefaultMultiValue(), plugin.getExpMultiValue(), true));
                                            }
                                            break;
                                        // future cases for future booster types
                                        // when type is non-existent
                                        default:
                                            player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                                            player.sendMessage(MessageUtils.errorMsgUsageSet);
                                    }
                                    break;
                                case 3:
                                    // Checking type
                                    switch (args[1].toLowerCase()) {
                                        case "exp":
                                            // tests if args[2] is parsable
                                            if (isMultiValueInt(args[2], player)) {
                                                if (Integer.parseInt(args[2]) == plugin.getExpMultiValue() && plugin.isExpMultiEnabled()) {
                                                    player.sendMessage(MessageUtils.msgMultiAlreadyActive("Exp", plugin.getDefaultMultiValue(), plugin.getExpMultiValue(), false));
                                                } else if (Integer.parseInt(args[2]) != plugin.getExpMultiValue() && plugin.isExpMultiEnabled()) {
                                                    plugin.setExpMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(MessageUtils.msgChangeMulti("Exp", plugin.getDefaultMultiValue(), plugin.getExpMultiValue(), false));
                                                } else {
                                                    plugin.setExpMultiEnabled(true);
                                                    plugin.setExpMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(MessageUtils.msgEnableMulti("Exp", plugin.getDefaultMultiValue(), plugin.getExpMultiValue(), false));
                                                }
                                            }
                                            break;
                                        // future cases for future booster types
                                        // when type is non-existent
                                        default:
                                            player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                                            player.sendMessage(MessageUtils.errorMsgUsageSet);
                                    }
                                    break;
                                    // end of case 3
                                default:
                                    player.sendMessage(MessageUtils.errorMsgTooManyArgs);
                                    player.sendMessage(MessageUtils.errorMsgUsageSet2);
                            }
                            break;
                        // REMOVE
                        case "remove":
                            switch (args.length) {
                                case 1:
                                    player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                                    player.sendMessage(MessageUtils.errorMsgUsageRemove);
                                    break;
                                case 2:
                                    switch (args[1].toLowerCase()) {
                                        case "exp":
                                            if (!plugin.isExpMultiEnabled()) {
                                                player.sendMessage(MessageUtils.msgMultiAlreadyInactive("Exp"));
                                            } else {
                                                plugin.setExpMultiEnabled(false);
                                                player.sendMessage(MessageUtils.msgDisableMulti("Exp"));
                                            }
                                            break;
                                        // future cases for future booster types
                                        // when type is non-existent
                                        default:
                                            player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                                            player.sendMessage(MessageUtils.errorMsgUsageRemove);
                                    }
                                    break;
                                default:
                                    player.sendMessage(MessageUtils.errorMsgTooManyArgs);
                                    player.sendMessage(MessageUtils.errorMsgUsageRemove2);
                            }
                            break;
                        // LIST
                        case "list":
                            if (args.length > 1) {
                                player.sendMessage(MessageUtils.errorMsgTooManyArgs);
                                player.sendMessage(MessageUtils.errorMsgUsageList);
                            } else {
                                player.sendMessage(MessageUtils.availableBoosters);
                            }
                            break;
                        // INFO
                        case "info":
                            if (args.length > 1) {
                                player.sendMessage(MessageUtils.errorMsgTooManyArgs);
                                player.sendMessage(MessageUtils.errorMsgUsageInfo);
                            } else {
                                player.sendMessage(MessageUtils.messageHeader("Active Boosters:"));
                                player.sendMessage(ChatColor.AQUA + plugin.getActiveBoosters());
                            }
                            break;
                        case "testtools":
                            switch (args.length) {
                                case 1:
                                    player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                                    player.sendMessage(MessageUtils.errorMsgUsageTesttools);
                                    break;
                                case 2:
                                    if (args[1].equals("expalerts")) {
                                        if (plugin.playerExpAlertEnabled(player.getUniqueId())) {
                                            plugin.setPlayerExpAlertEnabled(player.getUniqueId(), false);
                                            player.sendMessage(MessageUtils.playerDisableExpAlerts);
                                        } else {
                                            plugin.setPlayerExpAlertEnabled(player.getUniqueId(), true);
                                            player.sendMessage(MessageUtils.playerEnableExpAlerts);
                                        }
                                    } else {
                                        player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                                        player.sendMessage(MessageUtils.errorMsgUsageTesttools);
                                    }
                                    break;
                                default:
                                    player.sendMessage(MessageUtils.errorMsgTooManyArgs);
                                    player.sendMessage(MessageUtils.errorMsgUsageTesttools);
                            }
                            break;
                        default:
                            player.sendMessage(MessageUtils.errorMsgIncorrectArgs);
                            player.sendMessage(MessageUtils.errorMsgUsageAction);
                    }
                }
            } else {
                player.sendMessage(MessageUtils.playerMissingPermissionErr("miniboosters.command.booster"));
            }
        } else {
            plugin.getLogger().info(MessageUtils.consoleCommandErr);
        }
        return true;
    }

    public static boolean isMultiValueInt(String s, Player p) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            p.sendMessage(MessageUtils.errorMsgIncorrectArgs);
            p.sendMessage(MessageUtils.errorMsgUsageSet3);
            return false;
        }
        return true;
    }
}
