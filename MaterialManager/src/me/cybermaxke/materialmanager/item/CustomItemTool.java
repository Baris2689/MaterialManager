package me.cybermaxke.materialmanager.item;

import org.bukkit.entity.Player;

import me.cybermaxke.materialmanager.inventory.CustomItemStack;
import net.minecraft.server.Block;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EnumToolMaterial;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ItemTool;
import net.minecraft.server.World;

public class CustomItemTool extends ItemTool {

	public CustomItemTool(int arg0, int arg1, EnumToolMaterial arg2, Block[] arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	@Override
	public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2) {
		Player player = (Player) paramEntityLiving2.getBukkitEntity();
		CustomItemStack is = new CustomItemStack(paramItemStack);
		
		if (is.isCustomItem()) {			
			int damage = 2;
			
			if (is.getMaterial().isWeapon()) {
				damage = 1;
			}
			
			int ns = is.getDurability();
			int nm = is.getMaterial().getMaxDurability();
			int m = is.getType().getMaxDurability();
			
			int d = ns + damage;
			int d2 = (m * d) / nm;

			if (d >= nm) {
				is.getHandle().damage(d, paramEntityLiving2);
				return true;
			} else {			
				if (d2 >= m) {
					d2 = m - 1;
				}
				
				is.setDurability((short) (d));
				is.getHandle().setData(d2);
			}
		} else {
			is.getHandle().damage(1, paramEntityLiving2);
		}

		player.setItemInHand(is);
	    return true;
	}
	
	@Override
	public boolean a(ItemStack paramItemStack, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, EntityLiving paramEntityLiving) {
	    if (Block.byId[paramInt1].m(paramWorld, paramInt2, paramInt3, paramInt4) != 0.0D) {
	    	Player player = (Player) paramEntityLiving.getBukkitEntity();
			CustomItemStack is = new CustomItemStack(paramItemStack);
			
			if (is.isCustomItem()) {
				int damage = 2;
				
				if (is.getMaterial().isTool()) {
					damage = 1;
				}
				
				int ns = is.getDurability();
				int nm = is.getMaterial().getMaxDurability();
				int m = is.getType().getMaxDurability();
				
				int d = ns + damage;
				int d2 = (m * d) / nm;

				if (d >= nm) {
					is.getHandle().damage(d, paramEntityLiving);
					return true;
				} else {			
					if (d2 >= m) {
						d2 = m - 1;
					}
					
					is.setDurability((short) (d));
					is.getHandle().setData(d2);
				}
			} else {
				is.getHandle().damage(2, paramEntityLiving);
			}

			player.setItemInHand(is);
	    }
	    
	    return true;
	}
	
	public static void updateTools() {
		int woodAxeId = Item.WOOD_AXE.id;
		int woodPickaxeId = Item.WOOD_PICKAXE.id;
		int woodSpadeId = Item.WOOD_SPADE.id;
				
		int stoneAxeId = Item.STONE_AXE.id;
		int stonePickaxeId = Item.STONE_PICKAXE.id;
		int stoneSpadeId = Item.STONE_SPADE.id;
		
		int ironAxeId = Item.IRON_AXE.id;
		int ironPickaxeId = Item.IRON_PICKAXE.id;
		int ironSpadeId = Item.IRON_SPADE.id;
		
		int goldAxeId = Item.GOLD_AXE.id;
		int goldPickaxeId = Item.GOLD_PICKAXE.id;
		int goldSpadeId = Item.GOLD_SPADE.id;
		
		int diamondAxeId = Item.DIAMOND_AXE.id;
		int diamondPickaxeId = Item.DIAMOND_PICKAXE.id;
		int diamondSpadeId = Item.DIAMOND_SPADE.id;
		
		Item.byId[woodAxeId] = null;
		Item.byId[woodAxeId] = new CustomItemAxe(15, EnumToolMaterial.WOOD).b(0, 7).b("hatchetWood");
		
		Item.byId[woodPickaxeId] = null;
		Item.byId[woodPickaxeId] = new CustomItemPickaxe(14, EnumToolMaterial.WOOD).b(0, 6).b("pickaxeWood");
		
		Item.byId[woodSpadeId] = null;
		Item.byId[woodSpadeId] = new CustomItemSpade(13, EnumToolMaterial.WOOD).b(0, 5).b("shovelWood");
		
		Item.byId[stoneAxeId] = null;
		Item.byId[stoneAxeId] = new CustomItemAxe(19, EnumToolMaterial.STONE).b(1, 7).b("hatchetStone");
		
		Item.byId[stonePickaxeId] = null;
		Item.byId[stonePickaxeId] = new CustomItemPickaxe(18, EnumToolMaterial.STONE).b(1, 6).b("pickaxeStone");
		
		Item.byId[stoneSpadeId] = null;
		Item.byId[stoneSpadeId] = new CustomItemSpade(17, EnumToolMaterial.STONE).b(1, 5).b("shovelStone");
		
		Item.byId[ironAxeId] = null;
		Item.byId[ironAxeId] = new CustomItemAxe(2, EnumToolMaterial.IRON).b(2, 7).b("hatchetIron");
		
		Item.byId[ironPickaxeId] = null;
		Item.byId[ironPickaxeId] = new CustomItemPickaxe(1, EnumToolMaterial.IRON).b(2, 6).b("pickaxeIron");
		
		Item.byId[ironSpadeId] = null;
		Item.byId[ironSpadeId] = new CustomItemSpade(0, EnumToolMaterial.IRON).b(2, 5).b("shovelIron");
		
		Item.byId[goldAxeId] = null;
		Item.byId[goldAxeId] = new CustomItemAxe(30, EnumToolMaterial.GOLD).b(4, 7).b("hatchetGold");
		
		Item.byId[goldPickaxeId] = null;
		Item.byId[goldPickaxeId] = new CustomItemPickaxe(29, EnumToolMaterial.GOLD).b(4, 6).b("pickaxeGold");
		
		Item.byId[goldSpadeId] = null;
		Item.byId[goldSpadeId] = new CustomItemSpade(28, EnumToolMaterial.GOLD).b(4, 5).b("shovelGold");
		
		Item.byId[diamondAxeId] = null;
		Item.byId[diamondAxeId] = new CustomItemAxe(23, EnumToolMaterial.DIAMOND).b(3, 7).b("hatchetDiamond");
		
		Item.byId[diamondPickaxeId] = null;
		Item.byId[diamondPickaxeId] = new CustomItemPickaxe(22, EnumToolMaterial.DIAMOND).b(3, 6).b("pickaxeDiamond");
		
		Item.byId[diamondSpadeId] = null;
		Item.byId[diamondSpadeId] = new CustomItemSpade(21, EnumToolMaterial.DIAMOND).b(3, 5).b("shovelDiamond");
	}
}