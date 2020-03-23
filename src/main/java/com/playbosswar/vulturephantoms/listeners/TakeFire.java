package com.playbosswar.vulturephantoms.listeners;

import com.playbosswar.vulturephantoms.VultureManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;

public class TakeFire implements Listener {
    @EventHandler
    public void onEntityCombust(EntityCombustEvent e) {
        Entity entity = e.getEntity();
        if(entity instanceof Phantom) {
            if(VultureManager.getVulture((Phantom) entity) != null) {
                e.setCancelled(true);
            }
        }
    }
}
