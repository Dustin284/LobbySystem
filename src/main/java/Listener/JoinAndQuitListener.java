package Listener;

import Inventorys.LobbyItems;
import Manager.LocationManager;
import Manager.MySQLManager;
import Utils.*;
import Webhook.DiscordWebhookSender;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class JoinAndQuitListener implements Listener {

    private LocationManager locationManager = new LocationManager();
    private MySQLManager mySQLManager = new MySQLManager();
    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws SQLException {
        mySQLManager.connect();
        Player p = e.getPlayer();
        e.setJoinMessage("");

        //Chagne GameMode
        p.setGameMode(GameMode.ADVENTURE);

        //Give Lobby Items
        LobbyItems.giveLobbyItems(p);

        //Check if Location is Set
        if(locationManager.getLocation("Spawn") == null){
            p.sendMessage(Strings.prefix + "§cSpawn not set!");
        }

        //Teleport

        p.teleport(locationManager.getLocation("Spawn"));

        //Check if player is in Build
        if(Arrays.build.contains(p.getUniqueId())) {
            p.sendMessage(Strings.build_off);
            Arrays.build.remove(p.getUniqueId());
        }

        //Database
        if (mySQLManager.isPlayerExists(p.getUniqueId())) {
            System.out.println("Spieler bereits in der Datenbank vorhanden.");
        } else {
            mySQLManager.insertPlayer(p.getUniqueId());
            System.out.println("Spieler wurde zur Datenbank hinzugefügt.");
        }
        mySQLManager.updatePlayerJoins(p.getUniqueId());
        mySQLManager.disconnect();

        //Set Scoreboard
        p.setScoreboard(ScoreboardBuilder.getBaseScoreboard(p));
        //Set Tablist
        TablistBuilder tablistBuilder = new TablistBuilder();
        tablistBuilder.setAllPlayerTeams();
        tablistBuilder.updateTablist(p);
        DiscordWebhookSender.sendInfoWebhook(p.getName() + " joined the Server!");

    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage("");
    }
}
