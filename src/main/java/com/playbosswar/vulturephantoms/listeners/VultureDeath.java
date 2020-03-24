package com.playbosswar.vulturephantoms.listeners;

import com.playbosswar.vulturephantoms.Main;
import com.playbosswar.vulturephantoms.VultureManager;
import com.playbosswar.vulturephantoms.utilities.Vulture;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

public class VultureDeath implements Listener {
    private static Plugin plugin = Main.getPlugin();

    @EventHandler
    public void onVultureDeath(EntityDeathEvent e) {
        Vulture vulture = VultureManager.getVulture((Phantom) e.getEntity());
        assert vulture != null;

        if(e.getEntity().getKiller() == null || !(e.getEntity() instanceof Phantom)) {
            if(plugin.getConfig().getBoolean("dropItemsAfterDespawn")) {
                e.getDrops().addAll(vulture.getDrops());
                e.setDroppedExp(vulture.getExp());
            }
            return;
        }

        e.getDrops().addAll(vulture.getDrops());
        e.setDroppedExp(vulture.getExp());
    }
}
