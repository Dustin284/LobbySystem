package Listener;

import Inventorys.NavigatorInventory;
import Inventorys.PerksInventory;
import Utils.Arrays;
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
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemStack == null || itemStack.getType() == null || !itemStack.hasItemMeta()) {
            return;
        }
        if (itemMeta.getDisplayName() == null || itemMeta.getLore() == null) {
            return;
        }
        if(p.getInventory().getItemInMainHand().getType() == Material.COMPASS && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6Navigator")) {
            NavigatorInventory.createInventoryNavigator(p);
            return;
        }
        if(p.getInventory().getItemInMainHand().getItemMeta().hasEnchants() == true && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Perks")) {
            PerksInventory.createPerksInventoryMain(p);
            return;
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.ENDER_EYE && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Show Players")) {
            Arrays.playerhider.add(p.getUniqueId());


            return;
        }
        if(p.getInventory().getItemInMainHand().getType() == Material.ENDER_EYE && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Show Players")){
            return;
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.BARRIER) {
            return;
        }
    }

}
