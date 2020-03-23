package com.playbosswar.vulturephantoms.listeners;

import com.playbosswar.vulturephantoms.VultureManager;
import com.playbosswar.vulturephantoms.utilities.Vulture;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class VultureDeath implements Listener {
    @EventHandler
    public void onVultureDeath(EntityDeathEvent e) {
        if(e.getEntity().getKiller() == null || !(e.getEntity() instanceof Phantom)) {
            return;
        }

        Vulture vulture = VultureManager.getVulture((Phantom) e.getEntity());
        assert vulture != null;
        e.getDrops().addAll(vulture.getDrops());
        e.setDroppedExp(vulture.getExp());
    }
}
