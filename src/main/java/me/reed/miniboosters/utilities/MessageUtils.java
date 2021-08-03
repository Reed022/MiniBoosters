package me.reed.miniboosters.utilities;

import org.bukkit.ChatColor;

public class MessageUtils {
    /*
    Notes:
    The active boosters message is built in MiniBoosters.java
     */

    // Error message strings
    public static final String errorMsgIncorrectArgs = ChatColor.translateAlternateColorCodes('&', "&cIncorrect usage of this command! Please provide correct arguments!");
    public static final String errorMsgTooManyArgs = ChatColor.translateAlternateColorCodes('&', "&cIncorrect usage of this command! Too many arguments!");
    public static final String errorMsgUsageAction = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster <action>) Actions: set, remove, list, info, testtools");
    public static final String errorMsgUsageSet = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster set <type> <optional: multiValue>) Use &n/booster list &r&7for the list of booster types!");
    public static final String errorMsgUsageSet2 = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster set <type> <optional: multiValue>)");
    public static final String errorMsgUsageSet3 = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster set <type> <optional: multiValue>) <multiValue> must be an integer!");
    public static final String errorMsgUsageRemove = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster remove <type>) Use &n/booster list &r&7for the list of booster types!");
    public static final String errorMsgUsageRemove2 = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster remove <type>)");
    public static final String errorMsgUsageList = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster list)");
    public static final String errorMsgUsageInfo = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster info)");
    public static final String errorMsgUsageTesttools = ChatColor.translateAlternateColorCodes('&', "&7(Usage: /booster testtools <tool>) Tools: expalerts");

    // Multiplier status message methods (taking in types, values, and whether to include default multiplier value in the message)
    public static String msgEnableMulti(String multiType, int defaultMultiValue, int multiValue, boolean useDefault) {
        if (useDefault) {
            return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier is now &aACTIVE &3at &bdefault " + defaultMultiValue + "x&3!");
        } else {
            return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier is now &aACTIVE &3at &b" + multiValue + "x&3!");
        }
    }
    public static String msgDisableMulti(String multiType) {
        return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier is now &cINACTIVE&3!");
    }
    public static String msgChangeMulti(String multiType, int defaultMultiValue, int multiValue, boolean useDefault) {
        if (useDefault) {
            return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier has been &eCHANGED &3to &bdefault " + defaultMultiValue + "x&3!");
        } else {
            return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier has been &eCHANGED &3to &b" + multiValue + "x&3!");
        }
    }
    public static String msgMultiAlreadyActive(String multiType, int defaultMultiValue, int multiValue, boolean useDefault) {
        if (useDefault) {
            return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier is &balready active &3at &bdefault " + defaultMultiValue + "x&3!");
        } else {
            return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier is &balready active &3at &b" + multiValue + "x&3!");
        }
    }
    public static String msgMultiAlreadyInactive(String multiType) {
        return ChatColor.translateAlternateColorCodes('&', "&3" + multiType + " multiplier is &balready inactive&3!");
    }

    // General strings
    public static final String playerEnableExpAlerts = ChatColor.translateAlternateColorCodes('&', "&3Exp alerts are now &aACTIVE&3!");
    public static final String playerDisableExpAlerts = ChatColor.translateAlternateColorCodes('&', "&3Exp alerts are now &cINACTIVE&3!");
    public static final String availableBoosters = ChatColor.translateAlternateColorCodes('&', "&3Available boosters: &bexp, mob_drops, animal_drops, boss_drops");
    public static final String consoleCommandErr = "You must be in-game to use this command!";

    // General string methods (these methods take in values and could change - above general strings will never change)
    public static String expAlertMsg(int experienceValue) {
        return ChatColor.translateAlternateColorCodes('&', "&bExperience of target orb: &a" + experienceValue);
    }
    public static String playerMissingPermissionErr(String permissionName) {
        return ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command! &4(missing permission &n" + permissionName + "&r&4)");
    }
    public static String messageHeader(String headerContents) {
        return ChatColor.translateAlternateColorCodes('&', "&3-&b=&3- &f" + headerContents + " &3-&b=&3-");
    }

}
