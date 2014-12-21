package com.Majkl.colormaster.utils;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MyButton extends TextButton{
	
	
	public MyButton(String name, TextButtonStyle style, float width, float height, float x, float y) {
		super(name, style);
		setWidth(width);
		setHeight(height);
		setPosition(x, y);
	}
}
