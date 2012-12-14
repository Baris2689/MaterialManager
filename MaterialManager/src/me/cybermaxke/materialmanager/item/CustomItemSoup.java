package me.cybermaxke.materialmanager.item;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class CustomItemSoup extends CustomItemFood {

	public CustomItemSoup(int i, int j) {
		super(i, j, false);
		this.d(1);
	}

	@Override
	public ItemStack b(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman) {
	    super.b(paramItemStack, paramWorld, paramEntityHuman);
	    return new ItemStack(Item.BOWL);
	}
}