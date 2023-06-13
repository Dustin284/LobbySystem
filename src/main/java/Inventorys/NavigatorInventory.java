package Inventorys;

import Manager.ItemsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class NavigatorInventory {
    public static void createInventoryNavigator(Player player){
        Inventory Inv_Navigator = Bukkit.createInventory(null, 9*5, "§6Navigator");
        Inv_Navigator.setItem(10, ItemsManager.LS_Inv_Navigator_FreeBuild);
        Inv_Navigator.setItem(16, ItemsManager.LS_Inv_Navigator_SkyPvP);
        Inv_Navigator.setItem(22, ItemsManager.LS_Inv_Navigator_Spawn);
        Inv_Navigator.setItem(28, ItemsManager.LS_PC_soon);
        Inv_Navigator.setItem(34, ItemsManager.LS_PC_soon);
        player.openInventory(Inv_Navigator);
    }
}
