package Utils;

import Manager.ConfigManager;
import Manager.MessagesManager;
import org.bukkit.ChatColor;

public class Strings {
    static ConfigManager configManager = new ConfigManager();
    static MessagesManager messagesManager = new MessagesManager();


    public static String prefix = configManager.getPrefix();
    public static String noperms = prefix + messagesManager.getNeedPermission();
    public static String need_player = prefix + messagesManager.getNeedPlayer();

    //Build Command
    public static String build_on = prefix + messagesManager.getBuildOn();
    public static String build_off = prefix + messagesManager.getBuildOff();

}
