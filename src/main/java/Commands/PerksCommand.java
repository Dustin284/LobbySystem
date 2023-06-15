package Commands;

import Inventorys.PerksInventory;
import Manager.MessagesManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PerksCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        MessagesManager messagesManager = new MessagesManager();

        if(!(sender instanceof Player)){
            sender.sendMessage(messagesManager.getNeedPlayer());
        }
        Player player = (Player) sender;
        if(args.length == 0){
            PerksInventory.createPerksInventoryMain(player);
        }
        return false;
    }
}
