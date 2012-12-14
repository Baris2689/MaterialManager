package me.cybermaxke.materialmanager;

import java.util.Map.Entry;

import me.cybermaxke.materialmanager.enchantments.EnchantmentCustom;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Timer implements Runnable {
	
	public Timer(JavaPlugin p) {
		p.getServer().getScheduler().scheduleAsyncRepeatingTask(p, this, 0, 1);
	}

	@Override
	public void run() {
		if (Bukkit.getOnlinePlayers() == null)
			return;
		
		for (int ps = 0; ps < Bukkit.getOnlinePlayers().length; ps++) {
			Player p = Bukkit.getOnlinePlayers()[ps];
			PlayerInventory inv = p.getInventory();
			
			for (int a = 0; a < inv.getArmorContents().length; a++) {
				ItemStack is = inv.getArmorContents()[a];
				
				if (!is.getEnchantments().isEmpty()) {			
					for (Entry<org.bukkit.enchantments.Enchantment, Integer> enchs : is.getEnchantments().entrySet()) {
						if (enchs.getKey() instanceof EnchantmentCustom) {
							((EnchantmentCustom) enchs.getKey()).onTick(p, is, enchs.getValue());
						}
					}
				}
			}
		}
	}
}