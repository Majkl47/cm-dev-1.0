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
		case 6:
			return MyColor.MAGENTA;
		case 7:
			return MyColor.CYAN;
		default:
			return MyColor.GRAY;
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
		if (c == MyColor.MAGENTA) return 6;
		if (c == MyColor.CYAN) return 7;
		
		return -2;
	}
	
//	public Color mixer(Color c1, Color c2) {
//		if ((c1 == MyColor.BLUE && c2 == MyColor.RED) || (c1 == MyColor.RED && c2 == MyColor.BLUE)) return MyColor.MAGENTA;
//		if ((c1 == MyColor.BLUE && c2 == MyColor.GREEN) || (c1 == MyColor.GREEN && c2 == MyColor.BLUE)) return MyColor.CYAN;
//		if ((c1 == MyColor.YELLOW && c2 == MyColor.RED) || (c1 == MyColor.RED && c2 == MyColor.YELLOW)) return MyColor.ORANGE;
//		return c1;
//	}
	
	public int mixer(int c1, int c2) {
		Color cl1 = toColor(c1), cl2 = toColor(c2);
	if ((cl1 == MyColor.BLUE && cl2 == MyColor.RED) || (cl1 == MyColor.RED && cl2 == MyColor.BLUE)) return 6;
	if ((cl1 == MyColor.BLUE && cl2 == MyColor.GREEN) || (cl1 == MyColor.GREEN && cl2 == MyColor.BLUE)) return 7;
	if ((cl1 == MyColor.YELLOW && cl2 == MyColor.RED) || (cl1 == MyColor.RED && cl2 == MyColor.YELLOW)) return 5;
	return c1;
}
}
