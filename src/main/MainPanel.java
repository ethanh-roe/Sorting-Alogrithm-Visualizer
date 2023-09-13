package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import java.util.Timer;

public class MainPanel extends javax.swing.JPanel {
	LineList mainList = new LineList();


	static final int PanelWidth = 1024;
	static final int PanelHeight = 715;
	static int speed = 10;
	static int comparisons = 0;
	static int writesToArray = 0;
	static int numElements = 100;
	static int stroke = (PanelWidth / numElements) - 1;

	public MainPanel() {
		initPanel();

	}

	private void initPanel() {
		mainList.addLines(numElements);
		setBackground(Color.darkGray);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintList(g);
	}

	public void paintList(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(stroke));
		for (int i = 0; i < mainList.getSize(); i++) {
			Line l = mainList.get(i);
			g2.setColor(l.color);
			g2.drawLine(l.x1, l.y1, l.x2, l.y2);
		}
	}

	public void refresh() {
		stroke = (PanelWidth / numElements) - 1;
		repaint();
	}

	public LineList getLineList() {
		return mainList;
	}

	public void updateList() {
		mainList.addLines(numElements);
	}

}
