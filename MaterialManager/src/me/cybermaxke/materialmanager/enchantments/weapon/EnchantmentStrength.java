package me.cybermaxke.materialmanager.enchantments.weapon;

import me.cybermaxke.materialmanager.enchantments.EnchantmentWeapon;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnchantmentStrength extends EnchantmentWeapon {

	public EnchantmentStrength(int id) {
		super(id, "Strength", 10, 1);
	}
	
	@Override
	public void onHit(Player p, LivingEntity e, int lvl) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10 * lvl, 7 * lvl));
	}
	
	@Override
	public void onProjectileHitEntity(Player p, Projectile a, LivingEntity e, int lvl) {
		this.onHit(p, e, lvl);
	}
}