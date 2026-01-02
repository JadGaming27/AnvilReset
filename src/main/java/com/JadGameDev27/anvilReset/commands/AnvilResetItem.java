package com.JadGameDev27.anvilReset.commands;

import com.JadGameDev27.anvilReset.AnvilReset;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Repairable;
import org.jetbrains.annotations.NotNull;

public class AnvilResetItem implements CommandExecutor {
    private final AnvilReset plugin;
    int defaultlevel = 30;
    int defaultminlevelitem = 30;
    public AnvilResetItem (AnvilReset instance){
        plugin = instance;
    }

    @Override
    @Deprecated
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player)commandSender;
            int levelsrequired = plugin.getConfig().getInt("levels_to_reset");
            int resetto = plugin.getConfig().getInt("reset_to");
            int minlevelitem = plugin.getConfig().getInt("minimum_item_levels");
            ItemStack heldItem = p.getInventory().getItemInMainHand();
            if (heldItem != null && !heldItem.getType().isAir()){
                if (levelsrequired > 0 && resetto > -1 && minlevelitem > -1) {
                    if (p.getLevel() >= levelsrequired) {
                        Repairable r = (Repairable) heldItem.getItemMeta();
                        if (r.getRepairCost() >= minlevelitem) {
                            p.setLevel(p.getLevel()-levelsrequired);
                            r.setRepairCost(0);
                            heldItem.setItemMeta(r);
                            p.sendMessage(ChatColor.WHITE+"Item's repair cost reset successfully!" + ChatColor.RESET);
                        } else {
                            p.sendMessage(ChatColor.RED + "Item is not Too Expensive!" + ChatColor.RESET);
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "This command requires " + levelsrequired + " levels to reset the maximum repair cost!"+ChatColor.RESET);
                    }
                } else {
                    if (p.getLevel() >= defaultlevel) {
                        Repairable r = (Repairable) heldItem.getItemMeta();
                        if (r.getRepairCost() >= defaultminlevelitem) {
                            p.setLevel(p.getLevel()-defaultlevel);
                            r.setRepairCost(0);
                            heldItem.setItemMeta(r);
                            p.sendMessage(ChatColor.WHITE+"Item's repair cost reset successfully!" + ChatColor.RESET);
                        } else {
                            p.sendMessage(ChatColor.RED + "Item is not Too Expensive!" + ChatColor.RESET);
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "This command requires " + levelsrequired + " levels to reset the maximum repair cost!"+ChatColor.RESET);
                    }
                }
            } else {
                p.sendMessage(ChatColor.RED+"Please hold something in your main hand!"+ChatColor.RESET);
            }

        } else {
            commandSender.sendMessage(ChatColor.RED + "Only players are allowed to execute this command!"+ ChatColor.RESET);
        }
        return true;
    }
}
