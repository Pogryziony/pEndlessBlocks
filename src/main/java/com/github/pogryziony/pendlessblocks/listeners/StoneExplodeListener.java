package com.github.pogryziony.pendlessblocks.listeners;

import com.github.pogryziony.pendlessblocks.PEndlessBlocks;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.List;

public class StoneExplodeListener
        implements Listener
{
    private PEndlessBlocks plugin;

    public StoneExplodeListener(PEndlessBlocks plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onStoneExplode(EntityExplodeEvent event)
    {
        List<Block> blocks = event.blockList();
        if (!blocks.isEmpty()) {
            for (Block b : blocks) {
                if (this.plugin.getStoneManager().isStone(b.getLocation())) {
                    this.plugin.getStoneManager().removeStone(b.getLocation());
                }
            }
        }
    }
}
