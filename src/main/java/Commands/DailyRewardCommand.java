package Commands;

import Manager.MessagesManager;
import Utils.DailyReward;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DailyRewardCommand implements CommandExecutor {
    MessagesManager messagesManager = new MessagesManager();
    DailyReward dailyReward = new DailyReward();

    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(messagesManager.getPrefix() + messagesManager.getNeedPlayer());
            return true;
        }
        Player player = (Player) sender;
        if(dailyReward.canClaimDailyReward()){
            dailyReward.claimDailyReward(player);
        } else {
            sender.sendMessage(messagesManager.getPrefix() + messagesManager.getDailyRewardClaimed());
        }
        return true;
    }
}