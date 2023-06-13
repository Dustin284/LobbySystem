package Listener;

import Manager.LocationManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

public class ClickListener implements Listener {

    LocationManager locationManager = new LocationManager();

    //Navigator Click Event
    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        InventoryView view = e.getView();
        if(view.getTitle().equals("§6Navigator")){
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lFreeBuild")){
                e.setCancelled(true);
                p.teleport(locationManager.getLocation("FreeBuild"));
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lSkyPvP")){
                e.setCancelled(true);
                p.teleport(locationManager.getLocation("SkyPvP"));
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§lSpawn")){
                e.setCancelled(true);
                p.teleport(locationManager.getLocation("Spawn"));
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lSOON")){
                e.setCancelled(true);
            }
            if(e.getCurrentItem() == null){
                e.setCancelled(true);
            }
        }
    }
}
