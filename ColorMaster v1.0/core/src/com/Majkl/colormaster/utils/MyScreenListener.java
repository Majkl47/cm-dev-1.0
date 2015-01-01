package com.Majkl.colormaster.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MyScreenListener  extends MyListener{

	
	public MyScreenListener(TextButton button, Game game,Screen screen) {
		super(button, game, screen, false ,0);
	}
}
