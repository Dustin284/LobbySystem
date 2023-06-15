package Main;

import Commands.*;
import Listener.*;
import Manager.*;
import Utils.*;
import Webhook.DiscordWebhookSender;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class LobbySystem extends JavaPlugin {

      MySQLManager mySQLManager = new MySQLManager();

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage("§aLobbySystem wurde erfolgreich aktiviert!");
        Bukkit.getConsoleSender().sendMessage("§aVersion: " + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§aAuthor: " + getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage("§aWebhook: " + new ConfigManager().getDiscordWebhookLink());
        DiscordWebhookSender.sendSucessWebhook("------------------------------------");
        DiscordWebhookSender.sendSucessWebhook("LobbySystem wurde erfolgreich aktiviert!");
        DiscordWebhookSender.sendSucessWebhook("Version: " + getDescription().getVersion());
        DiscordWebhookSender.sendSucessWebhook("Author: " + getDescription().getAuthors());
        DiscordWebhookSender.sendSucessWebhook("------------------------------------");




        PlaytimeManager playtimeManager = new PlaytimeManager(this);
        playtimeManager.startPlaytimeTimer();
        File dataFolder = getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        mySQLManager.connect();
        LocationManager locationManager = new LocationManager();
        locationManager.createConfigFileIfNeeded();
        ConfigManager configManager = new ConfigManager();
        configManager.createConfigFileIfNeeded();
        MessagesManager messagesManager = new MessagesManager();
        messagesManager.createConfigFileIfNeeded();
        //Commands
        getCommand("setspawn").setExecutor(new SetCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("build").setExecutor(new BuildCommand());
        getCommand("coins").setExecutor(new CoinsCommand());
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("perks").setExecutor(new PerksCommand());
        getCommand("fly").setExecutor(new FlyCommand());

        //Listener
        Bukkit.getServer().getPluginManager().registerEvents(new JoinAndQuitListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BreakAndPlaceListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockedListeners(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new StatsCommand(), this);
        //1 Sekunde
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () ->{
        }, 0L, 20L);
        //30 Minuten
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () ->{
            ScoreboardBuilder.updateScoreboard(Bukkit.getOnlinePlayers().iterator().next());
        }, 0L, 36000L);

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}