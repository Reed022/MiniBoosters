package me.reed.miniboosters.listeners;

import me.reed.miniboosters.MiniBoosters;
import me.reed.miniboosters.utilities.MessageUtils;
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
            if (plugin.isExpMultiEnabled() && !exp.hasMetadata("XPDoubled")) {
                exp.setExperience(exp.getExperience() * plugin.getExpMultiValue());
                exp.setMetadata("XPDoubled", new FixedMetadataValue(plugin, true));
            }
            // Exp pickup tracking controller
            if (plugin.playerExpAlertEnabled(event.getTarget().getUniqueId())) {
                event.getTarget().sendMessage(MessageUtils.expAlertMsg(exp.getExperience()));
            }
        }

    }
}
