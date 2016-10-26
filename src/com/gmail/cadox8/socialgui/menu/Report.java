package com.gmail.cadox8.socialgui.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Report {

	private int id;

	public void openReportGUI(Player p, int id){
		Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatColor.RED + "Report Menu");

		inv.setItem(0, getBadPage());
		inv.setItem(1, getSpam());
		inv.setItem(2, getOffensiveImage());
		inv.setItem(3, getBrokenLink());
		inv.setItem(4, getNoCorrect());

		this.id = id;
		p.openInventory(inv);
	}

	public int getID(){
		return id;
	}

	public ItemStack getBadPage(){
		ItemStack i = new ItemStack(Material.WOOL, 1, (short) 7);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Bad Page");
		i.setItemMeta(im);

		return i;
	}

	public ItemStack getSpam(){
		ItemStack i = new ItemStack(Material.SIGN);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.AQUA + "Spam");
		i.setItemMeta(im);

		return i;
	}

	public ItemStack getOffensiveImage(){
		ItemStack i = new ItemStack(Material.PAINTING);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Offensive Image");
		i.setItemMeta(im);

		return i;
	}

	public ItemStack getBrokenLink(){
		ItemStack i = new ItemStack(Material.REDSTONE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Broken Link");
		i.setItemMeta(im);

		return i;
	}

	public ItemStack getNoCorrect(){
		ItemStack i = new ItemStack(Material.BARRIER);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "No Correct Type-Link");
		i.setItemMeta(im);

		return i;
	}
}
