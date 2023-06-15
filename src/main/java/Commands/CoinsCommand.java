package Commands;

import Manager.CoinsManager;
import Manager.MessagesManager;
import Utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MessagesManager messagesManager = new MessagesManager();
        Player p = (Player)sender;
        if(!(sender instanceof Player)){
            sender.sendMessage(Strings.need_player);
        }
        if(args.length == 0){
            p.sendMessage(Strings.prefix + messagesManager.getGetCoins() + CoinsManager.getPlayerCoinsDB(p.getUniqueId()));

        }
        return false;
    }
}
