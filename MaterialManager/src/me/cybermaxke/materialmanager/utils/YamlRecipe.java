package me.cybermaxke.materialmanager.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.cybermaxke.materialmanager.inventory.CustomItemStack;
import me.cybermaxke.materialmanager.materials.MaterialData;
import me.cybermaxke.materialmanager.recipes.CustomRecipe;
import me.cybermaxke.materialmanager.recipes.CustomRecipeShaped;
import me.cybermaxke.materialmanager.recipes.CustomRecipeShapeless;
import me.cybermaxke.materialmanager.recipes.RecipeData;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class YamlRecipe {
	private CustomItemStack result;
	private CustomRecipe recipe;

	public YamlRecipe(Plugin plugin, File file) {
		YamlConfiguration c = YamlConfiguration.loadConfiguration(file);
		
		if (!c.contains("Result")) {
			System.out.println("Couln't load '" + file.getName().replace(".yml", "") + "' because its missing parameters.");
		}
		
		String result = c.getString("Result");
		this.result = this.getItemFromString(result);
		
		if (c.contains("Amount")) {
			this.result.setAmount(c.getInt("Amount"));
		}
			
		if (c.getString("Type").equalsIgnoreCase("Shaped")) {
			if (!c.contains("Shape")) {
				System.out.println("Couln't load '" + file.getName().replace(".yml", "") + "' because its missing parameters.");
			}
			
			int width = 1;
			int height = 1;
		
			List<String> s1 = c.getStringList("Shape");
			height = s1.size();

			List<String> s2 = new ArrayList<String>();
			for (int i = 0; i < s1.size(); i++) {
				String[] l = s1.get(i).split("\\s+");
			
				for (int j = 0; j < l.length; j++) {
					s2.add(l[j]);
				
					if (l.length > width) {
						width = l.length;
					}
				}
			}
		
			CustomItemStack[] shape = new CustomItemStack[s2.size()];	
			for (int i = 0; i < s2.size(); i++) {
				shape[i] = this.getItemFromString(s2.get(i));
			}
		
			this.recipe = new CustomRecipeShaped(height, width, this.result, shape);
		} else {
			if (!c.contains("Ingredients")) {
				System.out.println("Couln't load '" + file.getName().replace(".yml", "") + "' because its missing parameters.");
			}
			
			List<String> in = c.getStringList("Ingredients");
			List<CustomItemStack> list = new ArrayList<CustomItemStack>();
			
			for (int i = 0; i < in.size(); i++) {
				if (this.getItemFromString(in.get(i)) != null) {
					list.add(this.getItemFromString(in.get(i)));
				}
			}
			
			this.recipe = new CustomRecipeShapeless(this.result, list);		
		}
		
		RecipeData.registerRecipe(this.recipe);
	}
	
	private org.bukkit.Material getMaterial(String mat) {
		try {
			return org.bukkit.Material.valueOf(mat);
		} catch (IllegalArgumentException ex) {  
		    return null;
		}
	}
	
	private CustomItemStack getItem(String id) {
		if (this.getMaterial(id) != null) {
			return new CustomItemStack(this.getMaterial(id));
		}
			
		if (MaterialData.getMaterialById(id) != null) {
			return new CustomItemStack(MaterialData.getMaterialById(id));
		}
		
		return null;
	}
	
	public CustomItemStack getItemFromString(String string) {
		String[] s = string.split(":");	
		CustomItemStack i = this.getItem(s[0]);	
		
		if (i != null) {
			int l = -1;
			
			if (s.length > 1) {
				try {
					l = Integer.valueOf(s[1]);
	    		} catch (Exception e) {
	    			return i;
	    		}
			}	
			
			if (l != -1) {
				i.getHandle().setData(l);
			}
		}
		
		return i;
	}
	
	public CustomItemStack getResult() {
		return this.result;
	}
	
	public CustomRecipe getRecipe() {
		return this.recipe;
	}
}