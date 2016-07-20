package com.gmail.cadox8.socialgui.utils;

public class Checks {

	public static boolean exitType(String type){
		for (ItemsLink il : ItemsLink.values()) {
			if (il.toString().toLowerCase().equalsIgnoreCase(type)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isInteger(String s){
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}
}
