package com.playbosswar.vulturephantoms.utilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ClearSkyCheck {
    public static boolean isSkyClear(Player p) {
        Location loc = p.getLocation();
        double yLocation = loc.getY();
        int relativeSpawnHeight = 10;

        for(double i = yLocation; i < yLocation + relativeSpawnHeight; i++) {
            Location blockLocation = new Location(loc.getWorld(), loc.getX(), i, loc.getZ());
            Block b = Objects.requireNonNull(loc.getWorld()).getBlockAt(blockLocation);
            if(b.getType() != Material.AIR) {
                return false;
            }
        }
        return true;
    }
}
