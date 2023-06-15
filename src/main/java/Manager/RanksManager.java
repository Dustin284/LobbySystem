package Manager;


import Webhook.DiscordWebhookSender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RanksManager {
    MySQLManager mySQLManager = new MySQLManager();


    public String getCurrentRank(UUID uuid){
        mySQLManager.connect();
        if (mySQLManager != null) {
            String playerRank = "User";

            try {
                Connection connection = mySQLManager.getConnection();
                String query = "SELECT Playerrank FROM stats_user_lobby WHERE Playername = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, uuid.toString());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    playerRank = resultSet.getString("Playerrank");
                }
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                DiscordWebhookSender.sendErrorWebhook(e.getMessage());
            }

            mySQLManager.disconnect();
            return playerRank;
        }
        return null;
    }

}
