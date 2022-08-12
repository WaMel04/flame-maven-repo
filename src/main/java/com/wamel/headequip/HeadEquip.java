package com.wamel.headequip;

import com.wamel.headequip.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeadEquip extends JavaPlugin {

    private static HeadEquip instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new Event(), instance);
        getLogger().info("§a플러그인 활성화 by WaMel_");
    }

    @Override
    public void onDisable() {
        getLogger().info("§c플러그인 비활성화 by WaMel_");
    }

    public static HeadEquip getInstance() {
        return instance;
    }

}
