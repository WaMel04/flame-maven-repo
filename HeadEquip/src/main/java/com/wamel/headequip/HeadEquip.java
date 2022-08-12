package com.wamel.headequip;

import com.wamel.headequip.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeadEquip extends JavaPlugin {

    private static HeadEquip instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new Event(), instance);
    }

    @Override
    public void onDisable() {
    }

    public static HeadEquip getInstance() {
        return instance;
    }

}
