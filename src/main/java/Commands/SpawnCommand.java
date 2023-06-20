package Commands;

import Manager.LocationManager;
import Manager.MessagesManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    LocationManager locationManager = new LocationManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        MessagesManager messagesManager = new MessagesManager();

        Player p = (Player) sender;

        if(!(sender instanceof Player)){
            sender.sendMessage(messagesManager.getPrefix() + messagesManager.getNeedPlayer());
        }
        if(locationManager.getLocation("Spawn") == null){
            p.sendMessage(messagesManager.getPrefix() + messagesManager.getSpawnNotSet());
            return true;
        }
        p.teleport(locationManager.getLocation("Spawn"));
        return false;
    }
}
