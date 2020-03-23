package com.playbosswar.vulturephantoms.utilities;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorCalculation {
    public static Location getLocAroundCircle(Location center, double radius, double angleRad) {
        double x = center.getX() + radius * Math.cos(angleRad);
        double z = center.getZ() + radius * Math.sin(angleRad);
        double y = center.getY();

        Location loc = new Location(center.getWorld(), x, y, z);
        Vector difference = center.toVector().clone().subtract(loc.toVector());
        Vector lookDir = new Vector(difference.getBlockZ(), 0.0, -difference.getBlockX());
        loc.setDirection(lookDir);

        return loc;
    }
}
