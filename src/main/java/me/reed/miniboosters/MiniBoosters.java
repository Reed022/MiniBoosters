package me.reed.miniboosters;

import me.reed.miniboosters.commands.DoubleExp;
import me.reed.miniboosters.commands.ExpTracking;
import me.reed.miniboosters.events.Experience;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class MiniBoosters extends JavaPlugin {

    private Map<UUID, Boolean> toggles = new HashMap<>();
    private boolean doubleExpStatus;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Experience(this), this);
        this.getCommand("doubleexp").setExecutor(new DoubleExp(this));
        this.getCommand("exptracker").setExecutor(new ExpTracking(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // For exp doubling
    public boolean getDoubleToggled() {
        return doubleExpStatus;
    }

    public void setDoubleToggled(Boolean toggled) {
        doubleExpStatus = toggled;
    }

    // For exp tracking
    public boolean getExpToggled(UUID uuid) {
        return toggles.getOrDefault(uuid, false);
    }

    public void setExpToggled(UUID uuid, Boolean toggled) {
        toggles.put(uuid, toggled);
    }
}
