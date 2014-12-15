package com.Majkl.colormaster.screens;

import com.Majkl.colormaster.utils.Items;


public class Levels {
	
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
		l1.fillGameMap(0, 1, 3);
		l1.fillGameMap(1, 1, 1);
		l1.fillGameMap(1, 2, 1);
		l1.fillGameMap(1, 3, 1);
		l1.fillGameMap(2, 3, 3);
		l1.fillGameMap(3, 3, 2);
		l1.fillGameMap(4, 3, 2);
		l1.fillGameMap(5, 3, 2);
		l1.fillGameMap(6, 3, 4);
		
		l1.fillGateColor(4, 3, 5);
		l1.fillGateColor(3, 3, 4);
		l1.fillGateColor(5, 3, 1);
		
		l1.fillDropColor(2, 3, 2);
		l1.fillDropColor(0, 1, 2);
		return l1;
	}
	
	public Items lvl_02() {
		Items l2 = new Items(10, 5);

		return l2;
	}
}
