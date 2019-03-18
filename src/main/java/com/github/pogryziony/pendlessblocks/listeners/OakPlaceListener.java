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

public class OakPlaceListener
        implements Listener
{
    private PEndlessBlocks plugin;

    public OakPlaceListener(PEndlessBlocks plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled=true, priority=EventPriority.HIGHEST)
    public void onOakPlace(BlockPlaceEvent event)
    {
        if (event.getBlock().getType().equals(Material.ENDER_STONE))
        {
            ItemStack is = new ItemStack(event.getItemInHand());
            Player p = event.getPlayer();
            if (is.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + ChatColor.BOLD.toString() + "Generator debu")) {
                if ((UtilManager.checkLores(is.getItemMeta().getLore(), Arrays.asList(ChatColor.LIGHT_PURPLE.toString() + "Postaw aby stworzyc", ChatColor.LIGHT_PURPLE.toString() + "generator debu"))) &&
                        (is.getItemMeta().getEnchants().size() == 1)) {
                    if (!this.plugin.getOakManager().isOak(event.getBlock().getLocation()))
                    {
                        this.plugin.getOakManager().createOak(event.getBlock().getLocation());
                        p.sendMessage("Postawiles generator debu, aby go zniszczyc uzyj zlotej siekiery");
                    }
                    else
                    {
                        p.sendMessage("W tym miejscu istnieje generator debu, aby go zniszczyc uzyj zlotej siekiery");
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
