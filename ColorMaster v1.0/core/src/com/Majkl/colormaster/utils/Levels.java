package com.Majkl.colormaster.utils;

import com.badlogic.gdx.math.Vector2;


public class Levels {
	
	public static final int LEVELS_MAX = 5;
	
	public Items select(int lvlNumber) {
		Items tempItems = null;
		switch(lvlNumber) {
		case 1:
			tempItems = lvl_1();
			break;
		case 2:
			tempItems = lvl_2();
			break;	
			
		case 3:
			tempItems = lvl_3();
			break;
			
		case 4:
			tempItems = lvl_4();
			break;
			
		case 5:
			tempItems = lvl_5();
			break;
			
		default:
			tempItems = new Items(1, 1);
			break;
		}
		return tempItems;
	}
	
	
	
	public Items lvl_1() {
		Items l1 = new Items(10, 5);
		l1.fillGameMap(0, 3, 1);
		l1.fillGameMap(1, 3, 1);
		l1.fillGameMap(2, 3, 3);
		l1.fillGameMap(3, 3, 2);
		l1.fillGameMap(4, 3, 4);
		
		l1.fillGateColor(3, 3, 1);
		
		l1.fillDropColor(2, 3, 1);
		
		l1.setStartPosition(new Vector2(0, 3));
		
		return l1;
	}
	
	public Items lvl_2() {
		Items l2 = new Items(10, 5);
		l2.fillGameMap(0, 3, 1);
		l2.fillGameMap(1, 3, 1);
		l2.fillGameMap(2, 3, 1);
		l2.fillGameMap(2, 4, 1);
		l2.fillGameMap(2, 2, 1);
		l2.fillGameMap(3, 4, 3);
		l2.fillGameMap(3, 2, 2);
		l2.fillGameMap(4, 4, 2);
		l2.fillGameMap(4, 2, 1);
		l2.fillGameMap(4, 3, 1);
		l2.fillGameMap(5, 3, 1);
		l2.fillGameMap(6, 3, 4);
		
		l2.fillGateColor(3, 2, 3);
		l2.fillGateColor(4, 4, 1);		
		
		l2.fillDropColor(3, 4, 3);
		
		l2.setStartPosition(new Vector2(0, 3));

		return l2;
	}
	
	public Items lvl_3() {
		Items l3 = new Items(10, 5);
		l3.fillGameMap(0, 3, 1);
		l3.fillGameMap(1, 3, 1);
		l3.fillGameMap(2, 3, 3);
		l3.fillGameMap(3, 3, 3);
		l3.fillGameMap(4, 3, 2);
		l3.fillGameMap(5, 3, 4);
		
		l3.fillGateColor(4, 3, 6);
		
		l3.fillDropColor(2, 3, 1);
		l3.fillDropColor(3, 3, 3);
		
		l3.setStartPosition(new Vector2(0, 3));
		
		return l3;
	}
	
	public Items lvl_4() {
		Items l4 = new Items(10, 5);
		l4.fillGameMap(0, 3, 1);
		l4.fillGameMap(1, 3, 1);
		l4.fillGameMap(2, 3, 1);
		l4.fillGameMap(2, 1, 3);
		l4.fillGameMap(2, 2, 3);
		l4.fillGameMap(2, 4, 3);
		l4.fillGameMap(3, 3, 2);
		l4.fillGameMap(4, 3, 2);
		l4.fillGameMap(5, 3, 4);
		
		l4.fillGateColor(3, 3, 6);
		l4.fillGateColor(4, 3, 2);
		
		l4.fillDropColor(2, 1, 2);
		l4.fillDropColor(2, 2, 1);
		l4.fillDropColor(2, 4, 3);
		
		l4.setStartPosition(new Vector2(0, 3));
		
		return l4;
	}
	
	public Items lvl_5() {
		Items l5 = new Items(10, 5);
		l5.fillGameMap(0, 4, 1);
		l5.fillGameMap(0, 3, 3);
		l5.fillGameMap(0, 2, 2);
		l5.fillGameMap(0, 0, 1);
		l5.fillGameMap(1, 4, 1);
		l5.fillGameMap(1, 2, 1);
		l5.fillGameMap(1, 0, 3);
		l5.fillGameMap(2, 4, 1);
		l5.fillGameMap(2, 2, 1);
		l5.fillGameMap(2, 1, 2);
		l5.fillGameMap(2, 0, 3);
		l5.fillGameMap(3, 4, 1);
		l5.fillGameMap(3, 2, 3);
		l5.fillGameMap(4, 4, 1);
		l5.fillGameMap(4, 2, 2);
		l5.fillGameMap(4, 1, 1);
		l5.fillGameMap(4, 0, 3);
		l5.fillGameMap(5, 4, 1);
		l5.fillGameMap(6, 4, 1);
		l5.fillGameMap(6, 3, 1);
		l5.fillGameMap(6, 2, 2);
		l5.fillGameMap(6, 1, 1);
		l5.fillGameMap(6, 0, 4);
		l5.fillGameMap(7, 3, 1);
		l5.fillGameMap(7, 2, 3);

		l5.fillGateColor(0, 2, 4);
		l5.fillGateColor(2, 1, 5);
		l5.fillGateColor(4, 2, 1);
		l5.fillGateColor(6, 2, 5);

		l5.fillDropColor(0, 3, 1);
		l5.fillDropColor(1, 0, 4);
		l5.fillDropColor(2, 1, 5);
		l5.fillDropColor(2, 0, 1);
		l5.fillDropColor(3, 2, 4);
		l5.fillDropColor(4, 0, 4);
		l5.fillDropColor(7, 2, 1);

		l5.setStartPosition(new Vector2(0, 0));

		return l5;
	}

}
