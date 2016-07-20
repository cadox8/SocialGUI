package com.gmail.cadox8.socialgui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.cadox8.socialgui.cmd.Help;
import com.gmail.cadox8.socialgui.events.Interact;
import com.gmail.cadox8.socialgui.files.Files;
import com.gmail.cadox8.socialgui.utils.Messages;

public class SocialGUI extends JavaPlugin {

	public void onEnable(){
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "========================");
		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Checking and Creating files. . .");
		Files.setupFiles();
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Setup Files Complete");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Registering Commands and Events. . .");
		registerCMDs();
		registerEvents();
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Register Complete");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "SocialGUI enabled");
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "SocialGUI Version: " + ChatColor.RED + getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "SocialGUI Autor: " + ChatColor.RED + getDescription().getAuthors().toString());
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "SocialGUI Utils: " + ChatColor.RED + "https://github.com/cadox8/SocialGUI");

		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "========================");
	}

	public void onDisable(){
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "========================");
		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Saving Files. . .");
		Files.setupFiles();
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Files Saved");

		Bukkit.getConsoleSender().sendMessage(" ");

		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "SocialGUI disbaled");

		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "========================");
	}

	private void registerCMDs(){
		getCommand("socialgui").setExecutor(new Help());
	}

	private void registerEvents(){
		new Interact(this);
	}
}
