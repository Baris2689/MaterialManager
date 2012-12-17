package me.cybermaxke.materialmanager.recipes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import me.cybermaxke.materialmanager.utils.YamlRecipe;
import net.minecraft.server.CraftingManager;

public class RecipeData {
	private static JavaPlugin plug;
	private static List<CustomRecipe> recipes = new ArrayList<CustomRecipe>();

	public RecipeData(JavaPlugin plugin) {
		plug = plugin;
		
		reloadRecipes();
	}
	
	@SuppressWarnings("unchecked")
	public static void registerRecipe(CustomRecipe recipe) {
		recipes.add(recipe);
		CraftingManager.getInstance().recipes.add(recipe);
        CraftingManager.getInstance().sort();
	}
	
	public static CustomRecipe[] getRecipes() {
		return recipes.toArray(new CustomRecipe[] {});
	}
	
	public static void clearRecipes() {
		CraftingManager.getInstance().recipes.clear();
		recipes.clear();
	}
	
	public static void loadRecipes() {
		File f = new File(plug.getDataFolder() + File.separator + "Recipes");
		
		if (!f.exists()) {
			f.mkdirs();
			return;
		}
		
		for (File fl : f.listFiles()) {
			if (fl.getName().endsWith(".yml")) {
				new YamlRecipe(plug, fl);
			}
		}
	}
	
	public static void loadDefaultRecipes() {
		RecipeData.registerRecipe(new CustomRecipeArmorDye());
		
		File f = new File(plug.getDataFolder() + File.separator + "RecipesDefault");
		
		if (!f.exists()) {
			f.mkdirs();
			return;
		}
		
		for (File fl : f.listFiles()) {
			if (fl.getName().endsWith(".yml")) {
				new YamlRecipe(plug, fl);
			}
		}
	}
	
	public static void reloadRecipes() {
		clearRecipes();
		loadDefaultRecipes();
		loadRecipes();
	}
}