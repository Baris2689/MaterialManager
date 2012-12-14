package me.cybermaxke.materialmanager.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.CraftingManager;

public class RecipeData {
	private static List<CustomRecipe> recipes = new ArrayList<CustomRecipe>();

	public RecipeData() {
		CraftingManager.getInstance().recipes.clear();		
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
}