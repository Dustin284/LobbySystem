package Listener;

import Inventorys.NavigatorInventory;
import Inventorys.PerksInventory;
import Manager.CoinsManager;
import Manager.MySQLManager;
import Utils.PlayerHider;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;

public class PlayerInteractListener implements Listener {

    private final PlayerHider playerHider;

    MySQLManager mySQLManager = new MySQLManager();
    public PlayerInteractListener(PlayerHider playerHiderTool) {
        this.playerHider = playerHiderTool;
    }


    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) throws SQLException {
        Player p = e.getPlayer();
        Action action = e.getAction();
        ItemStack itemStack = p.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock().getType() == Material.BRICKS){
                mySQLManager.connect();
                CoinsManager.addPlayerCoins(p.getUniqueId(), 1);
                mySQLManager.disconnect();
            }
        }
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
        if (p.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6Player Hider")) {
            playerHider.togglePlayerVisibility(p);
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.BARRIER) {
            return;
        }
        if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock().getType() == Material.CHEST){
                e.setCancelled(true);
            }
        }
    }

}
