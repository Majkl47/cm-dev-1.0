package com.Majkl.colormaster.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class ButtonCreator {
	private TextureAtlas buttonAtlas_gen;
	private Skin skin_gen;
	private BitmapFont font_gen;
	private TextButtonStyle buttonStyle_gen;

	public ButtonCreator() {
		buttonAtlas_gen = new TextureAtlas("buttons/buttons.pack");
		skin_gen = new Skin();
		skin_gen.addRegions(buttonAtlas_gen);
		font_gen = new BitmapFont(Gdx.files.internal("fonts/algeran.fnt"), false);
		
		buttonStyle_gen = new TextButtonStyle();	
		buttonStyle_gen.up = skin_gen.getDrawable("button");
		buttonStyle_gen.down = skin_gen.getDrawable("button_flipped");
		buttonStyle_gen.font = font_gen;
	}
	
	public TextButton newButton(String name, float width, float height, float posX, float posY) {
		TextButton newTextButton = new TextButton(name, buttonStyle_gen);
		newTextButton.setWidth(width);
		newTextButton.setHeight(height);
		newTextButton.setPosition(posX, posY);
		
		return newTextButton;
	}
	
	public Skin getSkin() {
		return skin_gen;
	}
	
	public void setSkin(Skin skin) {
		this.skin_gen = skin;
	}
	
	public TextButtonStyle getStyle() {
		return buttonStyle_gen;
	}
	
	public BitmapFont getFont() {
		return font_gen;
	}
	
	public void dispose() {
		buttonAtlas_gen.dispose();
		skin_gen.dispose();
		font_gen.dispose();
	}

}
