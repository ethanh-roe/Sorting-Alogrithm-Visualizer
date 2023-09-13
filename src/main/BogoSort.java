package main;

public class BogoSort {
	private TimerManager timerManager;
	private LineList lineList;
 
	public BogoSort(LineList lineList, TimerManager timerManager) {
		this.lineList = lineList;
		this.timerManager = timerManager;

	}

	public void sort() {

		while (!isSorted(lineList)) {
			if (timerManager.getScrambled()) {
				MainPanel.writesToArray = 0;
				MainPanel.comparisons = 0;
				return;
			}
			shuffle(lineList);

			timerManager.pause(MainPanel.speed);
		}
	}

	private void shuffle(LineList lineList) {
		int tempx1 = 0;
		int tempx2 = 0;
		for (int i = 0; i < lineList.getSize(); i++) {
			int rand = (int) (Math.random() * i);
			tempx1 = lineList.get(i).getx1();
			tempx2 = lineList.get(i).getx2();
			lineList.get(i).setxs(lineList.get(rand).getx1(), lineList.get(rand).getx2());
			lineList.get(rand).setxs(tempx1, tempx2);
			lineList.swap(lineList, i, rand);
			MainPanel.writesToArray++;
		}
	}

	private boolean isSorted(LineList lineList) {
		for (int i = 1; i < lineList.getSize(); i++) {
			MainPanel.comparisons++;
			if (lineList.get(i).getYSize() < lineList.get(i - 1).getYSize()) {
				
				return false;
			}

		}
		return true;
	}

}
