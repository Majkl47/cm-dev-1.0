package com.Majkl.colormaster.utils;

import com.badlogic.gdx.graphics.Color;

public class MyColor extends Color {
	
	public static final Color ORANGE = new Color(1, 0.5f, 0, 1);
	
	
	public MyColor() {
		
	}
	
	public Color toColor(int c) {
		switch (c) {
		case (-1):
			return MyColor.WHITE;
		case 0:
			return MyColor.BLACK;
		case 1:
			return MyColor.RED;
		case 2:
			return MyColor.GREEN;
		case 3:
			return MyColor.BLUE;
		case 4:
			return MyColor.YELLOW;
		case 5:
			return MyColor.ORANGE;
		default:
			return Color.GRAY;
		}
	}
	
	public int fromColor(Color c) {
		if (c == MyColor.WHITE) return -1;
		if (c == MyColor.BLACK) return 0;
		if (c == MyColor.RED) return 1;
		if (c == MyColor.GREEN) return 2;
		if (c == MyColor.BLUE) return 3;
		if (c == MyColor.YELLOW) return 4;
		if (c == MyColor.ORANGE) return 5;
		
		return -2;
	}
}
