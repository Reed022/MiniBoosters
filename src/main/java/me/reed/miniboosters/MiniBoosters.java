package me.reed.miniboosters;

import me.reed.miniboosters.commands.DoubleExp;
import me.reed.miniboosters.commands.ExpTracking;
import me.reed.miniboosters.events.ExpMultiplier;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class MiniBoosters extends JavaPlugin {

    private Map<UUID, Boolean> toggles = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ExpMultiplier(this), this);
        this.getCommand("doubleexp").setExecutor(new DoubleExp(this));
        this.getCommand("exptracker").setExecutor(new ExpTracking(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // For exp tracking
    public boolean isToggled(UUID uuid) {
        return toggles.getOrDefault(uuid, false);
    }

    public void setToggled(UUID uuid, Boolean toggled) {
        toggles.put(uuid, toggled);
    }
}
