package com.Majkl.colormaster.screens;

import com.Majkl.colormaster.utils.MyButton;
import com.Majkl.colormaster.utils.MyListener;
import com.Majkl.colormaster.utils.MyScreenListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class LevelScreen implements Screen {

	//Dimensions Shortcuts
	public static final int W = Gdx.graphics.getWidth();
	public static final int H = Gdx.graphics.getHeight();
	
	private boolean called = false;
	private static boolean backKeyPressed = false;
	
	
	private Game game;
	
	private MainMenu mainMenu;
	private GameScreen gameScreen;
	
	private Stage stage;
	
	private TextureAtlas buttonAtlas_gen;
	private Skin skin_gen;
	private BitmapFont font_gen;
	private TextButtonStyle buttonStyle_gen;
	private MyButton buttonMenu, button1, button2, button3, button4;
	
	
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
		
		//BUTTON MENU DECLARATION
		buttonMenu = new MyButton("MENU", buttonStyle_gen, W / 12, H / 8, 10, H - ((H / 8) + 10));	
		stage.addActor(buttonMenu);
		buttonMenu.addListener(new MyScreenListener(buttonMenu, game, mainMenu));
		
	
		//BUTTON 1. LEVEL DECLARATION
		button1= new MyButton("1", buttonStyle_gen,H / 8, H / 8, GameScreen.START_X, H - (H * 5) / 16);
		stage.addActor(button1);
		button1.addListener(new MyListener(button1, game, gameScreen, true, 1));
		
		
		//BUTTON 2. LEVEL DECLARATION
		button2 = new MyButton("2", buttonStyle_gen, H / 8, H / 8, GameScreen.START_X + (H * 3) / 16, H - (H * 5) / 16);
		stage.addActor(button2);
		button2.addListener(new MyListener(button2, game, gameScreen, true, 2));
		if(MainMenu.getMaxLevel() >= 2) button2.setVisible(true);
		else button2.setVisible(false);
		
		
		//BUTTON 3. LEVEL DECLARATION
		button3 = new MyButton("3", buttonStyle_gen, H / 8, H / 8, GameScreen.START_X + (H * 3) * 2 / 16, H - (H * 5) / 16);
		stage.addActor(button3);
		button3.addListener(new MyListener(button3, game, gameScreen, true, 3));
		if(MainMenu.getMaxLevel() >= 3) button3.setVisible(true);
		else button3.setVisible(false);
		
		
		//BUTTON 4. LEVEL DECLARATION
		button4 = new MyButton("4", buttonStyle_gen, H / 8, H / 8, GameScreen.START_X + (H * 3) * 3 / 16, H - (H * 5) / 16);
		stage.addActor(button4);
		button4.addListener(new MyListener(button4, game, gameScreen, true, 4));
		if(MainMenu.getMaxLevel() >= 4) button4.setVisible(true);
		else button4.setVisible(false);
		
		//Input processor change
		Gdx.input.setInputProcessor(stage);
		
	}

	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
		
		if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
			backKeyPressed = true;
			game.setScreen(mainMenu);
		}
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


	public static boolean isBackKeyPressed() {
		return backKeyPressed;
	}


	public static void setBackKeyPressed(boolean backKeyPressed) {
		LevelScreen.backKeyPressed = backKeyPressed;
	}

}
