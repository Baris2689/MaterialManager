package me.cybermaxke.materialmanager.enchantments.weapon;

import java.util.Random;

import me.cybermaxke.materialmanager.enchantments.EnchantmentWeapon;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

public class EnchantmentExplosion extends EnchantmentWeapon {

	public EnchantmentExplosion(int id) {
		super(id, "Explosive", 10, 1);
	}

	@Override
	public void onHit(Player p, LivingEntity e, int lvl) {
		if (new Random().nextInt(100) < 55 + 3 * lvl) {
			e.getWorld().createExplosion(e.getLocation(), 0F);
			e.damage((int) (1.2 + 0.275 * lvl), p);
		}
	}
	
	@Override
	public void onProjectileHit(Player p, Projectile a, int lvl) {
		if (new Random().nextInt(100) < 55 + 3 * lvl) {
			a.getWorld().createExplosion(a.getLocation(), 0F);
			
			for (Entity ent : a.getNearbyEntities(3, 3, 3)) {
				if (ent instanceof LivingEntity) {
					if (!ent.equals(p)) {
						((LivingEntity) ent).damage((int) (1.2 + 0.275 * lvl), p);
					}
				}
			}
		}
	}
}