package Commands;

import Manager.LocationManager;
import Manager.MessagesManager;
import Utils.Permissions;
import Utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCommand implements CommandExecutor {

    private LocationManager locationManager;



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MessagesManager messagesManager = new MessagesManager();
        LocationManager locationManager = new LocationManager();
        Player p = (Player)sender;
        if(!(sender instanceof Player)) {
            sender.sendMessage(Strings.need_player);
            return true;
        }
        if(!p.hasPermission(Permissions.LobbySystem_admin) || !p.hasPermission(Permissions.LobbySystem_setSpawn)){
            sender.sendMessage(Strings.noperms + " " + Permissions.LobbySystem_admin + " or " + Permissions.LobbySystem_setSpawn);
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(Strings.prefix + messagesManager.getSpawnSetUsage());
            return true;
        }
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("spawn")) {
                locationManager.addLocation("Spawn", p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                p.sendMessage(Strings.prefix + messagesManager.getSetSpawn() + "Spawn §8)");
                return true;
            }
            if(args[0].equalsIgnoreCase("FreeBuild")) {
                locationManager.addLocation("FreeBuild", p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                p.sendMessage(Strings.prefix + messagesManager.getSetSpawn() + "FreeBuild §8)");
                return true;
            }
            if(args[0].equalsIgnoreCase("SkyPvP")) {
                locationManager.addLocation("SkyPvP", p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                p.sendMessage(Strings.prefix + messagesManager.getSetSpawn() +  "SkyPvP §8)");
                return true;
            }
            sender.sendMessage(Strings.prefix + messagesManager.getSpawnSetUsage());
            return true;
        }

        return false;
    }
}
