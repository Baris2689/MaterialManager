package me.cybermaxke.materialmanager.enchantments.food;

import me.cybermaxke.materialmanager.enchantments.EnchantmentFood;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnchantmentFoodPoison extends EnchantmentFood {

	public EnchantmentFoodPoison(int id) {
		super(id, "Poisoning", 10, 1);
	}
	
	@Override
	public void onEat(Player p, ItemStack is, int lvl) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10 * lvl, 7 * lvl));
	}
}