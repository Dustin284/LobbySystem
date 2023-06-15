package Commands;

import Manager.MessagesManager;
import Utils.Arrays;
import Utils.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MessagesManager messagesManager = new MessagesManager();
        if(!(sender instanceof Player)){
            sender.sendMessage(messagesManager.getNeedPlayer());
        }
        if(args.length == 0){
            Player player = (Player) sender;
            if(player.hasPermission(Permissions.LobbySystem_perks_fly) || player.hasPermission(Permissions.LobbySystem_admin)){
                if(player.getAllowFlight()){
                    Arrays.fly.remove(player.getUniqueId());
                    player.setAllowFlight(false);
                    player.sendMessage(messagesManager.getPerksFlyOff());
                }else{
                    Arrays.fly.add(player.getUniqueId());
                    player.setAllowFlight(true);
                    player.sendMessage(messagesManager.getPerksFlyOn());
                }
            }else{
                player.sendMessage(messagesManager.getNeedPermission() + "LobbySystem.perks.fly");
            }
        }
        return false;
    }
}
