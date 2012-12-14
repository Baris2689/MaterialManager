package me.cybermaxke.materialmanager.item;

import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class CustomItemSeedFood extends CustomItemFood {
	private int b;
	private int c;

	public CustomItemSeedFood(int i, int j, float f, int k, int l) {
	    super(i, j, f, false);
	    this.b = k;
	    this.c = l;
	}

	@Override
	public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
	    if (l != 1)
	      return false;
	    
	    if ((entityhuman.a(i, j, k, l, itemstack)) && (entityhuman.a(i, j + 1, k, l, itemstack))) {
	      int i1 = world.getTypeId(i, j, k);

	      if ((i1 == this.c) && (world.isEmpty(i, j + 1, k))) {
	    	  	CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j + 1, k);

	        	world.setTypeId(i, j + 1, k, this.b);

	        	BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, i, j, k);

	        	if ((event.isCancelled()) || (!event.canBuild())) {
	        		event.getBlockPlaced().setTypeId(0);
	          		return false;
	       		}

	        	itemstack.count -= 1;
	        	return true;      	
	      	}
	      
	      	return false;
	    }

	    return false;
	}
}
