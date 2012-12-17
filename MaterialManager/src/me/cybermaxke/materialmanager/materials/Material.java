package me.cybermaxke.materialmanager.materials;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.cybermaxke.materialmanager.inventory.CustomItemStack;
import me.cybermaxke.materialmanager.item.CustomItemSword;
import me.cybermaxke.materialmanager.item.CustomItemTool;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;

public class Material {
	private Color color = null;
	private int damage;
	private String name;
	private String id;
	private String skullOwner = null;
	private org.bukkit.Material type;
	private byte data = -1;
	private Plugin plugin;
	private int customId;
	private int maxDurability = 0;
	private List<String> lore = new ArrayList<String>();
	private Map<Enchantment, Integer> enchs = new HashMap<Enchantment, Integer>();
	private boolean dyeable;
	private boolean weapon = false;
	private boolean tool = false;
	
	public Material(Plugin plugin, org.bukkit.Material type, byte data, String id, String name) {		
		this.setName(name);
		this.plugin = plugin;
		this.type = type;
		this.id = id;
		this.data = data;
		this.customId = MaterialData.registerItemData(this);
		this.maxDurability = type.getMaxDurability();
		MaterialData.addMaterial(this);
		
		if (new CustomItemStack(type).getHandle().getItem() instanceof CustomItemSword) {
			this.weapon = true;
		}
		if (new CustomItemStack(type).getHandle().getItem() instanceof CustomItemTool) {
			this.tool = true;
		}
	}
	
	public Material(Plugin plugin, org.bukkit.Material type, String id, String name) {		
		this(plugin, type, (byte) -1, name, name);
	}
	
	public String getSkullOwner() {
		return this.skullOwner;
	}
	
	public Material setSkullOwner(String owner) {
		this.skullOwner = owner;
		return this;
	}
	
	public boolean isWeapon() {
		return this.weapon;
	}
	
	public Material setWeapon(boolean weapon) {
		this.weapon = weapon;
		return this;
	}
	
	public boolean isTool() {
		return this.tool;
	}
	
	public Material setTool(boolean tool) {
		this.tool = tool;
		return this;
	}
	
	public byte getData() {
		return this.data;
	}
	
	public Material setColor(Color color) {
		this.color = color;
		return this;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Material setDamage(int damage) {
		this.damage = damage;
		return this;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getMaxDurability() {
		return this.maxDurability;
	}
	
	public Material setMaxDurability(int amount) {
		this.maxDurability = amount;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public org.bukkit.Material getType() {
		return this.type;
	}
	
	public Plugin getPlugin() {
		return this.plugin;
	}
	
	public int getCustomId() {
		return this.customId;
	}
	
	public Material addEnchantment(Enchantment ench, int lvl) {
		this.enchs.put(ench, lvl);
		return this;
	}
	
	public Map<Enchantment, Integer> getEnchantments() {
		return this.enchs;
	}
	
	public Material addEnchantments(Map<Enchantment, Integer> enchs) {
		this.enchs.putAll(enchs);
		return this;
	}
	
	public Material setName(String name) {
		this.name = ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', name);
		return this;
	}
	
	public Material setLore(String... lore) {
		this.lore.clear();
		
		for (int i = 0; i < lore.length; i++) {
			this.lore.add(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', lore[i]));
		}
		
		return this;
	}
	
	public Material addLore(String lore) {
		this.lore.add(ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', lore));
		return this;
	}
	
	public String[] getLore() {	
		if (this.lore.isEmpty()) {
			return null;
		}
		
		return this.lore.toArray(new String[] {});
	}
	
	public Material setDyeable(boolean dye) {
		this.dyeable = dye;
		return this;
	}
	
	public boolean isDyeable() {
		return this.dyeable;
	}
}