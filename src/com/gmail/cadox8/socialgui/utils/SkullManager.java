package com.gmail.cadox8.socialgui.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SkullManager {

	public static ItemStack getSkull(String type, String name, List<String> lore){
		switch (type.toLowerCase()) {
		case "twitter":
			return new SkullMaker().withSkinUrl(ItemsLink.TWITTER.getLink()).withLore(lore).withName(name).build();
		case "twitch":
			return new SkullMaker().withSkinUrl(ItemsLink.TWITCH.getLink()).withLore(lore).withName(name).build();
		case "web":
			return new SkullMaker().withSkinUrl(ItemsLink.WEB.getLink()).withLore(lore).withName(name).build();
		case "facebook":
			return new SkullMaker().withSkinUrl(ItemsLink.FACEBOOK.getLink()).withLore(lore).withName(name).build();
		case "instagram":
			return new SkullMaker().withSkinUrl(ItemsLink.INSTAGRAM.getLink()).withLore(lore).withName(name).build();
		case "skype":
			return new SkullMaker().withSkinUrl(ItemsLink.SKYPE.getLink()).withLore(lore).withName(name).build();
		case "youtube":
			return new SkullMaker().withSkinUrl(ItemsLink.YOUTUBE.getLink()).withLore(lore).withName(name).build();
		case "snapchat":
			return new SkullMaker().withSkinUrl(ItemsLink.SNAPCHAT.getLink()).withLore(lore).withName(name).build();
		case "reddit":
			return new SkullMaker().withSkinUrl(ItemsLink.REDDIT.getLink()).withLore(lore).withName(name).build();
		default:
			return new ItemStack(Material.AIR);
		}
	}
}
