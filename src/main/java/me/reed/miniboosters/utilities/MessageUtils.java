package me.reed.miniboosters.utilities;

import me.reed.miniboosters.MiniBoosters;
import org.bukkit.ChatColor;

public class MessageUtils {
    private final MiniBoosters plugin = new MiniBoosters();

    /*
    Notes:
    The active boosters message is built in MiniBoosters.java
     */

    // Error message strings
    public static final String errorMsgIncorrectArgs = ChatColor.translateAlternateColorCodes('&', "&cIncorrect usage of this command! Please provide correct arguments!");
    public static final String errorMsgTooManyArgs = ChatColor.translateAlternateColorCodes('&', "&cIncorrect usage of this command! Too many arguments!");
    public static final String usageErrMsgAction = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster <action>) Actions: set, remove, list, info");
    public static final String usageErrMsgSet = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster set <type> <optional: multiValue>) Use &n/booster list &r&7for the list of booster types!");
    public static final String usageErrMsgSet2 = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster set <type> <optional: multiValue>)");
    public static final String usageErrMsgSet3 = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster set <type> <optional: multiValue>) <multiValue> must be an integer!");
    public static final String usageErrMsgRemove = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster remove <type>) Use &n/booster list &r&7for the list of booster types!");
    public static final String usageErrMsgRemove2 = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster remove <type>)");
    public static final String usageErrMsgList = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster list)");
    public static final String usageErrMsgInfo = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster info)");

    // Multiplier status message methods (taking in types, values, and whether or not to include default multiplier value in the message)
    public static String setMultiMsgToggleActive(String multiType, int defaultMultiValue, int multiValue, boolean useDefault) {
        if (useDefault) {
            return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier is now &aACTIVE &3at &bdefault " + defaultMultiValue + "x&3!");
        } else {
            return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier is now &aACTIVE &3at &b" + multiValue + "x&3!");
        }
    }
    public static String removeMultiMsgToggleInactive(String multiType) {
        return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier is now &cINACTIVE&3!");
    }
    public static String setMultiMsgChanged(String multiType, int defaultMultiValue, int multiValue, boolean useDefault) {
        if (useDefault) {
            return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier has been &eCHANGED &3to &bdefault " + defaultMultiValue + "x&3!");
        } else {
            return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier has been &eCHANGED &3to &b" + multiValue + "x&3!");
        }
    }
    public static String setMultiMsgAlreadyActive(String multiType, int defaultMultiValue, int multiValue, boolean useDefault) {
        if (useDefault) {
            return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier is &balready active &3at &bdefault " + defaultMultiValue + "x&3!");
        } else {
            return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier is &balready active &3at &b" + multiValue + "x&3!");
        }
    }
    public static String removeMultiMsgAlreadyInactive(String multiType) {
        return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier is &balready inactive&3!");
    }

    // General strings
    public static final String availableBoosters = ChatColor.translateAlternateColorCodes('&', "&3Available boosters: &bexp");
    public static final String consoleCommandErr = "You must be in-game to use this command!";

    // General string methods (these methods take in values and could change - above general strings will never change)
    public static String playerMissingPermissionErr(String permissionName) {
        return ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command! &4(missing permission &n" + permissionName + "&r&4)");
    }
    public static String messageHeader(String headerContents) {
        return ChatColor.translateAlternateColorCodes('&', "&3-&b=&3- &f" + headerContents + " &3-&b=&3-");
    }

}
