package me.cybermaxke.materialmanager.enchantments.weapon;

import java.util.Random;

import me.cybermaxke.materialmanager.enchantments.EnchantmentWeapon;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

public class EnchantmentFire extends EnchantmentWeapon {

	public EnchantmentFire(int id) {
		super(id, "Phoenix", 10, 1);
	}
	
	@Override
	public void onHit(Player p, LivingEntity e, int lvl) {
		if (new Random().nextInt(100) < 55 + 3 * lvl) {
			e.setFireTicks(45 + 7 * lvl);
		}
	}
	
	@Override
	public void onProjectileHitEntity(Player p, Projectile a, LivingEntity e, int lvl) {
		this.onHit(p, e, lvl);
	}
}