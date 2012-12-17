package me.cybermaxke.materialmanager.inventory;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import me.cybermaxke.materialmanager.enchantments.Enchantments;
import me.cybermaxke.materialmanager.materials.Material;
import me.cybermaxke.materialmanager.materials.MaterialData;

import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.ChatColor;

public class CustomItemStack extends CraftItemStack {
	private Material mat;

	public CustomItemStack(org.bukkit.Material type) {
		super(type);
	}
	
	public CustomItemStack(org.bukkit.Material type, byte data) {
		super(type, data);
	}

	public CustomItemStack(ItemStack item) {
		super(item);
		
		if (this.containsEnchantment(Enchantments.ENCHANTMENT_DATA)) {
			int data = this.getEnchantmentLevel(Enchantments.ENCHANTMENT_DATA);
			this.mat = MaterialData.getByCustomId(data);
		}
	}
	
	public CustomItemStack(Material mat) {
		super(mat.getType());
		this.mat = mat;
		
		this.addUnsafeEnchantment(Enchantments.ENCHANTMENT_DATA, mat.getCustomId());
		this.addUnsafeEnchantment(Enchantments.ENCHANTMENT_DURABILITY, 0);
		this.addUnsafeEnchantments(mat.getEnchantments());
		this.setName(mat.getName());
		
		if (mat.getData() != -1) {
			this.getHandle().setData(mat.getData());
		}
		
		if (mat.getColor() != null) {
			this.setColor(mat.getColor());
		}
		
		this.clearLore();
		if (mat.getLore() != null) {
			this.setLore(mat.getLore());
		}
		
		if (mat.getSkullOwner() != null) {
			this.setSkullOwner(mat.getSkullOwner());
		}	
	}
	
	public CustomItemStack(org.bukkit.inventory.ItemStack i) {
		super(i);
		
		if (this.containsEnchantment(Enchantments.ENCHANTMENT_DATA)) {
			int data = this.getEnchantmentLevel(Enchantments.ENCHANTMENT_DATA);
			this.mat = MaterialData.getByCustomId(data);
		}
	}

	@Override
    public void setDurability(short durability) {
		if (this.isCustomItem() && durability > this.mat.getMaxDurability()) {
			durability = (short) this.mat.getMaxDurability();
		} else if (durability > this.getType().getMaxDurability()) {
    		durability = (short) this.getType().getMaxDurability();
    	}
    	
        if (this.containsEnchantment(Enchantments.ENCHANTMENT_DURABILITY)) {
        	this.removeEnchantment(Enchantments.ENCHANTMENT_DURABILITY);
		}
        
        this.addUnsafeEnchantment(Enchantments.ENCHANTMENT_DURABILITY, durability);
    }
	
	@Override
	public short getDurability() {
		if (this.containsEnchantment(Enchantments.ENCHANTMENT_DURABILITY)) {
			return (short) this.getEnchantmentLevel(Enchantments.ENCHANTMENT_DURABILITY);
		}
		
		return (short) this.getHandle().getData();
	}

	public CustomItemStack setName(String name) {
		this.getHandle().c(name);
		return this;
	}
	
	public String getName() {
		return this.getHandle().r();
	}
	
	public Material getMaterial() {
		return this.mat;
	}
	
	public boolean isCustomItem() {
		return this.mat != null;
	}
	
	public CustomItemStack setLore(String... lore) {
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
   	 
    	if (this.getHandle().getTag().hasKey("display")) {
    		tag = this.getHandle().getTag().getCompound("display");
    	}
     	
     	for (int i = 0; i < lore.length; i++) {
     		if (lore[i] != null) {
     			list.add(new NBTTagString("", ChatColor.translateAlternateColorCodes('&', lore[i])));
     		}
     	}
 
     	tag.set("Lore", list);
     	this.getHandle().getTag().setCompound("display", tag);
		return this;
	}
	
	public String[] getLore() { 	  	 
     	if (!this.getHandle().getTag().hasKey("display") || !this.getHandle().getTag().hasKey("Lore")) {
        	return null;
        }
     	
     	List<String> l = new ArrayList<String>();
		NBTTagCompound tag = this.getHandle().getTag().getCompound("display");
		NBTTagList list = tag.getList("Lore");
     	
     	for (int i = 0; i < list.size(); i++) {
     		l.add(list.get(i).getName());
     	}
     	
     	return l.toArray(new String[] {});
	}
	
	public CustomItemStack clearLore() {
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
   	 
    	if (this.getHandle().getTag().hasKey("display")) {
    		tag = this.getHandle().getTag().getCompound("display");
    	}
    	
    	tag.set("Lore", list);
     	this.getHandle().getTag().setCompound("display", tag);
		return this;
	}
	
	public CustomItemStack setColor(Color color) {
		NBTTagCompound tag = new NBTTagCompound();
   	 
    	if (this.getHandle().getTag().hasKey("display")) {
    		tag = this.getHandle().getTag().getCompound("display");
    	}
    	
    	tag.setInt("color", color.getRGB());
    	this.getHandle().getTag().setCompound("display", tag);
    	return this;
	}
	
	public Color getColor() {	   	 
    	if (!this.getHandle().getTag().hasKey("display")) {
    		return null;
    	}
    	
    	NBTTagCompound tag = this.getHandle().getTag().getCompound("display");
    	
    	if (!tag.hasKey("color")) {
    		return null;
    	}
    	
    	return new Color(tag.getInt("color"));
	}
	
	public CustomItemStack setSkullOwner(String owner) {
		NBTTagCompound tag = this.getHandle().getTag();
    	
    	tag.setString("SkullOwner", owner);
    	this.getHandle().tag = tag;
    	return this;
	}
	
	public String getSkullOwner() {	   	 
		NBTTagCompound tag = this.getHandle().getTag();
    	
    	if (!tag.hasKey("SkullOwner")) {
    		return null;
    	}
    	
    	return tag.getString("SkullOwner");
	}
}