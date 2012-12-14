package me.cybermaxke.materialmanager.recipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.inventory.Recipe;

import me.cybermaxke.materialmanager.inventory.CustomItemStack;

import net.minecraft.server.IRecipe;
import net.minecraft.server.InventoryCrafting;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class CustomRecipeShapeless implements IRecipe, CustomRecipe {
	private CustomItemStack result;
	private List<ItemStack> items;

	public CustomRecipeShapeless(CustomItemStack result, List<CustomItemStack> cs) {
		this.result = result;
		this.items = toItemstackList(cs);
	}
	
	public CustomRecipeShapeless(ItemStack result, List<ItemStack> cs) {
		this.result = new CustomItemStack(result);
		this.items = cs;
	}
	
	private static List<ItemStack> toItemstackList(List<CustomItemStack> is) {
		List<ItemStack> n = new ArrayList<ItemStack>();
				
		for (int i = 0; i < is.size(); i++) {
			n.add(is.get(i).getHandle());
		}
		
		return n;
	}

	@Override
    public int a() {
        return this.items.size();
    }
 
    @Override
    public ItemStack a(InventoryCrafting inv) {
        return this.result.clone().getHandle();
    }
 
    @Override
    public boolean a(InventoryCrafting inventorycrafting, World world) {
        List<ItemStack> arraylist = new ArrayList<ItemStack>(this.items);

        for (int i = 0; i < 3; i++) {
        	for (int j = 0; j < 3; j++) {
        		ItemStack itemstack = inventorycrafting.b(j, i);

        		if (itemstack != null) {
        			boolean flag = false;
        			Iterator<ItemStack> iterator = arraylist.iterator();

        			while (iterator.hasNext()) {
        				ItemStack itemstack1 = (ItemStack) iterator.next();
        				CustomItemStack is1 = new CustomItemStack(itemstack);
            	    	CustomItemStack is2 = new CustomItemStack(itemstack1);

        				if ((itemstack.id == itemstack1.id) && ((itemstack1.getData() == -1) || (itemstack.getData() == itemstack1.getData()))) {	       					
                	    	if ((is1.isCustomItem() && is2.isCustomItem()) && is1.getMaterial().getCustomId() == is2.getMaterial().getCustomId()) {
                	    		flag = true;
            					arraylist.remove(itemstack1);
            					break;
                	    	} else if (!is1.isCustomItem() && !is2.isCustomItem()) {
                	    		flag = true;
            					arraylist.remove(itemstack1);
            					break;
                	    	}				
        				}
        			}

        			if (!flag) {
        				return false;
        			}
        		}
        	}
        }

        return arraylist.isEmpty();
  	}
 
    @Override
    public ItemStack b() {
        return this.result.getHandle();
    }

	@Override
	public Recipe toBukkitRecipe() {
		return null;
	}
}