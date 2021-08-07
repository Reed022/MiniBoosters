package me.reed.miniboosters.commands;

import me.reed.miniboosters.MiniBoosters;
import me.reed.miniboosters.utilities.MessageUtils;
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
                                            // Same for each case down, but applying to that specific multiplier. Here, the player is trying to turn the multiplier on at default value.
                                            // Check if multiplier is already on at default value
                                            if (plugin.getExpMultiValue() == plugin.getDefaultMultiValue() && plugin.isExpMultiEnabled()) {
                                                player.sendMessage(MessageUtils.msgMultiAlreadyActive("Exp", plugin.getDefaultMultiValue(), plugin.getExpMultiValue(), true));
                                            } // Check if multiplier is already on, but at a value other than default. Then, set to default
                                            else if (plugin.getExpMultiValue() != plugin.getDefaultMultiValue() && plugin.isExpMultiEnabled()) {
                                                plugin.setExpMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(MessageUtils.msgChangeMulti("Exp", plugin.getDefaultMultiValue(), plugin.getExpMultiValue(), true));
                                            } // Else, multiplier is simply off, so turn it on at default
                                            else {
                                                plugin.setExpMultiEnabled(true);
                                                plugin.setExpMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(MessageUtils.msgEnableMulti("Exp", plugin.getDefaultMultiValue(), plugin.getExpMultiValue(), true));
                                            }
                                            break;
                                        case "mob_drops":
                                            if (plugin.getMobDropMultiValue() == plugin.getDefaultMultiValue() && plugin.isMobDropMultiEnabled()) {
                                                player.sendMessage(MessageUtils.msgMultiAlreadyActive("Mob drop", plugin.getDefaultMultiValue(), plugin.getMobDropMultiValue(), true));
                                            } else if (plugin.getMobDropMultiValue() != plugin.getDefaultMultiValue() && plugin.isMobDropMultiEnabled()) {
                                                plugin.setMobDropMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(MessageUtils.msgChangeMulti("Mob drop", plugin.getDefaultMultiValue(), plugin.getMobDropMultiValue(), true));
                                            } else {
                                                plugin.setMobDropMultiEnabled(true);
                                                plugin.setMobDropMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(MessageUtils.msgEnableMulti("Mob drop", plugin.getDefaultMultiValue(), plugin.getMobDropMultiValue(), true));
                                            }
                                            break;
                                        case "animal_drops":
                                            if (plugin.getAnimalDropMultiValue() == plugin.getDefaultMultiValue() && plugin.isAnimalDropMultiEnabled()) {
                                                player.sendMessage(MessageUtils.msgMultiAlreadyActive("Animal drop", plugin.getDefaultMultiValue(), plugin.getAnimalDropMultiValue(), true));
                                            } else if (plugin.getAnimalDropMultiValue() != plugin.getDefaultMultiValue() && plugin.isAnimalDropMultiEnabled()) {
                                                plugin.setAnimalDropMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(MessageUtils.msgChangeMulti("Animal drop", plugin.getDefaultMultiValue(), plugin.getAnimalDropMultiValue(), true));
                                            } else {
                                                plugin.setAnimalDropMultiEnabled(true);
                                                plugin.setAnimalDropMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(MessageUtils.msgEnableMulti("Animal drop", plugin.getDefaultMultiValue(), plugin.getAnimalDropMultiValue(), true));
                                            }
                                            break;
                                        case "boss_drops":
                                            if (plugin.getBossDropMultiValue() == plugin.getDefaultMultiValue() && plugin.isBossDropMultiEnabled()) {
                                                player.sendMessage(MessageUtils.msgMultiAlreadyActive("Boss drop", plugin.getDefaultMultiValue(), plugin.getBossDropMultiValue(), true));
                                            } else if (plugin.getBossDropMultiValue() != plugin.getDefaultMultiValue() && plugin.isBossDropMultiEnabled()) {
                                                plugin.setBossDropMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(MessageUtils.msgChangeMulti("Boss drop", plugin.getDefaultMultiValue(), plugin.getBossDropMultiValue(), true));
                                            } else {
                                                plugin.setBossDropMultiEnabled(true);
                                                plugin.setBossDropMultiValue(plugin.getDefaultMultiValue());
                                                player.sendMessage(MessageUtils.msgEnableMulti("Boss drop", plugin.getDefaultMultiValue(), plugin.getBossDropMultiValue(), true));
                                            }
                                            break;
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
                                            // Same for each case down, but applying to that specific multiplier. Here, the player is trying to turn the multiplier on at a custom value.
                                            // Check if args[2] is an integer
                                            if (isMultiValueInt(args[2], player)) {
                                                // Check if multiplier is already on at the custom value given in args[2]
                                                if (Integer.parseInt(args[2]) == plugin.getExpMultiValue() && plugin.isExpMultiEnabled()) {
                                                    player.sendMessage(MessageUtils.msgMultiAlreadyActive("Exp", plugin.getDefaultMultiValue(), plugin.getExpMultiValue(), false));
                                                } // Check if multiplier is already on at a value other than the one given in args[2]. Then, set to given value
                                                else if (Integer.parseInt(args[2]) != plugin.getExpMultiValue() && plugin.isExpMultiEnabled()) {
                                                    plugin.setExpMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(MessageUtils.msgChangeMulti("Exp", plugin.getDefaultMultiValue(), plugin.getExpMultiValue(), false));
                                                } // Else, multiplier is simply off, so turn it on at the custom value
                                                else {
                                                    plugin.setExpMultiEnabled(true);
                                                    plugin.setExpMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(MessageUtils.msgEnableMulti("Exp", plugin.getDefaultMultiValue(), plugin.getExpMultiValue(), false));
                                                }
                                            }
                                            break;
                                        case "mob_drops":
                                            if (isMultiValueInt(args[2], player)) {
                                                if (Integer.parseInt(args[2]) == plugin.getMobDropMultiValue() && plugin.isMobDropMultiEnabled()) {
                                                    player.sendMessage(MessageUtils.msgMultiAlreadyActive("Mob drop", plugin.getDefaultMultiValue(), plugin.getMobDropMultiValue(), false));
                                                } else if (Integer.parseInt(args[2]) != plugin.getMobDropMultiValue() && plugin.isMobDropMultiEnabled()) {
                                                    plugin.setMobDropMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(MessageUtils.msgChangeMulti("Mob drop", plugin.getDefaultMultiValue(), plugin.getMobDropMultiValue(), false));
                                                } else {
                                                    plugin.setMobDropMultiEnabled(true);
                                                    plugin.setMobDropMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(MessageUtils.msgEnableMulti("Mob drop", plugin.getDefaultMultiValue(), plugin.getMobDropMultiValue(), false));
                                                }
                                            }
                                            break;
                                        case "animal_drops":
                                            if (isMultiValueInt(args[2], player)) {
                                                if (Integer.parseInt(args[2]) == plugin.getAnimalDropMultiValue() && plugin.isAnimalDropMultiEnabled()) {
                                                    player.sendMessage(MessageUtils.msgMultiAlreadyActive("Animal drop", plugin.getDefaultMultiValue(), plugin.getAnimalDropMultiValue(), false));
                                                } else if (Integer.parseInt(args[2]) != plugin.getAnimalDropMultiValue() && plugin.isAnimalDropMultiEnabled()) {
                                                    plugin.setAnimalDropMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(MessageUtils.msgChangeMulti("Animal drop", plugin.getDefaultMultiValue(), plugin.getAnimalDropMultiValue(), false));
                                                } else {
                                                    plugin.setAnimalDropMultiEnabled(true);
                                                    plugin.setAnimalDropMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(MessageUtils.msgEnableMulti("Animal drop", plugin.getDefaultMultiValue(), plugin.getAnimalDropMultiValue(), false));
                                                }
                                            }
                                            break;
                                        case "boss_drops":
                                            if (isMultiValueInt(args[2], player)) {
                                                if (Integer.parseInt(args[2]) == plugin.getBossDropMultiValue() && plugin.isBossDropMultiEnabled()) {
                                                    player.sendMessage(MessageUtils.msgMultiAlreadyActive("Boss drop", plugin.getDefaultMultiValue(), plugin.getBossDropMultiValue(), false));
                                                } else if (Integer.parseInt(args[2]) != plugin.getBossDropMultiValue() && plugin.isBossDropMultiEnabled()) {
                                                    plugin.setBossDropMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(MessageUtils.msgChangeMulti("Boss drop", plugin.getDefaultMultiValue(), plugin.getBossDropMultiValue(), false));
                                                } else {
                                                    plugin.setBossDropMultiEnabled(true);
                                                    plugin.setBossDropMultiValue(Integer.parseInt(args[2]));
                                                    player.sendMessage(MessageUtils.msgEnableMulti("Boss drop", plugin.getDefaultMultiValue(), plugin.getBossDropMultiValue(), false));
                                                }
                                            }
                                            break;
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
                                            // Same for each case down, but applying to that specific multiplier. Here, the player is trying to turn the multiplier off.
                                            // Check if multiplier is already off
                                            if (!plugin.isExpMultiEnabled()) {
                                                player.sendMessage(MessageUtils.msgMultiAlreadyInactive("Exp"));
                                            } // Else, multiplier is on, so turn it off
                                            else {
                                                plugin.setExpMultiEnabled(false);
                                                player.sendMessage(MessageUtils.msgDisableMulti("Exp"));
                                            }
                                            break;
                                        case "mob_drops":
                                            if (!plugin.isMobDropMultiEnabled()) {
                                                player.sendMessage(MessageUtils.msgMultiAlreadyInactive("Mob drop"));
                                            } else {
                                                plugin.setMobDropMultiEnabled(false);
                                                player.sendMessage(MessageUtils.msgDisableMulti("Mob drop"));
                                            }
                                            break;
                                        case "animal_drops":
                                            if (!plugin.isAnimalDropMultiEnabled()) {
                                                player.sendMessage(MessageUtils.msgMultiAlreadyInactive("Animal drop"));
                                            } else {
                                                plugin.setAnimalDropMultiEnabled(false);
                                                player.sendMessage(MessageUtils.msgDisableMulti("Animal drop"));
                                            }
                                            break;
                                        case "boss_drops":
                                            if (!plugin.isBossDropMultiEnabled()) {
                                                player.sendMessage(MessageUtils.msgMultiAlreadyInactive("Boss drop"));
                                            } else {
                                                plugin.setBossDropMultiEnabled(false);
                                                player.sendMessage(MessageUtils.msgDisableMulti("Boss drop"));
                                            }
                                            break;
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
                                player.sendMessage(plugin.getActiveBoosters());
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
