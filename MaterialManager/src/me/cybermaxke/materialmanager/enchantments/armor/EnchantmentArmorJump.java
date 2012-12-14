package me.cybermaxke.materialmanager.enchantments.armor;

import net.minecraft.server.MobEffect;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.cybermaxke.materialmanager.enchantments.EnchantmentArmor;

public class EnchantmentArmorJump extends EnchantmentArmor {

	public EnchantmentArmorJump(int id) {
		super(id, "Jumping", 10, 1);
	}
	
	@Override
	public void onTick(Player player, ItemStack is, int lvl) {
		CraftPlayer p = (CraftPlayer) player;		
		p.getHandle().addEffect(new MobEffect(8, 20, (int) (1 + 0.35 * lvl)));
	}
}