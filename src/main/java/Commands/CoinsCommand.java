package Commands;

import Manager.CoinsManager;
import Manager.MessagesManager;
import Utils.Permissions;
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
            sender.sendMessage(messagesManager.getPrefix() + messagesManager.getNeedPlayer());
        }
        if(sender.hasPermission(Permissions.LobbySystem_admin) || sender.hasPermission(Permissions.LobbySystem_coins_add) || sender.hasPermission(Permissions.LobbySystem_coins_remove) || sender.hasPermission(Permissions.LobbySystem_coins_set)) {
            if (args.length >= 3) {
                if(args[0].equalsIgnoreCase("add")){
                    Player target = p.getServer().getPlayer(args[1]);
                    if(target == null){
                        p.sendMessage(messagesManager.getPrefix() + messagesManager.getPlayerNotOnline());
                    }
                    if(target != null){
                        CoinsManager.addPlayerCoins(target.getUniqueId(), Integer.parseInt(args[2]));
                        p.sendMessage(messagesManager.getPrefix() + messagesManager.getAddCoins().replaceAll("%player%", target.getName()).replaceAll("%coins%", args[2]));
                    }
                }else if(args[0].equalsIgnoreCase("remove")) {
                    Player target = p.getServer().getPlayer(args[1]);
                    if (target == null) {
                        p.sendMessage(messagesManager.getPrefix() + messagesManager.getPlayerNotOnline());
                    }
                    if (target != null) {
                        CoinsManager.removePlayerCoins(target.getUniqueId(), Integer.parseInt(args[2]));
                        p.sendMessage(messagesManager.getPrefix() + messagesManager.getRemoveCoins().replaceAll("%player%", target.getName()).replaceAll("%coins%", args[2]));
                    }
                }else if(args[0].equalsIgnoreCase("set")) {
                    Player target = p.getServer().getPlayer(args[1]);
                    if (target == null) {
                        p.sendMessage(messagesManager.getPrefix() + messagesManager.getPlayerNotOnline());
                    }
                    if (target != null) {
                        CoinsManager.setPlayerCoins(target.getUniqueId(), Integer.parseInt(args[2]));
                        p.sendMessage(messagesManager.getPrefix() + messagesManager.getSetCoins().replaceAll("%player%", target.getName()).replaceAll("%coins%", args[2]));
                    }
                }
            }else{
                sender.sendMessage(messagesManager.getPrefix() + "/coins <add/remove/set> <player> <amount>");
            }
        }else{
            sender.sendMessage(messagesManager.getNeedPermission());
        }





        return false;
    }
}
