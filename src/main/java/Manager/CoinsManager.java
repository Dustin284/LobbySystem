package Manager;

import Webhook.DiscordWebhookSender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CoinsManager {

    static MySQLManager mySQLManager = new MySQLManager();
    public static int getPlayerCoinsDB(UUID uuid) {
        int playercoins = 0;
        try{
            mySQLManager.connect();
            Connection connection = mySQLManager.getConnection();
            String query = "SELECT Playercoins FROM stats_user_lobby WHERE Playername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                playercoins = (int) resultSet.getLong("Playercoins");
            }
            statement.close();
            resultSet.close();
            mySQLManager.disconnect();
        } catch (SQLException e) {
            System.out.println(e);
            DiscordWebhookSender.sendErrorWebhook(e.toString());
        }
        return playercoins;

    }
    public static int setPlayerCoinsDB(UUID uuid, int coins) {
        int playercoins = getPlayerCoinsDB(uuid);
        playercoins = playercoins + coins;
        try{
            mySQLManager.connect();
            Connection connection = mySQLManager.getConnection();
            String query = "UPDATE stats_user_lobby SET Playercoins = ? WHERE Playername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, playercoins);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
            statement.close();
            mySQLManager.disconnect();
        } catch (SQLException e) {
            System.out.println(e);
            DiscordWebhookSender.sendErrorWebhook(e.toString());
        }
        return playercoins;
    }
}
