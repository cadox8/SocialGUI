package com.gmail.cadox8.socialgui.cmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.cadox8.socialgui.apis.JsonAPI;
import com.gmail.cadox8.socialgui.files.Files;
import com.gmail.cadox8.socialgui.links.BlockLinks;
import com.gmail.cadox8.socialgui.menu.GUI;
import com.gmail.cadox8.socialgui.utils.Checks;
import com.gmail.cadox8.socialgui.utils.ItemsLink;
import com.gmail.cadox8.socialgui.utils.Messages;
import com.gmail.cadox8.socialgui.utils.SkullManager;

public class Help implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("socialgui") && ((sender instanceof Player))) {
			if (args.length == 0) {
				GUI.playerPage.put(p, 1);

				GUI.openGUI(p, GUI.playerPage.get(p));
				return true;
			}

			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("report")) {
					int id;

					if (Checks.isInteger(args[1])) {
						id = Integer.parseInt(args[1]);
					} else {
						return true;
					}

					if (!Files.social.contains("social.type_" + id)) {
						p.sendMessage(Messages.prefix + ChatColor.RED + "There is not link with this id");
						return true;
					}

					for (Player pl : Bukkit.getOnlinePlayers()) {
						if (pl.hasPermission("socialgui.admin")) {
							pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_PLING, 5.0F, 5.0F);
							pl.sendMessage(Messages.prefix + ChatColor.YELLOW + p.getName() + ChatColor.RED + " has just reported the link wit id " + ChatColor.YELLOW + id);
							JsonAPI.jsonURL(pl, "[Click to open link]", Files.social.getString("social.link_" + id), ChatColor.DARK_GREEN, ChatColor.LIGHT_PURPLE, Files.social.getString("social.link_" + id));
							JsonAPI.jsonMessages(pl, ChatColor.DARK_RED + "[Click to delete link]", Files.social.getString("social.link_" + id), ChatColor.LIGHT_PURPLE, "/social delete " + id);

							int id2 = Files.rep.getInt("id");

							id2++;

							Files.rep.set("id", id2);
							Files.rep.set("report_" + id2, id);

							Files.saveFiles();
						}
					}
				}

				if (p.hasPermission("socialgui.admin")) {
					if (args[0].equalsIgnoreCase("delete")) {
						int id;

						if (Checks.isInteger(args[1])) {
							id = Integer.parseInt(args[1]);
						} else {
							return true;
						}

						Files.social.set("social.type_" + id, null);
						Files.social.set("social.link_" + id, null);
						Files.social.set("social.player_" + id, null);

						Files.saveFiles();

						p.sendMessage(Messages.prefix + ChatColor.GREEN + "Deleted id " + ChatColor.GOLD + id);
					}

					if (args[0].equalsIgnoreCase("give")) {
						String type = args[1].toLowerCase();

						if (!Checks.exitType(type)) {
							p.sendMessage(Messages.prefix + ChatColor.RED + "Please, insert a valid type");
							List<String> types = new ArrayList<String>();

							for (ItemsLink il : ItemsLink.values()) {
								types.add(il.toString().toLowerCase());
							}

							p.sendMessage(Messages.prefix + ChatColor.AQUA + "Types: " + ChatColor.YELLOW + types.toString());
							return true;
						}

						p.getInventory().addItem(SkullManager.getSkull(type, ChatColor.AQUA + WordUtils.capitalizeFully(type), Arrays.asList("")));
					}
				}
			}

			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("add")) {
					String type = args[1].toLowerCase();
					String link = args[2];
					int id = Files.social.getInt("id");

					if (!link.startsWith("http://") || !link.contains(".")) {
						p.sendMessage(Messages.prefix + ChatColor.RED + "Please, insert a valid link (with http:// and .something)");
						return true;
					}

					if (!Checks.exitType(type)) {
						p.sendMessage(Messages.prefix + ChatColor.RED + "Please, insert a valid type");
						List<String> types = new ArrayList<String>();

						for (ItemsLink il : ItemsLink.values()) {
							types.add(il.toString().toLowerCase());
						}

						p.sendMessage(Messages.prefix + ChatColor.AQUA + "Types: " + ChatColor.YELLOW + types.toString());
						return true;
					}

					if (BlockLinks.isBlockedLink(link)) {
						p.sendMessage(Messages.prefix + ChatColor.RED + "Sorry, but you can't put this link");
						return true;
					}

					id++;

					Files.social.set("id", id);

					Files.social.set("social.type_" + id, type.toLowerCase());
					Files.social.set("social.link_" + id, link);
					Files.social.set("social.player_" + id, p.getName());

					Files.saveFiles();

					p.sendMessage(Messages.prefix + ChatColor.GREEN + "Added " + ChatColor.RED + type + ChatColor.GREEN + " with link " + ChatColor.AQUA + link);
				}
			}
		}
		return false;
	}
}
