package com.github.pogryziony.pendlessblocks;

import com.github.pogryziony.pendlessblocks.commands.ReloadCommand;
import com.github.pogryziony.pendlessblocks.listeners.*;
import com.github.pogryziony.pendlessblocks.managers.ConfigManager;
import com.github.pogryziony.pendlessblocks.managers.CraftingManager;
import com.github.pogryziony.pendlessblocks.managers.OakManager;
import com.github.pogryziony.pendlessblocks.managers.StoneManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PEndlessBlocks extends JavaPlugin{
    private ConfigManager configManager;
    private OakManager oakManager;
    private StoneManager stoneManager;
    public ConfigManager getConfigManager()
    {
        return this.configManager;
    }

    public OakManager getOakManager()
    {
        return this.oakManager;
    }
    public StoneManager getStoneManager(){ return this.stoneManager;}



    public void onEnable()
    {
        saveDefaultConfig();
        this.configManager = new ConfigManager(this);
        this.oakManager = new OakManager(this);
        this.stoneManager = new StoneManager(this);
        getCommand("pendlessreload").setExecutor(new ReloadCommand(this));
        Bukkit.getPluginManager().registerEvents(new OakBreakListener(this), this);
        Bukkit.getPluginManager().registerEvents(new OakPlaceListener(this), this);
        Bukkit.getPluginManager().registerEvents(new OakExplodeListener(this), this);
        Bukkit.getPluginManager().registerEvents(new OakOnFireListener(this),this);
        new CraftingManager(this).OakCrafting();
        Bukkit.getPluginManager().registerEvents(new StoneBreakListener(this),this);
        Bukkit.getPluginManager().registerEvents(new StonePlaceListener(this),this);
        new CraftingManager(this).StoneCrafting();
    }

    public void onDisable() {}
}
