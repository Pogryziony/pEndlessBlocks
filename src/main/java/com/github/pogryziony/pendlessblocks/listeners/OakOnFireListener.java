package com.github.pogryziony.pendlessblocks.listeners;

import com.github.pogryziony.pendlessblocks.PEndlessBlocks;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class OakOnFireListener implements Listener {
    private PEndlessBlocks plugin;

    public OakOnFireListener(PEndlessBlocks plugin){this.plugin = plugin;}

    @EventHandler
    public void onFire(BlockBurnEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onFirePlace(BlockPlaceEvent event){
        if(event.getBlock().getType() == Material.FIRE && event.getPlayer().getGameMode() == GameMode.SURVIVAL)
            killFire(event.getBlock());
    }

    public void killFire(final Block b){
        new BukkitRunnable(){
            public void run(){
                b.setType(Material.AIR);
            }
        }.runTaskLater(plugin, 100);
    }
}
