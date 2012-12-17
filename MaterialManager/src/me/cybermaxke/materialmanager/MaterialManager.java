package me.cybermaxke.materialmanager;

import java.io.IOException;
import java.util.logging.Level;

import me.cybermaxke.materialmanager.materials.MaterialData;
import me.cybermaxke.materialmanager.recipes.RecipeData;
import me.cybermaxke.materialmanager.utils.Metrics;
import me.cybermaxke.materialmanager.utils.Metrics.Graph;
import me.cybermaxke.materialmanager.utils.Metrics.Plotter;

import org.bukkit.plugin.java.JavaPlugin;

public class MaterialManager extends JavaPlugin {

	@Override
	public void onEnable() {
		new MaterialData(this);
		new RecipeData(this);
		new GiveMaterialCommand(this);
		new ReloadMaterialCommand(this);
		new PlayerListener(this);
		new Timer(this);
		
		try {
			MaterialData.load();
		} catch (IOException e) {
			this.getLogger().log(Level.WARNING, "Couldn't load the material data.");
		}
		
		try {
			MaterialData.save();
		} catch (IOException e) {
			this.getLogger().log(Level.WARNING, "Couldn't save the material data.");
		}
		
		try {
		    Metrics m = new Metrics(this);
		    
		    Graph g = m.createGraph("Number of materials.");
			g.addPlotter(new Plotter("Materials") {
				
				@Override
				public int getValue() {
					return MaterialData.getMaterials().length;
				}
				
			});
			
			Graph g2 = m.createGraph("Number of recipes. (Defaults included)");
			g2.addPlotter(new Plotter("Recipes") {
				
				@Override
				public int getValue() {
					return RecipeData.getRecipes().length;
				}
				
			});
			
		    m.start();
		    this.getLogger().log(Level.INFO, "Metrics loaded.");
		} catch (Exception e) {
			this.getLogger().log(Level.WARNING, "Couldn't load Metrics!");
		}
		
		this.getLogger().log(Level.INFO, "The plugin is loaded.");
	}
	
	@Override
	public void onDisable() {
		try {
			MaterialData.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	private void loadRecipes() {
		File f = new File(this.getDataFolder() + File.separator + "Recipes");
		
		if (!f.exists()) {
			f.mkdirs();
			return;
		}
		
		for (File fl : f.listFiles()) {
			if (fl.getName().endsWith(".yml")) {
				new YamlRecipe(this, fl);
			}
		}
	}
	
	private void loadDefaultRecipes() {
		RecipeData.registerRecipe(new CustomRecipeArmorDye());
		
		File f = new File(this.getDataFolder() + File.separator + "RecipesDefault");
		
		if (!f.exists()) {
			f.mkdirs();
			return;
		}
		
		for (File fl : f.listFiles()) {
			if (fl.getName().endsWith(".yml")) {
				new YamlRecipe(this, fl);
			}
		}
	}*/
}