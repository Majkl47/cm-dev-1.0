package com.Majkl.colormaster.screens;

import com.Majkl.colormaster.utils.MyButton;
import com.Majkl.colormaster.utils.MyChangeScreenListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MainMenu implements Screen {
	
	private Game game;
	
	private GameScreen gameScreen;
	private LevelScreen levelScreen;
	
	private Image background;
	
	private Stage stage;
	
	private TextureAtlas buttonAtlas_gen;
	private Skin skin_gen;
	private BitmapFont font_gen;
	private TextButtonStyle buttonStyle_gen;
	private Texture backgroundTexture;  
	private MyButton buttonContinue, buttonNewGame, buttonLevels, buttonDel;
	
	
	public MainMenu(Game game) {
		this.game = game;
	}

	
	
	@Override
	public void show() {
		Gdx.input.setCatchBackKey(true);
		
		gameScreen = new GameScreen(game);
		levelScreen = new LevelScreen(game);
		
		backgroundTexture = new Texture(Gdx.files.internal("background.png"));
		background = new Image(backgroundTexture);
		
		stage = new Stage();
		stage.addActor(background);
		background.toBack();
		background.setSize(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		
		buttonAtlas_gen = new TextureAtlas("buttons/buttons.pack");
		skin_gen = new Skin();
		skin_gen.addRegions(buttonAtlas_gen);
		font_gen = new BitmapFont(Gdx.files.internal("fonts/algeran.fnt"), false);
		
		buttonStyle_gen = new TextButtonStyle();	
		buttonStyle_gen.up = skin_gen.getDrawable("button");
		buttonStyle_gen.down = skin_gen.getDrawable("button_flipped");
		buttonStyle_gen.font = font_gen;
		
		/**
		 * BUTTON CONTINUE DECLARATION
		 */
		buttonContinue = new MyButton("CONTINUE", buttonStyle_gen, 3.5f, 8, 4);
		
		stage.addActor(buttonContinue);
		
		buttonContinue.addListener(new MyChangeScreenListener(buttonContinue, game, gameScreen));
		
	
		/**
		 * BUTTON NEW GAME DECLARATION
		 */
		buttonNewGame = new MyButton("NEW GAME", buttonStyle_gen, 3.5f, 8, 4);
		
		stage.addActor(buttonNewGame);
		
		buttonNewGame.addListener(new MyChangeScreenListener(buttonNewGame, game, gameScreen));
	
		
		/**
		 * BUTTON SELECT LEVEL DECLARATION
		 */
		buttonLevels = new MyButton("SELECT LEVEL", buttonStyle_gen, 3.5f, 8, 5.5f);
		
		stage.addActor(buttonLevels);
				
		buttonLevels.addListener(new MyChangeScreenListener(buttonLevels, game, levelScreen));
		
		buttonDel = new MyButton("DEL", buttonStyle_gen, 3.5f, 8, 7);
		
		stage.addActor(buttonDel);
		
		buttonDel.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
					FileHandle file = Gdx.files.local("data.dat");
					if (file.exists()) {
						file.delete();
					}
			}
		});
		
		
		
		
		Gdx.input.setInputProcessor(stage);
		
		FileHandle file = Gdx.files.local("data.dat");
		if(!file.exists()){
			buttonContinue.setVisible(false);
			buttonNewGame.setVisible(true);
		} else {
			buttonContinue.setVisible(true);
			buttonNewGame.setVisible(false);
		}
		
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
		buttonContinue.clearListeners();
		buttonNewGame.clearListeners();
		buttonLevels.clearListeners();
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
		if (gameScreen.isCalled()) gameScreen.dispose();
		if (levelScreen.isCalled()) levelScreen.dispose();
		stage.dispose();
		buttonAtlas_gen.dispose();
		skin_gen.dispose();
		backgroundTexture.dispose();
	}

}
