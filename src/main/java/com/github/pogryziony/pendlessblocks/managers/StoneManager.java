package com.github.pogryziony.pendlessblocks.managers;

import com.github.pogryziony.pendlessblocks.PEndlessBlocks;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class StoneManager
{
    private List<String> stones = new ArrayList();
    private PEndlessBlocks plugin;
    private YamlConfiguration data;

    public StoneManager(PEndlessBlocks plugin)
    {
        this.plugin = plugin;
        this.data = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "stones.yml"));
        setupData();
        loadData();
        plugin.getLogger().log(Level.INFO, "Successfully loaded " + this.stones.size() + " stone generators!");
    }

    private void saveData()
    {
        try
        {
            this.data.save(new File(this.plugin.getDataFolder(), "stones.yml"));
        }
        catch (Exception e)
        {
            System.out.println("Wystapil blad podczas zapisu pliku stones.yml");
        }
    }

    private void loadData()
    {
        this.stones.addAll(this.data.getStringList("data.stones"));
    }

    private void setupData()
    {
        if (!this.data.isConfigurationSection("data"))
        {
            this.data.createSection("data");
            this.data.set("data.stones", this.stones);
            saveData();
        }
    }

    public void createStone(final Location loc)
    {
        this.stones.add(loc.getWorld().getName() + ";" + loc.getBlockX() + ";" + loc.getBlockY() + ";" + loc.getBlockZ());
        this.data.set("data.stones", this.stones);
        saveData();
        loc.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 0);
        Bukkit.getScheduler().runTaskLater(this.plugin, () -> loc.getBlock().setType(Material.STONE), 1L);
    }

    public void removeStone(Location loc)
    {
        this.stones.remove(loc.getWorld().getName() + ";" + loc.getBlockX() + ";" + loc.getBlockY() + ";" + loc.getBlockZ());
        this.data.set("data.stones", this.stones);
        saveData();
    }

    public boolean isStone(Location loc)
    {
        for (String string : this.stones) {
            if (string.equalsIgnoreCase(loc.getWorld().getName() + ";" + loc.getBlockX() + ";" + loc.getBlockY() + ";" + loc.getBlockZ())) {
                return true;
            }
        }
        return false;
    }

    public void regenStone(final Location loc)
    {
        Bukkit.getScheduler().runTaskLater(this.plugin, () -> loc.getBlock().setType(Material.STONE), this.plugin.getConfigManager().regenTime * 20);
    }
}
