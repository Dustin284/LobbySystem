package Manager;

import Webhook.DiscordWebhookSender;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesManager {
    private File configFile;
    private FileConfiguration config;

    public MessagesManager() {
        configFile = new File(Bukkit.getPluginsFolder().getPath().toString() + "/LobbySystem/messages.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void createConfigFileIfNeeded() {
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                defaultConfig();
            } catch (IOException e) {
                e.printStackTrace();
                DiscordWebhookSender.sendErrorWebhook(e.toString());
            }
        }
    }

    public void defaultConfig() {
        config.set("prefix", "§5§lBlox§6§lUnited §8§l✘ §7");
        config.set("need_player", "§7Du musst ein Spieler sein!");
        config.set("need_permission", "§7Du hast keine Rechte dazu!");
        config.set("Player_not_online", "§7Der Spieler ist nicht online!");
        config.set("build_on", "§7Build mode is now §aenabled§r!");
        config.set("build_off", "§7Build mode is now §cdisabled§r!");
        config.set("coins.get", "§7Deine Coins betragen: §6");
        config.set("coins.add", "§7Du hast %player% §6%coins% §7Coins gegeben!");
        config.set("coins.remove", "§7Du hast %player% §6%coins% §7Coins entfernt!");
        config.set("coins.set", "§7Du hast %player% §6%coins% §7Coins gesetzt!");
        config.set("set_spawn", "§7Der Spawn wurde gesetzt! §8(§e ");
        config.set("spawn_not_set", "§cSpawn wurde noch nicht gesetzt!");
        config.set("spawn_set_usage", "§cBitte benutze /setspawn <spawn/freebuild/skypvp>");
        config.set("get_playtime", "§7Deine Spielzeit beträgt: §6");
        config.set("playerhider_on", "§cNow you see all players!");
        config.set("playerhider_off", "§aNow you dont see anyone.");
        config.set("dailyreward_claimed", "§7Du hast deine tägliche Belohnung bereits abgeholt!");
        config.set("dailyreward_claim", "§7Du hast deine tägliche Belohnung abgeholt!");
        config.set("perks.fly_on", "§7Du hast nun §aFly §7aktiviert!");
        config.set("perks.fly_off", "§7Du hast nun §cFly §7deaktiviert!");
        config.set("perks.fly_not_available", "§7Du hast keine Rechte dazu!");
        config.set("perks.not_enogh_money", "§7Du hast nicht genügend Coins!");
        config.set("perks.fly_buy", "§7Du hast §aFly §7gekauft!");
        config.set("perks.doublejump_on", "§7Du hast nun §aDoubleJump §7aktiviert!");
        config.set("perks.doublejump_off", "§7Du hast nun §cDoubleJump §7deaktiviert!");
        config.set("perks.doublejump_not_available", "§7Du hast keine Rechte dazu!");
        config.set("perks.doublekump_buy", "§7Du hast §aDoubleJump §7gekauft!");


        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
            DiscordWebhookSender.sendErrorWebhook(e.toString());
        }
    }

    public String getPrefix(){
        String Prefix = config.getString("prefix");
        return Prefix;
    }
    public String getNeedPlayer(){
        String need_player = config.getString("need_player");
        return need_player;
    }
    public String getNeedPermission(){
        String need_permission = config.getString("need_permission");
        return need_permission;
    }

    public String getBuildOn(){
        String build_on = config.getString("build_on");
        return build_on;
    }
    public String getBuildOff(){
        String build_off = config.getString("build_off");
        return build_off;
    }
    public String getGetCoins(){
        String get_coins = config.getString("coins.get");
        return get_coins;
    }
    public String getAddCoins(){
        String add_coins = config.getString("coins.add");
        return add_coins;
    }
    public String getRemoveCoins(){
        String remove_coins = config.getString("coins.remove");
        return remove_coins;
    }
    public String getSetCoins(){
        String set_coins = config.getString("coins.set");
        return set_coins;
    }
    public String getSetSpawn(){
        String set_spawn = config.getString("set_spawn");
        return set_spawn;
    }
    public String getSpawnNotSet(){
        String spawn_not_set = config.getString("spawn_not_set");
        return spawn_not_set;
    }
    public String getSpawnSetUsage(){
        String spawn_set_usage = config.getString("spawn_set_usage");
        return spawn_set_usage;
    }
    public String getPerksFlyOn(){
        String perks_fly_on = config.getString("perks.fly_on");
        return perks_fly_on;
    }
    public String getPerksFlyOff(){
        String perks_fly_off = config.getString("perks.fly_off");
        return perks_fly_off;
    }
    public String getPerksFlyNotAvailable(){
        String perks_fly_not_available = config.getString("perks.fly_not_available");
        return perks_fly_not_available;
    }
    public String getGetPlaytime(){
        String get_playtime = config.getString("get_playtime");
        return get_playtime;
    }
    public String getPlayerNotOnline(){
        String player_not_online = config.getString("Player_not_online");
        return player_not_online;
    }
    public String getPlayerHiderOn(){
        String playerhider_on = config.getString("playerhider_on");
        return playerhider_on;
    }
    public String getPlayerHiderOff(){
        String playerhider_off = config.getString("playerhider_off");
        return playerhider_off;
    }
    public String getDailyRewardClaimed(){
        String dailyreward_claimed = config.getString("dailyreward_claimed");
        return dailyreward_claimed;
    }
    public String getDailyRewardClaim(){
        String dailyreward_claim = config.getString("dailyreward_claim");
        return dailyreward_claim;
    }

    public String getPerksNotEnoughMoney(){
        String perks_not_enogh_money = config.getString("perks.not_enogh_money");
        return perks_not_enogh_money;
    }
    public String getPerksFlyBuy(){
        String perks_fly_buy = config.getString("perks.fly_buy");
        return perks_fly_buy;
    }

    public String getPerksDoubleJumpOn(){
        String perks_doublejump_on = config.getString("perks.doublejump_on");
        return perks_doublejump_on;
    }
    public String getPerksDoubleJumpOff(){
        String perks_doublejump_off = config.getString("perks.doublejump_off");
        return perks_doublejump_off;
    }
    public String getPerksDoubleJumpNotAvailable(){
        String perks_doublejump_not_available = config.getString("perks.doublejump_not_available");
        return perks_doublejump_not_available;
    }
    public String getPerksDoubleJumpBuy(){
        String perks_doublejump_buy = config.getString("perks.doublejump_buy");
        return perks_doublejump_buy;
    }


}