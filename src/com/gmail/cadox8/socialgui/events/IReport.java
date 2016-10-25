package com.gmail.cadox8.socialgui.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.gmail.cadox8.socialgui.SocialGUI;
import com.gmail.cadox8.socialgui.api.JsonAPI;
import com.gmail.cadox8.socialgui.files.Files;
import com.gmail.cadox8.socialgui.menu.Report;
import com.gmail.cadox8.socialgui.utils.Messages;

public class IReport implements Listener {

	private SocialGUI plugin;

	public IReport(SocialGUI Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onInteractGUI(InventoryClickEvent e){
		final Player p = (Player) e.getWhoClicked();
		int id = Report.getID();

		if(e.getInventory().getName().contains(ChatColor.RED + "Report Menu")){
			if(e.getCurrentItem() == null){
				return;
			}
			if(e.getCurrentItem().getItemMeta() == null){
				return;
			}

			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Report.getBadPage().getItemMeta().getDisplayName())){
				e.setCancelled(true);
				p.closeInventory();

				for(Player pl : Bukkit.getOnlinePlayers()){
					if(pl.hasPermission("socialgui.admin")){
						pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_PLING, 5.0F, 5.0F);
						pl.sendMessage(Messages.reportLink.replace("%player%", p.getName()).replace("%id%", id + ""));
						JsonAPI.jsonURL(pl, Messages.openLink, ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), Files.social.getString("social.link_" + id));
						JsonAPI.jsonMessages(pl, Messages.deleteLink, ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), "/social delete " + id);
					}
				}

				int id2 = Files.rep.getInt("id");

				id2++;

				Files.rep.set("id", id2);
				Files.rep.set("report_" + id2, id);
				Files.rep.set("report_" + id2 + ".Reason", Report.getBadPage().getItemMeta().getDisplayName());

				Files.saveFiles();
			}

			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Report.getSpam().getItemMeta().getDisplayName())){
				e.setCancelled(true);
				p.closeInventory();

				for(Player pl : Bukkit.getOnlinePlayers()){
					if(pl.hasPermission("socialgui.admin")){
						pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_PLING, 5.0F, 5.0F);
						pl.sendMessage(Messages.reportLink.replace("%player%", p.getName()).replace("%id%", id + ""));
						JsonAPI.jsonURL(pl, Messages.openLink, ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), Files.social.getString("social.link_" + id));
						JsonAPI.jsonMessages(pl, Messages.deleteLink, ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), "/social delete " + id);
					}
				}

				int id2 = Files.rep.getInt("id");

				id2++;

				Files.rep.set("id", id2);
				Files.rep.set("report_" + id2, id);
				Files.rep.set("report_" + id2 + ".Reason", Report.getSpam().getItemMeta().getDisplayName());

				Files.saveFiles();
			}

			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Report.getOffensiveImage().getItemMeta().getDisplayName())){
				e.setCancelled(true);
				p.closeInventory();

				for(Player pl : Bukkit.getOnlinePlayers()){
					if(pl.hasPermission("socialgui.admin")){
						pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_PLING, 5.0F, 5.0F);
						pl.sendMessage(Messages.reportLink.replace("%player%", p.getName()).replace("%id%", id + ""));
						JsonAPI.jsonURL(pl, Messages.openLink, ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), Files.social.getString("social.link_" + id));
						JsonAPI.jsonMessages(pl, Messages.deleteLink, ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), "/social delete " + id);
					}
				}

				int id2 = Files.rep.getInt("id");

				id2++;

				Files.rep.set("id", id2);
				Files.rep.set("report_" + id2, id);
				Files.rep.set("report_" + id2 + ".Reason", Report.getOffensiveImage().getItemMeta().getDisplayName());

				Files.saveFiles();
			}

			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Report.getBrokenLink().getItemMeta().getDisplayName())){
				e.setCancelled(true);
				p.closeInventory();

				for(Player pl : Bukkit.getOnlinePlayers()){
					if(pl.hasPermission("socialgui.admin")){
						pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_PLING, 5.0F, 5.0F);
						pl.sendMessage(Messages.reportLink.replace("%player%", p.getName()).replace("%id%", id + ""));
						JsonAPI.jsonURL(pl, Messages.openLink, ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), Files.social.getString("social.link_" + id));
						JsonAPI.jsonMessages(pl, Messages.deleteLink, ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), "/social delete " + id);
					}
				}

				int id2 = Files.rep.getInt("id");

				id2++;

				Files.rep.set("id", id2);
				Files.rep.set("report_" + id2, id);
				Files.rep.set("report_" + id2 + ".Reason", Report.getBrokenLink().getItemMeta().getDisplayName());

				Files.saveFiles();
			}

			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Report.getNoCorrect().getItemMeta().getDisplayName())){
				e.setCancelled(true);
				p.closeInventory();

				for(Player pl : Bukkit.getOnlinePlayers()){
					if(pl.hasPermission("socialgui.admin")){
						pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_PLING, 5.0F, 5.0F);
						pl.sendMessage(Messages.reportLink.replace("%player%", p.getName()).replace("%id%", id + ""));
						JsonAPI.jsonURL(pl, Messages.openLink, ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), Files.social.getString("social.link_" + id));
						JsonAPI.jsonMessages(pl, Messages.deleteLink, ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), "/social delete " + id);
					}
				}

				int id2 = Files.rep.getInt("id");

				id2++;

				Files.rep.set("id", id2);
				Files.rep.set("report_" + id2, id);
				Files.rep.set("report_" + id2 + ".Reason", Report.getNoCorrect().getItemMeta().getDisplayName());

				Files.saveFiles();
			}
		}
	}
}
