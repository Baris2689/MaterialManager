package me.cybermaxke.materialmanager.item;

import java.lang.reflect.Method;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import me.cybermaxke.materialmanager.inventory.CustomItemStack;
import me.cybermaxke.materialmanager.enchantments.EnchantmentFood;

import net.minecraft.server.Block;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.Item;
import net.minecraft.server.ItemFood;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MobEffect;
import net.minecraft.server.MobEffectList;
import net.minecraft.server.PotionBrewer;
import net.minecraft.server.World;

public class CustomItemFood extends ItemFood {
	private int cn;
	private int co;
	private int cp;
	private float cq;

	public CustomItemFood(int i, int j, float f, boolean flag) {
		super(i, j, f, flag);
	}

	public CustomItemFood(int i, int j, boolean flag) {
		this(j, j, 0.6F, flag);
	}

	@Override
	public void c(ItemStack itemstack, World world, EntityHuman entityhuman) {
		Player player = (Player) entityhuman.getBukkitEntity();
		CustomItemStack is = new CustomItemStack(itemstack);
		 	
		for (Entry<org.bukkit.enchantments.Enchantment, Integer> enchs : is.getEnchantments().entrySet()) {
			if (enchs.getKey() instanceof EnchantmentFood) {
				((EnchantmentFood) enchs.getKey()).onEat(player, is, enchs.getValue());
			}
		}
		    
		if (!is.isCustomItem()) {
			if ((!world.isStatic) && (this.cn > 0) && (world.random.nextFloat() < this.cq)) {
				entityhuman.addEffect(new MobEffect(this.cn, this.co * 20, this.cp));
			}
		}
	}
	
	@Override
	public ItemFood a(int i, int j, int k, float f) {
	    this.cn = i;
	    this.co = j;
	    this.cp = k;
	    this.cq = f;
	    return this;
	}
	
