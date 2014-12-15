package com.Majkl.colormaster.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class LevelScreen implements Screen {

	private boolean called = false;
	
	private Game game;
	
	private MainMenu mainMenu;
	private GameScreen gameScreen;
	
	
	
	public LevelScreen(Game game) {
		this.game = game;
	}


	@Override
	public void show() {
		Gdx.input.setCatchBackKey(true);
		
		called = true;
		
		mainMenu = new MainMenu(game);
		gameScreen = new GameScreen(game);
		
	}

	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
	}


	//GETTERS AND SETTERS
	public boolean isCalled() {
		return called;
	}

}
