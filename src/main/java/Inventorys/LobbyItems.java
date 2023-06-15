package Inventorys;
import Manager.ItemsManager;
import org.bukkit.entity.Player;

public class LobbyItems {
    public static void giveLobbyItems(Player p) {
        p.getInventory().clear();
        p.getInventory().setItem(0, ItemsManager.LS_Perks);
        p.getInventory().setItem(2, ItemsManager.LS_PC_soon);
        p.getInventory().setItem(4, ItemsManager.LS_Navigator);
        p.getInventory().setItem(6, ItemsManager.LS_PC_soon);
        p.getInventory().setItem(8, ItemsManager.LS_PC_soon);
    }
}
