package main;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

public class SelectionSort {

	private TimerManager timerManager;
	private LineList lineList;

	public SelectionSort(LineList lineList, TimerManager timerManager) {
		this.lineList = lineList;
		this.timerManager = timerManager;
	}

	public void sort() {
		int tempx1 = 0;
		int tempx2 = 0;

		for (int j = 0; j < lineList.getSize(); j++) {
			if(timerManager.getScrambled()) {
				MainPanel.writesToArray = 0;
				MainPanel.comparisons = 0;
				return;
			}
			int currentMin = j;
			 timerManager.pause(MainPanel.speed);
			for (int i = j + 1; i < lineList.getSize(); i++) {
				lineList.get(i).setColor(Color.red);
				lineList.get(currentMin).setColor(Color.blue);
				MainPanel.comparisons++;
				if (lineList.get(i).getYSize() < lineList.get(currentMin).getYSize()) {
					lineList.get(currentMin).setColor(Color.white);
					currentMin = i;
					lineList.get(currentMin).setColor(Color.blue);
				}
				 timerManager.pause(MainPanel.speed);
				lineList.get(i).setColor(Color.white);

			}
			timerManager.pause(MainPanel.speed);
			tempx1 = lineList.get(j).getx1();
			tempx2 = lineList.get(j).getx2();

			lineList.get(j).setxs(lineList.get(currentMin).getx1(), lineList.get(currentMin).getx2());
			lineList.get(currentMin).setxs(tempx1, tempx2);

			lineList.swap(lineList, j, currentMin);
			MainPanel.writesToArray++;
			lineList.get(j).setColor(Color.green);
		}

	}

}
