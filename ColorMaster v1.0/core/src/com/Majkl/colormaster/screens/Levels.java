package com.Majkl.colormaster.screens;

import com.Majkl.colormaster.utils.Items;


public class Levels {
	
	public static final int LEVELS_MAX = 2;
	
	public Items select(int lvlNumber) {
		Items tempItems = null;
		switch(lvlNumber) {
		case 1:
			tempItems = lvl_01();
			break;
		case 2:
			tempItems = lvl_02();
			break;			
			
		default:
			tempItems = new Items(1, 1);
			break;
		}
		return tempItems;
	}
	
	
	
	public Items lvl_01() {
		Items l1 = new Items(10, 5);
		l1.fillGameMap(0, 0, 1);
		l1.fillGameMap(1, 0, 1);
		l1.fillGameMap(2, 0, 3);
		l1.fillGameMap(3, 0, 2);
		l1.fillGameMap(4, 0, 4);
		
		l1.fillGateColor(3, 0, 1);
		
		l1.fillDropColor(2, 0, 1);
		
		return l1;
	}
	
	public Items lvl_02() {
		Items l2 = new Items(10, 5);
		l2.fillGameMap(0, 0, 1);
		l2.fillGameMap(1, 0, 1);
		l2.fillGameMap(2, 0, 3);
		l2.fillGameMap(3, 0, 1);
		l2.fillGameMap(3, 1, 2);
		l2.fillGameMap(4, 1, 1);
		l2.fillGameMap(5, 1, 4);
		
		l2.fillGateColor(3, 1, 3);
		
		l2.fillDropColor(2, 0, 3);

		return l2;
	}
}
