package me.cybermaxke.materialmanager.item;

import me.cybermaxke.materialmanager.inventory.CustomItemStack;
import net.minecraft.server.Block;
import net.minecraft.server.EnumToolMaterial;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;

public class CustomItemPickaxe extends CustomItemTool {
	private static Block[] c = { Block.COBBLESTONE, Block.DOUBLE_STEP, Block.STEP, Block.STONE, Block.SANDSTONE, Block.MOSSY_COBBLESTONE, Block.IRON_ORE, Block.IRON_BLOCK, Block.COAL_ORE, Block.GOLD_BLOCK, Block.GOLD_ORE, Block.DIAMOND_ORE, Block.DIAMOND_BLOCK, Block.ICE, Block.NETHERRACK, Block.LAPIS_ORE, Block.LAPIS_BLOCK, Block.REDSTONE_ORE, Block.GLOWING_REDSTONE_ORE, Block.RAILS, Block.DETECTOR_RAIL, Block.GOLDEN_RAIL };
	
	public CustomItemPickaxe(int arg0, EnumToolMaterial arg2) {
		super(arg0, 2, arg2, c);
	}

	@Override
	public boolean canDestroySpecialBlock(Block paramBlock) {
		if (paramBlock == Block.OBSIDIAN) return this.b.d() == 3;
		if ((paramBlock == Block.DIAMOND_BLOCK) || (paramBlock == Block.DIAMOND_ORE)) return this.b.d() >= 2;
		if ((paramBlock == Block.EMERALD_ORE) || (paramBlock == Block.EMERALD_BLOCK)) return this.b.d() >= 2;
		if ((paramBlock == Block.GOLD_BLOCK) || (paramBlock == Block.GOLD_ORE)) return this.b.d() >= 2;
		if ((paramBlock == Block.IRON_BLOCK) || (paramBlock == Block.IRON_ORE)) return this.b.d() >= 1;
		if ((paramBlock == Block.LAPIS_BLOCK) || (paramBlock == Block.LAPIS_ORE)) return this.b.d() >= 1;
		if ((paramBlock == Block.REDSTONE_ORE) || (paramBlock == Block.GLOWING_REDSTONE_ORE)) return this.b.d() >= 2;
		if (paramBlock.material == Material.STONE) return true;
		if (paramBlock.material == Material.ORE) return true;
		return paramBlock.material == Material.HEAVY;
	}

	@Override
	public float getDestroySpeed(ItemStack paramItemStack, Block paramBlock) {
		CustomItemStack is = new CustomItemStack(paramItemStack);
		
		if ((paramBlock != null) && ((paramBlock.material == Material.ORE) || (paramBlock.material == Material.HEAVY) || (paramBlock.material == Material.STONE))) {
			if (is.isCustomItem() && !is.getMaterial().isTool()) {
				return super.getDestroySpeed(paramItemStack, paramBlock);
			}
			
			return this.a;
		}
		
		return super.getDestroySpeed(paramItemStack, paramBlock);
	}
}