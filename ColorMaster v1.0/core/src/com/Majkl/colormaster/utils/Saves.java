package com.Majkl.colormaster.utils;

import java.io.IOException;
import java.io.OutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class Saves {

	private Json json;
	
	public Saves() {
		json = new Json();
	}
	
	public void saveGame(Items items) throws IOException{
		FileHandle file = Gdx.files.local("data.dat");
						
		String itemsString = json.toJson(items);
		
		OutputStream out = null;
		try{
			file.writeString(Base64Coder.encodeString(itemsString), false);
		} catch(Exception ex){
			System.out.println(ex.toString());
		} finally{
			if(out != null) try{ out.close();} catch(Exception ex){};
		}

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

}
