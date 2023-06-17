package Commands;

import Manager.MessagesManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class IpCommand implements CommandExecutor {

    MessagesManager messagesManager = new MessagesManager();
    @Override
    public boolean onCommand(CommandSender sender,  Command command,  String label,  String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(messagesManager.getNeedPlayer());
        }

        Player player = (Player) sender;
        if(player.hasPermission("LobbySystem.ip")){
            if(args.length == 0){
                player.sendMessage("§7Your IP: §e" + player.getAddress().getAddress().getHostAddress());
            }else{
                Player target = player.getServer().getPlayer(args[0]);
                player.sendMessage("§7The IP from §e" + target.getName() + "§7 is §e" + target.getAddress().getAddress().getHostAddress());
            }
        }
        return false;
    }
}
