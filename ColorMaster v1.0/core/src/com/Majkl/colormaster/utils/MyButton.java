package com.Majkl.colormaster.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MyButton extends TextButton{
	
	
	public MyButton(String name, TextButtonStyle style, float width, float height, float position) {
		super(name, style);
		setWidth(Gdx.graphics.getWidth() / width);
		setHeight(Gdx.graphics.getHeight() / height);
		setPosition((Gdx.graphics.getWidth() / 2) - (this.getWidth() / 2), Gdx.graphics.getHeight() - this.getHeight() * position);
	}
}
