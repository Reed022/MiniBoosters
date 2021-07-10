package me.reed.miniboosters.events;

import me.reed.miniboosters.MiniBoosters;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class Experience implements Listener {

    private final MiniBoosters plugin;
    public Experience(MiniBoosters plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onExperienceDrop(EntityTargetEvent event) {
        Entity e = event.getEntity();

        if (e.getType() == EntityType.EXPERIENCE_ORB && event.getTarget() instanceof Player) {
            ExperienceOrb exp = (ExperienceOrb) e;
            // Exp doubling controller
            if (plugin.getDoubleToggled() && !exp.hasMetadata("XPDoubled")) {
                exp.setExperience(exp.getExperience() * 2);
                exp.setMetadata("XPDoubled", new FixedMetadataValue(plugin, true));
            }
            // Exp pickup tracking controller
            if (plugin.getExpToggled(event.getTarget().getUniqueId())) {
                event.getTarget().sendMessage(ChatColor.AQUA + "Experience of Target Orb: " + ChatColor.GREEN + exp.getExperience());
            }
        }

    }
}
