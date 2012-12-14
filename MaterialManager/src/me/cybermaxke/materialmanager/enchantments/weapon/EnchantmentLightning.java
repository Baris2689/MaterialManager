package me.cybermaxke.materialmanager.enchantments.weapon;

import java.util.Random;

import me.cybermaxke.materialmanager.enchantments.EnchantmentWeapon;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

public class EnchantmentLightning extends EnchantmentWeapon {

	public EnchantmentLightning(int id) {
		super(id, "Lightning", 10, 1);
	}

	@Override
	public void onHit(Player p, LivingEntity e, int lvl) {
		if (new Random().nextInt(100) < 55 + 3 * lvl) {
			e.getWorld().strikeLightningEffect(e.getLocation());
			e.damage((int) (0.275 * lvl), p);
		}
	}
	
	@Override
	public void onProjectileHit(Player p, Projectile a, int lvl) {
		if (new Random().nextInt(100) < 55 + 3 * lvl) {
			a.getWorld().strikeLightningEffect(a.getLocation());
			
			for (Entity ent : a.getNearbyEntities(2, 2, 2)) {
				if (ent instanceof LivingEntity) {
					if (!ent.equals(p)) {
						((LivingEntity) ent).damage((int) (0.275 * lvl), p);
					}
				}
			}
		}
	}
}