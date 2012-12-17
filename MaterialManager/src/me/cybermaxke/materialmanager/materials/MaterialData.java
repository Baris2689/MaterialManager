package me.cybermaxke.materialmanager.materials;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import me.cybermaxke.materialmanager.item.CustomItemFood;
import me.cybermaxke.materialmanager.item.CustomItemSword;
import me.cybermaxke.materialmanager.item.CustomItemTool;
import me.cybermaxke.materialmanager.utils.YamlMaterial;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class MaterialData {
	private static Plugin plug;
	private static int data = 1000;
	private static Map<String, Material> mats = new HashMap<String, Material>();
	private static Map<Integer, String> matDataById = new HashMap<Integer, String>();
	private static Map<String, Integer> matData = new HashMap<String, Integer>();
	
	public MaterialData(Plugin plugin) {
		CustomItemFood.updateFood();
		CustomItemSword.updateSwords();
		CustomItemTool.updateTools();
		plug = plugin;
		
		loadMaterials();
	}
	
	public static void loadMaterials() {
		File f = new File(plug.getDataFolder() + File.separator + "Materials");
		
		if (!f.exists()) {
			f.mkdirs();
			return;
		}
		
		for (File fl : f.listFiles()) {
			if (fl.getName().endsWith(".yml")) {
				new YamlMaterial(plug, fl);
			}
		}
	}
	
	public static void save() throws IOException {
		File f = new File(plug.getDataFolder() + File.separator + "MaterialData.yml");
		YamlConfiguration c = new YamlConfiguration();
		
		if (!f.exists()) {
			f.createNewFile();
		} else {
			c = YamlConfiguration.loadConfiguration(f);
		}
		
		for (Entry<String, Integer> d : matData.entrySet()) {
			c.set(d.getKey(), d.getValue());
		}
		
		c.save(f);
	}
	
	public static void load() throws IOException {
		File f = new File(plug.getDataFolder() + File.separator + "MaterialData.yml");

		if (!f.exists()) {
			return;
		}
		
		YamlConfiguration c = YamlConfiguration.loadConfiguration(f);

		for (Entry<String, Object> d : c.getValues(false).entrySet()) {
			matData.put(d.getKey(), (Integer) d.getValue());
			matDataById.put((Integer) d.getValue(), d.getKey());
		}
	}
	
	public static int registerItemData(Material material) {
		if (matData.containsKey(material.getId().toLowerCase())) {
			return matData.get(material.getId().toLowerCase());
		}
		
		while (matDataById.containsKey(data)) {
			data++;
		}
		
		matDataById.put(data, material.getId().toLowerCase());
		matData.put(material.getId().toLowerCase(), data);
		return data;
	}
	
	public static Material addMaterial(Material mat) {
		return mats.put(mat.getId().toLowerCase(), mat);
	}
	
	public static Material getMaterialById(String id) {
		if (!mats.containsKey(id.toLowerCase())) {
			return null;
		}
		
		return mats.get(id.toLowerCase());
	}
	
	public static Material[] getMaterials() {
		return mats.values().toArray(new Material[] {});
	}
	
	public static Material getByCustomId(int id) {
		if (!matDataById.containsKey(id)) {
			return null;
		}
		
		if (!mats.containsKey(matDataById.get(id))) {
			return null;
		}
		
		return mats.get(matDataById.get(id));
	}
}