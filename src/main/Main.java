package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new JFrame("Sorting Algorithm Visualizer");
				MainPanel panel = new MainPanel();

				frame.setLayout(new BorderLayout());

				UiInterface UI = new UiInterface(panel);
				frame.add(UI, BorderLayout.NORTH);

				frame.add(panel, BorderLayout.CENTER);

				frame.setPreferredSize(new Dimension(1024, 800));

				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//				frame.setResizable(false);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}

		});

	}

}
