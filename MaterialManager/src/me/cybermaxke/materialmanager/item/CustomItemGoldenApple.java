package me.cybermaxke.materialmanager.item;

import me.cybermaxke.materialmanager.inventory.CustomItemStack;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MobEffect;
import net.minecraft.server.MobEffectList;
import net.minecraft.server.World;

public class CustomItemGoldenApple extends CustomItemFood {
	
	public CustomItemGoldenApple(int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean) {
	    super(paramInt1, paramInt2, paramFloat, paramBoolean);
	    this.a(true);
	}

	@Override
	public void c(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman) {
		CustomItemStack is = new CustomItemStack(paramItemStack);
		
	    if (!is.isCustomItem() && paramItemStack.getData() > 0) {
	    	if (!paramWorld.isStatic) {
	    		paramEntityHuman.addEffect(new MobEffect(MobEffectList.REGENERATION.id, 600, 3));
	        	paramEntityHuman.addEffect(new MobEffect(MobEffectList.RESISTANCE.id, 6000, 0));
	        	paramEntityHuman.addEffect(new MobEffect(MobEffectList.FIRE_RESISTANCE.id, 6000, 0));
	      	}
	    } else  {
	    	super.c(paramItemStack, paramWorld, paramEntityHuman);
	    }
	}
}