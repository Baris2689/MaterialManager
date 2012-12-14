package me.cybermaxke.materialmanager.item;

import me.cybermaxke.materialmanager.inventory.CustomItemStack;
import net.minecraft.server.Block;
import net.minecraft.server.EnumToolMaterial;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;

public class CustomItemAxe extends CustomItemTool {
	private static Block[] c = { Block.WOOD, Block.BOOKSHELF, Block.LOG, Block.CHEST, Block.DOUBLE_STEP, Block.STEP, Block.PUMPKIN, Block.JACK_O_LANTERN };

	public CustomItemAxe(int arg0, EnumToolMaterial arg2) {
		super(arg0, 3, arg2, c);
	}

	@Override
	public float getDestroySpeed(ItemStack paramItemStack, Block paramBlock) {
		CustomItemStack is = new CustomItemStack(paramItemStack);
		
		if ((paramBlock != null) && ((paramBlock.material == Material.WOOD) || (paramBlock.material == Material.PLANT) || (paramBlock.material == Material.REPLACEABLE_PLANT))) {
			if (is.isCustomItem() && !is.getMaterial().isTool()) {
				return super.getDestroySpeed(paramItemStack, paramBlock);
			}
			
	    	return this.a;
	    }
	    
	    return super.getDestroySpeed(paramItemStack, paramBlock);
	}
}