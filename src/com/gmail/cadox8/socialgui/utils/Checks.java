package com.gmail.cadox8.socialgui.utils;

import com.gmail.cadox8.socialgui.events.Chat;

public class Checks {

	public static boolean existType(String type){
		for(ItemsLink il : ItemsLink.values()){
			if(il.toString().toLowerCase().equalsIgnoreCase(type)){
				return true;
			}
		}
		return false;
	}

	public static boolean isInteger(String s){
		try{
			Integer.parseInt(s);
		}catch(NumberFormatException e){
			return false;
		}catch(NullPointerException e){
			return false;
		}
		return true;
	}

	public static void addBlocks(){
		Chat.urlEnds.add(".com");
		Chat.urlEnds.add(".de");
		Chat.urlEnds.add(".fr");
		Chat.urlEnds.add(".it");
		Chat.urlEnds.add(".biz");
		Chat.urlEnds.add(".org");
		Chat.urlEnds.add(".net");
		Chat.urlEnds.add(".es");
		Chat.urlEnds.add(".ca");
		Chat.urlEnds.add(".ga");
		Chat.urlEnds.add(".tk");
		Chat.urlEnds.add("ml");
		Chat.urlEnds.add("http://");
		Chat.urlEnds.add("www.");
	}
}
