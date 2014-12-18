package com.Majkl.colormaster.screens;

import com.Majkl.colormaster.utils.MyButton;
import com.Majkl.colormaster.utils.MyListener;
import com.Majkl.colormaster.utils.MyScreenListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class LevelScreen implements Screen {

	private boolean called = false;
	
	
	private Game game;
	
	private MainMenu mainMenu;
	private GameScreen gameScreen;
	
	private Stage stage;
	
	private TextureAtlas buttonAtlas_gen;
	private Skin skin_gen;
	private BitmapFont font_gen;
	private TextButtonStyle buttonStyle_gen;
	private MyButton buttonMenu, button1, button2;
	
	
	public LevelScreen(Game game) {
		this.game = game;
	}


	@Override
	public void show() {
		Gdx.input.setCatchBackKey(true);
		
		called = true;
		
		mainMenu = new MainMenu(game);
		gameScreen = new GameScreen(game);
		
		stage = new Stage();
		
		buttonAtlas_gen = new TextureAtlas("buttons/buttons.pack");
		skin_gen = new Skin();
		skin_gen.addRegions(buttonAtlas_gen);
		font_gen = new BitmapFont(Gdx.files.internal("fonts/algeran.fnt"), false);
		
		buttonStyle_gen = new TextButtonStyle();	
		buttonStyle_gen.up = skin_gen.getDrawable("button");
		buttonStyle_gen.down = skin_gen.getDrawable("button_flipped");
		buttonStyle_gen.font = font_gen;
		
		gameScreen.setRestart(true);
		
		/**
		 * BUTTON MENU DECLARATION
		 */
		buttonMenu = new MyButton("MENU", buttonStyle_gen, 12, 8, 1);
		buttonMenu.setPosition(10, Gdx.graphics.getHeight() - (buttonMenu.getHeight() + 10));
		
		stage.addActor(buttonMenu);
		
		buttonMenu.addListener(new MyScreenListener(buttonMenu, game, mainMenu));
		
	
		/**
		 * BUTTON 1. LEVEL DECLARATION
		 */
		button1= new MyButton("1", buttonStyle_gen, 8, 8, 8);
		button1.setWidth(button1.getHeight());
		button1.setPosition(GameScreen.START_X, Gdx.graphics.getHeight() - button1.getHeight() * 5 / 2);
		
		stage.addActor(button1);
		
		
		button1.addListener(new MyListener(button1, game, gameScreen, true, 1));
		
		/**
		 * BUTTON 2. LEVEL DECLARATION
		 */

		button2 = new MyButton("2", buttonStyle_gen, 3.5f, 8, 5.5f);
		button2.setWidth(button2.getHeight());
		button2.setPosition(GameScreen.START_X + button2.getWidth() * 3 / 2, Gdx.graphics.getHeight() - button2.getHeight() * 5 / 2);
		
		stage.addActor(button2);
				
		button2.addListener(new MyListener(button2, game, gameScreen, true, 2));
		
		if(MainMenu.getMaxLevel() >= 2) button2.setVisible(true);
		else button2.setVisible(false);
		
		Gdx.input.setInputProcessor(stage);
		
	}

	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
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
