package me.cybermaxke.materialmanager.item;

import net.minecraft.server.Block;
import net.minecraft.server.EnumToolMaterial;

public class CustomItemSpade extends CustomItemTool {
	private static Block[] c = { Block.GRASS, Block.DIRT, Block.SAND, Block.GRAVEL, Block.SNOW, Block.SNOW_BLOCK, Block.CLAY, Block.SOIL, Block.SOUL_SAND, Block.MYCEL };

	public CustomItemSpade(int arg0, EnumToolMaterial arg2) {
		super(arg0, 1, arg2, c);
	}

	public boolean canDestroySpecialBlock(Block paramBlock) {
	    if (paramBlock == Block.SNOW) return true;
	    return paramBlock == Block.SNOW_BLOCK;
	}
}