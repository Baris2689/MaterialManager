package me.cybermaxke.materialmanager.recipes;

import java.util.ArrayList;
import java.util.Arrays;

import me.cybermaxke.materialmanager.inventory.CustomItemStack;

import net.minecraft.server.BlockCloth;
import net.minecraft.server.EntitySheep;
import net.minecraft.server.EnumArmorMaterial;
import net.minecraft.server.InventoryCrafting;
import net.minecraft.server.Item;
import net.minecraft.server.ItemArmor;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class CustomRecipeArmorDye extends CustomRecipeShapeless {
	
	public CustomRecipeArmorDye() {
	    super(new ItemStack(Item.LEATHER_HELMET, 0, 0), Arrays.asList(new ItemStack[] { new ItemStack(Item.INK_SACK, 0, 5) }));
	}

	@Override
	public boolean a(InventoryCrafting inventorycrafting, World world) {
	    ItemStack itemstack = null;
	    ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>();

	    for (int i = 0; i < inventorycrafting.getSize(); i++) {
	    	ItemStack itemstack1 = inventorycrafting.getItem(i);

	    	if (itemstack1 != null) {
	    		if ((itemstack1.getItem() instanceof ItemArmor)) {
	    			ItemArmor itemarmor = (ItemArmor)itemstack1.getItem();

	    			if ((itemarmor.d() != EnumArmorMaterial.CLOTH) || (itemstack != null)) {
	    				return false;
	    			}
	    			
	    			CustomItemStack is = new CustomItemStack(itemstack1);
	    			if (is.isCustomItem() && !is.getMaterial().isDyeable()) {
	    				return false;
	    			}

	    			itemstack = itemstack1;
	    		} else {
	    			if (itemstack1.id != Item.INK_SACK.id) {
	    				return false;
	    			}
	    			
	    			CustomItemStack is = new CustomItemStack(itemstack1);
	    			if (is.isCustomItem()) {
	    				return false;
	    			}

	    			arraylist.add(itemstack1);
	    		}
	    	}
	    }

	    return (itemstack != null) && (!arraylist.isEmpty());
	}

	@Override
	public ItemStack a(InventoryCrafting inventorycrafting) {
	    ItemStack itemstack = null;
	    int[] aint = new int[3];
	    int i = 0;
	    int j = 0;
	    ItemArmor itemarmor = null;

	    for (int k = 0; k < inventorycrafting.getSize(); k++) {
	    	ItemStack itemstack1 = inventorycrafting.getItem(k);

	    	if (itemstack1 != null) {
	    		if ((itemstack1.getItem() instanceof ItemArmor)) {
	    			itemarmor = (ItemArmor)itemstack1.getItem();
	    			if ((itemarmor.d() != EnumArmorMaterial.CLOTH) || (itemstack != null)) {
	    				return null;
	    			}

	    			itemstack = itemstack1.cloneItemStack();
	    			if (itemarmor.b_(itemstack1)) {
	    				int l = itemarmor.b(itemstack);
	    				float f = (l >> 16 & 0xFF) / 255.0F;
	    				float f1 = (l >> 8 & 0xFF) / 255.0F;
	    				float f2 = (l & 0xFF) / 255.0F;

	    				i = (int)(i + Math.max(f, Math.max(f1, f2)) * 255.0F);
	    				aint[0] = (int)(aint[0] + f * 255.0F);
	    				aint[1] = (int)(aint[1] + f1 * 255.0F);
	    				aint[2] = (int)(aint[2] + f2 * 255.0F);
	    				j++;
	    			}
	    		} else {
	    			if (itemstack1.id != Item.INK_SACK.id) {
	    				return null;
	    			}

	    			float[] afloat = EntitySheep.d[BlockCloth.e_(itemstack1.getData())];
	    			int j1 = (int)(afloat[0] * 255.0F);
	    			int k1 = (int)(afloat[1] * 255.0F);

	    			int i1 = (int)(afloat[2] * 255.0F);
	    			i += Math.max(j1, Math.max(k1, i1));
	    			aint[0] += j1;
	    			aint[1] += k1;
	    			aint[2] += i1;
	    			j++;
	    		}
	    	}
	    }

	    if (itemarmor == null) {
	      return null;
	    }
	    int k = aint[0] / j;
	    int l1 = aint[1] / j;

	    int l = aint[2] / j;
	    float f = i / j;
	    float f1 = Math.max(k, Math.max(l1, l));
	    k = (int)(k * f / f1);
	    l1 = (int)(l1 * f / f1);
	    l = (int)(l * f / f1);
	    int i1 = (k << 8) + l1;
	    i1 = (i1 << 8) + l;
	    itemarmor.b(itemstack, i1);
	    return itemstack;
	}

	public int a() {
		  return 10;
	}

	public ItemStack b() {
		return null;
	}
}