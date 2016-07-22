package com.gmail.cadox8.socialgui.utils;

import org.bukkit.ChatColor;

import com.gmail.cadox8.socialgui.files.Files;

public class Messages {

	//Data
	public static String prefix = ChatColor.GRAY + " || " + ChatColor.GREEN + "SocialGUI" + ChatColor.GRAY + " || ";

	//Game
	public static String noLinkID = colorize(Files.cfg.getString("Messages.noLinkID"));
	public static String deletedLink = colorize(Files.cfg.getString("Messages.deletedLink"));

	public static String deleteLink = colorize(Files.cfg.getString("Messages.deleteLink"));
	public static String openLink = colorize(Files.cfg.getString("Messages.openLink"));

	public static String reportLink = colorize(Files.cfg.getString("Messages.reportLink"));

	public static String validType = colorize(Files.cfg.getString("Messages.validType"));
	public static String validLink = colorize(Files.cfg.getString("Messages.validLink"));
	public static String blockedLink = colorize(Files.cfg.getString("Messages.blockedLink"));

	public static String addLink = colorize(Files.cfg.getString("Messages.addLink"));

	public static String giveSkull = colorize(Files.cfg.getString("Messages.giveSkull"));

	public static String notAllowed = colorize(Files.cfg.getString("Messages.notAllowedIP"));

	private static String colorize(String message){
		return prefix + ChatColor.translateAlternateColorCodes('&', message);
	}
}
