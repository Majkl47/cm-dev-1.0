package com.Majkl.colormaster.screens;

import com.Majkl.colormaster.utils.MyButton;
import com.Majkl.colormaster.utils.MyScreenListener;
import com.Majkl.colormaster.utils.Saves;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;

public class MainMenu implements Screen {
	
	public static final int W = Gdx.graphics.getWidth();
	public static final int H = Gdx.graphics.getHeight();
	
	private boolean backKeyPressed = false;	
	
	private static int maxLevel;
	private static int currentLevel;
	private Saves saves;
	
	private Game game;
	
	private GameScreen gameScreen;
	private LevelScreen levelScreen;
	
	private Image background;
	
	private Stage stage;
	
	private TextureAtlas buttonAtlas_gen;
	private Skin skin_gen;
	private BitmapFont font_gen;
	private TextButtonStyle buttonStyle_gen;
	private Texture backgroundTexture, dialogBackground; 
	private MyButton buttonContinue, buttonNewGame, buttonLevels, buttonDel;
	private Image dialogImage;
	
	
	public MainMenu(Game game) {
		this.game = game;
	}

	
	
	@Override
	public void show() {
		Gdx.input.setCatchBackKey(true);
		
		gameScreen = new GameScreen(game);
		levelScreen = new LevelScreen(game);
		
		saves = new Saves();
		maxLevel = saves.loadMaxLevel();
		
		backgroundTexture = new Texture(Gdx.files.internal("background.png"));
		background = new Image(backgroundTexture);
		dialogBackground = new Texture(Gdx.files.internal("field.png"));
		dialogImage = new Image(dialogBackground);
		
		stage = new Stage();
		stage.addActor(background);
		background.toBack();
		background.setSize(W , H);
		
		buttonAtlas_gen = new TextureAtlas("buttons/buttons.pack");
		skin_gen = new Skin();
		skin_gen.addRegions(buttonAtlas_gen);

		font_gen = new BitmapFont(Gdx.files.internal("fonts/algeran.fnt"), false);
		BitmapFont dialogFont = new BitmapFont(Gdx.files.internal("fonts/dialog.fnt"));

		
		buttonStyle_gen = new TextButtonStyle();	
		buttonStyle_gen.up = skin_gen.getDrawable("button");
		buttonStyle_gen.down = skin_gen.getDrawable("button_flipped");
		buttonStyle_gen.font = font_gen;
		
		skin_gen.add("default",  new WindowStyle(dialogFont, Color.GREEN, dialogImage.getDrawable()));
		skin_gen.add("default", new LabelStyle(dialogFont, Color.BLUE));
		skin_gen.add("default", buttonStyle_gen);
		
		/**
		 * BUTTON CONTINUE DECLARATION
		 */
		buttonContinue = new MyButton("CONTINUE", buttonStyle_gen, W / 3.5f, H / 8, (W / 2) - (W / 7), H -  (H / 8) * 4);
		
		stage.addActor(buttonContinue);
		
		buttonContinue.addListener(new MyScreenListener(buttonContinue, game, gameScreen));
		
	
		/**
		 * BUTTON NEW GAME DECLARATION
		 */
		buttonNewGame = new MyButton("NEW GAME", buttonStyle_gen, W / 3.5f, H / 8, (W / 2) - ( W / 7), H -  (H / 8) * 4);
		
		stage.addActor(buttonNewGame);
		
		buttonNewGame.addListener(new MyScreenListener(buttonNewGame, game, gameScreen));
	
		
		/**
		 * BUTTON SELECT LEVEL DECLARATION
		 */
		buttonLevels = new MyButton("SELECT LEVEL", buttonStyle_gen, W / 3.5f, H / 8, (W / 2) - ( W / 7), H -  (H / 8) * 5.5f);
		
		stage.addActor(buttonLevels);
				
		buttonLevels.addListener(new MyScreenListener(buttonLevels, game, levelScreen));
		
		//BUTTON DEL
		//for testing purposes
		//it will erase file "data.dat"
		
		buttonDel = new MyButton("DEL", buttonStyle_gen, W / 3.5f, H / 8, (W / 2) - ( W / 7), H -  (H / 8) * 7);
		
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
					file = Gdx.files.local("data2.dat");
					if (file.exists()) {
						file.delete();
					}
			}
		});
	
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		
		FileHandle file = Gdx.files.local("data.dat");
		if(!file.exists()){
			buttonContinue.setVisible(false);
			buttonNewGame.setVisible(true);
		} else {
			buttonContinue.setVisible(true);
			buttonNewGame.setVisible(false);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.BACK) && !backKeyPressed){
			backKeyPressed = true;
			new Dialog("CONFIRM EXIT", skin_gen){
				{
					background.setColor(background.getColor().r, background.getColor().g, background.getColor().b, 0.2f);
					text("DO YOU REALLY WANT TO QUIT?");
					button("YES", "Y");
					button("NO", "N");
					
				}
				@Override
				protected void result(Object object) {
					if(object == "Y") Gdx.app.exit();
					if(object == "N"){
						background.setColor(background.getColor().r, background.getColor().g, background.getColor().b, 1);
						backKeyPressed = false;
					}
					super.result(object);
				}
			}.show(stage);
		}
		
		
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


	//GETTERS AND SETTERS
	public static int getMaxLevel() {
		return maxLevel;
	}



	public static void setMaxLevel(int maxLevel) {
		MainMenu.maxLevel = maxLevel;
	}



	public static int getCurrentLevel() {
		return currentLevel;
	}



	public static void setCurrentLevel(int currentLevel) {
		MainMenu.currentLevel = currentLevel;
	}

}
