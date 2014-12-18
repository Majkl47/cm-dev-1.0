package com.Majkl.colormaster.utils;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class Saves {

	private Json json;
	
	public Saves() {
		json = new Json();
	}
	
	public void saveGame(Items items) throws IOException {
		FileHandle file = Gdx.files.local("data.dat");
						
		String itemsString = json.toJson(items);
		
		file.writeString(Base64Coder.encodeString(itemsString), false);

		System.out.println("Saving Game....");		
	}
	
	public Items loadGame() {
		FileHandle file = Gdx.files.local("data.dat");
		Items items = null;
		if (!file.exists()) return null;
		String itemsString = file.readString();
		items = (Items) json.fromJson(Items.class, Base64Coder.decodeString(itemsString));
		System.out.println("Loading Game....");
		return items;
		
	}
	
	public void saveMaxLevel(int value) {
		FileHandle file = Gdx.files.local("data2.dat");
			
		file.writeString(Base64Coder.encodeString(json.toJson(value)), false);
		
		System.out.println("Saving MaxLevel....");
		
	}
	
	public int loadMaxLevel() {
		FileHandle file = Gdx.files.local("data2.dat");
		if (!file.exists()) return 1;
		
		System.out.println("Loading maxLevel....");
		
		return (int) json.fromJson(Integer.class, Base64Coder.decodeString(file.readString()));
	}

}
