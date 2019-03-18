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

public class OakManager
{
    private List<String> oaks = new ArrayList();
    private PEndlessBlocks plugin;
    private YamlConfiguration data;

    public OakManager(PEndlessBlocks plugin)
    {
        this.plugin = plugin;
        this.data = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "oaks.yml"));
        setupData();
        loadData();
        plugin.getLogger().log(Level.INFO, "Successfully loaded " + this.oaks.size() + " oak generators!");
    }

    private void saveData()
    {
        try
        {
            this.data.save(new File(this.plugin.getDataFolder(), "oaks.yml"));
        }
        catch (Exception e)
        {
            System.out.println("Wystapil blad podczas zapisu pliku oaks.yml");
        }
    }

    private void loadData()
    {
        this.oaks.addAll(this.data.getStringList("data.oaks"));
    }

    private void setupData()
    {
        if (!this.data.isConfigurationSection("data"))
        {
            this.data.createSection("data");
            this.data.set("data.oaks", this.oaks);
            saveData();
        }
    }

    public void createOak(final Location loc)
    {
        this.oaks.add(loc.getWorld().getName() + ";" + loc.getBlockX() + ";" + loc.getBlockY() + ";" + loc.getBlockZ());
        this.data.set("data.oaks", this.oaks);
        saveData();
        loc.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 0);
        Bukkit.getScheduler().runTaskLater(this.plugin, () -> loc.getBlock().setType(Material.LOG), 1L);
    }

    public void removeOak(Location loc)
    {
        this.oaks.remove(loc.getWorld().getName() + ";" + loc.getBlockX() + ";" + loc.getBlockY() + ";" + loc.getBlockZ());
        this.data.set("data.oaks", this.oaks);
        saveData();
    }

    public boolean isOak(Location loc)
    {
        for (String string : this.oaks) {
            if (string.equalsIgnoreCase(loc.getWorld().getName() + ";" + loc.getBlockX() + ";" + loc.getBlockY() + ";" + loc.getBlockZ())) {
                return true;
            }
        }
        return false;
    }

    public void regenOak(final Location loc)
    {
        Bukkit.getScheduler().runTaskLater(this.plugin, () -> loc.getBlock().setType(Material.LOG), this.plugin.getConfigManager().regenTime * 20);
    }
}
