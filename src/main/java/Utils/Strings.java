package Utils;

import Manager.ConfigManager;
import org.bukkit.ChatColor;

public class Strings {
    static ConfigManager configManager = new ConfigManager();


    public static String prefix = configManager.getPrefix();
    public static String noperms = prefix + "§cYou do not have permission to use this command!";
    public static String need_player = prefix + "§cYou must be a player to use this command!";

    //Build Command
    public static String build_on = prefix + "Build mode is now §aenabled§r!";
    public static String build_off = prefix + "Build mode is now §cdisabled§r!";

}
