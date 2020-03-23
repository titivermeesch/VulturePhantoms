package com.playbosswar.vulturephantoms;

import com.playbosswar.vulturephantoms.utilities.PhantomDespawner;
import com.playbosswar.vulturephantoms.utilities.Vulture;
import org.bukkit.entity.Phantom;

import java.util.ArrayList;
import java.util.UUID;

public class VultureManager {
    private static ArrayList<Vulture> vultures = new ArrayList<>();

    public static void addVulture(Vulture v) {
        vultures.add(v);
    }

    public static Vulture getVulture(Phantom p) {
        for(Vulture vulture : vultures) {
            if(vulture.getPhantom().equals(p)) {
                return vulture;
            }
        }
        return null;
    }

    public static int getVulturesSize() {
        return vultures.size();
    }

    public static void removeVulture(int index) {
        vultures.get(index).despawnVulture();
        vultures.remove(index);
    }

    public static void removeVulture(UUID uuid) {
        for (Vulture vulture : vultures) {
            if (vulture.getUUID().equals(uuid)) {
                vulture.despawnVulture();
                vultures.remove(vulture);
                return;
            }
        }
    }

    public static ArrayList<Vulture> getAllVultures() {
        return vultures;
    }

    public static void clearVultures() {
        vultures.clear();
        PhantomDespawner.removeVulturePhantoms();
    }
}
