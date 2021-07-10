package me.reed.miniboosters;

import me.reed.miniboosters.commands.DoubleExp;
import me.reed.miniboosters.commands.ExpTracking;
import me.reed.miniboosters.events.ExpMultiplier;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiniBoosters extends JavaPlugin {

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
}
