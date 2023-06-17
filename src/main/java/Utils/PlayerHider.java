package Utils;

import Manager.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerHider implements Listener{


    private final JavaPlugin plugin;
    private final Set<UUID> hiddenPlayers;
    MessagesManager messagesManager = new MessagesManager();

    public PlayerHider(JavaPlugin plugin) {
        this.plugin = plugin;
        this.hiddenPlayers = new HashSet<>();
    }

    public void enable() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void disable() {
        hiddenPlayers.clear();
    }

    public boolean isPlayerHidden(Player player) {
        return hiddenPlayers.contains(player.getUniqueId());
    }

    public void togglePlayerVisibility(Player player) {
        UUID playerId = player.getUniqueId();
        if (hiddenPlayers.contains(playerId)) {
            hiddenPlayers.remove(playerId);
            showAllPlayers(player);
            player.sendMessage(messagesManager.getPrefix() +  messagesManager.getPlayerHiderOn());
        } else {
            hiddenPlayers.add(playerId);
            hideAllPlayers(player);
            player.sendMessage(messagesManager.getPrefix() + messagesManager.getPlayerHiderOff());
        }
    }

    private void hideAllPlayers(Player player) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer != player) {
                player.hidePlayer(plugin, onlinePlayer);
            }
        }
    }

    private void showAllPlayers(Player player) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer != player) {
                player.showPlayer(plugin, onlinePlayer);
            }
        }
    }


}