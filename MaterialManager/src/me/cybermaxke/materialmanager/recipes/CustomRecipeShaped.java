package me.cybermaxke.materialmanager.recipes;

import org.bukkit.inventory.Recipe;

import me.cybermaxke.materialmanager.inventory.CustomItemStack;

import net.minecraft.server.IRecipe;
import net.minecraft.server.InventoryCrafting;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class CustomRecipeShaped implements IRecipe, CustomRecipe {
	private CustomItemStack result;
	private ItemStack[] items;
	private int height;
	private int width;

	public CustomRecipeShaped(int height, int width, CustomItemStack result, CustomItemStack... cs) {
		this.result = result;
		this.items = toItemstackArray(cs);
		this.height = height;
		this.width = width;
	}
	
	public CustomRecipeShaped(int height, int width, ItemStack result, ItemStack... cs) {
		this.result = new CustomItemStack(result);
		this.items = cs;
		this.height = height;
		this.width = width;
	}
	
	public CustomRecipeShaped(CustomItemStack result, CustomItemStack... cs) {
		this(3, 3, result, cs);
	}
	
	private static ItemStack[] toItemstackArray(CustomItemStack... is) {
		ItemStack[] n = new ItemStack[is.length];
				
		for (int i = 0; i < n.length; i++) {
			if (is[i] != null) {
				n[i] = is[i].getHandle();
			} else {
				n[i] = null;
			}
		}
		
		return n;
	}

	@Override
    public int a() {
        return this.width * this.height;
    }
 
    @Override
    public ItemStack a(InventoryCrafting inv) {
        return this.result.clone().getHandle();
    }
 
    @Override
    public boolean a(InventoryCrafting inventorycrafting, World world) {
        for (int i = 0; i <= 3 - this.width; i++) {
        	for (int j = 0; j <= 3 - this.height; j++) {
        	  if (a(inventorycrafting, i, j, true)) {
            		return true;
            	}

            	if (a(inventorycrafting, i, j, false)) {
            		return true;
            	}
          	}
        }

        return false;
   	}

 
    @Override
    public ItemStack b() {
        return this.result.getHandle();
    }
    
    public boolean a(InventoryCrafting inventorycrafting, int i, int j, boolean flag) {
    	for (int k = 0; k < 3; k++) {
    		for (int l = 0; l < 3; l++) {
    			int i1 = k - i;
    			int j1 = l - j;
    			ItemStack itemstack = null;
    			
    			if ((i1 >= 0) && (j1 >= 0) && (i1 < this.width) && (j1 < this.height)) {
    				if (flag)
            	  		itemstack = this.items[(this.width - i1 - 1 + j1 * this.width)];
              		else {
            	  		itemstack = this.items[(i1 + j1 * this.width)];
              		}
            	}

            	ItemStack itemstack1 = inventorycrafting.b(k, l);

            	if ((itemstack1 != null) || (itemstack != null)) {
            		if (((itemstack1 == null) && (itemstack != null)) || ((itemstack1 != null) && (itemstack == null))) {
            	  		return false;
              		}

              		if (itemstack.id != itemstack1.id) {
            	  		return false;
              		}

              		if ((itemstack.getData() != -1) && (itemstack.getData() != itemstack1.getData())) {
            	  		return false;
              		}
              		
              		CustomItemStack is1 = new CustomItemStack(itemstack);
        	    	CustomItemStack is2 = new CustomItemStack(itemstack1);
        	    	
        	    	if ((is1.isCustomItem() && !is2.isCustomItem()) || (!is1.isCustomItem() && is2.isCustomItem())) {
        	    		return false;
        	    	}
        	    	
        	    	if ((is1.isCustomItem() && is2.isCustomItem())) {
        	    		if (is1.getMaterial().getCustomId() != is2.getMaterial().getCustomId()) {
        	    			return false;
        	    		}
        	    	}
            	}
          	}
        }

        return true;
 	}

	@Override
	public Recipe toBukkitRecipe() {
		return null;
	}
}