package me.reed.miniboosters.events;

import me.reed.miniboosters.MiniBoosters;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private final MiniBoosters plugin;

    public PlayerQuit(MiniBoosters plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Cleanup system for hashmap toggles
        Player player = event.getPlayer();
        plugin.removePlayerExpAlertEntry(player.getUniqueId());
    }
}
