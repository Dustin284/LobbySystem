package Manager;

import Webhook.DiscordWebhookSender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class JoinsManager {
    static MySQLManager mySQLManager = new MySQLManager();
    public static int getJoins(UUID uuid) {
        int playerjoins = 0;
        try {
            mySQLManager.connect();
            Connection connection = mySQLManager.getConnection();
            String query = "SELECT Playerjoinslobby FROM stats_user_lobby WHERE Playername = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                playerjoins = resultSet.getInt("Playerjoinslobby");
            }
            statement.close();
            resultSet.close();
            mySQLManager.disconnect();
        } catch (SQLException e) {
            System.out.println(e);
            DiscordWebhookSender.sendErrorWebhook(e.toString());
        }
        return playerjoins;
    }
}
