package com.Majkl.colormaster.screens;

import com.Majkl.colormaster.utils.MyButton;
import com.Majkl.colormaster.utils.MyScreenListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class EndScreen implements Screen {
	
	public static final int W = Gdx.graphics.getWidth();
	public static final int H = Gdx.graphics.getHeight();

	private Game game;
	private TextureAtlas atlas;
	private Skin skin;
	private BitmapFont font;
	private TextButtonStyle style;
	private MyButton buttonConfirm;
	private Stage stage;
	
	private Label text;
	private LabelStyle textStyle;
	
	
	public EndScreen(Game game) {
		this.game = game;
	}
	
	
	@Override
	public void show() {
		stage = new Stage();
		atlas = new TextureAtlas("buttons/buttons.pack");
		skin = new Skin();
		skin.addRegions(atlas);

		font = new BitmapFont(Gdx.files.internal("fonts/algeran.fnt"), false);
		
		textStyle = new LabelStyle(font, Color.BLACK);
		text = new Label("CONGRATULATIONS, YOU SOLVED ALL AVAILABLE LEVELS!", textStyle);
		text.setPosition(W / 2 - text.getWidth() / 2, H * 2 / 3);
		
		stage.addActor(text);
		
		
		style = new TextButtonStyle();	
		style.up = skin.getDrawable("button");
		style.down = skin.getDrawable("button_flipped");
		style.font = font;
		
		buttonConfirm = new MyButton("CONTINUE", style, W / 3.5f, H / 8, (W / 2) - (W / 7), H -  (H / 8) * 7.5f);
		
		stage.addActor(buttonConfirm);
		
		buttonConfirm.addListener(new MyScreenListener(buttonConfirm, game, new MainMenu(game)));
		
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
		// TODO Auto-generated method stub
		
	}

}
