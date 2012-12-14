package me.cybermaxke.materialmanager.enchantments.armor;

import java.util.Random;

import me.cybermaxke.materialmanager.enchantments.EnchantmentArmor;

import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EnchantmentArmorPhoenix extends EnchantmentArmor {

	public EnchantmentArmorPhoenix(int id) {
		super(id, "Phoenix", 2, 1);
	}

	@Override
	public void onTick(Player player, ItemStack is, int lvl) {
		if (new Random().nextInt(100) < 5 + lvl * 1.2) {
			player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
		}
		
		if (new Random().nextInt(100) < 5 + lvl * 2.6) {
			for (Entity e : player.getNearbyEntities(2, 2, 2)) {
				if (e instanceof LivingEntity) {
					if (!e.equals(player)) {
						e.setFireTicks(20);
					}
				}
			}
		}
	}
}