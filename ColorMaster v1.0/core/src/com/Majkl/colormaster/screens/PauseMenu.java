package com.Majkl.colormaster.screens;

import com.Majkl.colormaster.utils.MyButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


public class PauseMenu {
	
	private boolean called = false;
	
	private boolean isOpen; 
	
	private int eventCode;
	
	private TextureAtlas buttonAtlas_gen;
	private Skin skin_gen;
	private BitmapFont font_gen;
	private TextButtonStyle buttonStyle_gen;
	private Stage stage;
	private MyButton buttonResume, buttonRestart, buttonLevels, buttonMenu;
	
	
		

	public PauseMenu() {
		called = true;
		
		buttonAtlas_gen = new TextureAtlas("buttons/buttons.pack");
		skin_gen = new Skin();
		skin_gen.addRegions(buttonAtlas_gen);
		font_gen = new BitmapFont(Gdx.files.internal("fonts/algeran.fnt"), false);
		stage = new Stage();
		
		buttonStyle_gen = new TextButtonStyle();	
		buttonStyle_gen.up = skin_gen.getDrawable("button");
		buttonStyle_gen.down = skin_gen.getDrawable("button_flipped");
		buttonStyle_gen.font = font_gen;
		
		buttonResume = new MyButton("RESUME", buttonStyle_gen, 3.5f, 8, 3f);
		buttonResume.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,	int pointer, int button) {
				if(Gdx.input.getX() >= buttonResume.getX() && Gdx.input.getX() < buttonResume.getX() + buttonResume.getWidth() && (Gdx.graphics.getHeight() - 
						Gdx.input.getY()) >= buttonResume.getY() && (Gdx.graphics.getHeight() - Gdx.input.getY()) < buttonResume.getY() + buttonResume.getHeight()) {
					eventCode = 1;
					setOpen(false);
				}
			}
		});
		stage.addActor(buttonResume);
		
		buttonRestart = new MyButton("RESTART", buttonStyle_gen, 3.5f, 8, 4.5f);
		buttonRestart.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,	int pointer, int button) {
				if(Gdx.input.getX() >= buttonRestart.getX() && Gdx.input.getX() < buttonRestart.getX() + buttonRestart.getWidth() && (Gdx.graphics.getHeight() - 
						Gdx.input.getY()) >= buttonRestart.getY() && (Gdx.graphics.getHeight() - Gdx.input.getY()) < buttonRestart.getY() + buttonRestart.getHeight()) {
					eventCode = 2;
					setOpen(false);
				}
			}
		});
		stage.addActor(buttonRestart);
		
		buttonLevels = new MyButton("SELECT LEVEL", buttonStyle_gen, 3.5f, 8, 6);
		buttonLevels.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,	int pointer, int button) {
				if(Gdx.input.getX() >= buttonLevels.getX() && Gdx.input.getX() < buttonLevels.getX() + buttonLevels.getWidth() && (Gdx.graphics.getHeight() - 
						Gdx.input.getY()) >= buttonLevels.getY() && (Gdx.graphics.getHeight() - Gdx.input.getY()) < buttonLevels.getY() + buttonLevels.getHeight()) {
					eventCode = 3;
					setOpen(false);
				}
			}
		});
		stage.addActor(buttonLevels);
		
		buttonMenu = new MyButton("MENU", buttonStyle_gen, 12, 8, 1);
		buttonMenu.setPosition(10, Gdx.graphics.getHeight() - (buttonMenu.getHeight() + 10));
		buttonMenu.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,	int pointer, int button) {
				if(Gdx.input.getX() >= buttonMenu.getX() && Gdx.input.getX() < buttonMenu.getX() + buttonMenu.getWidth() && (Gdx.graphics.getHeight() - 
						Gdx.input.getY()) >= buttonMenu.getY() && (Gdx.graphics.getHeight() - Gdx.input.getY()) < buttonMenu.getY() + buttonMenu.getHeight()) {
					eventCode = 4;
					setOpen(false);
				}
			}
		});
		stage.addActor(buttonMenu);
		
		
	}
	
	public void render() {
		Gdx.input.setInputProcessor(stage);
		
		ShapeRenderer render = new ShapeRenderer();
		Gdx.gl20.glEnable(GL20.GL_BLEND);
		render.begin(ShapeType.Filled);
		render.setColor(0, 0, 0, 0.6f);
		render.rect(-10, -10, Gdx.graphics.getWidth() + 20, Gdx.graphics.getHeight() + 20);
		render.end();

		Gdx.gl20.glDisable(GL20.GL_BLEND);
		
		stage.act();
		stage.draw();
	}
	
	public void dispose() {
		buttonAtlas_gen.dispose();
		skin_gen.dispose();
		font_gen.dispose();
		stage.dispose();
	}
	
	
	//GETTERS AND SETTERS
	public boolean isCalled() {
		return called;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public int getEventCode() {
		return eventCode;
	}
}
