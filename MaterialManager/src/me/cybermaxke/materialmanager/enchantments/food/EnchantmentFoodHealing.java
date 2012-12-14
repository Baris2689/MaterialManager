package me.cybermaxke.materialmanager.enchantments.food;

import me.cybermaxke.materialmanager.enchantments.EnchantmentFood;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnchantmentFoodHealing extends EnchantmentFood {

	public EnchantmentFoodHealing(int id) {
		super(id, "Regeneration", 10, 1);
	}
	
	@Override
	public void onEat(Player p, ItemStack is, int lvl) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 10 * lvl, 7 * lvl));
	}
}