package eu.xap3y.xabasis.managers;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("ALL")
@Getter
@Setter
public class ItemCreator {

    private String name;

    private String[] lore;

    private ItemFlag[] flags = null;

    private HashMap<Enchantment, Integer> enchants = null;

    private int amount = 1;

    private Material material;

    private boolean unbreakable = false;


    /**
     * @param material the material of the item
     */
    public ItemCreator(Material material) {
        this.material = material;
    }

    /**
     * @param amount the amount of the item
     */
    public ItemCreator setLore(String... lore) {
        this.lore = lore;
        return this;
    }

    /**
     * @param amount the amount of the item
     */
    public ItemCreator setFlags(ItemFlag... flags) {
        this.flags = flags;
        return this;
    }

    /**
     * @param amount the amount of the item
     */
    public ItemCreator setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param level        the level of the enchantment
     * @param enchantments the enchantments to add
     */
    public ItemCreator addEnchants(int level, Enchantment... enchantments) {
        if (enchants == null) {
            enchants = new HashMap<>();
        }
        for (Enchantment enchantment : enchantments) {
            enchants.put(enchantment, level);
        }
        return this;
    }

    /**
     * @param enchantment the enchantment to add
     * @param level       the level of the enchantment
     */
    public ItemCreator addEnchant(Enchantment enchantment, int level) {
        if (enchants == null) {
            enchants = new HashMap<>();
        }
        enchants.put(enchantment, level);
        return this;
    }

    /**
     * @return the created item
     */
    public ItemStack create() {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Texter.colored(name));
        meta.setLore(Arrays.asList(lore).stream().map(Texter::colored).toList());
        if (flags != null) {
            meta.addItemFlags(flags);
        }
        if (enchants != null) {
            enchants.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true));
        }
        meta.setUnbreakable(unbreakable);

        for (ItemFlag flag : flags) {
            if (flag == ItemFlag.HIDE_ATTRIBUTES) {
                meta.setAttributeModifiers(item.getType().getDefaultAttributeModifiers(item.getType().getEquipmentSlot()));
            }
        }
        item.setItemMeta(meta);
        return item;
    }
}
