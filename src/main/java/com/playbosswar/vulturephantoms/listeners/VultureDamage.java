package com.playbosswar.vulturephantoms.listeners;

import com.playbosswar.vulturephantoms.Main;
import com.playbosswar.vulturephantoms.VultureManager;
import com.playbosswar.vulturephantoms.utilities.Vulture;
import org.bukkit.GameMode;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class VultureDamage implements Listener {
    private static Plugin plugin = Main.getPlugin();

    @EventHandler
    public void onVultureDamage(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Phantom)) {
            return;
        }

        Player p = (Player) e.getDamager();
        Vulture vulture = VultureManager.getVulture((Phantom) e.getEntity());
        assert vulture != null;

        if (plugin.getConfig().getBoolean("preventCreativeKill") && p.getGameMode() == GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }
}
