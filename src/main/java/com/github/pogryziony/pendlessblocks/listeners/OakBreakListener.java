package com.github.pogryziony.pendlessblocks.listeners;

import com.github.pogryziony.pendlessblocks.PEndlessBlocks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OakBreakListener
        implements Listener
{
    private PEndlessBlocks plugin;

    public OakBreakListener(PEndlessBlocks plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onOakBreak(BlockBreakEvent event)
    {
        Player p = event.getPlayer();
        if (this.plugin.getOakManager().isOak(event.getBlock().getLocation())) {
            if (event.getPlayer().getItemInHand().getType().equals(Material.GOLD_AXE))
            {
                this.plugin.getOakManager().removeOak(event.getBlock().getLocation());
                event.getBlock().setType(Material.AIR);
                p.sendMessage(ChatColor.GOLD.toString() + "Zniszczono generator debu");
            }
            else
            {
                this.plugin.getOakManager().regenOak(event.getBlock().getLocation());
            }
        }
    }
}
