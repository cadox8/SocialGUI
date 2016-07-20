package com.gmail.cadox8.socialgui.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.cadox8.socialgui.links.BlockLinks;

public class Files {

	public static File fileConfig = new File("plugins/SocialGUI", "config.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(fileConfig);

	public static File fileSocial = new File("plugins/SocialGUI", "social.yml");
	public static YamlConfiguration social = YamlConfiguration.loadConfiguration(fileSocial);

	public static File fileRep = new File("plugins/SocialGUI", "reports.yml");
	public static YamlConfiguration rep = YamlConfiguration.loadConfiguration(fileRep);

	public static void setupFiles(){
		if (!fileConfig.exists()) {
			fileConfig.mkdir();

			cfg.set("GUI.Name", "&cSocialGUI");

			cfg.set("blocked", BlockLinks.getDefaultLinks());
		}
		if (!fileSocial.exists()) {
			fileSocial.mkdir();

			social.set("id", 0);
		}
		if (!fileRep.exists()) {
			fileRep.mkdir();

			rep.set("id", 0);
		}
		saveFiles();
	}

	public static void saveFiles(){
		try {
			cfg.save(fileConfig);
			cfg.load(fileConfig);
			social.save(fileSocial);
			social.load(fileSocial);
			rep.save(fileRep);
			rep.load(fileRep);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
}
