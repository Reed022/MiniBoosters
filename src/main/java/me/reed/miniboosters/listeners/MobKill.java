package me.reed.miniboosters.listeners;

import me.reed.miniboosters.MiniBoosters;
import me.reed.miniboosters.utilities.MessageUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MobKill implements Listener {

    private final MiniBoosters plugin;
    private final EntityType[] passiveMobList = {EntityType.BAT, EntityType.BEE, EntityType.CAT, EntityType.CHICKEN,
            EntityType.COD, EntityType.COW, EntityType.DOLPHIN, EntityType.DONKEY, EntityType.FOX, EntityType.HORSE,
            EntityType.IRON_GOLEM, EntityType.LLAMA, EntityType.MUSHROOM_COW, EntityType.MULE, EntityType.OCELOT,
            EntityType.PANDA, EntityType.PARROT, EntityType.PIG, EntityType.RABBIT, EntityType.SALMON, EntityType.SHEEP,
            EntityType.SKELETON_HORSE, EntityType.SNOWMAN, EntityType.SQUID, EntityType.STRIDER, EntityType.TRADER_LLAMA,
            EntityType.TROPICAL_FISH, EntityType.TURTLE, EntityType.VILLAGER, EntityType.WANDERING_TRADER, EntityType.WOLF, EntityType.ZOMBIE_HORSE};

    private final EntityType[] hostileMobList = {EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.DROWNED,
            EntityType.ENDERMAN, EntityType.ENDERMITE, EntityType.EVOKER,
            EntityType.GHAST, EntityType.GUARDIAN, EntityType.HOGLIN, EntityType.HUSK, EntityType.MAGMA_CUBE, EntityType.PHANTOM,
            EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.PILLAGER, EntityType.POLAR_BEAR, EntityType.PUFFERFISH,
            EntityType.RAVAGER, EntityType.SHULKER, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.SPIDER,
            EntityType.STRAY, EntityType.VEX, EntityType.VINDICATOR, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOGLIN,
            EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIFIED_PIGLIN};

    private final EntityType[] bossList = {EntityType.ELDER_GUARDIAN, EntityType.ENDER_DRAGON, EntityType.WITHER};

    public MobKill(MiniBoosters plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void mobDies (EntityDeathEvent event) {
        Entity e = event.getEntity();
        Entity k = event.getEntity().getKiller();
        if (k != null) {
            final List<ItemStack> entityDrops = event.getDrops();
            if (isEntityPassive(e.getType())) {
                if (plugin.isAnimalDropMultiEnabled()) {
                    for (ItemStack i : entityDrops) {
                        i.setAmount(i.getAmount() * plugin.getAnimalDropMultiValue());
                        if (plugin.playerEntityDropAlertEnabled(k.getUniqueId())) {
                            k.sendMessage(MessageUtils.entityDropAlertMsg(i.getAmount() / plugin.getAnimalDropMultiValue(), i.getAmount(), i.getType()));
                        }
                    }
                    event.getDrops().addAll(entityDrops);
                } else {
                    for (ItemStack i : entityDrops) {
                        if (plugin.playerEntityDropAlertEnabled(k.getUniqueId())) {
                            k.sendMessage(MessageUtils.entityDropAlertMsg(i.getAmount(), i.getAmount(), i.getType()));
                        }
                    }
                }
            } else if (isEntityHostile(e.getType())) {
                if (plugin.isMobDropMultiEnabled()) {
                    for (ItemStack i : entityDrops) {
                        i.setAmount(i.getAmount() * plugin.getMobDropMultiValue());
                        if (plugin.playerEntityDropAlertEnabled(k.getUniqueId())) {
                            k.sendMessage(MessageUtils.entityDropAlertMsg(i.getAmount() / plugin.getMobDropMultiValue(), i.getAmount(), i.getType()));
                        }
                    }
                    event.getDrops().addAll(entityDrops);
                } else {
                    for (ItemStack i : entityDrops) {
                        if (plugin.playerEntityDropAlertEnabled(k.getUniqueId())) {
                            k.sendMessage(MessageUtils.entityDropAlertMsg(i.getAmount(), i.getAmount(), i.getType()));
                        }
                    }
                }
            } else if (isEntityBoss(e.getType())) {
                if (plugin.isBossDropMultiEnabled()) {
                    for (ItemStack i : entityDrops) {
                        i.setAmount(i.getAmount() * plugin.getBossDropMultiValue());
                        if (plugin.playerEntityDropAlertEnabled(k.getUniqueId())) {
                            k.sendMessage(MessageUtils.entityDropAlertMsg(i.getAmount() / plugin.getBossDropMultiValue(), i.getAmount(), i.getType()));
                        }
                    }
                    event.getDrops().addAll(entityDrops);
                } else {
                    for (ItemStack i : entityDrops) {
                        if (plugin.playerEntityDropAlertEnabled(k.getUniqueId())) {
                            k.sendMessage(MessageUtils.entityDropAlertMsg(i.getAmount(), i.getAmount(), i.getType()));
                        }
                    }
                }
            }
        }
    }

    public boolean isEntityPassive(EntityType e) {
        boolean isPassive = false;
        for (EntityType entityType : passiveMobList) {
            if (entityType == e) {
                isPassive = true;
                break;
            }
        }
        return isPassive;
    }

    public boolean isEntityHostile(EntityType e) {
        boolean isHostile = false;
        for (EntityType entityType : hostileMobList) {
            if (entityType == e) {
                isHostile = true;
                break;
            }
        }
        return isHostile;
    }

    public boolean isEntityBoss(EntityType e) {
        boolean isBoss = false;
        for (EntityType entityType : bossList) {
            if (entityType == e) {
                isBoss = true;
                break;
            }
        }
        return isBoss;
    }

}
