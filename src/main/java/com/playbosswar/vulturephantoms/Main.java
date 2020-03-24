package com.playbosswar.vulturephantoms;

import com.playbosswar.vulturephantoms.commands.VultureCommand;
import com.playbosswar.vulturephantoms.listeners.PlayerDeath;
import com.playbosswar.vulturephantoms.listeners.TakeFire;
import com.playbosswar.vulturephantoms.listeners.VultureDamage;
import com.playbosswar.vulturephantoms.listeners.VultureDeath;
import com.playbosswar.vulturephantoms.utilities.PhantomDespawner;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {
    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new TakeFire(), this);
        getServer().getPluginManager().registerEvents(new VultureDeath(), this);
        getServer().getPluginManager().registerEvents(new VultureDamage(), this);
        Objects.requireNonNull(getCommand("vulturephantoms")).setExecutor(new VultureCommand());
        Bukkit.getConsoleSender().sendMessage("VulturePhantoms v1.1 has been enabled");
    }

    @Override
    public void onDisable() {
        PhantomDespawner.removeVulturePhantoms();
        plugin = null;
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
