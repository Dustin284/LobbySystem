package Inventorys;

import Manager.CoinsManager;
import Manager.ItemsManager;
import Manager.JoinsManager;
import Manager.PlaytimeManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class StatsInventory {

    public static void createStatsInventoryMain(Player player) {
        Inventory Inv_Setup = Bukkit.createInventory(null, 9 * 3, "§6Stats-Main ✘ " + player.getName());
        for (int slot : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26}) {
            Inv_Setup.setItem(slot, ItemsManager.LS_glass_pane);
        }
        for (int slot : new int[]{11, 12, 13, 14, 15, 16}) {
            Inv_Setup.setItem(slot, ItemsManager.LS_PC_soon);
        }
        Inv_Setup.setItem(10, ItemsManager.createItem(Material.GRASS_BLOCK, 1, "Lobby", false, "Click for Lobby Stats"));
        player.openInventory(Inv_Setup);
    }


    public static void createStatsInventoryLobby(Player player){
        Inventory Inv_Setup = Bukkit.createInventory(null, 9*3, "§6Stats-Lobby ✘ " + player.getName());
        for (int slot : new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17,18,19,20,21,22,23,24,25,26}) {
            Inv_Setup.setItem(slot, ItemsManager.LS_glass_pane);
        }
        Inv_Setup.setItem(10, ItemsManager.createItem(Material.CLOCK, 1, "§6§lPlaytime", false, "§7§oYour playtime on the server:", ChatColor.RESET.toString() + "" + PlaytimeManager.formatPlaytime(PlaytimeManager.getPlayerPlaytimeDB(player.getUniqueId()))));
        Inv_Setup.setItem(11, ItemsManager.createItem(Material.PLAYER_HEAD, 1, "§6§lJoins", false, "§7§oYour joins on the server:", ChatColor.RESET.toString() + "" + JoinsManager.getJoins(player.getUniqueId())));
        Inv_Setup.setItem(12, ItemsManager.createItem(Material.GOLD_INGOT, 1, "§6§lCoins", false, "§7§oYour coins on the server:", ChatColor.RESET.toString() + "" + CoinsManager.getPlayerCoinsDB(player.getUniqueId())));
        player.openInventory(Inv_Setup);
    }
}
