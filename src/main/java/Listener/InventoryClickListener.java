package Listener;

import Manager.CoinsManager;
import Manager.LocationManager;
import Manager.MessagesManager;
import Utils.Arrays;
import Utils.Permissions;
import Webhook.DiscordWebhookSender;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    LocationManager locationManager = new LocationManager();
    MessagesManager messagesManager = new MessagesManager();

    //Navigator Click Event
    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        InventoryView view = e.getView();
        ItemStack currentItem = e.getCurrentItem();
        if (currentItem == null) {
            return;
        }
        if (e.getCurrentItem().getType() == Material.NETHER_BRICK){
            e.setCancelled(true);
        }
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
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lFly") && e.getCurrentItem().getItemMeta().hasEnchants() == false){
                p.setAllowFlight(true);
                Arrays.fly.add(p.getUniqueId());
                p.closeInventory();
                e.setCancelled(true);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lFly") && e.getCurrentItem().getItemMeta().hasEnchants() == true){
                p.setAllowFlight(false);
                Arrays.fly.remove(p.getUniqueId());
                p.closeInventory();
                e.setCancelled(true);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lFly") && e.getCurrentItem().getType() == Material.NETHER_BRICK){
                e.setCancelled(true);
                p.sendMessage(messagesManager.getPerksFlyNotAvailable());
                p.closeInventory();
            }
        }
        if(view.getTitle().contains("Stats")){
            e.setCancelled(true);
        }
        if(view.getTitle().equalsIgnoreCase("§6Shop-Perks")){
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lFly - Buy")){
                if(CoinsManager.getPlayerCoinsDB(p.getUniqueId()) >= 2000){
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getDisplayName() + " permission set " + Permissions.LobbySystem_perks_fly);
                    CoinsManager.removePlayerCoins(p.getUniqueId(), 2000);
                    p.sendMessage(messagesManager.getPrefix() + messagesManager.getPerksFlyBuy());
                    p.closeInventory();
                }else{
                    p.sendMessage(messagesManager.getPrefix() + messagesManager.getPerksFlyNotEnoughMoney());
                    p.closeInventory();
                }

                e.setCancelled(true);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lSOON")){
                e.setCancelled(true);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7")){
                e.setCancelled(true);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lAllready buyed")){
                e.setCancelled(true);
            }
        }
    }
}
