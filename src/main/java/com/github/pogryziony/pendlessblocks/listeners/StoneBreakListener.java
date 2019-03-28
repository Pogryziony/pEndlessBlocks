package com.github.pogryziony.pendlessblocks.listeners;

import com.github.pogryziony.pendlessblocks.PEndlessBlocks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class StoneBreakListener
        implements Listener
{
    private PEndlessBlocks plugin;

    public StoneBreakListener(PEndlessBlocks plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onStoneBreak(BlockBreakEvent event)
    {
        Player p = event.getPlayer();
        if (this.plugin.getStoneManager().isStone(event.getBlock().getLocation())) {
            if (event.getPlayer().getItemInHand().getType().equals(Material.GOLD_AXE))
            {
                this.plugin.getStoneManager().removeStone(event.getBlock().getLocation());
                event.getBlock().setType(Material.AIR);
                p.sendMessage(ChatColor.GOLD.toString() + "Zniszczono generator kamienia");
            }
            else
            {
                this.plugin.getStoneManager().regenStone(event.getBlock().getLocation());
            }
        }
    }
}
