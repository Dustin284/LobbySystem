package Manager;

import Webhook.DiscordWebhookSender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MessagesManagerDB {

    static MySQLManager mySQLManager = new MySQLManager();

    public static int getPlayerMessagesLobby(UUID uuid) {
        long messages = 0;
        try {
            mySQLManager.connect();
            Connection connection = mySQLManager.getConnection();
            String query = "SELECT PlayerMessageslobby FROM stats_user_lobby WHERE Playername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                messages = resultSet.getInt("PlayerMessageslobby");
            }
            statement.close();
            resultSet.close();
            mySQLManager.disconnect();
        } catch (SQLException e) {
            System.out.println(e);
            DiscordWebhookSender.sendErrorWebhook(e.getMessage());
        }
        return (int) messages;
    }
    public static long getPlayerMessagesGlobal(UUID uuid) {
        long playtime = 0;
        try {
            mySQLManager.connect();
            Connection connection = mySQLManager.getConnection();
            String query = "SELECT PlayerMessagesglobal FROM stats_user WHERE Playername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                playtime = resultSet.getLong("PlayerMessagesglobal");
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
}
