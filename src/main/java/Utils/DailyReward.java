package Utils;

import Manager.CoinsManager;
import Manager.MessagesManager;
import Manager.MessagesManagerDB;
import org.bukkit.entity.Player;

import java.util.Date;

public class DailyReward {
    private String playerName;
    private Date lastLogin;

    MessagesManager messagesManager = new MessagesManager();

    // Konstruktor, Getter und Setter für playerName, coins und lastLogin

    public DailyReward() {
        lastLogin = new Date(); // Initialisierung des lastLogin-Datums beim Erstellen eines DailyReward-Objekts
    }

    public boolean canClaimDailyReward() {
        Date now = new Date();
        long hoursSinceLastLogin = (now.getTime() - lastLogin.getTime()) / (60 * 60 * 1000);

        return hoursSinceLastLogin >= 24;
    }

    public void claimDailyReward(Player player) {
        if (canClaimDailyReward()) {
            CoinsManager.addPlayerCoins(player.getUniqueId(), 100);
            lastLogin = new Date();
            player.sendMessage(messagesManager.getPrefix() + messagesManager.getDailyRewardClaim());
        } else {
            player.sendMessage(messagesManager.getPrefix() + messagesManager.getDailyRewardClaimed());
        }
    }
}