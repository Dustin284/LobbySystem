package Manager;
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

    //Navigator Items
    public static ItemStack LS_Inv_Navigator_FreeBuild = ItemsManager.createItem(Material.GRASS_BLOCK, 1, "§a§lFreeBuild", false, "§7§oBuild what you want!");
    public static ItemStack LS_Inv_Navigator_SkyPvP = ItemsManager.createItem(Material.DIAMOND_SWORD, 1, "§b§lSkyPvP", false, "§7§oFight in the sky!");
    public static ItemStack LS_Inv_Navigator_Spawn = ItemsManager.createItem(Material.NETHER_STAR, 1, "§e§lSpawn", false, "§7§oGo to the spawn!");



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