package me.cybermaxke.materialmanager.item;

import org.bukkit.entity.Player;

import me.cybermaxke.materialmanager.inventory.CustomItemStack;

import net.minecraft.server.Block;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EnumToolMaterial;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ItemSword;
import net.minecraft.server.World;

public class CustomItemSword extends ItemSword {

	public CustomItemSword(int arg0, EnumToolMaterial arg1) {
		super(arg0, arg1);
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

	public static void updateSwords() {
		int woodSwordId = Item.WOOD_SWORD.id;
		int stoneSwordId = Item.STONE_SWORD.id;
		int ironSwordId = Item.IRON_SWORD.id;
		int	goldSwordId = Item.GOLD_SWORD.id;
		int diamondSwordId = Item.DIAMOND_SWORD.id;
		
		Item.byId[woodSwordId] = null;
		Item.byId[woodSwordId] = new CustomItemSword(12, EnumToolMaterial.WOOD).b(0, 4).b("swordWood");
		
		Item.byId[stoneSwordId] = null;
		Item.byId[stoneSwordId] = new CustomItemSword(16, EnumToolMaterial.STONE).b(1, 4).b("swordStone");
		
		Item.byId[ironSwordId] = null;
		Item.byId[ironSwordId] = new CustomItemSword(11, EnumToolMaterial.IRON).b(2, 4).b("swordIron");
		
		Item.byId[goldSwordId] = null;
		Item.byId[goldSwordId] = new CustomItemSword(27, EnumToolMaterial.GOLD).b(4, 4).b("swordGold");
		
		Item.byId[diamondSwordId] = null;
		Item.byId[diamondSwordId] = new CustomItemSword(20, EnumToolMaterial.DIAMOND).b(3, 4).b("swordDiamond");
	}
}