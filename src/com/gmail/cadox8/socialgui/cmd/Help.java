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
				Files.saveFiles();
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
						p.sendMessage(Messages.noLinkID);
						return true;
					}

					for (Player pl : Bukkit.getOnlinePlayers()) {
						if (pl.hasPermission("socialgui.admin")) {
							pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_PLING, 5.0F, 5.0F);
							pl.sendMessage(Messages.reportLink.replace("%player%", p.getName()).replace("%id%", id + ""));
							JsonAPI.jsonURL(pl, Messages.openLink, ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), Files.social.getString("social.link_" + id));
							JsonAPI.jsonMessages(pl, Messages.deleteLink, ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), "/social delete " + id);

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

						p.sendMessage(Messages.deletedLink.replace("%id", id + ""));
					}

					if (args[0].equalsIgnoreCase("give")) {
						String type = args[1].toLowerCase();

						if (!Checks.existType(type)) {
							p.sendMessage(Messages.validType);
							List<String> types = new ArrayList<String>();

							for (ItemsLink il : ItemsLink.values()) {
								types.add(il.toString().toLowerCase());
							}

							p.sendMessage(Messages.prefix + ChatColor.YELLOW + types.toString());
							return true;
						}

						p.sendMessage(Messages.giveSkull);
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
						p.sendMessage(Messages.validLink);
						return true;
					}

					if (!Checks.existType(type)) {
						p.sendMessage(Messages.validType);
						List<String> types = new ArrayList<String>();

						for (ItemsLink il : ItemsLink.values()) {
							types.add(il.toString().toLowerCase());
						}

						p.sendMessage(Messages.prefix + ChatColor.YELLOW + types.toString());
						return true;
					}

					if (BlockLinks.isBlockedLink(link)) {
						p.sendMessage(Messages.blockedLink.replace("link", link));
						return true;
					}

					id++;

					Files.social.set("id", id);

					Files.social.set("social.type_" + id, type.toLowerCase());
					Files.social.set("social.link_" + id, link);
					Files.social.set("social.player_" + id, p.getName());

					Files.saveFiles();

					p.sendMessage(Messages.addLink.replace("%link%", link).replace("%type%", type));
				}
			}
		}
		return false;
	}
}
