package com.wamel.headequip.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Event extends JavaPlugin implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(isEquipable(event.getItemInHand()))
            return;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.isCancelled())
            return;
        if(!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return;
        if(!(isEquipable(event.getItem())))
            return;

        Player player = event.getPlayer();

        if(player.getEquipment().getHelmet().getType().equals(Material.AIR)) {
            player.getEquipment().setHelmet(player.getItemInHand().clone());
            player.getItemInHand().setType(Material.AIR);
        }
    }

    private Boolean isEquipable(ItemStack item) {
        if(item.getItemMeta().getLore() == null)
            return false;

        List<String> lores = item.getItemMeta().getLore();
        if(lores.contains("장착 가능")) {
            return true;
        } else {
            return false;
        }
    }

}
