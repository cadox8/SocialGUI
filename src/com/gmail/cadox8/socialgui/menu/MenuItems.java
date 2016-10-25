package com.gmail.cadox8.socialgui.menu;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.cadox8.socialgui.files.Files;
import com.gmail.cadox8.socialgui.utils.SkullManager;

public class MenuItems {

	public List<ItemStack> getItemsPerPage(Player p, int page){
		List<ItemStack> items = new ArrayList<ItemStack>();
		List<String> lore = new ArrayList<String>();
		int min = 46;
		int tot = Files.social.getInt("id");

		lore.clear();
		items.clear();

		lore.add(ChatColor.GOLD + "Left Click to view");

		if(p.hasPermission("socialgui.admin")){
			lore.add(ChatColor.RED + "Shift + Right Click to delete");
		}else{
			lore.add(ChatColor.RED + "Right Click to report");
		}

		if(page == 1){
			for(int x = 1; x < min; x++){
				if(x > tot){
					break;
				}
				if(Files.social.getString("social.type_" + x) == null){
					continue;
				}
				lore.add(x + "");
				items.add(SkullManager.getSkull(Files.social.getString("social.type_" + x), ChatColor.AQUA + WordUtils.capitalizeFully(Files.social.getString("social.type_" + x) + ChatColor.BLACK + " - " + ChatColor.GREEN + WordUtils.capitalizeFully(Files.social.getString("social.player_" + x))), lore));
				lore.remove(2);
			}
		}

		if(page >= 2){
			for(int x = (min * page) - min; x < min * page; x++){
				if(x > tot){
					break;
				}
				if(Files.social.getString("social.type_" + x) == null){
					continue;
				}
				lore.add(x + "");
				items.add(SkullManager.getSkull(Files.social.getString("social.type_" + x), ChatColor.AQUA + WordUtils.capitalizeFully(Files.social.getString("social.type_" + x) + ChatColor.BLACK + " - " + ChatColor.GREEN + WordUtils.capitalizeFully(Files.social.getString("social.player_" + x))), lore));
				lore.remove(2);
			}
		}

		return items;
	}

	public List<ItemStack> getItemsByPlayer(Player p, int page, String player){
		List<ItemStack> items = new ArrayList<ItemStack>();
		List<String> lore = new ArrayList<String>();
		int min = 46;
		int tot = Files.social.getInt("id");

		lore.clear();
		items.clear();

		lore.add(ChatColor.GOLD + "Left Click to view");

		if(p.hasPermission("socialgui.admin")){
			lore.add(ChatColor.RED + "Shift + Right Click to delete");
		}else{
			lore.add(ChatColor.RED + "Right Click to report");
		}

		if(page == 1){
			for(int x = 1; x < min; x++){
				if(x > tot){
					break;
				}
				if(Files.social.getString("social.type_" + x) == null){
					continue;
				}
				if(Files.social.getString("social.player_" + x).equalsIgnoreCase(player)){
					lore.add(x + "");
					items.add(SkullManager.getSkull(Files.social.getString("social.type_" + x), ChatColor.AQUA + WordUtils.capitalizeFully(Files.social.getString("social.type_" + x) + ChatColor.BLACK + " - " + ChatColor.GREEN + WordUtils.capitalizeFully(Files.social.getString("social.player_" + x))), lore));
					lore.remove(2);

					continue;
				}
			}
		}

		if(page >= 2){
			for(int x = (min * page) - min; x < min * page; x++){
				if(x > tot){
					break;
				}
				if(Files.social.getString("social.type_" + x) == null){
					continue;
				}
				if(Files.social.getString("social.player_" + x).equalsIgnoreCase(player)){
					lore.add(x + "");
					items.add(SkullManager.getSkull(Files.social.getString("social.type_" + x), ChatColor.AQUA + WordUtils.capitalizeFully(Files.social.getString("social.type_" + x) + ChatColor.BLACK + " - " + ChatColor.GREEN + WordUtils.capitalizeFully(Files.social.getString("social.player_" + x))), lore));
					lore.remove(2);

					continue;
				}
			}
		}

		return items;
	}

	public List<ItemStack> getItemsByType(Player p, int page, String type){
		List<ItemStack> items = new ArrayList<ItemStack>();
		List<String> lore = new ArrayList<String>();
		int min = 46;
		int tot = Files.social.getInt("id");

		lore.clear();
		items.clear();

		lore.add(ChatColor.GOLD + "Left Click to view");

		if(p.hasPermission("socialgui.admin")){
			lore.add(ChatColor.RED + "Shift + Right Click to delete");
		}else{
			lore.add(ChatColor.RED + "Right Click to report");
		}

		if(page == 1){
			for(int x = 1; x < min; x++){
				if(x > tot){
					break;
				}
				if(Files.social.getString("social.type_" + x) == null){
					continue;
				}
				if(Files.social.getString("social.type_" + x).equalsIgnoreCase(type)){
					lore.add(x + "");
					items.add(SkullManager.getSkull(Files.social.getString("social.type_" + x), ChatColor.AQUA + WordUtils.capitalizeFully(Files.social.getString("social.type_" + x) + ChatColor.BLACK + " - " + ChatColor.GREEN + WordUtils.capitalizeFully(Files.social.getString("social.player_" + x))), lore));
					lore.remove(2);

					continue;
				}
			}
		}

		if(page >= 2){
			for(int x = (min * page) - min; x < min * page; x++){
				if(x > tot){
					break;
				}
				if(Files.social.getString("social.type_" + x) == null){
					continue;
				}
				if(Files.social.getString("social.type_" + x).equalsIgnoreCase(type)){
					lore.add(x + "");
					items.add(SkullManager.getSkull(Files.social.getString("social.type_" + x), ChatColor.AQUA + WordUtils.capitalizeFully(Files.social.getString("social.type_" + x) + ChatColor.BLACK + " - " + ChatColor.GREEN + WordUtils.capitalizeFully(Files.social.getString("social.player_" + x))), lore));
					lore.remove(2);

					continue;
				}
			}
		}

		return items;
	}
}
