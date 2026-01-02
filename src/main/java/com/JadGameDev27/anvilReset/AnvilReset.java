package com.JadGameDev27.anvilReset;

import org.bukkit.plugin.java.JavaPlugin;
import com.JadGameDev27.anvilReset.commands.AnvilResetItem;

public final class AnvilReset extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        this.getCommand("anvilresetitem").setExecutor(new AnvilResetItem(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
