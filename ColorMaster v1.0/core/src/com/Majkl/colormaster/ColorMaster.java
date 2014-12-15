package com.Majkl.colormaster;

import com.Majkl.colormaster.screens.MainMenu;
import com.badlogic.gdx.Game;

public class ColorMaster extends Game {

	private Game game;
	private MainMenu mainMenu;
	
	@Override
	public void create () {
		game = this;
		mainMenu = new MainMenu(game);
		setScreen(mainMenu);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose() {
		mainMenu.dispose();
		super.dispose();
	}
}
