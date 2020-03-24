package com.playbosswar.vulturephantoms.utilities;

import com.playbosswar.vulturephantoms.Main;
import com.playbosswar.vulturephantoms.VultureManager;
import org.bukkit.World;
import org.bukkit.entity.Phantom;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.UUID;

public class Vulture {
    private static Plugin plugin = Main.getPlugin();

    private UUID uuid;
    private ArrayList<ItemStack> drops;
    private Phantom phantom;
    private int exp;

    public Vulture(UUID uuid, ArrayList<ItemStack> drops, Phantom phantom, int exp) {
        this.uuid = uuid;
        this.drops = drops;
        this.phantom = phantom;
        this.exp = exp;
        VultureManager.addVulture(this);
    }

    public void despawnVulture() {
        World phantomWorld = this.phantom.getWorld();
        if (plugin.getConfig().getBoolean("dropItemsOnReload")) {
            for (ItemStack i : this.drops) {
                phantomWorld.dropItem(this.phantom.getLocation(), i);
            }
        }
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

    public int getExp() {
        return this.exp;
    }
}
