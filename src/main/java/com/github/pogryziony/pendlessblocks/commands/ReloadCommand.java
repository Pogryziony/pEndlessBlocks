package com.github.pogryziony.pendlessblocks.commands;

import com.github.pogryziony.pendlessblocks.PEndlessBlocks;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand
        implements CommandExecutor
{
    private PEndlessBlocks plugin;

    public ReloadCommand(PEndlessBlocks plugin)
    {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!sender.hasPermission("pEndlessBlocks.reload"))
        {
            sender.sendMessage("You can't use this command!");
            return false;
        }else {
            this.plugin.getConfigManager().reloadConfig();
            sender.sendMessage("Config has been reloaded");
            return true;
        }
    }
}
