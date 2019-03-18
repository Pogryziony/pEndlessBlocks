package com.github.pogryziony.pendlessblocks.managers;

import com.github.pogryziony.pendlessblocks.PEndlessBlocks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CraftingManager
{
    private PEndlessBlocks plugin;

    public CraftingManager(PEndlessBlocks plugin)
    {
        this.plugin = plugin;
    }

    public void OakCrafting()
    {
        ItemStack oak = new ItemStack(Material.ENDER_STONE, 1);
        ItemMeta meta = oak.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + "Generator debu");
        meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE.toString() + "Postaw aby stworzyc", ChatColor.LIGHT_PURPLE.toString() + "generator debu"));
        oak.setItemMeta(meta);
        oak.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 10);
        ShapedRecipe oakGenerator = new ShapedRecipe(oak).shape(
                this.plugin.getConfigManager().oak1,
                this.plugin.getConfigManager().oak2,
                this.plugin.getConfigManager().oak3);
        for (String ingredient : this.plugin.getConfigManager().ingredients.keySet()) {
            oakGenerator.setIngredient(ingredient.charAt(0), Material.getMaterial(this.plugin.getConfigManager().ingredients.get(ingredient)));
        }
        Bukkit.addRecipe(oakGenerator);
    }


}
