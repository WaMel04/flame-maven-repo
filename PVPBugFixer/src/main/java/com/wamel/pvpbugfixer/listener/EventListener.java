package com.wamel.pvpbugfixer.listener;

import com.wamel.pvpbugfixer.PVPBugFixer;
import net.minecraft.server.v1_5_R3.MathHelper;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class EventListener implements Listener {

    private PVPBugFixer plugin = PVPBugFixer.getInstance();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.isCancelled())
            return;
        if(!(event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)))
            return;
        if(!(event.getDamager() instanceof Player && event.getEntity() instanceof Player))
            return;
        Player attacker = (Player) event.getDamager();
        Player victim = (Player) event.getEntity();
        float yaw = attacker.getLocation().getYaw();

        if(victim.getNoDamageTicks() > 0)
            return;

        int kLevel = 1;
        kLevel += attacker.getItemInHand().getEnchantmentLevel(Enchantment.KNOCKBACK);

        Vector vec = new Vector((-MathHelper.sin(yaw * 3.1415927F / 180.0F)) * kLevel * 0.5F, 0.4D, (MathHelper.cos(yaw * 3.1415927F / 180.0F) * kLevel * 0.5F));
        new BukkitRunnable() {

            @Override
            public void run() {
                victim.setVelocity(vec);
            }

        }.runTask(plugin);
    }

}
