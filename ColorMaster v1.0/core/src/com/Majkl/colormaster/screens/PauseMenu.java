package com.Majkl.colormaster.screens;

import com.Majkl.colormaster.utils.ButtonCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


public class PauseMenu {
	
	//Dimensions Shortcuts
	public static final int W = Gdx.graphics.getWidth();
	public static final int H = Gdx.graphics.getHeight();
	
	private boolean called = false;
	
	private boolean isOpen; 
	
	private int eventCode;
	
	private Stage stage;
	private ButtonCreator buttonCreator;
	private TextButton buttonResume, buttonRestart, buttonLevels, buttonMenu;
	
	
		

	public PauseMenu() {
		called = true;
		
		stage = new Stage();
		buttonCreator = new ButtonCreator();
		
		
		//BUTTON RESUME DECLARATION
		buttonResume = buttonCreator.newButton("RESUME", W / 3.5f, H / 8, (W / 2) - (W / 7), H - (H / 8) * 3);
		buttonResume.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,	int pointer, int button) {
				if(Gdx.input.getX() >= buttonResume.getX() && Gdx.input.getX() < buttonResume.getX() + buttonResume.getWidth() && (H - 
						Gdx.input.getY()) >= buttonResume.getY() && (H - Gdx.input.getY()) < buttonResume.getY() + buttonResume.getHeight()) {
					eventCode = 1;
					setOpen(false);
				}
			}
		});
		stage.addActor(buttonResume);
		
		
		//BUTTON RESTART DECLARATION
		buttonRestart = buttonCreator.newButton("RESTART", W / 3.5f, H / 8, (W / 2) - (W / 7), H - (H / 8) * 4.5f);
		buttonRestart.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,	int pointer, int button) {
				if(Gdx.input.getX() >= buttonRestart.getX() && Gdx.input.getX() < buttonRestart.getX() + buttonRestart.getWidth() && (H - 
						Gdx.input.getY()) >= buttonRestart.getY() && (H - Gdx.input.getY()) < buttonRestart.getY() + buttonRestart.getHeight()) {
					eventCode = 2;
					setOpen(false);
				}
			}
		});
		stage.addActor(buttonRestart);
		
		
		//BUTTON SELECT LEVEL DECLARATION
		buttonLevels = buttonCreator.newButton("SELECT LEVEL", W / 3.5f, H / 8, (W / 2) - (W / 7), H - (H / 8) * 6);
		buttonLevels.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,	int pointer, int button) {
				if(Gdx.input.getX() >= buttonLevels.getX() && Gdx.input.getX() < buttonLevels.getX() + buttonLevels.getWidth() && (H - 
						Gdx.input.getY()) >= buttonLevels.getY() && (H - Gdx.input.getY()) < buttonLevels.getY() + buttonLevels.getHeight()) {
					eventCode = 3;
					setOpen(false);
				}
			}
		});
		stage.addActor(buttonLevels);
		
		
		//BUTTON MENU DECLARATION
		buttonMenu = buttonCreator.newButton("MENU", W / 12, H / 8, 10, H - ((H / 8) + 10));
		buttonMenu.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,	int pointer, int button) {
				if(Gdx.input.getX() >= buttonMenu.getX() && Gdx.input.getX() < buttonMenu.getX() + buttonMenu.getWidth() && (H - 
						Gdx.input.getY()) >= buttonMenu.getY() && (H - Gdx.input.getY()) < buttonMenu.getY() + buttonMenu.getHeight()) {
					eventCode = 4;
					setOpen(false);
				}
			}
		});
		stage.addActor(buttonMenu);
		
		
	}
	
	public void render() {
		Gdx.input.setInputProcessor(stage);
		
		//BACK KEY functionality
		if (Gdx.input.isKeyPressed(Keys.BACK) && GameScreen.isBackKeyReleased()) {
			eventCode = 1;
			GameScreen.setPauseBackKeyPressed(true);
			setOpen(false);
		}
		
		
		//FADE OUT effect
		ShapeRenderer render = new ShapeRenderer();
		Gdx.gl20.glEnable(GL20.GL_BLEND);
		render.begin(ShapeType.Filled);
		render.setColor(0, 0, 0, 0.6f);
		render.rect(-10, -10, W + 20, H + 20);
		render.end();

		Gdx.gl20.glDisable(GL20.GL_BLEND);
		
		
		stage.act();
		stage.draw();
	}
	
	public void dispose() {
		stage.dispose();
		buttonCreator.dispose();
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
