package Commands;

import Manager.MessagesManager;
import Manager.PlaytimeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaytimeCommand implements CommandExecutor {

    MessagesManager messagesManager = new MessagesManager();

    @Override
    public boolean onCommand(CommandSender sender,Command command,String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(messagesManager.getNeedPlayer());
        }
        Player player = (Player) sender;
        sender.sendMessage(messagesManager.getPrefix() + messagesManager.getGetPlaytime() + PlaytimeManager.formatPlaytime(PlaytimeManager.getPlayerPlaytimeDB(player.getUniqueId())));
        return false;
    }
}
