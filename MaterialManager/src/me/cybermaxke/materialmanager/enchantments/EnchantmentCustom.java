package me.cybermaxke.materialmanager.enchantments;

import java.lang.reflect.Field;

import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class EnchantmentCustom extends Enchantment {
	private int minLvl;
	private int maxLvl;
	private String name;
	
	public EnchantmentCustom(int id, String name, int maxLvl, int minLvl) {
	    super(id);
	    this.name = name;
	    this.maxLvl = maxLvl;
	    this.minLvl = minLvl;
	     
	    try {
	    	boolean v = org.bukkit.enchantments.Enchantment.isAcceptingRegistrations();
	    	
	      	Field f = org.bukkit.enchantments.Enchantment.class.getDeclaredField("acceptingNew");
	      	f.setAccessible(true);
	      	f.set(null, Boolean.valueOf(true));
	      	
	      	org.bukkit.enchantments.Enchantment.registerEnchantment(this);
	      	
	      	f.set(null, Boolean.valueOf(v));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	@Override
	public boolean canEnchantItem(ItemStack is) {
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment ench) {
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ALL;
	}

	@Override
	public int getMaxLevel() {
		return this.maxLvl;
	}

	@Override
	public String getName() {
		return "CustomEnchantent" + this.getId();
	}
	
	public String getEnchantmentName() {
		return this.name;
	}

	@Override
	public int getStartLevel() {
		return this.minLvl;
	}
	
	public void onHit(Player player, LivingEntity ent, int lvl) {
		
	}
	
	public void onProjectileHitEntity(Player player, Projectile p, LivingEntity ent, int lvl) {
		
	}
	
	public void onProjectileHit(Player player, Projectile p, int lvl) {
		
	}
	
	public void onInteract(Player player, Action action, Block block, int lvl) {
		
	}
	
	public void onTick(Player player, ItemStack is, int lvl) {
		
	}
	
	public void onEat(Player player, ItemStack is, int lvl) {
		
	}
}