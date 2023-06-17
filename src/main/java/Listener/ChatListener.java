package Listener;

import Manager.MySQLManager;
import Utils.Permissions;
import Manager.RanksManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.SQLException;

public class ChatListener implements Listener {

    MySQLManager mySQLManager = new MySQLManager();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) throws SQLException {
        RanksManager ranksManager = new RanksManager(); // Erstelle eine Instanz der RanksManager-Klasse
        Player p = (Player) e.getPlayer();
        String msg = e.getMessage();
        String tmp = ranksManager.getCurrentRank(p.getUniqueId());
        String prefix = tmp.substring(3);
        if(!p.hasPermission(Permissions.LobbySystem_chat_color) || !p.hasPermission(Permissions.LobbySystem_admin)){
            msg = msg.replace("&", "§");
            e.setFormat(prefix + " §7✘ §f" + p.getDisplayName() + "§7: " + msg);
            mySQLManager.connect();
            mySQLManager.updatePlayerMessage(p.getUniqueId());
            mySQLManager.disconnect();
            return;
        }
        msg = msg.replace("&", "§");
        e.setFormat(prefix + " §7✘ §f" + p.getDisplayName() + "§7: " + msg);
        mySQLManager.connect();
        mySQLManager.updatePlayerMessage(p.getUniqueId());
        mySQLManager.disconnect();
    }

}
