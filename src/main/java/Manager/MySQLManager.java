package Manager;

import Webhook.DiscordWebhookSender;
import org.bukkit.Bukkit;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.UUID;


public class MySQLManager {


    private String FILE = Bukkit.getPluginsFolder().getPath().toString() + "/LobbySystem/MySQL.yml";
    private Connection connection;

    public void connect() {
        // Pfad zur MySQL-Konfigurationsdatei (mysql.yml) anpassen
        File configFile = new File(FILE);

        if (!configFile.exists()) {
            System.out.println("MySQL-Konfigurationsdatei nicht gefunden.");
            return;
        }

        try {
            // Lese die Konfigurationsdatei
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(new FileInputStream(configFile));

            // Verbindungsdaten aus der Konfiguration lesen
            String host = config.get("host").toString();
            int port = (int) config.get("port");
            String database = config.get("database").toString();
            String username = config.get("username").toString();
            String password = config.get("password").toString();

            // Verbindung zur MySQL-Datenbank herstellen
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Verbindung zur MySQL-Datenbank hergestellt.");

        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der MySQL-Konfigurationsdatei.");
            DiscordWebhookSender.sendErrorWebhook("Fehler beim Lesen der MySQL-Konfigurationsdatei.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Herstellen der Verbindung zur MySQL-Datenbank.  " + e.getMessage());
            DiscordWebhookSender.sendErrorWebhook("Fehler beim Herstellen der Verbindung zur MySQL-Datenbank.  " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {
        // Verbindung zur MySQL-Datenbank trennen
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            DiscordWebhookSender.sendErrorWebhook("Fehler beim Schließen der Verbindung zur MySQL-Datenbank.  " + e.getMessage());
            return;
        }
    }

    public boolean isPlayerExists(UUID uuid) throws SQLException {
        connect(); // Stelle sicher, dass die Verbindung hergestellt wird

        if (connection == null) {
            return false; // Abbruch, wenn die Verbindung immer noch null ist
        }

        String query = "SELECT COUNT(*) AS count FROM stats_user_lobby WHERE Playername = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        }

        return false;
    }

    public void createConfigFileIfNeeded() {
        // Überprüfen, ob die MySQL-Konfigurationsdatei vorhanden ist, andernfalls erstellen
        File configFile = new File(FILE);
        if (!configFile.exists()) {
            try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();

                // Die Inhalte in die Datei schreiben
                FileWriter writer = new FileWriter(configFile);
                writer.write("host: localhost\n");
                writer.write("port: 3306\n");
                writer.write("database: my_database\n");
                writer.write("username: my_username\n");
                writer.write("password: my_password\n");
                writer.close();
            } catch (IOException e) {
                DiscordWebhookSender.sendErrorWebhook(e.getMessage());
                return;
            }
        }
    }

    private Map<String, Object> readConfigFile() {
        try {
            File configFile = new File(FILE);
            FileInputStream inputStream = new FileInputStream(configFile);
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(inputStream);

            inputStream.close();

            return config;
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Konfigurationsdatei: " + e.getMessage());
            e.printStackTrace();
            DiscordWebhookSender.sendErrorWebhook(e.getMessage());
            return null;
        }
    }


    public void insertPlayer(UUID uuid) throws SQLException {
        connect(); // Stelle sicher, dass die Verbindung hergestellt wird

        if (connection == null) {
            return; // Abbruch, wenn die Verbindung immer noch null ist
        }

        String query = "INSERT INTO stats_user_lobby (Playername, Playerrank, PlayerCoins, PlayerPlaytimeLobby, PlayerJoinsLobby, PlayerMessageslobby) VALUES (?, ?, 0, 0, 0, 0)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid.toString());
            statement.setString(2, "000§7User");
            statement.executeUpdate();

            connection.commit(); // Committe die Änderungen
        }
    }

    public void updatePlayerJoins(UUID uuid) throws SQLException {
        String selectQuery = "SELECT Playerjoinslobby FROM stats_user_lobby WHERE Playername = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setString(1, uuid.toString());
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int currentJoins = resultSet.getInt("Playerjoinslobby");
                    int newJoins = currentJoins + 1;

                    String updateQuery = "UPDATE stats_user_lobby SET Playerjoinslobby = ? WHERE Playername = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                        updateStatement.setInt(1, newJoins);
                        updateStatement.setString(2, uuid.toString());
                        updateStatement.executeUpdate();
                    }
                }
            }
        }
    }

    public void updatePlayerMessage(UUID uuid) throws SQLException {
        String selectQuery = "SELECT PlayerMessageslobby FROM stats_user_lobby WHERE Playername = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setString(1, uuid.toString());
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int currentMessages = resultSet.getInt("PlayerMessageslobby");
                    int newMessage = currentMessages + 1;

                    String updateQuery = "UPDATE stats_user_lobby SET PlayerMessageslobby = ? WHERE Playername = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                        updateStatement.setInt(1, newMessage);
                        updateStatement.setString(2, uuid.toString());
                        updateStatement.executeUpdate();
                    }
                }
            }
        }
    }

    public void CheckIfTableExist() {
        try {
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "stats_user_lobby", null);
            if (rs.next()) {
            } else {
                // Tabelle existiert nicht
                String query = "CREATE TABLE stats_user_lobby (Playername VARCHAR(36) NOT NULL, Playerrank VARCHAR(36) NOT NULL, PlayerCoins INT NOT NULL, PlayerPlaytimeLobby INT NOT NULL, PlayerJoinsLobby INT NOT NULL, PlayerMessageslobby INT NOT NULL, PRIMARY KEY (Playername))";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.executeUpdate();
                    connection.commit(); // Committe die Änderungen
                }
            }
        } catch (SQLException e) {
            DiscordWebhookSender.sendErrorWebhook(e.getMessage());
            return;
        }
    }
}