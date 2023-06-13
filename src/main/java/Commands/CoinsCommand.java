package Commands;

import Manager.CoinsManager;
import Utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        if(!(sender instanceof Player)){
            sender.sendMessage(Strings.need_player);
        }
        if(args.length == 0){
            p.sendMessage(Strings.prefix + " Your coins: " + CoinsManager.getPlayerCoinsDB(p.getUniqueId()));

        }
        return false;
    }
}
