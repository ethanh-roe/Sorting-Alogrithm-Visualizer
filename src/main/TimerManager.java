package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.TimerTask;

public class TimerManager implements ActionListener {
	private boolean running;
	MainPanel panel;
	Timer timer;
	UiInterface UI;
	private boolean scrambled;

	public TimerManager(MainPanel panel, UiInterface UI) {
		this.panel = panel;
		this.UI = UI;
		scrambled = false;
		running = true;
		timer = new Timer(MainPanel.speed, this);
		timer.start();
	}

	public void pause(int delay) {
		if(scrambled) {
			return;
		}
		while (!running) {
			try {
				Thread.sleep(1);
				if (scrambled) {
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void stop() {
		running = false;
	}

	public void start() {
		running = true;

	}
	
	public boolean getScrambled() {
		return scrambled;
	}

	public void setScrambled(boolean t) {
		scrambled = t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		panel.refresh();
		timer.setDelay(MainPanel.speed);
		UI.updateLabels();
	}

}
