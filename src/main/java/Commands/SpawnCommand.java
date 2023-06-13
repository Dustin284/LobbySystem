package Commands;

import Manager.LocationManager;
import Utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    LocationManager locationManager = new LocationManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        if(!(sender instanceof Player)){
            sender.sendMessage(Strings.need_player);
        }
        if(locationManager.getLocation("Spawn") == null){
            p.sendMessage(Strings.prefix + "§cSpawn not set!");
            return true;
        }
        p.teleport(locationManager.getLocation("Spawn"));
        return false;
    }
}
