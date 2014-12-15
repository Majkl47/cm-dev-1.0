package com.Majkl.colormaster.utils;

import com.badlogic.gdx.math.Vector2;


public class Items {
	private transient int[][] gatesColor;
	private transient int[][] dropsColor; 
	private transient int width;
	private transient int height;
	private int[][] gameMap;
	private Vector2 playerPosition;
	private int playerColor;
	private int currentLevel;
	



	public Items(int sizeX, int sizeY) {
		gameMap = new int[sizeX][sizeY];
		gatesColor = new int[sizeX][sizeY];
		dropsColor = new int[sizeX][sizeY];
		this.width = sizeX;
		this.height = sizeY;
	}
	
	public Items() {
	}

	
	public void fillGameMap(int x, int y, int value) {
		gameMap[x][y] = value;
	}
	
	public int getGameMapValue(int x, int y) {
		return gameMap[x][y];
	}
	
	public int[][] getMap() {
		return gameMap;
	}

	public void fillDropColor(int x, int y, int value) {
		dropsColor[x][y] = value;
	}
	
	public int getDropColor(int x , int y) {
		return dropsColor[x][y];
	}
	
	public void fillGateColor(int x, int y, int value) {
		gatesColor[x][y] = value;
	}
	
	public int getGateColor(int x, int y) {
		return gatesColor[x][y];
	}

	public void setPlayer(Vector2 playerPosition, int playerColor) {
		this.playerPosition = playerPosition;
		this.playerColor = playerColor;
	}
	
	public void setPlayerColor(int playerColor) {
		this.playerColor = playerColor;
	}
	
	public  void setPlayerPosition(Vector2 playerPosition) {
		this.playerPosition = playerPosition;
	}

	public int getPlayerColor() {
		return playerColor;
	}
	

	public Vector2 getPlayerPosition() {
		return playerPosition;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}
	
	
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public void deleteItem(int x, int y) {
		gameMap[x][y] = 1;
	}
	
	public void copyArray(int[][] tempArray) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				gameMap[i][j] = tempArray[i][j];
			}
		}
	}
}
