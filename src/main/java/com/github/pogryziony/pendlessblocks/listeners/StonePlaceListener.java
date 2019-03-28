package com.github.pogryziony.pendlessblocks.listeners;

import com.github.pogryziony.pendlessblocks.PEndlessBlocks;
import com.github.pogryziony.pendlessblocks.managers.UtilManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class StonePlaceListener
        implements Listener
{
    private PEndlessBlocks plugin;

    public StonePlaceListener(PEndlessBlocks plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled=true, priority=EventPriority.HIGHEST)
    public void onStonePlace(BlockPlaceEvent event)
    {
        if (event.getBlock().getType().equals(Material.ENDER_STONE))
        {
            ItemStack is = new ItemStack(event.getItemInHand());
            Player p = event.getPlayer();
            if (is.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + ChatColor.BOLD.toString() + "Generator kamienia")) {
                if ((UtilManager.checkLores(is.getItemMeta().getLore(), Arrays.asList(ChatColor.LIGHT_PURPLE.toString() + "Postaw aby stworzyc", ChatColor.LIGHT_PURPLE.toString() + "generator kamienia"))) &&
                        (is.getItemMeta().getEnchants().size() == 1)) {
                    if (!this.plugin.getStoneManager().isStone(event.getBlock().getLocation()))
                    {
                        this.plugin.getOakManager().createOak(event.getBlock().getLocation());
                        p.sendMessage("Postawiles generator kamienia, aby go zniszczyc uzyj zlotego kilofa");
                    }
                    else
                    {
                        p.sendMessage("W tym miejscu istnieje generator kamienia, aby go zniszczyc uzyj zlotego kilofa");
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
