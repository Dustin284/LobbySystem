package Listener;

import Inventorys.NavigatorInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action action = e.getAction();
        ItemStack itemStack = p.getInventory().getItemInMainHand();
        if (itemStack == null || itemStack.getType() == null || !itemStack.hasItemMeta()) {
            return;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta.getDisplayName() == null || itemMeta.getLore() == null) {
            return;
        }

        if(p.getInventory().getItemInMainHand().getType() == Material.COMPASS && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6Navigator")) {
            NavigatorInventory.createInventoryNavigator(p);
            return;
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Player Hider")) {
            return;
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.BARRIER) {
            return;
        }
        if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Addons")){
            return;
        }
    }

}
