package com.gmail.cadox8.socialgui.utils;

public enum ItemsLink {

	TWITTER("http://textures.minecraft.net/texture/dcb76166d1e1e449457b5c4436b3f48b7d768ac60f19e2c6b25ea42c4bad7c"), TWITCH("http://textures.minecraft.net/texture/46be65f44cd21014c8cddd0158bf75227adcb1fd179f4c1acd158c88871a13f"), INSTAGRAM("http://textures.minecraft.net/texture/25b3f2cfa0739c4e828316f39f90b05bc1f4ed27b1e35888511f558d4675"), SKYPE("http://textures.minecraft.net/texture/2ec182da7d3c0a8acc3be9b77c29be47e08c20b050b13fd4c4c7d71f66273"), FACEBOOK("http://textures.minecraft.net/texture/deb46126904463f07ecfc972aaa37373a22359b5ba271821b689cd5367f75762"), YOUTUBE("http://textures.minecraft.net/texture/d2f6c07a326def984e72f772ed645449f5ec96c6ca256499b5d2b84a8dce"), WEB("http://textures.minecraft.net/texture/56936d4f0d1b93fef775b1fbd19281b70c6f88475bb5a41bf372c12f1f8a22");

	private String link;

	ItemsLink(String link){
		this.link = link;
	}

	public String getLink(){
		return this.link;
	}
}
