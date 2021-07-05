package me.reed.reedsplugin.events;

import me.reed.reedsplugin.commands.DoubleExp;
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

        if (e.getType() == EntityType.EXPERIENCE_ORB && DoubleExp.getDoubleExpStatus()) {
            ExperienceOrb exp = (ExperienceOrb) e;
            exp.setExperience(exp.getExperience() * 2);
            DoubleExp.getSender().sendMessage(ChatColor.AQUA + "Experience of Target Orb: " + ChatColor.GREEN + exp.getExperience());
        } else if (e.getType() == EntityType.EXPERIENCE_ORB && !DoubleExp.getDoubleExpStatus()) {
            ExperienceOrb exp = (ExperienceOrb) e;
            DoubleExp.getSender().sendMessage(ChatColor.AQUA + "Experience of Target Orb: " + ChatColor.GREEN + exp.getExperience());
        }

    }
}
