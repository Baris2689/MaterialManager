package me.cybermaxke.materialmanager.utils;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;

import me.cybermaxke.materialmanager.enchantments.Enchantments;
import me.cybermaxke.materialmanager.materials.Material;

public class YamlMaterial {
	private Material mat;

	public YamlMaterial(Plugin plugin, File file) {
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		
		if (!c.contains("Name") || !c.contains("Type")) {
			System.out.println("Couln't load '" + file.getName().replace(".yml", "") + "' because its missing parameters.");
		}
			
		String name = c.getString("Name");
		org.bukkit.Material type;
		int data = -1;
		
		String[] s = c.getString("Type").split(":");
		
		type = this.getMaterial(s[0]);
		if (s.length > 1) {
			try {
				data = Integer.valueOf(s[1]);
    		} catch (Exception e) {}
		}
		
		if (type == null) {
			System.out.println("Couln't load '" + file.getName().replace(".yml", "") + "' because the type " + s[0].toUpperCase() + "doesn't exist.");
			return;
		}
	
		List<String> lore = null;
		if (c.contains("Lore")) {
			lore = c.getStringList("Lore");
		}
		
		int durability = type.getMaxDurability();
		if (c.contains("MaxDurability")) {
			durability = c.getInt("MaxDurability");
		}
		
		Color color = null;
		if (c.contains("Color")) {
			color = Color.decode(c.getString("Color"));
		}
		
		String skullOwner = null;
		if (c.contains("SkullOwner")) {
			skullOwner = c.getString("SkullOwner");
		}
		
		int damage = 1;
		if (c.contains("Damage")) {
			damage = c.getInt("Damage");
		}
		
		Map<Enchantment, Integer> enchs = null;
		if (c.contains("Enchantments")) {
			enchs = new HashMap<Enchantment, Integer>();
			ConfigurationSection sc = c.getConfigurationSection("Enchantments");
			String[] k = sc.getKeys(false).toArray(new String[] {});
			
			for (int i = 0; i < k.length; i++) {
				if (Enchantments.getEnchantment(k[i]) != null) {
					int lvl = c.getInt("Enchantments." + k[i]);
					enchs.put(Enchantments.getEnchantment(k[i]), lvl);
				}
			}
		}
		
		this.mat = new Material(plugin, type, (byte) data, file.getName().replace(".yml", ""), name);	
		this.mat.setMaxDurability(durability);
		this.mat.setSkullOwner(skullOwner);
		this.mat.setDamage(damage);
		this.mat.setColor(color);
		
		if (lore != null) {
			this.mat.setLore(lore.toArray(new String[] {}));
		}
		if (enchs != null) {
			this.mat.addEnchantments(enchs);
		}
		if (c.contains("Weapon")) {
			this.mat.setWeapon(c.getBoolean("Weapon"));
		}
		if (c.contains("Tool")) {
			this.mat.setTool(c.getBoolean("Tool"));
		}
	}
	
	public Material getMaterial() {
		return this.mat;
	}
	
	private org.bukkit.Material getMaterial(String mat) {
		try {
			return org.bukkit.Material.valueOf(mat);
		} catch (IllegalArgumentException ex) {  
		    return null;
		}
	}
}