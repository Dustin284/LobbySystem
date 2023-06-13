package Commands;

import Inventorys.LobbyItems;
import Utils.Arrays;
import Utils.Permissions;
import Utils.Strings;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class BuildCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
                sender.sendMessage(Strings.need_player);
                return true;
        }
        Player p = (Player) sender;

        if (p.hasPermission(Permissions.LobbySystem_build)) {
            if (Arrays.build.contains(p.getUniqueId())) {
                Arrays.build.remove(p.getUniqueId());
                p.sendMessage(Strings.build_off);
                p.setGameMode(GameMode.ADVENTURE);
                LobbyItems.giveLobbyItems(p);
            } else {
                Arrays.build.add(p.getUniqueId());
                p.sendMessage(Strings.build_on);
                p.setGameMode(GameMode.CREATIVE);
            }
        } else {
            p.sendMessage(Strings.noperms + " [" + Permissions.LobbySystem_build + "]");
        }

        return true;
    }
}
