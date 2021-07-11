package me.reed.miniboosters;

import me.reed.miniboosters.commands.Booster;
import me.reed.miniboosters.commands.ExpTracker;
import me.reed.miniboosters.events.Experience;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class MiniBoosters extends JavaPlugin {

    private Map<UUID, Boolean> toggles = new HashMap<>();
    private boolean expMultiToggle;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Experience(this), this);
        this.getCommand("booster").setExecutor(new Booster(this));
        this.getCommand("exptracker").setExecutor(new ExpTracker(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // For exp doubling
    public boolean getExpMultiToggled() {
        return expMultiToggle;
    }

    public void setExpMultiToggled(Boolean toggled) {
        expMultiToggle = toggled;
    }

    // For exp tracking
    public boolean getExpTrackerToggled(UUID uuid) {
        return toggles.getOrDefault(uuid, false);
    }

    public void setExpTrackerToggled(UUID uuid, Boolean toggled) {
        toggles.put(uuid, toggled);
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
}
