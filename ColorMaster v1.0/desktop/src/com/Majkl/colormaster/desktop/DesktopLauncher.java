package com.Majkl.colormaster.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Majkl.colormaster.ColorMaster;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Color Master v1.0";
		config.width = 1136;
		config.height = 640;
		new LwjglApplication(new ColorMaster(), config);
	}
}
