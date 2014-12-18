package com.Majkl.colormaster.utils;

import com.Majkl.colormaster.screens.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MyListener extends InputListener {

	private MyButton button;
	private Game game;
	private Screen screen;
	private boolean setLevel;
	private int currentLevel;
	
	
	public MyListener(MyButton button, Game game, Screen screen, boolean setLevel, int currentLevel) {
		this.button = button;
		this.game = game;
		this.screen = screen;
		this.setLevel = setLevel;
		this.currentLevel = currentLevel;
	}
	
	@Override
	public  boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	}
	
	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		if(Gdx.input.getX() >= this.button.getX() && Gdx.input.getX() < this.button.getX() + this.button.getWidth() && (Gdx.graphics.getHeight() - 
				Gdx.input.getY()) >= this.button.getY() && (Gdx.graphics.getHeight() - Gdx.input.getY()) < this.button.getY() + this.button.getHeight()) {
			if (setLevel) {
				MainMenu.setCurrentLevel(currentLevel);
			}
			
			game.setScreen(screen);
		}
	}
}
