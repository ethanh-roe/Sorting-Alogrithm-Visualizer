package main;

import java.awt.Color;
import java.util.ArrayList;

public class LineList {

	ArrayList<Line> list;

	public LineList() {
		list = new ArrayList<>();
	}

	public void addLines(int size) {
		list.clear();
		int i = 0;

		int x1 = MainPanel.PanelWidth - (MainPanel.numElements * (MainPanel.stroke + 1)) + (MainPanel.stroke / 2) - 12;
		int x2 = x1;

		int y1 = 0;
		int y2 = MainPanel.PanelHeight;
		while (i++ < size) {
			
			y1 = (int) (Math.random() * (MainPanel.PanelHeight - 100)) + 100;
			list.add(new Line(x1, y1, x2, y2, Color.white));
			x1 += MainPanel.stroke + 1;
			x2 += MainPanel.stroke + 1;

		}

	}

	public void scramble() {
		addLines(list.size());

	}
	
	public void swap(LineList list, int i, int j) {
		Line temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

	public int getSize() {
		return list.size();
	}

	public Line get(int index) {
		return list.get(index);
	}

	public void set(int index, Line line) {
		list.set(index, line);
	}


}
