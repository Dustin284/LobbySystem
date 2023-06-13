package Listener;

import Utils.Permissions;
import Manager.RanksManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        RanksManager ranksManager = new RanksManager(); // Erstelle eine Instanz der RanksManager-Klasse
        Player p = (Player) e.getPlayer();
        String msg = e.getMessage();
        String tmp = ranksManager.getCurrentRank(p.getUniqueId());
        String prefix = tmp.substring(3);

        if(!p.hasPermission(Permissions.LobbySystem_chat_color) || !p.hasPermission(Permissions.LobbySystem_admin)){
            msg = msg.replace("&", "§");
            e.setFormat(prefix + " §7✘ §f" + p.getDisplayName() + "§7: " + msg);
            return;
        }
        msg = msg.replace("&", "§");
        e.setFormat(prefix + " §7✘ §f" + p.getDisplayName() + "§7: " + msg);

    }

}
