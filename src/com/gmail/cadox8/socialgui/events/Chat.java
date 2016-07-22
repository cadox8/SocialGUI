package com.gmail.cadox8.socialgui.events;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.cadox8.socialgui.SocialGUI;
import com.gmail.cadox8.socialgui.utils.Messages;

public class Chat implements Listener {

	private SocialGUI plugin;

	public Chat(SocialGUI Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	private Pattern pattern;
	private Matcher matcher;

	private String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public static List<String> urlEnds = new ArrayList<String>();

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String message = e.getMessage();

		if (!p.hasPermission("socialgui.admin")) {
			pattern = Pattern.compile(IPADDRESS_PATTERN);
			String[] parts = message.split("\\s");

			for (String s : parts) {
				matcher = pattern.matcher(s);

				String[] check;
				if (s.contains(":")) {
					check = s.split(":");

					for (String g : check) {
						matcher = pattern.matcher(g);
						if (matcher.matches()) {
							p.sendMessage(Messages.notAllowed);
							e.setCancelled(true);
							return;
						}
					}
				}

				if (matcher.matches()) {
					p.sendMessage(Messages.notAllowed);
					e.setCancelled(true);
					return;
				}

				for (String nope : urlEnds) {
					if (s.contains(nope)) {
						p.sendMessage(Messages.notAllowed);
						e.setCancelled(true);
						return;
					}
				}
			}
		}
	}
}
