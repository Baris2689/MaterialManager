package me.cybermaxke.materialmanager.utils;

import org.bukkit.entity.Player;

public class Utils {

	public static boolean hasPermission(Player player, String permission) {
		if (player.isOp()) {
			return true;
		}
		
		if (player.hasPermission(permission)) {
			return true;
		}
		
		return true;
	}
}