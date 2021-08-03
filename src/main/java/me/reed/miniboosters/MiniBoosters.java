package me.reed.miniboosters;

import me.reed.miniboosters.commands.Booster;
import me.reed.miniboosters.listeners.Experience;
import me.reed.miniboosters.listeners.MobKill;
import me.reed.miniboosters.listeners.PlayerQuit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class MiniBoosters extends JavaPlugin {

    private Map<UUID, Boolean> toggles = new HashMap<>();
    private final int defaultMultiValue = 2;
    private boolean expMultiToggle;
    private int expMultiValue;
    private boolean mobMultiToggle;
    private int mobMultiValue;
    private boolean animalMultiToggle;
    private int animalMultiValue;
    private boolean bossMultiToggle;
    private int bossMultiValue;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Experience(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
        getServer().getPluginManager().registerEvents(new MobKill(this), this);
        this.getCommand("booster").setExecutor(new Booster(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // For exp alert
    public boolean playerExpAlertEnabled(UUID uuid) {
        return toggles.getOrDefault(uuid, false);
    }

    public void setPlayerExpAlertEnabled(UUID uuid, Boolean toggled) {
        toggles.put(uuid, toggled);
    }

    public void removePlayerExpAlertEntry(UUID uuid) {
        toggles.remove(uuid);
    }

    // Booster states
    public int getDefaultMultiValue() {
        return defaultMultiValue;
    }

    public boolean isExpMultiEnabled() {
        return expMultiToggle;
    }
    public void setExpMultiEnabled(Boolean toggled) {
        expMultiToggle = toggled;
    }
    public int getExpMultiValue() {
        return expMultiValue;
    }
    public void setExpMultiValue(int v) {
        expMultiValue = v;
    }

    public boolean isMobMultiEnabled() {
        return mobMultiToggle;
    }
    public void setMobMultiEnabled(Boolean toggled) {
        mobMultiToggle = toggled;
    }
    public int getMobMultiValue() {
        return mobMultiValue;
    }
    public void setMobMultiValue(int v) {
        mobMultiValue = v;
    }

    public boolean isAnimalMultiEnabled() {
        return animalMultiToggle;
    }
    public void setAnimalMultiEnabled(Boolean toggled) {
        animalMultiToggle = toggled;
    }
    public int getAnimalMultiValue() {
        return animalMultiValue;
    }
    public void setAnimalMultiValue(int v) {
        animalMultiValue = v;
    }

    public boolean isBossMultiEnabled() {
        return bossMultiToggle;
    }
    public void setBossMultiEnabled(Boolean toggled) {
        bossMultiToggle = toggled;
    }
    public int getBossMultiValue() {
        return bossMultiValue;
    }
    public void setBossMultiValue(int v) {
        bossMultiValue = v;
    }

    public String getActiveBoosters() {
        StringBuilder activeBoosters = new StringBuilder();
        if (expMultiToggle) {
            String exp = ChatColor.AQUA + "exp " + ChatColor.GREEN + "(" + expMultiValue + ")";
            activeBoosters.append(exp);
            if (mobMultiToggle || animalMultiToggle || bossMultiToggle) {
                activeBoosters.append(", ");
            }
        }
        if (mobMultiToggle) {
            String mobs = ChatColor.AQUA + "mob drops " + ChatColor.GREEN + "(" + mobMultiValue + ")";
            activeBoosters.append(mobs);
            if (animalMultiToggle || bossMultiToggle) {
                activeBoosters.append(", ");
            }
        }
        if (animalMultiToggle) {
            String animals = ChatColor.AQUA + "animal drops " + ChatColor.GREEN + "(" + animalMultiValue + ")";
            activeBoosters.append(animals);
            if (bossMultiToggle) {
                activeBoosters.append(", ");
            }
        }
        if (bossMultiToggle) {
            String bosses = ChatColor.AQUA + "boss drops " + ChatColor.GREEN + "(" + bossMultiValue + ")";
            activeBoosters.append(bosses);
        }
        if (!expMultiToggle && !mobMultiToggle && !animalMultiToggle && !bossMultiToggle) {
            String none = ChatColor.GRAY + "none";
            activeBoosters.append(none);
        }
        return activeBoosters.toString();
    }
}
