package com.caiomgt.sabotage;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GameManager {
    public boolean gameStarted = false;
    public JavaPlugin plugin;
    //Player lists
    public List<Player> sabs;
    public List<Player> innos;
    public List<Player> dets;
    public GameManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public boolean Start(World world) {
        if (world.getPlayerCount() >= 2) {
            List<Player> plrs = world.getPlayers();
            //remove force-picked players from generating roles
            plrs.removeAll(sabs);
            plrs.removeAll(innos);
            plrs.removeAll(dets);
            Collections.shuffle(plrs);
            int sabCount = plrs.size() / 3;
            int detCount = plrs.size() / 8;
            for (Player plr : plrs) {
                if (detCount >= 1) {
                    detCount--;
                    AddDet(plr);
                } else if (sabCount >= 1) {
                    sabCount--;
                    AddSab(plr);
                } else {
                    AddInno(plr);
                }
            }
            gameStarted = true;
            return true;
        }
        return false;
    }

    public boolean AddSab(Player plr) {
        sabs.add(plr);
        return true;
    }
    public boolean AddInno(Player plr) {
        innos.add(plr);
        return true;
    }
    public boolean AddDet(Player plr) {
        if (plr.getWorld().getPlayerCount() <= 8) {
            return false;
        }
        dets.add(plr);
        return true;
    }
}
