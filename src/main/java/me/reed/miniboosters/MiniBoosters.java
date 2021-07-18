package me.reed.miniboosters;

import me.reed.miniboosters.commands.Booster;
import me.reed.miniboosters.listeners.Experience;
import me.reed.miniboosters.listeners.PlayerQuit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class MiniBoosters extends JavaPlugin {

    private Map<UUID, Boolean> toggles = new HashMap<>();
    private final int defaultMultiValue = 2;
    private boolean expMultiToggle;
    private int expMultiValue;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Experience(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
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

    // For exp doubling
    public boolean isExpMultiEnabled() {
        return expMultiToggle;
    }

    public void setExpMultiEnabled(Boolean toggled) {
        expMultiToggle = toggled;
    }

    public String getActiveBoosters() {
        StringBuilder activeBoosters = new StringBuilder();
        if (expMultiToggle) {
            activeBoosters.append("exp");
            // when there are more booster types, add if within here to append a comma
        }
        if (!expMultiToggle) {
            activeBoosters.append("none");
        }
        return activeBoosters.toString();
    }

    public int getDefaultMultiValue() {
        return defaultMultiValue;
    }

    public int getExpMultiValue() {
        return expMultiValue;
    }

    public void setExpMultiValue(int d) {
        expMultiValue = d;
    }
}
