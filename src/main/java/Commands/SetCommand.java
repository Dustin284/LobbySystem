package Commands;

import Manager.LocationManager;
import Manager.MessagesManager;
import Utils.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCommand implements CommandExecutor {

    private LocationManager locationManager;
    MessagesManager messagesManager = new MessagesManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MessagesManager messagesManager = new MessagesManager();
        LocationManager locationManager = new LocationManager();
        Player p = (Player)sender;
        if(!(sender instanceof Player)) {
            sender.sendMessage(messagesManager.getPrefix() + messagesManager.getNeedPlayer());
            return true;
        }
        if(!p.hasPermission(Permissions.LobbySystem_admin) || !p.hasPermission(Permissions.LobbySystem_setSpawn)){
            sender.sendMessage(messagesManager.getPrefix() + messagesManager.getNeedPermission() + " " + Permissions.LobbySystem_admin + " | " + Permissions.LobbySystem_setSpawn);
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(messagesManager.getPrefix() + messagesManager.getSpawnSetUsage());
            return true;
        }
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("spawn")) {
                locationManager.addLocation("Spawn", p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                p.sendMessage(messagesManager.getPrefix() + messagesManager.getSetSpawn() + "Spawn §8)");
                return true;
            }
            if(args[0].equalsIgnoreCase("FreeBuild")) {
                locationManager.addLocation("FreeBuild", p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                p.sendMessage(messagesManager.getPrefix() + messagesManager.getSetSpawn() + "FreeBuild §8)");
                return true;
            }
            if(args[0].equalsIgnoreCase("SkyPvP")) {
                locationManager.addLocation("SkyPvP", p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                p.sendMessage(messagesManager.getPrefix() + messagesManager.getSetSpawn() +  "SkyPvP §8)");
                return true;
            }
            sender.sendMessage(messagesManager.getPrefix() + messagesManager.getSpawnSetUsage());
            return true;
        }

        return false;
    }
}
