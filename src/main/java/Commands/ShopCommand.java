package Commands;

import Inventorys.ShopInventory;
import Manager.CoinsManager;
import Manager.MessagesManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShopCommand implements CommandExecutor {

    MessagesManager messagesManager = new MessagesManager();
    CoinsManager coinsManager = new CoinsManager();

    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(messagesManager.getPrefix() + messagesManager.getNeedPlayer());
        }
        Player p = (Player) sender;
        ShopInventory.createPerksInventoryMain(p);
        return false;
    }
}
