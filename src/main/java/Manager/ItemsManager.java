package Manager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemsManager {


    //Placeholder
    public static ItemStack LS_PC_soon = ItemsManager.createItem(Material.BARRIER, 1, "§c§lSOON", true, "§7§oComing soon!");
    public static ItemStack LS_glass_pane = ItemsManager.createItem(Material.GRAY_STAINED_GLASS_PANE, 1, "§7", false, "");
    //Inventory
    public static ItemStack LS_Navigator = ItemsManager.createItem(Material.COMPASS, 1, "§6Navigator", true, "§7§oChose your gamemode!");
    public static ItemStack LS_Perks = ItemsManager.createItem(Material.LEGACY_REDSTONE_COMPARATOR, 1, "§6Perks", true, "§7§oChose your perks!");
    public static ItemStack LS_Player_Hider = ItemsManager.createItem(Material.BLAZE_ROD, 1, "§6Player Hider", true, "§7§oHide players!");


    //Navigator Items
    public static ItemStack LS_Inv_Navigator_FreeBuild = ItemsManager.createItem(Material.GRASS_BLOCK, 1, "§a§lFreeBuild", false, "§7§oBuild what you want!");
    public static ItemStack LS_Inv_Navigator_SkyPvP = ItemsManager.createItem(Material.DIAMOND_SWORD, 1, "§b§lSkyPvP", false, "§7§oFight in the sky!");
    public static ItemStack LS_Inv_Navigator_Spawn = ItemsManager.createItem(Material.NETHER_STAR, 1, "§e§lSpawn", false, "§7§oGo to the spawn!");

    //Perks Items
    public static ItemStack LS_Inv_Perks_Fly = ItemsManager.createItem(Material.FEATHER, 1, "§a§lFly", false, "§7§oClick to activate!");
    public static ItemStack LS_Inv_Perks_Fly_Enchanted = ItemsManager.createItem(Material.FEATHER, 1, "§a§lFly", true, "§7§oClick to deactivate!");
    public static ItemStack LS_Inv_Perks_Fly_not_available = ItemsManager.createItem(Material.NETHER_BRICK, 1, "§c§lFly", false, "§7§oYou need to buy this perk!");
    public static ItemStack LS_Inv_Perks_allready_buyes = ItemsManager.createItem(Material.NETHER_STAR, 1, ChatColor.GREEN + "§a§lAllready buyed", true, "§7§oYou allready buyed this perk!");
    public static ItemStack LS_Inv_Perks_Fly_buy = ItemsManager.createItem(Material.FEATHER, 1, ChatColor.GREEN + "§a§lFly - Buy", false, "§7§oClick to Buy the Perk", ChatColor.RESET.toString() + "" + "§7§oPrice: §e§l2000 Coins");
    public static ItemStack createItem(Material material, int amount, String displayName, Boolean enchated, String... lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setUnbreakable(true);
        List<String> loreList = new ArrayList<>();
        for (String line : lore) {
            loreList.add(line);
        }
        meta.setLore(loreList);
        if(enchated == true){
            meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            item.setItemMeta(meta);
            return item;
        }
        item.setItemMeta(meta);
        return item;
    }
}