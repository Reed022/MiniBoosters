package me.reed.reedsplugin.events;

import me.reed.reedsplugin.commands.DoubleExp;
import me.reed.reedsplugin.commands.ExpTracking;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class ExpMultiplier implements Listener {

    @EventHandler
    public void onExperienceDrop(EntityTargetEvent event) {
        Entity e = event.getEntity();

        if (e.getType() == EntityType.EXPERIENCE_ORB) {
            ExperienceOrb exp = (ExperienceOrb) e;
            // Exp doubling controller
            if (DoubleExp.getDoubleExpStatus()) {
                exp.setExperience(exp.getExperience() * 2);
            }
            // Exp pickup tracking controller
            if (ExpTracking.getExpTrackingStatus()) {
                DoubleExp.getSender().sendMessage(ChatColor.AQUA + "Experience of Target Orb: " + ChatColor.GREEN + exp.getExperience());
            }
        }

    }
}
