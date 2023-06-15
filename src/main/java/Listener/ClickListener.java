package Listener;

import Manager.LocationManager;
import Utils.Arrays;
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
        if(view.getTitle().equals("§6Perks")){
            if(e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null){
                e.setCancelled(true);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lSOON")){
                e.setCancelled(true);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7")){
                e.setCancelled(true);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lFly") && e.getCurrentItem().getItemMeta().getLore().contains("activate")){
                p.setAllowFlight(true);
                Arrays.fly.add(p.getUniqueId());
                p.closeInventory();
                e.setCancelled(true);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lFly") && e.getCurrentItem().getItemMeta().getLore().contains("deactivate")){
                p.setAllowFlight(false);
                Arrays.fly.remove(p.getUniqueId());
                p.closeInventory();
                e.setCancelled(true);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lFly") && e.getCurrentItem().getItemMeta().getLore().contains("not available")){
                e.setCancelled(true);
                p.closeInventory();
            }
        }
    }
}
