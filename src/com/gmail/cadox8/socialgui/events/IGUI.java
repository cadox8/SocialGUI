package com.gmail.cadox8.socialgui.events;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.gmail.cadox8.socialgui.SocialGUI;
import com.gmail.cadox8.socialgui.api.JsonAPI;
import com.gmail.cadox8.socialgui.files.Files;
import com.gmail.cadox8.socialgui.menu.GUI;
import com.gmail.cadox8.socialgui.menu.Report;
import com.gmail.cadox8.socialgui.utils.Links;
import com.gmail.cadox8.socialgui.utils.Messages;

public class IGUI implements Listener {

	private SocialGUI plugin;

	public IGUI(SocialGUI Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onInteractGUI(InventoryClickEvent e){
		final Player p = (Player) e.getWhoClicked();

		if(e.getInventory().getName().contains(ChatColor.translateAlternateColorCodes('&', Files.cfg.getString("GUI.Name")))){
			if(e.getCurrentItem() == null){
				return;
			}
			if(e.getCurrentItem().getItemMeta() == null){
				return;
			}

			e.setCancelled(true);

			//Search
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(GUI.getUserLinks(p).getItemMeta().getDisplayName())){
				e.setCancelled(true);
				p.closeInventory();

				GUI.openGUIPlayer(p, GUI.playerPage.get(p), p.getName());
			}

			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(GUI.getUserSearch().getItemMeta().getDisplayName())){
				e.setCancelled(true);
				p.closeInventory();

				Chat.onSearch.add(p);
				p.sendMessage(Messages.prefix + ChatColor.GOLD + "Please, put the user name to search it");
			}

			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(GUI.getTypeSearch().getItemMeta().getDisplayName())){
				e.setCancelled(true);
				p.closeInventory();

				Chat.onSearch.add(p);
				p.sendMessage(Messages.prefix + ChatColor.GOLD + "Please, put the type to search it");
			}

			//Pages
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(GUI.getNextItem().getItemMeta().getDisplayName())){
				e.setCancelled(true);
				p.closeInventory();

				GUI.playerPage.put(p, GUI.playerPage.get(p) + 1);
				GUI.openGUI(p, GUI.playerPage.get(p));
				return;
			}

			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(GUI.getPrevItem().getItemMeta().getDisplayName())){
				e.setCancelled(true);
				p.closeInventory();

				GUI.playerPage.put(p, GUI.playerPage.get(p) - 1);
				GUI.openGUI(p, GUI.playerPage.get(p));
				return;
			}

			//Items

			if(e.getCurrentItem().getItemMeta().getLore().get(2) != null && e.getClick() == ClickType.SHIFT_RIGHT){
				if(p.hasPermission("socialgui.admin")){
					e.setCancelled(true);
					p.closeInventory();

					int id = Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(2));

					JsonAPI.jsonMessages(p, Messages.prefix + ChatColor.DARK_RED + "[Click to delete link]", ChatColor.LIGHT_PURPLE + Files.social.getString("social.link_" + id), "/social delete " + id);
				}
			}

			if(e.getCurrentItem().getItemMeta().getLore().get(2) != null && e.getClick() == ClickType.RIGHT){
				e.setCancelled(true);
				p.closeInventory();

				int id = Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(2));

				Report.openReportGUI(p, id);
			}

			if(e.getCurrentItem().getItemMeta().getLore().get(2) != null && e.getClick() == ClickType.LEFT){
				e.setCancelled(true);
				p.closeInventory();
				int id = Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(2));

				String type = WordUtils.capitalizeFully(Files.social.getString("social.type_" + id));
				String player = WordUtils.capitalizeFully(Files.social.getString("social.player_" + id));

				Links.openURLBrowser(Files.social.getString("social.link_" + id));

				p.sendMessage(Messages.prefix + ChatColor.RED + "WARNING: The server is not responsable about the links posted in this plugin. We will check all to prevent extrange links. Also, you can report them if you right click on them");

				JsonAPI.jsonURL(p, Messages.prefix + ChatColor.GREEN + "Click to open " + ChatColor.AQUA + type + ChatColor.GREEN + " link posted by the player " + ChatColor.YELLOW + player + ChatColor.GREEN + " (if not openned yet)", ChatColor.RED + Files.social.getString("social.link_" + id), Files.social.getString("social.link_" + id));
			}
		}
	}
}
