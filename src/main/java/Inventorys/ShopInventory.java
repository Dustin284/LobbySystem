package Inventorys;

import Manager.ItemsManager;
import Utils.Arrays;
import Utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ShopInventory {

    public static void createPerksInventoryMain(Player player) {
        Inventory Inv_Setup = Bukkit.createInventory(null, 9 * 3, "§6Shop-Perks");
        for (int slot : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26}) {
            Inv_Setup.setItem(slot, ItemsManager.LS_glass_pane);
        }
        for (int slot : new int[]{ 12, 13, 14, 15, 16}) {
            Inv_Setup.setItem(slot, ItemsManager.LS_PC_soon);
        }
        if(!player.hasPermission(Permissions.LobbySystem_perks_fly)){
            Inv_Setup.setItem(10, ItemsManager.LS_Inv_Perks_Fly_buy);
        }else{
            Inv_Setup.setItem(10, ItemsManager.LS_Inv_Perks_allready_buyes);
        }
        if(!player.hasPermission(Permissions.LobbySystem_perks_doubleJump)) {
            Inv_Setup.setItem(11, ItemsManager.LS_Inv_Perks_DoubleJump_buy);
        }else{
            Inv_Setup.setItem(11, ItemsManager.LS_Inv_Perks_allready_buyes);
        }
        player.openInventory(Inv_Setup);
    }

}
