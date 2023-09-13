package main;

import java.awt.Color;
import java.util.ArrayList;

public class MergeSort	{
	private TimerManager timerManager;
	private LineList lineList;
	
	public MergeSort(LineList lineList, TimerManager timerManager) {
		this.lineList = lineList;
		this.timerManager = timerManager;
	}
	
	public void sort(LineList lineList, int lef, int rig) {

		if (lef < rig) {

			int m = lef + (rig - lef) / 2;
			sort(lineList, lef, m);
			sort(lineList, m + 1, rig);

			merge(lineList, lef, m, rig);
			
		}

	}

	private void merge(LineList arr, int lef, int mid, int rig) {
		if(timerManager.getScrambled()) {
			MainPanel.writesToArray = 0;
			MainPanel.comparisons = 0;
			return;
		}
		int n1 = mid - lef + 1;
		int n2 = rig - mid;

		ArrayList<Line> left = new ArrayList<>();
		ArrayList<Line> right = new ArrayList<>();

		for (int i = 0; i < n1; ++i) {
			left.add(arr.get(lef + i));
		}
		for (int j = 0; j < n2; ++j) {
			right.add(arr.get(mid + 1 + j));
		}
		int lefti = 0;
		int righti = 0;
		int k = lef;
		int x = lineList.get(k).getx1();
		
		while (lefti < n1 && righti < n2) {
			MainPanel.comparisons++;
			left.get(lefti).setColor(Color.red);
			right.get(righti).setColor(Color.red);
			if (left.get(lefti).getYSize() <= right.get(righti).getYSize()) {
				lineList.set(k, left.get(lefti));
				lineList.get(k).setxs(x, x);
				MainPanel.writesToArray++;
				lefti++;
				x += MainPanel.stroke + 1;
				timerManager.pause(MainPanel.speed);
				left.get(lefti - 1).setColor(Color.white);
				lineList.get(k).setColor(Color.white);
			} else {
				lineList.set(k, right.get(righti));
				lineList.get(k).setxs(x, x);
				lineList.get(k).setColor(Color.red);
				MainPanel.writesToArray++;
				righti++;
				x += MainPanel.stroke + 1;
				timerManager.pause(MainPanel.speed);
				right.get(righti - 1).setColor(Color.red);
				lineList.get(k).setColor(Color.white);
			}
			timerManager.pause(MainPanel.speed);
			k++;

		}
		while (lefti < n1) {
			left.get(lefti).setColor(Color.red);
			lineList.set(k, left.get(lefti));
			lineList.get(k).setxs(x, x);
			lineList.get(k).setColor(Color.red);
			MainPanel.writesToArray++;
			lefti++;
			x += MainPanel.stroke + 1;
			k++;
			timerManager.pause(MainPanel.speed);
			left.get(lefti - 1).setColor(Color.white);
			lineList.get(k - 1).setColor(Color.white);
		}

		while (righti < n2) {
			lineList.set(k, right.get(righti));
			lineList.get(k).setxs(x, x);
			lineList.get(k).setColor(Color.red);
			right.get(righti).setColor(Color.red);
			MainPanel.writesToArray++;
			righti++;
			k++;
			x += MainPanel.stroke + 1;
			timerManager.pause(MainPanel.speed);
			right.get(righti - 1).setColor(Color.white);
			lineList.get(k - 1).setColor(Color.white);
		}
		

	}

}
