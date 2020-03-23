package com.playbosswar.vulturephantoms.listeners;

import com.playbosswar.vulturephantoms.Main;
import com.playbosswar.vulturephantoms.VultureManager;
import com.playbosswar.vulturephantoms.utilities.ClearSkyCheck;
import com.playbosswar.vulturephantoms.utilities.VectorCalculation;
import com.playbosswar.vulturephantoms.utilities.Vulture;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerDeath implements Listener {
    private static Plugin plugin = Main.getPlugin();
    private static double radius = plugin.getConfig().getDouble("circleRadius");

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        int exp = p.getTotalExperience();
        ArrayList<ItemStack> playerDrops = new ArrayList<>(e.getDrops());
        Location phantomLocation = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + plugin.getConfig().getInt("spawnHeight"), p.getLocation().getZ());

        if (!ClearSkyCheck.isSkyClear(p) || p.hasPermission("vulturephantoms.exclude")) {
            return;
        }

        if (plugin.getConfig().getStringList("disabledWorlds").contains(p.getWorld().getName())) {
            return;
        }

        int totalPhantoms = VultureManager.getVulturesSize();
        int phantomLimit = plugin.getConfig().getInt("spawnLimit");
        boolean limitReached = totalPhantoms >= phantomLimit;

        if (phantomLimit > 0 && limitReached) {
            String clearType = plugin.getConfig().getString("limitHandler");
            assert clearType != null;
            if (clearType.equalsIgnoreCase("spawn-none")) {
                return;
            }

            if (clearType.equalsIgnoreCase("remove-all")) {
                VultureManager.clearVultures();
            }

            if (clearType.equalsIgnoreCase("remove-1")) {
                VultureManager.removeVulture(0);
            }
        }

        UUID uuid = UUID.randomUUID();
        Location spawnLoc = VectorCalculation.getLocAroundCircle(phantomLocation, radius, 0.0);
        Phantom phantom = (Phantom) p.getWorld().spawnEntity(spawnLoc, EntityType.PHANTOM);
        phantom.setAI(false);
        phantom.setInvulnerable(true);
        phantom.setRemoveWhenFarAway(false);
        Vulture vulture = new Vulture(uuid, playerDrops, phantom, exp);
        if (plugin.getConfig().getBoolean("vultureStealDrops")) {
            e.getDrops().clear();
            e.setDroppedExp(0);
            vulture.getPhantom().setInvulnerable(false);
        }

        AtomicInteger tick = new AtomicInteger();
        int timeUntilDespawn = plugin.getConfig().getInt("despawnAfter") * 20;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            double angle = 2 * Math.PI / 4 * tick.get() / 20;
            Location loc = VectorCalculation.getLocAroundCircle(phantomLocation, radius, angle);
            vulture.getPhantom().teleport(loc);
            tick.getAndIncrement();
            if (tick.get() >= timeUntilDespawn) {
                VultureManager.removeVulture(uuid);
            }
        }, 0L, 1L);
    }
}

