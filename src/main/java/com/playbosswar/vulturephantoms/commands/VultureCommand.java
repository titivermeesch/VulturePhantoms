package com.playbosswar.vulturephantoms.commands;

import com.playbosswar.vulturephantoms.Main;
import com.playbosswar.vulturephantoms.utilities.PhantomDespawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VultureCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!sender.hasPermission("vulturephantoms.use")) {
            sender.sendMessage("§cYou lack the permission vulturephantoms.use");
        }
        if(args.length == 0) {
            sender.sendMessage("§3VulturePhantoms v1.1");
            sender.sendMessage("§3Use /vp reload to reload the config file.");
            sender.sendMessage("§3Use /vp clear to clear all the vulture phantoms");
            return true;
        }
        if(args.length == 1) {
            String argument = args[0];
            if(argument.equalsIgnoreCase("reload")) {
                PhantomDespawner.removeVulturePhantoms();
                Main.getPlugin().reloadConfig();
                sender.sendMessage("§2VulturePhantoms reloaded");
                return true;
            }
            if(argument.equalsIgnoreCase("clear")) {
                PhantomDespawner.removeVulturePhantoms();
                sender.sendMessage("§2Cleared all Vulture Phantoms");
                return true;
            }
        }
        return false;
    }
}
