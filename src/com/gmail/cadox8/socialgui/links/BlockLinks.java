package com.gmail.cadox8.socialgui.links;

import java.util.ArrayList;
import java.util.List;

import com.gmail.cadox8.socialgui.files.Files;

public class BlockLinks {

	public static List<String> getBlockedLinks(){
		return Files.cfg.getStringList("blocked");
	}

	public static boolean isBlockedLink(String link){
		if (getBlockedLinks().contains(link)) {
			return true;
		}

		for (String s : getBlockedLinks()) {
			if (s.contains(link)) {
				return true;
			}
			if (link.contains(s)) {
				return true;
			}
		}

		return false;
	}

	public static List<String> getDefaultLinks(){
		List<String> links = new ArrayList<String>();

		//TODO: Add more default links

		links.add("http://xvideos.com");
		links.add("http://pornhub.com");
		links.add("http://adf.ly");
		links.add("http://gyazoo.com");

		return links;
	}
}
