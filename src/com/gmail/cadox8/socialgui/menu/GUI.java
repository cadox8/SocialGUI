package com.gmail.cadox8.socialgui.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.cadox8.socialgui.files.Files;
import com.gmail.cadox8.socialgui.utils.Messages;
import com.gmail.cadox8.socialgui.utils.SkullManager;

public class GUI {

	public static HashMap<Player, Integer> playerPage = new HashMap<Player, Integer>();

	public static void openGUI(Player p, int page){
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', Files.cfg.getString("GUI.Name")));
		int tot = Files.social.getInt("id");
		List<ItemStack> items = getItemsPerPage(p, page);

		if (items.isEmpty()) {
			p.sendMessage(Messages.prefix + ChatColor.AQUA + "Sorry, but there aren't links to show");
			return;
		}

		for (ItemStack i : items) {
			inv.addItem(i);
		}

		if (page == 1 && Math.round(tot / 45) >= 1) {
			inv.setItem(50, getNextItem());
		}

		if (page >= 2) {
			if (Math.round(tot / 45) >= page) {
				inv.setItem(50, getNextItem());
			}
			inv.setItem(47, getPrevItem());
		}

		p.openInventory(inv);
	}

	public static ItemStack getNextItem(){
		ItemStack i = new ItemStack(Material.ARROW);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Next Page");
		i.setItemMeta(im);

		return i;
	}

	public static ItemStack getPrevItem(){
		ItemStack i = new ItemStack(Material.ARROW);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.AQUA + "Prev Page");
		i.setItemMeta(im);

		return i;
	}

	private static List<ItemStack> getItemsPerPage(Player p, int page){
		List<ItemStack> items = new ArrayList<ItemStack>();
		List<String> lore = new ArrayList<String>();
		int min = 46;
		int tot = Files.social.getInt("id");

		lore.clear();
		items.clear();

		lore.add(ChatColor.GOLD + "Left Click to view");

		if (p.hasPermission("socialgui.admin")) {
			lore.add(ChatColor.RED + "Shift + Right Click to delete");
		} else {
			lore.add("");
		}

		if (page == 1) {
			for (int x = 1; x < min; x++) {
				if (x > tot) {
					break;
				}
				if (Files.social.getString("social.type_" + x) == null) {
					continue;
				}
				lore.add(x + "");
				items.add(SkullManager.getSkull(Files.social.getString("social.type_" + x), ChatColor.AQUA + WordUtils.capitalizeFully(Files.social.getString("social.type_" + x) + ChatColor.BLACK + " - " + ChatColor.GREEN + WordUtils.capitalizeFully(Files.social.getString("social.player_" + x))), lore));
				lore.remove(2);
			}
		}

		if (page >= 2) {
			for (int x = (min * page) - min; x < min * page; x++) {
				if (x > tot) {
					break;
				}
				if (Files.social.getString("social.type_" + x) == null) {
					continue;
				}
				lore.add(x + "");
				items.add(SkullManager.getSkull(Files.social.getString("social.type_" + x), ChatColor.AQUA + WordUtils.capitalizeFully(Files.social.getString("social.type_" + x) + ChatColor.BLACK + " - " + ChatColor.GREEN + WordUtils.capitalizeFully(Files.social.getString("social.player_" + x))), lore));
				lore.remove(2);
			}
		}

		return items;
	}
}
