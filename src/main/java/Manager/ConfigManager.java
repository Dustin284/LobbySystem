package Manager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private File configFile;
    private FileConfiguration config;

    public ConfigManager() {
        configFile = new File(Bukkit.getPluginsFolder().getPath() + "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void createConfigFileIfNeeded() {
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                defaultConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void defaultConfig() {
        config.set("prefix", "§5§lBlox§6§lUnited §8§l✘ §7");
        config.set("tablist.header", "&6&lBloxUnited.de");
        config.set("tablist.footer", "&7Dein Netzwerk");
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTablistHeader() {
        String TablistHeader = config.getString("tablist.header");
        return TablistHeader;
    }
    public String getTablistFooter(){
        String TablistFooter = config.getString("tablist.footer");
        return TablistFooter;
    }
    public String getPrefix(){
        String Prefix = config.getString("prefix");
        return Prefix;
    }
}