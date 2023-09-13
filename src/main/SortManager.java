package main;

import java.awt.Color;

public class SortManager {
	
	
	
	static void sortComplete(LineList list, TimerManager timerManager) {
		int i = 0;
		while(i < list.getSize()) {
			list.get(i).setColor(Color.white);
			i++;
		}
		i = 0;
		while(i < list.getSize()) {
			list.get(i).setColor(Color.green);
			timerManager.pause(MainPanel.speed * 2);
			i++;
		}
		
	}

}
