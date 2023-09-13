package main;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

public class BubbleSort {
	private TimerManager timerManager;
	private LineList lineList;

	public BubbleSort(LineList lineList, TimerManager timerManager) {
		this.lineList = lineList;
		this.timerManager = timerManager;
	}

	public void sort() {
		int swaps = 1;
		int tempx1 = 0;
		int tempx2 = 0;

		while (swaps > 0) {
			swaps = 0;
			for (int i = 1; i < lineList.getSize(); i++) {
				if(timerManager.getScrambled()) {
					MainPanel.writesToArray = 0;
					MainPanel.comparisons = 0;
					return;
				}
				MainPanel.comparisons++;
				lineList.get(i).setColor(Color.red);
				lineList.get(i - 1).setColor(Color.red);
				if (lineList.get(i - 1).getYSize() > lineList.get(i).getYSize()) {
					tempx1 = lineList.get(i - 1).getx1();
					tempx2 = lineList.get(i - 1).getx2();

					lineList.get(i - 1).setxs(lineList.get(i).getx1(), lineList.get(i).getx2());

					lineList.get(i).setxs(tempx1, tempx2);

					lineList.swap(lineList, i, i - 1);
					MainPanel.writesToArray++;

					swaps++;
				}
				timerManager.pause(MainPanel.speed);
				lineList.get(i).setColor(Color.white);
				lineList.get(i - 1).setColor(Color.white);

			}

		}

	}

}
