package Commands;

import Inventorys.LobbyItems;
import Manager.MessagesManager;
import Utils.Arrays;
import Utils.Permissions;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class BuildCommand implements CommandExecutor {

    MessagesManager messagesManager = new MessagesManager();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
                sender.sendMessage(messagesManager.getPrefix() + messagesManager.getNeedPlayer());
                return true;
        }
        Player p = (Player) sender;

        if (p.hasPermission(Permissions.LobbySystem_build)) {
            if (Arrays.build.contains(p.getUniqueId())) {
                Arrays.build.remove(p.getUniqueId());
                p.sendMessage(messagesManager.getPrefix() + messagesManager.getBuildOff());
                p.setGameMode(GameMode.SURVIVAL);
                LobbyItems.giveLobbyItems(p);
            } else {
                Arrays.build.add(p.getUniqueId());
                p.sendMessage(messagesManager.getPrefix() + messagesManager.getBuildOn());
                p.setGameMode(GameMode.CREATIVE);

            }
        } else {
            p.sendMessage(messagesManager.getPrefix() + " [" + Permissions.LobbySystem_build + "]");
        }

        return true;
    }
}
