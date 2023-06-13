package Listener;

import Inventorys.LobbyItems;
import Utils.Arrays;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
        Player p = (Player) e.getPlayer();
        if(!Arrays.build.contains(p.getUniqueId())){
            LobbyItems.giveLobbyItems(p);
        }
    }

}
