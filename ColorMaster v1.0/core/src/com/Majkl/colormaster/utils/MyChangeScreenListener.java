package com.Majkl.colormaster.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MyChangeScreenListener extends InputListener {

	MyButton button;
	Game game;
	Screen screen;
	
	
	public MyChangeScreenListener(MyButton button, Game game, Screen screen) {
		this.button = button;
		this.game = game;
		this.screen = screen;
	}
	
	@Override
	public  boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	}
	
	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		if(Gdx.input.getX() >= this.button.getX() && Gdx.input.getX() < this.button.getX() + this.button.getWidth() && (Gdx.graphics.getHeight() - 
				Gdx.input.getY()) >= this.button.getY() && (Gdx.graphics.getHeight() - Gdx.input.getY()) < this.button.getY() + this.button.getHeight()) {
				game.setScreen(screen);
		}
	}
}
