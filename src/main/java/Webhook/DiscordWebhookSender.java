package Webhook;
import Manager.ConfigManager;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DiscordWebhookSender {

    public static void sendInfoWebhook(String content) {
        ConfigManager configManager = new ConfigManager();
        try {
            URL url = new URL(configManager.getDiscordWebhookLink());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to POST
            connection.setRequestMethod("POST");

            // Enable output and set content type
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Construct the JSON payload with content and avatar URL
            String payload = "{\"content\":\"" + content
                    + "\", \"avatar_url\":\"" + "https://i.ibb.co/vxvhWBT/1024px-Logo-informations-svg.png" + "\"}";

            // Write the JSON payload to the connection
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(payload);
            outputStream.flush();
            outputStream.close();

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendErrorWebhook(String content) {
        ConfigManager configManager = new ConfigManager();
        try {
            URL url = new URL(configManager.getDiscordWebhookLink());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to POST
            connection.setRequestMethod("POST");

            // Enable output and set content type
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Construct the JSON payload with content and avatar URL
            String payload = "{\"content\":\"" + content
                    + "\", \"avatar_url\":\"" + "https://i.ibb.co/D9sRZnB/png-clipart-computer-icons-error-closeup-miscellaneous-text-thumbnail.png" + "\"}";

            // Write the JSON payload to the connection
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(payload);
            outputStream.flush();
            outputStream.close();

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendSucessWebhook(String content) {
        ConfigManager configManager = new ConfigManager();
        try {
            URL url = new URL(configManager.getDiscordWebhookLink());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to POST
            connection.setRequestMethod("POST");

            // Enable output and set content type
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Construct the JSON payload with content and avatar URL
            String payload = "{\"content\":\"" + content
                    + "\", \"avatar_url\":\"" + "https://i.ibb.co/FB5C4vW/2381035.png" + "\"}";

            // Write the JSON payload to the connection
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(payload);
            outputStream.flush();
            outputStream.close();

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}