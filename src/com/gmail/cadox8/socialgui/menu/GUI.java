package com.gmail.cadox8.socialgui.menu;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.cadox8.socialgui.files.Files;
import com.gmail.cadox8.socialgui.utils.Messages;
import com.gmail.cadox8.socialgui.utils.SkullMaker;

public class GUI {

	public static HashMap<Player, Integer> playerPage = new HashMap<Player, Integer>();

	private MenuItems mi = new MenuItems();

	public void openGUI(Player p, int page){
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', Files.cfg.getString("GUI.Name")));
		int tot = Files.social.getInt("id");
		List<ItemStack> items = mi.getItemsPerPage(p, page);

		if(items.isEmpty()){
			p.sendMessage(Messages.prefix + ChatColor.AQUA + "Sorry, but there aren't links to show");
			return;
		}

		for(ItemStack i : items){
			inv.addItem(i);
		}

		inv.setItem(45, getTypeSearch());
		inv.setItem(53, getUserSearch());

		inv.setItem(49, getUserLinks(p));

		if(page == 1 && Math.round(tot / 45) >= 1){
			inv.setItem(50, getNextItem());
		}

		if(page >= 2){
			if(Math.round(tot / 45) >= page){
				inv.setItem(50, getNextItem());
			}
			inv.setItem(47, getPrevItem());
		}

		p.openInventory(inv);
	}

	public void openGUIPlayer(Player p, int page, String player){
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', Files.cfg.getString("GUI.Name")));
		int tot = Files.social.getInt("id");
		List<ItemStack> items = mi.getItemsByPlayer(p, page, player);

		if(items.isEmpty()){
			p.sendMessage(Messages.prefix + ChatColor.AQUA + "Sorry, but this player doesn't added links yet");
			return;
		}

		for(ItemStack i : items){
			inv.addItem(i);
		}

		inv.setItem(45, getTypeSearch());
		inv.setItem(53, getUserSearch());

		inv.setItem(49, getUserLinks(p));

		if(page == 1 && Math.round(tot / 45) >= 1){
			inv.setItem(50, getNextItem());
		}

		if(page >= 2){
			if(Math.round(tot / 45) >= page){
				inv.setItem(50, getNextItem());
			}
			inv.setItem(47, getPrevItem());
		}

		p.openInventory(inv);
	}

	public void openGUIType(Player p, int page, String type){
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', Files.cfg.getString("GUI.Name")) + ChatColor.BLACK + "(Search: " + ChatColor.AQUA + type + ChatColor.BLACK + ")");
		int tot = Files.social.getInt("id");
		List<ItemStack> items = mi.getItemsByType(p, page, type);

		if(items.isEmpty()){
			p.sendMessage(Messages.prefix + ChatColor.AQUA + "Sorry, but there aren't links with this type");
			return;
		}

		for(ItemStack i : items){
			inv.addItem(i);
		}

		inv.setItem(45, getTypeSearch());
		inv.setItem(53, getUserSearch());

		inv.setItem(49, getUserLinks(p));

		if(page == 1 && Math.round(tot / 45) >= 1){
			inv.setItem(50, getNextItem());
		}

		if(page >= 2){
			if(Math.round(tot / 45) >= page){
				inv.setItem(50, getNextItem());
			}
			inv.setItem(47, getPrevItem());
		}

		p.openInventory(inv);
	}

	public ItemStack getNextItem(){
		ItemStack i = new ItemStack(Material.ARROW);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Next Page");
		i.setItemMeta(im);

		return i;
	}

	public ItemStack getPrevItem(){
		ItemStack i = new ItemStack(Material.ARROW);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.AQUA + "Prev Page");
		i.setItemMeta(im);

		return i;
	}

	public ItemStack getUserSearch(){
		ItemStack i = new ItemStack(Material.SIGN);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Search By Player Name");
		i.setItemMeta(im);

		return i;
	}

	public ItemStack getTypeSearch(){
		ItemStack i = new SkullMaker().withSkinUrl("http://textures.minecraft.net/texture/843a77fd2ab245303ed2d1b26563ebc4653af4139a2b3daef15a8cc7defd0").withName(ChatColor.RED + "Search By Type").build();

		return i;
	}

	public ItemStack getUserLinks(Player p){
		return new SkullMaker().withOwner(p.getName()).withName(ChatColor.GOLD + "Get my links").build();
	}
}
