package com.wamel.headequip.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Event extends JavaPlugin implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return;
        if(event.getItem() == null)
            return;
        if(!(isEquipable(event.getItem())))
            return;

        event.setCancelled(true);
        Player player = event.getPlayer();

        if(player.getEquipment().getHelmet() == null) {
            player.getEquipment().setHelmet(player.getItemInHand().clone());
            player.setItemInHand(new ItemStack(Material.AIR));
            player.updateInventory();
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.isCancelled())
            return;
        if(event.getClick().equals(ClickType.NUMBER_KEY)) {
            int slot = event.getHotbarButton();
            Inventory inv = event.getWhoClicked().getInventory();

            if(event.getRawSlot() != 5)
                return;
            if(inv.getItem(slot) == null)
                return;
            if(!(isEquipable(inv.getItem(slot))))
                return;

            event.setCancelled(true);
            ItemStack changeItem = inv.getItem(slot).clone();

            if(event.getCurrentItem() == null) {
                event.setCurrentItem(changeItem);
                inv.setItem(slot, new ItemStack(Material.AIR));
            } else {
                inv.setItem(slot, event.getCurrentItem());
                event.setCurrentItem(changeItem);
            }

            ((Player) event.getWhoClicked()).updateInventory();
        } else if(event.getClick().equals(ClickType.LEFT) || event.getClick().equals(ClickType.RIGHT)) {
            if(event.getRawSlot() != 5)
                return;
            if(event.getCursor().getType().equals(Material.AIR))
                return;
            if(!(isEquipable(event.getCursor())))
                return;

            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack changeItem = event.getCursor().clone();

            if(player.getEquipment().getHelmet() == null) {
                player.getEquipment().setHelmet(changeItem);
                event.setCursor(new ItemStack(Material.AIR));
                player.updateInventory();
            } else {
                event.setCursor(player.getEquipment().getHelmet());
                player.getEquipment().setHelmet(changeItem);
                player.updateInventory();
            }
        } else if(event.getClick().equals(ClickType.SHIFT_LEFT) || event.getClick().equals(ClickType.SHIFT_RIGHT)) {
            if(event.getRawSlot() == 5)
                return;
            if(event.getCurrentItem() == null)
                return;
            if(!(isEquipable(event.getCurrentItem())))
                return;

            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();

            if(player.getEquipment().getHelmet() == null) {
                player.getEquipment().setHelmet(event.getCurrentItem().clone());
                event.setCurrentItem(new ItemStack(Material.AIR));
                player.updateInventory();
            }
        }
    }

    private Boolean isEquipable(ItemStack item) {
        if(item.getItemMeta().getLore() == null)
            return false;

        List<String> lores = item.getItemMeta().getLore();
        if(lores.contains("§7장착 가능")) {
            return true;
        } else {
            return false;
        }
    }

}
