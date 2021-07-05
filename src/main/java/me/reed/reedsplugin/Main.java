package me.reed.reedsplugin;

import me.reed.reedsplugin.commands.DoubleExp;
import me.reed.reedsplugin.events.ExpMultiplier;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ExpMultiplier(), this);
        this.getCommand("doubleexp").setExecutor(new DoubleExp(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
