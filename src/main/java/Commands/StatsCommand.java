package Commands;

import Inventorys.StatsInventory;
import Utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import static Inventorys.StatsInventory.createStatsInventoryLobby;

public class StatsCommand implements CommandExecutor, Listener {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Strings.need_player);
        }
        if(args.length == 0){
            Player p = (Player) sender;
            StatsInventory.createStatsInventoryMain(p);
        }
        if(args.length == 1){
            Player player = (Player) sender;
            StatsInventory.createStatsInventoryMain(player);
        }

        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        InventoryView view = e.getView();
        if(view.getTitle().contains("Stats")){
            if(view.getTitle().contains("Stats")) {
                if (e.getCurrentItem().getType() == Material.GRASS_BLOCK) {
                    createStatsInventoryLobby(p.getPlayer());
                }
                e.setCancelled(true);
            }
        }
    }



}
