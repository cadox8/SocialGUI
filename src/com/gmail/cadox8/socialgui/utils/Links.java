package com.gmail.cadox8.socialgui.utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Links {

	public static void openURLBrowser(String link){
		try {
			Desktop d = Desktop.getDesktop();
			d.browse(new URI(link));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
