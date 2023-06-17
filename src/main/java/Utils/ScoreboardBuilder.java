package Utils;


import Manager.CoinsManager;
import Manager.PlaytimeManager;
import Manager.RanksManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import static Manager.PlaytimeManager.formatPlaytime;

public class ScoreboardBuilder {

    public static Scoreboard getBaseScoreboard(Player player) {
    RanksManager ranksManager = new RanksManager();
    long playtime = PlaytimeManager.getPlayerPlaytimeDB(player.getUniqueId());
    String playerRank = ranksManager.getCurrentRank(player.getUniqueId());
    String tmp = ranksManager.getCurrentRank(player.getUniqueId());
    String prefix = tmp.substring(3);

    Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    Objective objective = scoreboard.registerNewObjective("main", "dummy", "§aBloxUnited");
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);

    objective.getScore("§7➦ %clan%").setScore(0);
    objective.getScore("§fClan:").setScore(1);
    objective.getScore("§a§l       ").setScore(2);
    objective.getScore("§7➦ §a" + formatPlaytime(playtime)).setScore(3);
    objective.getScore("§fPlaytime:").setScore(4);
    objective.getScore("§a ").setScore(5);
    objective.getScore("§7➥ §6" + CoinsManager.getPlayerCoinsDB(player.getUniqueId())).setScore(6);
    objective.getScore("§fCoins:").setScore(7);
    objective.getScore("§a  ").setScore(8);
    objective.getScore("§7➥ " + prefix).setScore(9);
    objective.getScore("§fRank:").setScore(10);
    objective.getScore("§a").setScore(11);

    return scoreboard;
}


    public static void updateScoreboard(Player player) {
        long playtime = PlaytimeManager.getPlayerPlaytimeDB(player.getUniqueId());
        // Restlicher Code für die Aktualisierung des Scoreboards
        Scoreboard s = player.getScoreboard();
        Objective objective = s.getObjective("main");
        if(objective != null) {
            // Entferne bestehende Scoreboard-Einträge
            for (String entry : objective.getScoreboard().getEntries()) {
                objective.getScoreboard().resetScores(entry);
            }
            // Aktualisiere die Playtime
            updatePlaytimeScore(objective, PlaytimeManager.getPlayerPlaytimeDB(player.getUniqueId()), player);
            // Restlicher Code...
        }
    }

    private static void updatePlaytimeScore(Objective objective, long playtime ,Player player) {
        RanksManager ranksManager = new RanksManager(); // Erstelle eine Instanz der RanksManager-Klasse
        String playerRank = ranksManager.getCurrentRank(player.getUniqueId()); // Hole den Rang des Spielers aus der Datenbank
        String tmp = ranksManager.getCurrentRank(player.getUniqueId());
        String prefix = tmp.substring(3);
        String formattedPlaytime = formatPlaytime(playtime);
        objective.getScore("§7➦ %clan%").setScore(0);
        objective.getScore("§fClan:").setScore(1);
        objective.getScore("§a§l       ").setScore(2);
        objective.getScore("§7➦ §a" + formattedPlaytime).setScore(3);
        objective.getScore("§fPlaytime:").setScore(4);
        objective.getScore("§a ").setScore(5);
        objective.getScore("§7➥ §6" + CoinsManager.getPlayerCoinsDB(player.getUniqueId())).setScore(6);
        objective.getScore("§fCoins:").setScore(7);
        objective.getScore("§a  ").setScore(8);
        objective.getScore("§7➥ " + prefix).setScore(9);
        objective.getScore("§fRank:").setScore(10);
        objective.getScore("§a").setScore(11);

    }



}