package com.github.pogryziony.pendlessblocks.managers;

import com.github.pogryziony.pendlessblocks.PEndlessBlocks;

import java.util.HashMap;


public class ConfigManager
{
    private PEndlessBlocks plugin;
    int regenTime;
    String oak1;
    String oak2;
    String oak3;
    HashMap<String, Integer> ingredients = new HashMap<>();

    public ConfigManager(PEndlessBlocks plugin)
    {
        this.plugin = plugin;
        loadConfig();
    }

    private void saveConfig()
    {
        this.plugin.getConfig().set("config.regen-time", this.regenTime);
        this.plugin.getConfig().set("config.oak.line1", this.oak1);
        this.plugin.getConfig().set("config.oak.line2", this.oak2);
        this.plugin.getConfig().set("config.oak.line3", this.oak3);

        for (String ingredient : this.ingredients.keySet()) {
            this.plugin.getConfig().set("config.ingredients." + ingredient, this.ingredients.get(ingredient));
        }
        this.plugin.saveConfig();
    }

    private void loadConfig()
    {
        this.regenTime = this.plugin.getConfig().getInt("config.regen-time", 2);
        this.oak1 = this.plugin.getConfig().getString("config.oak.line1", "eee");
        this.oak2 = this.plugin.getConfig().getString("config.oak.line2", "eoe");
        this.oak3 = this.plugin.getConfig().getString("config.oak.line3", "eee");

        for (String ingredient : this.plugin.getConfig().getConfigurationSection("config.ingredients").getKeys(false)) {
            this.ingredients.put(ingredient, this.plugin.getConfig().getInt("config.ingredients." + ingredient));
        }
    }

    public void reloadConfig()
    {
        this.plugin.reloadConfig();
        saveConfig();
        loadConfig();
    }
}
