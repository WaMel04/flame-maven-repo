package com.wamel.pvpbugfixer;

import com.wamel.pvpbugfixer.listener.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class PVPBugFixer extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GREEN + "플러그인이 활성화되었습니다. by WaMel_");
        registerEvents();
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.RED + "플러그인이 비활성화되었습니다. by WaMel_");
    }

    public static PVPBugFixer getInstance() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("PVPBugFixer");
        if (plugin == null)
            return null;
        if (!(plugin instanceof PVPBugFixer))
            return null;
        return (PVPBugFixer) plugin;
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

}
