package me.cybermaxke.materialmanager;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.ItemSword;

import me.cybermaxke.materialmanager.enchantments.EnchantmentCustom;
import me.cybermaxke.materialmanager.inventory.CustomItemStack;

public class PlayerListener implements Listener {
	
	public PlayerListener(JavaPlugin p) {
		Bukkit.getServer().getPluginManager().registerEvents(this, p);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getItemInHand();
		
		if (i.getEnchantments().isEmpty())
			return;
		
		for (Entry<org.bukkit.enchantments.Enchantment, Integer> enchs : i.getEnchantments().entrySet()) {
			if (enchs.getKey() instanceof EnchantmentCustom) {
				((EnchantmentCustom) enchs.getKey()).onInteract(p, e.getAction(), e.getClickedBlock(), enchs.getValue());
			}
		}
	}

	@EventHandler
	public void onEntityDamageBySword(EntityDamageByEntityEvent e) {
		if (e.isCancelled())
			return;
		
		if (!(e.getDamager() instanceof Player))
			return;
		
		if (!(e.getEntity() instanceof LivingEntity))
			return;
		
		Player p = (Player) e.getDamager();
		ItemStack i = p.getItemInHand();
		
		if (i == null)
			return;
		
		CustomItemStack is = new CustomItemStack(i);
		if (is.isCustomItem() && is.getHandle().getItem() instanceof ItemSword) {			
			int ds = 1;
			int de = e.getDamage();
			
			try {
				Field f = ItemSword.class.getDeclaredField("damage");
				f.setAccessible(true);
				ds = (int) f.get(is.getHandle().getItem());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			e.setDamage((de / ds) * is.getMaterial().getDamage());		
		}
		
		if (i.getEnchantments().isEmpty())
			return;
		
		for (Entry<org.bukkit.enchantments.Enchantment, Integer> enchs : i.getEnchantments().entrySet()) {
			if (enchs.getKey() instanceof EnchantmentCustom) {
				((EnchantmentCustom) enchs.getKey()).onHit(p, (LivingEntity) e.getEntity(), enchs.getValue());
			}
		}
	}
	
	private static Map<UUID, ItemStack> projectileData = new HashMap<UUID, ItemStack>();
	
	public static void addProjectileData(Projectile p, ItemStack w) {
		projectileData.put(p.getUniqueId(), w);
	}
	
	@EventHandler
	public void onArrowShoot(EntityShootBowEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		
		if (!(e.getProjectile() instanceof Arrow))
			return;
		
		Arrow a = (Arrow) e.getProjectile();
		Player p = (Player) e.getEntity();
		ItemStack i = p.getItemInHand();
		
		projectileData.put(a.getUniqueId(), i);
	}
	
	@EventHandler
	public void onEntityDamageByBow(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Projectile))
			return;
		
		if (!(e.getEntity() instanceof LivingEntity))
			return;
		
		Projectile a = (Projectile) e.getDamager();
		
		if (!(a.getShooter() instanceof Player))
			return;
		
		if (!(projectileData.containsKey(a.getUniqueId())))
			return;
		
		Player p = (Player) a.getShooter();
		ItemStack i = projectileData.get(a.getUniqueId());
		
		if (i.getEnchantments().isEmpty())
			return;
		
		for (Entry<org.bukkit.enchantments.Enchantment, Integer> enchs : i.getEnchantments().entrySet()) {
			if (enchs.getKey() instanceof EnchantmentCustom) {
				((EnchantmentCustom) enchs.getKey()).onProjectileHitEntity(p, a, (LivingEntity) e.getEntity(), enchs.getValue());
			}
		}
		
		projectileData.remove(a.getUniqueId());
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		Projectile a = e.getEntity();
		
		if (!(a.getShooter() instanceof Player))
			return;
		
		if (!(projectileData.containsKey(a.getUniqueId())))
			return;
		
		Player p = (Player) a.getShooter();
		ItemStack i = projectileData.get(a.getUniqueId());
		
		if (i.getEnchantments().isEmpty())
			return;
		
		for (Entry<org.bukkit.enchantments.Enchantment, Integer> enchs : i.getEnchantments().entrySet()) {
			if (enchs.getKey() instanceof EnchantmentCustom) {
				((EnchantmentCustom) enchs.getKey()).onProjectileHit(p, a, enchs.getValue());
			}
		}
		
		projectileData.remove(a.getUniqueId());
	}
}