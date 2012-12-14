package me.cybermaxke.materialmanager.enchantments.weapon;

import me.cybermaxke.materialmanager.enchantments.EnchantmentWeapon;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnchantmentWeakness extends EnchantmentWeapon {

	public EnchantmentWeakness(int id) {
		super(id, "Weakness", 10, 1);
	}
	
	@Override
	public void onHit(Player p, LivingEntity e, int lvl) {
		e.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10 * lvl, 7 * lvl));
	}
	
	@Override
	public void onProjectileHitEntity(Player p, Projectile a, LivingEntity e, int lvl) {
		this.onHit(p, e, lvl);
	}
}