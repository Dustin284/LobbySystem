package Inventorys;

import Manager.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class StatsInventory {



    public static void createStatsInventoryMain(Player player, Player target) {
        RanksManager ranksManager = new RanksManager();
        String tmp = ranksManager.getCurrentRank(target.getUniqueId());
        String rank = tmp.substring(3);
        Inventory Inv_Setup = Bukkit.createInventory(null, 9 * 3, "§6Stats-Main ✘ " + target.getName());
        for (int slot : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26}) {
            Inv_Setup.setItem(slot, ItemsManager.LS_glass_pane);
        }
        Inv_Setup.setItem(10, ItemsManager.createItem(Material.GRASS_BLOCK, 1, "Lobby", false, "Click for Lobby Stats"));
        Inv_Setup.setItem(16, ItemsManager.createItem(Material.PAPER, 1, "Rank:", false, ChatColor.RESET + "The Rank from the Player is: ", ChatColor.RESET.toString() + "" + rank));
        player.openInventory(Inv_Setup);
    }


    public static void createStatsInventoryLobby(Player player, Player target) {
        Inventory Inv_Setup = Bukkit.createInventory(null, 9*3, "§6Stats-Lobby ✘ " + target.getName());
        for (int slot : new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17,18,19,20,21,22,23,24,25,26}) {
            Inv_Setup.setItem(slot, ItemsManager.LS_glass_pane);
        }
        Inv_Setup.setItem(10, ItemsManager.createItem(Material.CLOCK, 1, "§6§lPlaytime", false, "§7§oYour playtime on the server:", ChatColor.RESET.toString() + "" + PlaytimeManager.formatPlaytime(PlaytimeManager.getPlayerPlaytimeDB(target.getUniqueId()))));
        Inv_Setup.setItem(11, ItemsManager.createItem(Material.PLAYER_HEAD, 1, "§6§lJoins", false, "§7§oYour joins on the server:", ChatColor.RESET.toString() + "" + JoinsManager.getJoins(target.getUniqueId())));
        Inv_Setup.setItem(12, ItemsManager.createItem(Material.GOLD_INGOT, 1, "§6§lCoins", false, "§7§oYour coins on the server:", ChatColor.RESET.toString() + "" + CoinsManager.getPlayerCoinsDB(target.getUniqueId())));
        Inv_Setup.setItem(13, ItemsManager.createItem(Material.PAPER, 1, "§6§lMessage", false, "§7§oYour messages on the server:", ChatColor.RESET.toString() + "" + MessagesManagerDB.getPlayerPlaytimeDB(target.getUniqueId())));

        if(player.hasPermission("lobbysystem.ip")){
            Inv_Setup.setItem(16, ItemsManager.createItem(Material.OAK_SIGN, 1, "§6§lIP", false, "§7§oYour IP on the server:", ChatColor.RESET.toString() + "" + target.getAddress().getAddress().getHostAddress()));
        }

        player.openInventory(Inv_Setup);
    }
}