	public static void updateFood() {
		int breadId = Item.BREAD.id;
		int rawFishId = Item.RAW_FISH.id;
		int cookedFishId = Item.COOKED_FISH.id;
		int cookieId = Item.COOKIE.id;
		int pumpkinPieId = Item.PUMPKIN_PIE.id;
		int carrotGoldenId = Item.CARROT_GOLDEN.id;
		int carrotId = Item.CARROT.id;
		int potatoId = Item.POTATO.id;
		int potatoBakedId = Item.POTATO_BAKED.id;
		int potatoPoisonId = Item.POTATO_POISON.id;
		int rawBeefId = Item.RAW_BEEF.id;
		int cookedBeefId = Item.COOKED_BEEF.id;
		int rawChickenId = Item.RAW_CHICKEN.id;
		int cookedChickenId = Item.COOKED_CHICKEN.id;
		int rottenFleshId = Item.COOKED_CHICKEN.id;
		int melonId = Item.MELON.id;
		int goldenAppleId = Item.GOLDEN_APPLE.id;
		int porkId = Item.PORK.id;
		int grilledPorkId = Item.GRILLED_PORK.id;
		int appleId = Item.APPLE.id;
		int mushroomSoupId = Item.MUSHROOM_SOUP.id;
		
		Item.byId[mushroomSoupId] = null;
		Item.byId[mushroomSoupId] = new CustomItemSoup(26, 6).b(8, 4).b("mushroomStew");
		
		Item.byId[appleId] = null;
		Item.byId[appleId] = new CustomItemFood(4, 4, 0.3F, false).b(10, 0).b("apple");
		
		Item.byId[appleId] = null;
		Item.byId[appleId] = new CustomItemFood(4, 4, 0.3F, false).b(10, 0).b("apple");
		
		Item.byId[grilledPorkId] = null;
		Item.byId[grilledPorkId] = new CustomItemFood(64, 8, 0.8F, true).b(8, 5).b("porkchopCooked");
		
		Item.byId[porkId] = null;
		Item.byId[porkId] = new CustomItemFood(63, 3, 0.3F, true).b(7, 5).b("porkchopRaw");
		
		Item.byId[goldenAppleId] = null;
		Item.byId[goldenAppleId] = new CustomItemGoldenApple(66, 4, 1.2F, false).j().a(MobEffectList.REGENERATION.id, 5, 0, 1.0F).b(11, 0).b("appleGold");
		
		Item.byId[melonId] = null;
		Item.byId[melonId] = new CustomItemFood(104, 2, 0.3F, false).b(13, 6).b("melon");

		Item.byId[rottenFleshId] = null;
		Item.byId[rottenFleshId] = new CustomItemFood(111, 4, 0.1F, true).a(MobEffectList.HUNGER.id, 30, 0, 0.8F).b(11, 5).b("rottenFlesh");
		
		Item.byId[cookedChickenId] = null;
		Item.byId[cookedChickenId] = new CustomItemFood(110, 6, 0.6F, true).b(10, 7).b("chickenCooked");
		
		Item.byId[rawChickenId] = null;
		Item.byId[rawChickenId] = new CustomItemFood(109, 2, 0.3F, true).a(MobEffectList.HUNGER.id, 30, 0, 0.3F).b(9, 7).b("chickenRaw");
		
		Item.byId[rawBeefId] = null;
		Item.byId[rawBeefId] = new CustomItemFood(107, 3, 0.3F, true).b(9, 6).b("beefRaw");
		
		Item.byId[cookedBeefId] = null;
		Item.byId[cookedBeefId] = new CustomItemFood(108, 8, 0.8F, true).b(10, 6).b("beefCooked");

		Item.byId[breadId] = null;
		Item.byId[breadId] = new CustomItemFood(41, 5, 0.6F, false).b(9, 2).b("bread");
		
		Item.byId[rawFishId] = null;
		Item.byId[rawFishId] = new CustomItemFood(93, 2, 0.3F, false).b(9, 5).b("fishRaw");
		
		Item.byId[cookedFishId] = null;
		Item.byId[cookedFishId] = new CustomItemFood(94, 5, 0.6F, false).b(10, 5).b("fishCooked");
		
		Item.byId[cookieId] = null;
		Item.byId[cookieId] = new CustomItemFood(101, 2, 0.1F, false).b(12, 5).b("cookie");
		
		Item.byId[pumpkinPieId] = null;
		Item.byId[pumpkinPieId] = new CustomItemFood(144, 8, 0.3F, false).b(8, 9).b("pumpkinPie").a(CreativeModeTab.h);
		
		Item.byId[carrotGoldenId] = null;
		Item.byId[carrotGoldenId] = new CustomItemFood(140, 6, 1.2F, false).b(6, 9).b("carrotGolden");
		invokeMethod("c", Item.class, new Class[] { PotionBrewer.class }, Item.byId[carrotGoldenId], new Object[] { PotionBrewer.l });
		
		Item.byId[carrotId] = null;
		Item.byId[carrotId] = new CustomItemSeedFood(135, 4, 0.6F, Block.CARROTS.id, Block.SOIL.id).b(8, 7).b("carrots");
		
		Item.byId[potatoId] = null;
		Item.byId[potatoId] = new CustomItemSeedFood(136, 1, 0.3F, Block.POTATOES.id, Block.SOIL.id).b(7, 7).b("potato");
		
		Item.byId[potatoBakedId] = null;
		Item.byId[potatoBakedId] = new CustomItemFood(137, 6, 0.6F, false).b(6, 7).b("potatoBaked");
		
		Item.byId[potatoPoisonId] = null;
		Item.byId[potatoPoisonId] = new ItemFood(138, 2, 0.3F, false).a(MobEffectList.POISON.id, 5, 0, 0.6F).b(6, 8).b("potatoPoisonous");
	}
	
	private static void invokeMethod(String name, Class<?> clas, Class<?>[] classes, Object obj, Object[] objs) {
		try {
			Method m = clas.getDeclaredMethod(name, classes);
			m.setAccessible(true);
			m.invoke(obj, objs);
		} catch (Exception e) {}
	}
}