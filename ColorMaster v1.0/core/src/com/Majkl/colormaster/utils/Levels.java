package com.Majkl.colormaster.utils;

import com.badlogic.gdx.math.Vector2;


public class Levels {
	
	public static final int LEVELS_MAX = 4;
	
	public Items select(int lvlNumber) {
		Items tempItems = null;
		switch(lvlNumber) {
		case 1:
			tempItems = lvl_01();
			break;
		case 2:
			tempItems = lvl_02();
			break;	
			
		case 3:
			tempItems = lvl_03();
			break;
			
		case 4:
			tempItems = lvl_04();
			break;
			
		default:
			tempItems = new Items(1, 1);
			break;
		}
		return tempItems;
	}
	
	
	
	public Items lvl_01() {
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
	
	public Items lvl_02() {
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
	
	public Items lvl_03() {
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
	
	public Items lvl_04() {
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
}
