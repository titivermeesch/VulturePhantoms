package com.playbosswar.vulturephantoms.utilities;

import com.playbosswar.vulturephantoms.VultureManager;
import org.bukkit.entity.Phantom;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class Vulture {
    private UUID uuid;
    private ArrayList<ItemStack> drops;
    private Phantom phantom;

    public Vulture(UUID uuid, ArrayList<ItemStack> drops, Phantom phantom) {
        this.uuid = uuid;
        this.drops = drops;
        this.phantom = phantom;
        VultureManager.addVulture(this);
    }

    public void despawnVulture() {
        this.phantom.remove();
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public ArrayList<ItemStack> getDrops() {
        return this.drops;
    }

    public Phantom getPhantom() {
        return this.phantom;
    }
}
