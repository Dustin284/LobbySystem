package Listener;

import Utils.Arrays;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BreakAndPlaceListener implements Listener {


    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(Arrays.build.contains(e.getPlayer().getUniqueId())){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(Arrays.build.contains(e.getPlayer().getUniqueId())){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }
}
