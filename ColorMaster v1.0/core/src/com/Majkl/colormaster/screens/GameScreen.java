package com.Majkl.colormaster.screens;

import java.io.IOException;

import com.Majkl.colormaster.utils.ButtonCreator;
import com.Majkl.colormaster.utils.Items;
import com.Majkl.colormaster.utils.Levels;
import com.Majkl.colormaster.utils.MyColor;
import com.Majkl.colormaster.utils.MyInputProcessor;
import com.Majkl.colormaster.utils.Saves;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameScreen implements Screen {
	
	//Dimensions Shortcuts
	public static final int W = Gdx.graphics.getWidth();
	public static final int H = Gdx.graphics.getHeight();
	
	//START POSITION
	public static final int START_X = W / 8; 
	public static final int START_Y = H / 8;
	
	private boolean called = false;
	private boolean restart;
	private boolean backKeyPressed = false;
	private static boolean backKeyReleased = false, pauseBackKeyPressed = false;
	
	private Game game;
	
	private PauseMenu pauseMenu;
	private MainMenu mainMenu;
	private LevelScreen levelScreen;
	
	private Items currentItems;	
	private Levels levels;
	private Saves saves;
	private int fieldLength;
	
	private Texture player, field, drop, gate, finish;
	private SpriteBatch batch;
	private Stage stage;
	private MyColor color;
	private MyInputProcessor processor;
	private InputMultiplexer plex;
	
	private Vector2 position;
	private int positionX, positionY;
	
	private ButtonCreator buttonCreator;
	private TextButton buttonPause;

	
	public GameScreen(Game game) {
		this.game = game;
	}
	

	@Override
	public void show() {
		Gdx.input.setCatchBackKey(true);
		
		called = true;
		backKeyPressed = false;
		
		pauseMenu = new PauseMenu();
		levelScreen = new LevelScreen(game);
		mainMenu = new MainMenu(game);		
		
		player = new Texture(Gdx.files.internal("player/circle.png"));
		field = new Texture(Gdx.files.internal("field.png"));
		drop = new Texture(Gdx.files.internal("rain_drop.png"));
		gate = new Texture(Gdx.files.internal("gate.png"));
		finish = new Texture(Gdx.files.internal("finish.png"));
		batch = new SpriteBatch();
		color = new MyColor();
		stage = new Stage();
		buttonCreator = new ButtonCreator();

		
		//BUTTON PAUSE DECLARATION
		buttonPause = buttonCreator.newButton("PAUSE", W / 12,H / 8, 10, H - ((H / 8) + 10));
		buttonPause.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(Gdx.input.getX() >= buttonPause.getX() && Gdx.input.getX() < buttonPause.getX() + buttonPause.getWidth() && (H - 
						Gdx.input.getY()) >= buttonPause.getY() && (H - Gdx.input.getY()) < buttonPause.getY() + buttonPause.getHeight()) {
					pauseMenu.setOpen(true);
				}
			}
		});
		stage.addActor(buttonPause);
				

		levels = new Levels();
		saves = new Saves();
		Items tempItems = null;
		tempItems = saves.loadGame();
		

		//Initializing items from saves or default values
		if(tempItems == null) {
			currentItems = levels.select(1);
			computeGameplan();
			position = currentItems.getStartPosition().scl(fieldLength);
			position.add(START_X, START_Y);
			currentItems.setCurrentLevel(1);
			MainMenu.setCurrentLevel(1);
			currentItems.setPlayer(position, -1);
			
		} else if (restart) {
			currentItems = levels.select(MainMenu.getCurrentLevel());
			computeGameplan();
			position = currentItems.getStartPosition().scl(fieldLength);
			position.add(START_X, START_Y);
			currentItems.setPlayer(position, -1);
			currentItems.setCurrentLevel(MainMenu.getCurrentLevel());
						
		} else {
			currentItems = levels.select(tempItems.getCurrentLevel());
			currentItems.setPlayer(tempItems.getPlayerPosition(), tempItems.getPlayerColor());
			position = tempItems.getPlayerPosition();
			currentItems.setCurrentLevel(tempItems.getCurrentLevel());
			MainMenu.setCurrentLevel(tempItems.getCurrentLevel());
			currentItems.copyArray(tempItems.getMap());
			computeGameplan();
		}
		
		
		processor = new MyInputProcessor(fieldLength, position);
		
		//setting every non-assigned field value -1
		for(int i = 0; i < currentItems.getWidth(); i++) {
			for (int j = 0; j < currentItems.getHeight(); j++) {
				if (currentItems.getGameMapValue(i, j) < 1) {
					currentItems.fillGameMap(i, j, (-1));
				}
			}
		}
		
		//Input processor change
		plex = new InputMultiplexer();
		plex.addProcessor(stage);
		plex.addProcessor(processor);
		Gdx.input.setInputProcessor(plex);

	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//BACK KEY functionality
		if (Gdx.input.isKeyPressed(Keys.BACK) && !backKeyPressed && !pauseBackKeyPressed && !pauseMenu.isOpen()) {
			backKeyReleased = false;
			backKeyPressed = true;
			pauseMenu.setOpen(true);
		} 
		if ((pauseMenu.isOpen() || backKeyPressed) && !Gdx.input.isKeyPressed(Keys.BACK)) {
			backKeyReleased = true;
		} 
		if (pauseBackKeyPressed && !Gdx.input.isKeyPressed(Keys.BACK)) {
			pauseBackKeyPressed = false;
		}
		
		
		//ARRIVING TO THE FINISH
		if ((currentItems.getPlayerPosition().x - START_X) / fieldLength == currentItems.getValuePosition(4).x &&
				(currentItems.getPlayerPosition().y - START_Y) / fieldLength == currentItems.getValuePosition(4).y) {
			MainMenu.setCurrentLevel(MainMenu.getCurrentLevel() + 1);
			currentItems.setCurrentLevel(MainMenu.getCurrentLevel());
			if (Levels.LEVELS_MAX < MainMenu.getCurrentLevel()) {
				currentItems.setPlayer(position, -1);
				MainMenu.setCurrentLevel(MainMenu.getCurrentLevel() - 2);
				currentItems.setCurrentLevel(MainMenu.getCurrentLevel());
				try {
					saves.saveGame(currentItems);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				game.setScreen(new EndScreen(game));
			} else {
				if (MainMenu.getCurrentLevel() > MainMenu.getMaxLevel()) {
					MainMenu.setMaxLevel(currentItems.getCurrentLevel());
					saves.saveMaxLevel(currentItems.getCurrentLevel());
				}

				try {
					saves.saveGame(currentItems);
				} catch (IOException e) {
					e.printStackTrace();
				}
				restart = true;
				game.setScreen(this);

				
			}
		}
		
		stage.act();
		stage.draw();
		
		processor.update(currentItems.getPlayerPosition());
		
		positionX = (int) (processor.getPosition().x  - START_X) / fieldLength;
		positionY = (int)(processor.getPosition().y  - START_Y) / fieldLength;
	
		//Movement basics
		if (positionX < 0 || positionY < 0 
				|| positionX > currentItems.getWidth() - 1 
				|| positionY > currentItems.getHeight() - 1) {
			processor.positionReadjust();
		} else {
			//Interactions with objects
			switch (currentItems.getGameMapValue(positionX, positionY)) {
			case -1:
				processor.positionReadjust();
				break;
			case 1:break;
			case 2:
				if (currentItems.getPlayerColor() == currentItems.getGateColor(positionX, positionY)) {
					currentItems.deleteItem(positionX, positionY);
					currentItems.setPlayerColor(-1);
				} else processor.positionReadjust();
				break;
			case 3:
				if (currentItems.getPlayerColor() != currentItems.getDropColor(positionX, positionY)) {
					if (currentItems.getPlayerColor() != (-1)) {
						if (color.mixer(currentItems.getPlayerColor(), currentItems.getDropColor(positionX, positionY)) != currentItems.getPlayerColor()) {
							currentItems.setPlayerColor(color.mixer(currentItems.getPlayerColor(), currentItems.getDropColor(positionX, positionY)));
							currentItems.deleteItem(positionX, positionY);
						}
					} else {
						currentItems.setPlayerColor(currentItems.getDropColor(positionX, positionY));
						currentItems.deleteItem(positionX, positionY);
					}
				}
				break;
			case 4:
				break;
			}
		} 

		currentItems.setPlayerPosition(processor.getPosition());
		
				
		//Drawing the game
		batch.begin();
		for(int i = 0; i < currentItems.getWidth(); i++) {
			for (int j = 0; j < currentItems.getHeight(); j++) {
				if (currentItems.getGameMapValue(i, j) > (-1)) {
					switch (currentItems.getGameMapValue(i, j)) {
					case 1: 
						batch.setColor(MyColor.BLACK);
						batch.draw(field, START_X + (i * fieldLength), START_Y + (j * fieldLength), fieldLength, fieldLength);
						break;
					case 2:
						batch.setColor(MyColor.BLACK);
						batch.draw(field, START_X + (i * fieldLength), START_Y + (j * fieldLength), fieldLength, fieldLength);
						batch.setColor(color.toColor(currentItems.getGateColor(i, j)));
						batch.draw(gate, START_X + (i * fieldLength), START_Y + (j * fieldLength), fieldLength, fieldLength);
						break;
					case 3:
						batch.setColor(MyColor.BLACK);
						batch.draw(field, START_X + (i * fieldLength), START_Y + (j * fieldLength), fieldLength, fieldLength);
						batch.setColor(color.toColor(currentItems.getDropColor(i, j)));
						batch.draw(drop, START_X + (i * fieldLength), START_Y + (j * fieldLength), fieldLength, fieldLength);
						break;
					case 4:
						batch.setColor(MyColor.WHITE);
						batch.draw(finish, START_X + (i * fieldLength), START_Y + (j * fieldLength), fieldLength, fieldLength);
						break;
					}
				}
			}
		}	
		batch.setColor(color.toColor(currentItems.getPlayerColor()));
		batch.draw(player, currentItems.getPlayerPosition().x, currentItems.getPlayerPosition().y, fieldLength, fieldLength);
		batch.end();
		
		
		//Pause menu switch functionality
		if (pauseMenu.isOpen()) {
			pauseMenu.render();
		} else {
			backKeyPressed = false;
			restart = false;
			switch(pauseMenu.getEventCode()) {
			case 1:
				Gdx.input.setInputProcessor(plex);
				break;
			case 2:
				restart = true;
				game.setScreen(this);
				break;
			case 3:
				game.setScreen(levelScreen);
				try {
					saves.saveGame(currentItems);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			case 4:
				game.setScreen(mainMenu);
				try {
					saves.saveGame(currentItems);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}

		}
		
		
	}
	
	@Override
	public void resize(int width, int height) {}

	@Override
	public void hide() {
		buttonPause.clearListeners();
	}

	@Override
	public void pause() {
		try {
			saves.saveGame(currentItems);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
		if(pauseMenu.isCalled()) pauseMenu.dispose();
		player.dispose();
		field.dispose();
		gate.dispose();
		drop.dispose();
		finish.dispose();
		batch.dispose();
		stage.dispose();
		buttonCreator.dispose();
	}
	
	public void computeGameplan() {
		fieldLength = (H - (2 * START_Y)) / currentItems.getHeight();
		if (fieldLength * currentItems.getWidth() > W - (2 * START_X)) {
			fieldLength = (W - (2 * START_X)) / currentItems.getWidth(); 
		}
	}


	//GETTERS AND SETTERS
	public boolean isCalled() {
		return called;
	}


	public void setRestart(boolean restart) {
		this.restart = restart;
	}
		
	public int getCurrentLevel() {
		return currentItems.getCurrentLevel();
	}
	
	public void setCurrentLevel(int level) {
		currentItems.setCurrentLevel(level);
	}


	public  boolean isBackKeyPressed() {
		return backKeyPressed;
	}


	public void setBackKeyPressed(boolean backKeyPressed) {
		this.backKeyPressed = backKeyPressed;
	}


	public static boolean isBackKeyReleased() {
		return backKeyReleased;
	}


	public static void setPauseBackKeyPressed(boolean pauseBackKeyPressed) {
		GameScreen.pauseBackKeyPressed = pauseBackKeyPressed;
	}

}
