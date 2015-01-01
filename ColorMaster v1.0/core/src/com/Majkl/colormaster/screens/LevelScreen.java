package com.Majkl.colormaster.screens;

import java.util.ArrayList;
import java.util.List;

import com.Majkl.colormaster.utils.ButtonCreator;
import com.Majkl.colormaster.utils.Levels;
import com.Majkl.colormaster.utils.MyListener;
import com.Majkl.colormaster.utils.MyScreenListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

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
	
	private ButtonCreator buttonCreator;
	private List<TextButton> buttons;
	
	
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
		buttons = new ArrayList<TextButton>();
		
		gameScreen.setRestart(true);
		buttonCreator = new ButtonCreator();

		buttons.add(buttonCreator.newButton("MENU", W / 12, H / 8, 10, H - ((H / 8) + 10)));
		buttons.get(0).addListener(new MyScreenListener(buttons.get(0), game, mainMenu));
		stage.addActor(buttons.get(0));
		

		//Initialization of all buttons
		int i = 0, j = 0, count = 1;
		while (count <= Levels.LEVELS_MAX && count <= 21) {
			buttons.add(buttonCreator.newButton(Integer.toString(count), H / 8, H / 8,
						GameScreen.START_X + (H * 3) * i / 16, H - (H * 5) / 16 - (j * ((H * 3) / 16))));
			buttons.get(count).addListener(new MyListener(buttons.get(count), game, gameScreen, true, count));
			stage.addActor(buttons.get(count));
			if(MainMenu.getMaxLevel() >= count) buttons.get(count).setVisible(true);
			else buttons.get(count).setVisible(false);
			i++;
			if (count % 7 == 0) {
				j++;
				i = 0;
			}
			count++;
		}
		
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
		stage.dispose();
		buttonCreator.dispose();
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
