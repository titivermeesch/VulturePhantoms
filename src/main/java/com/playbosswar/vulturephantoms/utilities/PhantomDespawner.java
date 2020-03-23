package com.playbosswar.vulturephantoms.utilities;

import com.playbosswar.vulturephantoms.VultureManager;

public class PhantomDespawner {
    public static void removeVulturePhantoms() {
        for(Vulture vulture : VultureManager.getAllVultures()) {
            vulture.despawnVulture();
        }
    }
}
