package com.Majkl.colormaster.screens;

import com.Majkl.colormaster.utils.ButtonCreator;
import com.Majkl.colormaster.utils.MyScreenListener;
import com.Majkl.colormaster.utils.Saves;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;

public class MainMenu implements Screen {
	
	//Dimensions Shortcuts
	public static final int W = Gdx.graphics.getWidth();
	public static final int H = Gdx.graphics.getHeight();
	
	private boolean backKeyPressed = false;	
	
	private static int maxLevel;
	private static int currentLevel;
	private Saves saves;
	
	private Game game;
	
	private GameScreen gameScreen;
	private LevelScreen levelScreen;
		
	private Stage stage;
	private ButtonCreator buttonCreator;
	private Skin skin;
	private Texture backgroundTexture, dialogBackground; 
	private TextButton buttonContinue, buttonNewGame, buttonLevels, buttonDel;
	private Image background, dialogImage;
	
	
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
		
		buttonCreator = new ButtonCreator();
		
		BitmapFont dialogFont = new BitmapFont(Gdx.files.internal("fonts/dialog.fnt"));		
		skin = buttonCreator.getSkin(); 
		skin.add("default",  new WindowStyle(dialogFont, Color.GREEN, dialogImage.getDrawable()));
		skin.add("default", new LabelStyle(dialogFont, Color.BLUE));
		buttonCreator.setSkin(skin);
		skin.add("default", buttonCreator.getStyle());
		
		//BUTTON CONTINUE DECLARATION
		buttonContinue = buttonCreator.newButton("CONTINUE", W / 3.5f, H / 8, (W / 2) - (W / 7), H -  (H / 8) * 4);
		buttonContinue.addListener(new MyScreenListener(buttonContinue, game, gameScreen));
		stage.addActor(buttonContinue);

		//BUTTON NEW GAME DECLARATION
		buttonNewGame = buttonCreator.newButton("NEW GAME", W / 3.5f, H / 8, (W / 2) - ( W / 7), H -  (H / 8) * 4);
		buttonNewGame.addListener(new MyScreenListener(buttonNewGame, game, gameScreen));
		stage.addActor(buttonNewGame);

		//BUTTON SELECT LEVEL DECLARATION
		buttonLevels = buttonCreator.newButton("SELECT LEVEL", W / 3.5f, H / 8, (W / 2) - ( W / 7), H -  (H / 8) * 5.5f);
		buttonLevels.addListener(new MyScreenListener(buttonLevels, game, levelScreen));
		stage.addActor(buttonLevels);

		//BUTTON DEL
		//for testing purposes
		//it will erase file "data.dat"
		buttonDel = buttonCreator.newButton("DEL", W / 3.5f, H / 8, (W / 2) - ( W / 7), H -  (H / 8) * 7);
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
	
		stage.addActor(buttonDel);
		
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		
		//Check for previous saves, shows appropriate button
		FileHandle file = Gdx.files.local("data.dat");
		if(!file.exists()){
			buttonContinue.setVisible(false);
			buttonNewGame.setVisible(true);
		} else {
			buttonContinue.setVisible(true);
			buttonNewGame.setVisible(false);
		}
		
		//BACK KEY functionality
		if (LevelScreen.isBackKeyPressed() && !Gdx.input.isKeyPressed(Keys.BACK)) {
			LevelScreen.setBackKeyPressed(false);
		}
			//Dialog Window implementation
		if(Gdx.input.isKeyPressed(Input.Keys.BACK) && !backKeyPressed && !LevelScreen.isBackKeyPressed()){
			backKeyPressed = true;
			new Dialog("CONFIRM EXIT", skin){
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
		skin.dispose();
		buttonCreator.dispose();
		backgroundTexture.dispose();
		dialogBackground.dispose();
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
