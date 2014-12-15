package com.Majkl.colormaster.screens;

import java.io.IOException;

import com.Majkl.colormaster.utils.Items;
import com.Majkl.colormaster.utils.MyButton;
import com.Majkl.colormaster.utils.MyColor;
import com.Majkl.colormaster.utils.MyInputProcessor;
import com.Majkl.colormaster.utils.Saves;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class GameScreen implements Screen {
	
	public static final int START_X = Gdx.graphics.getWidth() / 8; 
	public static final int START_Y = Gdx.graphics.getHeight() / 8;
	
	private boolean called = false;
	
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
	
	private TextureAtlas buttonAtlas_gen;
	private Skin skin_gen;
	private BitmapFont font_gen;
	private TextButtonStyle buttonStyle_gen;
	private MyButton buttonPause;

	
	public GameScreen(Game game) {
		this.game = game;
	}
	

	@Override
	public void show() {
		Gdx.input.setCatchBackKey(true);
		
		called = true;
		
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
		
		buttonAtlas_gen = new TextureAtlas("buttons/buttons.pack");
		skin_gen = new Skin();
		skin_gen.addRegions(buttonAtlas_gen);
		font_gen = new BitmapFont(Gdx.files.internal("fonts/algeran.fnt"), false);
		
		buttonStyle_gen = new TextButtonStyle();	
		buttonStyle_gen.up = skin_gen.getDrawable("button");
		buttonStyle_gen.down = skin_gen.getDrawable("button_flipped");
		buttonStyle_gen.font = font_gen;
		
		buttonPause = new MyButton("PAUSE", buttonStyle_gen, 12, 8, 1);
		buttonPause.setPosition(10, Gdx.graphics.getHeight() - (buttonPause.getHeight() + 10));
		buttonPause.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(Gdx.input.getX() >= buttonPause.getX() && Gdx.input.getX() < buttonPause.getX() + buttonPause.getWidth() && (Gdx.graphics.getHeight() - 
						Gdx.input.getY()) >= buttonPause.getY() && (Gdx.graphics.getHeight() - Gdx.input.getY()) < buttonPause.getY() + buttonPause.getHeight()) {
					pauseMenu.setOpen(true);
				}
			}
		});
		stage.addActor(buttonPause);
		
		levels = new Levels();
		saves = new Saves();
		position = new Vector2(START_X, START_Y);
		Items tempItems = null;
		tempItems = saves.loadGame();

		
		if(tempItems == null){
			currentItems = levels.select(1);
			currentItems.setCurrentLevel(1);
			currentItems.setPlayer(position, -1);
		} else {
			currentItems = levels.select(tempItems.getCurrentLevel());
			currentItems.setPlayer(tempItems.getPlayerPosition(), tempItems.getPlayerColor());
			position = tempItems.getPlayerPosition();
			currentItems.setCurrentLevel(tempItems.getCurrentLevel());
			currentItems.copyArray(tempItems.getMap());
		}
		
		computeGameplan();
		
		processor = new MyInputProcessor(fieldLength, position);
		
		//setting every non-assigned field value -1
		for(int i = 0; i < currentItems.getWidth(); i++) {
			for (int j = 0; j < currentItems.getHeight(); j++) {
				if (currentItems.getGameMapValue(i, j) < 1) {
					currentItems.fillGameMap(i, j, (-1));
				}
			}
		}
		
		plex = new InputMultiplexer();
		plex.addProcessor(stage);
		plex.addProcessor(processor);
		Gdx.input.setInputProcessor(plex);

//		saveGame();

	}
	
	public void saveGame() {
		// testing, for deletion
		try {
			saves.saveGame(currentItems);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		
		stage.act();
		stage.draw();
		
		processor.update(currentItems.getPlayerPosition());
		
		positionX = (int) (processor.getPosition().x  - START_X) / fieldLength;
		positionY = (int)(processor.getPosition().y  - START_Y) / fieldLength;
	
		if (positionX < 0 || positionY < 0 
				|| positionX > currentItems.getWidth() - 1 
				|| positionY > currentItems.getHeight() - 1) {
			processor.positionReadjust();
		} else {
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
					currentItems.setPlayerColor(currentItems.getDropColor(positionX, positionY));
					currentItems.deleteItem(positionX, positionY);
				}
				break;
			case 4:
				break;
			}
		} 

		currentItems.setPlayerPosition(processor.getPosition());
		
				
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
		
		if (pauseMenu.isOpen()) {
			pauseMenu.render();
		} else {
			switch(pauseMenu.getEventCode()) {
			case 1:
				Gdx.input.setInputProcessor(plex);
				break;
			case 2:
				break;
			case 3:
				game.setScreen(levelScreen);
				break;
			case 4:
				game.setScreen(mainMenu);
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
	public void pause() {}

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
	}
	
	public void computeGameplan() {
		fieldLength = (Gdx.graphics.getHeight() - (2 * START_Y)) / currentItems.getHeight();
		if (fieldLength * currentItems.getWidth() > Gdx.graphics.getWidth() - (2 * START_X)) {
			fieldLength = (Gdx.graphics.getWidth() - (2 * START_X)) / currentItems.getWidth(); 
		}
	}


	//GETTERS AND SETTERS
	public boolean isCalled() {
		return called;
	}
	
	
}
