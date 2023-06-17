package Commands;

import Inventorys.StatsInventory;
import Utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.lang.annotation.Target;

import static Inventorys.StatsInventory.createStatsInventoryLobby;

public class StatsCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Strings.need_player);
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            StatsInventory.createStatsInventoryMain(p, p);
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target != null) {
                StatsInventory.createStatsInventoryMain(p, target);
            } else {
                p.sendMessage(ChatColor.RED + "Invalid player!");
            }
        } else {
            p.sendMessage(ChatColor.RED + "Invalid command usage!");
        }
        return true;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem != null) {
            if (clickedItem.getType() == Material.GRASS_BLOCK && e.getView().getTitle().startsWith("§6Stats-Main ✘")) {
                String targetName = e.getView().getTitle().replace("§6Stats-Main ✘ ", "");
                Player target = Bukkit.getPlayerExact(targetName);
                if (target != null) {
                    StatsInventory.createStatsInventoryLobby(p, target);
                } else {
                    p.sendMessage(ChatColor.RED + "Invalid player!");
                }
                e.setCancelled(true);
            }
        }
    }
}