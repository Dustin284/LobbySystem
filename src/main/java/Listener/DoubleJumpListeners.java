package Listener;

import Utils.Arrays;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.HashMap;

public class DoubleJumpListeners implements Listener {



    private HashMap<Player, Boolean> cooldown = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        cooldown.put(p, false);
    }

    @EventHandler
    public void onFly(PlayerToggleFlightEvent e){
        if (Arrays.doubleJump.contains(e.getPlayer().getUniqueId()) && !Arrays.fly.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            if(cooldown.get(e.getPlayer())) return;
            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().setY(1));
            cooldown.put(e.getPlayer(), true);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(e.getPlayer().isOnGround()){
            cooldown.put(e.getPlayer(), false);
        }
    }
}
