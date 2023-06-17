package Manager;

import java.util.UUID;

import Webhook.DiscordWebhookSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;

public class PlaytimeManager {

    private JavaPlugin plugin;

    public PlaytimeManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    static MySQLManager mySQLManager = new MySQLManager();


    public void updatePlayerPlaytime(UUID uuid, long playtime) {
        try {
            mySQLManager.connect();
            Connection connection = mySQLManager.getConnection();
            String query = "UPDATE stats_user_lobby SET Playerplaytimelobby = ? WHERE Playername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, playtime);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
            statement.close();
            mySQLManager.disconnect();
        } catch (SQLException e) {
            System.out.println(e);
            DiscordWebhookSender.sendErrorWebhook(e.getMessage());
        }
    }

    public static long getPlayerPlaytimeDB(UUID uuid) {
        long playtime = 0;
        try {
            mySQLManager.connect();
            Connection connection = mySQLManager.getConnection();
            String query = "SELECT Playerplaytimelobby FROM stats_user_lobby WHERE Playername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                playtime = resultSet.getLong("Playerplaytimelobby");
            }
            statement.close();
            resultSet.close();
            mySQLManager.disconnect();
        } catch (SQLException e) {
            System.out.println(e);
            DiscordWebhookSender.sendErrorWebhook(e.getMessage());
        }
        return playtime;
    }

    public void startPlaytimeTimer() {
        plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                incrementPlayerPlaytime(player.getUniqueId());
            }
        }, 20 * 60, 20 * 60); // Starte alle 60 Sekunden (20 Ticks)
    }

    private void incrementPlayerPlaytime(UUID uuid) {
        long playtime = getPlayerPlaytimeDB(uuid);
        updatePlayerPlaytime(uuid, playtime + 1);
    }

    public static String formatPlaytime(long playtime) {
        if (playtime >= 60) {
            long hours = playtime / 60;
            long minutes = playtime % 60;
            return hours + " h";
        } else {
            return playtime + " min";
        }
    }


    public void updatePlayerPlaytimeGlobal(UUID uuid, long playtime) {
        try {
            mySQLManager.connect();
            Connection connection = mySQLManager.getConnection();
            String query = "UPDATE stats_user SET PlayerplaytimeGLOBAL = ? WHERE Playername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, playtime);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
            statement.close();
            mySQLManager.disconnect();
        } catch (SQLException e) {
            System.out.println(e);
            DiscordWebhookSender.sendErrorWebhook(e.getMessage());
        }
    }

    public static long getPlayerPlaytimeDBGlobal(UUID uuid) {
        long playtime = 0;
        try {
            mySQLManager.connect();
            Connection connection = mySQLManager.getConnection();
            String query = "SELECT PlayerplaytimeGLOBAL FROM stats_user WHERE Playername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                playtime = resultSet.getLong("PlayerplaytimeGLOBAL");
            }
            statement.close();
            resultSet.close();
            mySQLManager.disconnect();
        } catch (SQLException e) {
            System.out.println(e);
            DiscordWebhookSender.sendErrorWebhook(e.getMessage());
        }
        return playtime;
    }

    public void startPlaytimeTimerGlobal() {
        plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                incrementPlayerPlaytimeGlobal(player.getUniqueId());
            }
        }, 20 * 60, 20 * 60); // Starte alle 60 Sekunden (20 Ticks)
    }

    private void incrementPlayerPlaytimeGlobal(UUID uuid) {
        long playtime = getPlayerPlaytimeDBGlobal(uuid);
        updatePlayerPlaytimeGlobal(uuid, playtime + 1);
    }

}