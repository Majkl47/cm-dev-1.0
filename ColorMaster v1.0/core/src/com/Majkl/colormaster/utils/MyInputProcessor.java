package com.Majkl.colormaster.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;


public class MyInputProcessor implements InputProcessor {
	
	private Vector2 position;
	private int downX, downY, distance;
	private String direction;
	
	public MyInputProcessor(int fieldlength, Vector2 startPosition) {
		distance = fieldlength;
		position = startPosition;
		direction = "";
	}
	
	public void update(Vector2 position) {
		this.position = position;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		downX = screenX;
		downY = screenY;
		return true;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (screenX == downX && screenY == downY) return true;
		if (Math.abs(screenX - downX) < Gdx.graphics.getWidth() / 100 && Math.abs(screenY - downY) < Gdx.graphics.getWidth() / 100) return true;
		if (Math.abs(screenX - downX) > Math.abs(screenY - downY)) {
			if (downX < screenX) {
				direction = "right";
				position.x += distance;
			} else {
				direction = "left";
				position.x -= distance;
			}
		} else if (downY < screenY) {
			direction = "down";
			position.y -= distance;
		} else {
			direction = "up";
			position.y += distance;
		}
		return true;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void positionReadjust() {
		if(direction == "up"){
			position.y -= distance;
		}
		if(direction == "down"){
			position.y += distance;
		}
		if(direction == "left"){
			position.x += distance;
		}
		if(direction == "right"){
			position.x -= distance;
		}
	}
}
