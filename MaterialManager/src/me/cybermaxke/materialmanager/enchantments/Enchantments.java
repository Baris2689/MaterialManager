package me.cybermaxke.materialmanager.enchantments;

import java.lang.reflect.Field;

import me.cybermaxke.materialmanager.enchantments.armor.EnchantmentArmorJump;
import me.cybermaxke.materialmanager.enchantments.armor.EnchantmentArmorNightVision;
import me.cybermaxke.materialmanager.enchantments.armor.EnchantmentArmorPhoenix;
import me.cybermaxke.materialmanager.enchantments.armor.EnchantmentArmorSpeed;
import me.cybermaxke.materialmanager.enchantments.armor.EnchantmentArmorWaterBreathing;
import me.cybermaxke.materialmanager.enchantments.food.EnchantmentFoodHealing;
import me.cybermaxke.materialmanager.enchantments.food.EnchantmentFoodPoison;
import me.cybermaxke.materialmanager.enchantments.weapon.EnchantmentExplosion;
import me.cybermaxke.materialmanager.enchantments.weapon.EnchantmentFire;
import me.cybermaxke.materialmanager.enchantments.weapon.EnchantmentHealing;
import me.cybermaxke.materialmanager.enchantments.weapon.EnchantmentLightning;
import me.cybermaxke.materialmanager.enchantments.weapon.EnchantmentPoison;
import me.cybermaxke.materialmanager.enchantments.weapon.EnchantmentSlowness;
import me.cybermaxke.materialmanager.enchantments.weapon.EnchantmentStrength;
import me.cybermaxke.materialmanager.enchantments.weapon.EnchantmentWeakness;

import org.bukkit.enchantments.Enchantment;

public class Enchantments {
	public static final EnchantmentCustom ENCHANTMENT_DATA = new EnchantmentData(150);
	public static final EnchantmentCustom ENCHANTMENT_DURABILITY = new EnchantmentData(151);
	public static final EnchantmentCustom ENCHANTMENT_MAX_DURABILITY = new EnchantmentData(152);
	
	public static final EnchantmentCustom WEAPON_EXPLOSION = new EnchantmentExplosion(201);
	public static final EnchantmentCustom WEAPON_HEALING = new EnchantmentHealing(202);
	public static final EnchantmentCustom WEAPON_LIGHTNING = new EnchantmentLightning(203);
	public static final EnchantmentCustom WEAPON_POISON = new EnchantmentPoison(204);
	public static final EnchantmentCustom WEAPON_SLOWNESS = new EnchantmentSlowness(205);
	public static final EnchantmentCustom WEAPON_STRENGTH = new EnchantmentStrength(206);
	public static final EnchantmentCustom WEAPON_WEAKNESS = new EnchantmentWeakness(207);
	public static final EnchantmentCustom WEAPON_FIRE = new EnchantmentFire(208);
	
	public static final EnchantmentCustom ARMOR_JUMP = new EnchantmentArmorJump(251);
	public static final EnchantmentCustom ARMOR_NIGHT_VISION = new EnchantmentArmorNightVision(252);
	public static final EnchantmentCustom ARMOR_FIRE = new EnchantmentArmorPhoenix(253);
	public static final EnchantmentCustom ARMOR_WATER_BREATHING = new EnchantmentArmorWaterBreathing(254);
	public static final EnchantmentCustom ARMOR_SPEED = new EnchantmentArmorSpeed(255);
	
	public static final EnchantmentCustom FOOD_HEALING = new EnchantmentFoodHealing(100);
	public static final EnchantmentCustom FOOD_POISON = new EnchantmentFoodPoison(101);
	
	public static Enchantment getBukkitEnchantment(String name) {
		try {
			Field f = Enchantment.class.getDeclaredField(name.toUpperCase());
			try {
				return (Enchantment) f.get(f);
			} catch (Exception e) {}
		} catch (NoSuchFieldException e) {}
		
		return null;
	}
	
	public static EnchantmentCustom getCustomEnchantment(String name) {
		try {
			Field f = Enchantments.class.getDeclaredField(name.toUpperCase());
			try {
				return (EnchantmentCustom) f.get(f);
			} catch (Exception e) {}
		} catch (NoSuchFieldException e) {}
		
		return null;
	}
	
	public static Enchantment getEnchantment(String name) {
		if (getBukkitEnchantment(name) != null) {
			return getBukkitEnchantment(name);
		}
		
		if (getCustomEnchantment(name) != null) {
			return getCustomEnchantment(name);
		}
		
		return null;
	}
}