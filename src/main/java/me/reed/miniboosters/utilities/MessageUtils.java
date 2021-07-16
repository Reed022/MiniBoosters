package me.reed.miniboosters.utilities;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import me.reed.miniboosters.MiniBoosters;
import org.bukkit.ChatColor;

public class MessageUtils {
    private final MiniBoosters plugin = new MiniBoosters();

    public static final String errorMsgIncorrectArgs = ChatColor.RED + "Incorrect usage of this command! Please provide correct arguments!";
    public static final String errorMsgTooManyArgs = ChatColor.RED + "Incorrect usage of this command! Too many arguments!";
    public static final String actionList = ChatColor.BOLD + "Actions: " + ChatColor.RESET + ChatColor.GRAY + "set, remove, list, info";
    public static final String listNotice = ChatColor.GRAY + "Use " + ChatColor.UNDERLINE + "/booster list" + ChatColor.RESET + ChatColor.GRAY + " for the list of booster types";
    public static final String typeList = ChatColor.AQUA + "exp";

    public static final String alreadyActive = ChatColor.DARK_AQUA + "is " + ChatColor.AQUA + "already active" + ChatColor.DARK_AQUA + " at";
    public static final String alreadyInactive = ChatColor.DARK_AQUA + "is " + ChatColor.AQUA + "already inactive";
    public static final String changed = ChatColor.DARK_AQUA + "has been " + ChatColor.YELLOW + "changed" + ChatColor.DARK_AQUA + " to";
    public static final String toggledActive = ChatColor.DARK_AQUA + "is now " + ChatColor.GREEN + "active" + ChatColor.DARK_AQUA + " at";
    public static final String toggledInactive = ChatColor.DARK_AQUA + "is now " + ChatColor.RED + "inactive";

    /**
     * @param usage value determines arg1 of the "(Usage)" portion of the error message
     * @param extraArgs value determines how many extra args appear in the "usage" line of the error message
     *                  null = add no other params, 1 = add <type> 2 = add <multiValue>
     * @param usageContext value determines the context added to the end of the usage line
     *                     null = blank usage context, "actions" = add action list, "list" = add (/booster list) notice
     * @return the error message
     */
    public static String incorrectUsageMsg(@NotNull String usage, @NotNull int extraArgs, @Nullable String usageContext) {
        StringBuilder errorBuilder = new StringBuilder();
        String usageHolder;
        String usageContextHolder;
        boolean exclamationUse;

        /*
        FORMAT:
        (Usage: <usageHolder>) <usageContextHolder><"!" - depending on exclamationUse value>
        EXAMPLE:
        (Usage: set <type>) Use /booster list for the list of booster types!
         */
        switch (usage) {
            case "action":
                usageHolder = "<action>";
                usageContextHolder = actionList;
                exclamationUse = false;
                break;
            case "set":
                // set usage args --> (Usage: <usage args>) xxx
                switch (extraArgs) {
                    case 1:
                        usageHolder = "set <type>";
                        break;
                    case 2:
                        usageHolder = "set <type> <multiValue>";
                        break;
                    default:
                        usageHolder = "set";
                }
                // set usage context --> (Usage: xxx) <Usage context>
                if (usageContext.equals("list")) {
                    usageContextHolder = listNotice;
                    exclamationUse = true;
                } else {
                    usageContextHolder = "";
                    exclamationUse = false;
                }
                break;
            case "remove":
                // set usage args --> (Usage: <usage args>) xxx
                if (extraArgs == 1) {
                    usageHolder = "remove <type>";
                } else {
                    usageHolder = "remove";
                }
                // set usage context --> (Usage: xxx) <Usage context>
                if (usageContext.equals("list")) {
                    usageContextHolder = listNotice;
                    exclamationUse = true;
                } else {
                    usageContextHolder = "";
                    exclamationUse = false;
                }
                break;
            case "list":
                usageHolder = "list";
                usageContextHolder = "";
                exclamationUse = false;
                break;
            case "info":
                usageHolder = "info";
                usageContextHolder = "";
                exclamationUse = false;
                break;
            default:
                usageHolder = "Unknown usage given.";
                usageContextHolder = "";
                exclamationUse = false;
        }
        String usageMsg = ChatColor.GRAY + "(Usage: /booster " + usageHolder + ") " + usageContextHolder;
        errorBuilder.append(usageMsg);
        if (exclamationUse) {
            errorBuilder.append("!");
        }
        return errorBuilder.toString();
    }

    /**
     * @param newStatus determines the contents of the message depending on which status is relevant
     *                  - alreadyActive, - changed, - toggledActive
     * @param multiType if the multiplier type exists, it's capitalized and used at beginning of message
     * @param useDefault if true, the message will specify the multiplier value as default
     * @return the new status message
     */
    public static String setMultiplierMsg(String multiType, String newStatus, boolean useDefault) {
        String multiTypeHolder;
        String newStatusHolder;
        String multiValueHolder;

        if (multiType.equals("exp")) {
            multiTypeHolder = multiType.substring(0, 1).toUpperCase() + multiType.substring(1);
            if (useDefault) {
                // multiValueHolder = ChatColor.AQUA + "default" + plugin.getDefaultMultiValue() + "x";
                multiValueHolder = "";
                // ^ v temporary so I don't get an error with initialization
            } else {
                // multiValueHolder = ChatColor.AQUA + plugin.getExpMultiValue() + "x";
                multiValueHolder = "";
            }
        } else {
            multiTypeHolder = "Unknown multiType given.";
            multiValueHolder = "Unable to find multiValue (Unknown multiType given).";
        }
        /*
        FORMAT:
        <multiTypeHolder> multiplier <newStatusHolder> <multiValueHolder> <"!" - depending on exclamationUse value>
        EXAMPLE:
        Exp multiplier is already active at default 2x!
        */
        switch (newStatus) {
            case "alreadyActive":
                newStatusHolder = alreadyActive;
                break;
            case "changed":
                newStatusHolder = changed;
                break;
            case "toggledActive":
                newStatusHolder = toggledActive;
                break;
            default:
                newStatusHolder = "Unknown status given.";


        }
        return ChatColor.DARK_AQUA + multiTypeHolder + " multiplier" + newStatusHolder + multiValueHolder + ChatColor.DARK_AQUA + "!";
    }
}
