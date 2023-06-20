package Listener;

import Inventorys.LobbyItems;
import Manager.LocationManager;
import Manager.MessagesManager;
import Manager.MySQLManager;
import Utils.*;
import Webhook.DiscordWebhookSender;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class JoinAndQuitListener implements Listener {

    private LocationManager locationManager = new LocationManager();
    private MySQLManager mySQLManager = new MySQLManager();

    private ArrayList<UUID> joins = new ArrayList<>();
    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws SQLException {
        MessagesManager messagesManager = new MessagesManager();
        mySQLManager.connect();
        Player p = e.getPlayer();
        e.setJoinMessage("");

        //Chagne GameMode
        p.setGameMode(GameMode.SURVIVAL);

        //Give Lobby Items
        LobbyItems.giveLobbyItems(p);

        //Check if Location is Set
        if(locationManager.getLocation("Spawn") == null){
            p.sendMessage(messagesManager.getPrefix() + "§cSpawn not set!");
            DiscordWebhookSender.sendErrorWebhook("Spawn not set!");
        }

        //Teleport

        p.teleport(locationManager.getLocation("Spawn"));

        //Check if player is in Build
        if(Arrays.build.contains(p.getUniqueId())) {
            p.sendMessage(messagesManager.getPrefix() + messagesManager.getBuildOff());
            Arrays.build.remove(p.getUniqueId());
        }

        //Check if player is in Fly
        if(Arrays.fly.contains(p.getUniqueId())) {
            p.sendMessage(messagesManager.getPrefix() + messagesManager.getPerksFlyOff());
            Arrays.fly.remove(p.getUniqueId());
        }
        if(Arrays.doubleJump.contains(p.getUniqueId())){
            p.sendMessage(messagesManager.getPrefix() + messagesManager.getPerksDoubleJumpOff());
            Arrays.doubleJump.remove(p.getUniqueId());
        }

        //Database
        if (mySQLManager.isPlayerExists(p.getUniqueId())) {
            joins.remove(p.getUniqueId());
         } else {
            mySQLManager.insertPlayer(p.getUniqueId());
            joins.add(p.getUniqueId());

            System.out.println("Spieler wurde zur Datenbank hinzugefügt.");
            DiscordWebhookSender.sendSucessWebhook("Spieler wurde zur Datenbank hinzugefügt (" + p.getName() + " | " + e.getPlayer().getUniqueId()  + ")");

        }
        mySQLManager.updatePlayerJoins(p.getUniqueId());
        mySQLManager.disconnect();

        //Set Scoreboard
        p.setScoreboard(ScoreboardBuilder.getBaseScoreboard(p));
        //Set Tablist
        TablistBuilder tablistBuilder = new TablistBuilder();
        tablistBuilder.setAllPlayerTeams();
        tablistBuilder.updateTablist(p);

        DiscordWebhookSender.sendInfoWebhook(p.getName() + " joined the Server! (" + p.getPlayer().getAddress().getAddress().getHostAddress() + ")");
        if(joins.contains(p.getUniqueId())){
            p.kickPlayer("§cYou are not registered! \n Please rejoin!");
        }

    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage("");
    }
}
