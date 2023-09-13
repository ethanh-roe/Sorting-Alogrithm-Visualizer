package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UiInterface extends JPanel {

	int sortType;

	MainPanel panel;

	JPanel speedPanel;
	JPanel numElementsPanel;
	JPanel statsPanel;

	JLabel comparisonsLabel;
	JLabel writesLabel;
	JLabel speedLabel;
	JLabel numElementsLabel;

	JSlider speedSlider;
	JSlider numElementsSlider;

	JButton scrambleButton;
	JButton stopButton;
	JButton sortButton;

	JComboBox sortSelection;


	private TimerManager timerManager;

	public UiInterface(MainPanel panel) {
		this.panel = panel;
		this.setBackground(Color.darkGray);

		speedPanel = new JPanel();
		speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.PAGE_AXIS));
		speedPanel.setBackground(Color.darkGray);

		numElementsPanel = new JPanel();
		numElementsPanel.setLayout(new BoxLayout(numElementsPanel, BoxLayout.PAGE_AXIS));
		numElementsPanel.setBackground(Color.darkGray);

		statsPanel = new JPanel();
		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.PAGE_AXIS));
		statsPanel.setBackground(Color.darkGray);

		initUi();
	}

	private void initUi() {
		speedLabel = new JLabel(Integer.toString(MainPanel.speed) + "ms");
		speedLabel.setForeground(Color.white);

		numElementsLabel = new JLabel(Integer.toString(MainPanel.numElements) + " elements");
		numElementsLabel.setForeground(Color.white);

		comparisonsLabel = new JLabel(Integer.toString(MainPanel.comparisons) + " Comparisons");
		comparisonsLabel.setForeground(Color.white);

		writesLabel = new JLabel(Integer.toString(MainPanel.writesToArray) + " Writes to Main Array");
		writesLabel.setForeground(Color.white);

		speedSlider = new JSlider();
		numElementsSlider = new JSlider();

		scrambleButton = new JButton("Scramble");

		stopButton = new JButton("Stop");
		stopButton.setEnabled(false);

		sortButton = new JButton("Sort");

		initNumElementsSlider();
		initSpeedSlider();

		speedPanel.add(speedLabel);
		speedPanel.add(speedSlider);

		numElementsPanel.add(numElementsLabel);
		numElementsPanel.add(numElementsSlider);

		statsPanel.add(comparisonsLabel);
		statsPanel.add(writesLabel);

		initComboBox();
		this.add(statsPanel);
		this.add(speedPanel);
		this.add(numElementsPanel);
		this.add(scrambleButton);
		this.add(sortButton);
		this.add(stopButton);
		this.add(sortSelection);

		initActionListeners();
		initChangeListeners();
		
		timerManager = new TimerManager(panel, this);
	}

	private void initComboBox() {
		String[] sortStrings = { "Bogo Sort", "Selection Sort", "Bubble Sort", "Merge Sort" };

		sortSelection = new JComboBox(sortStrings);
		sortSelection.setSelectedIndex(3);
		sortType = 3;

	}

	private void initNumElementsSlider() {
		numElementsSlider.setMinimum(10);
		numElementsSlider.setMaximum(1000);
		numElementsSlider.setValue(MainPanel.numElements);

	}

	private void initSpeedSlider() {
		speedSlider.setMinimum(1);
		speedSlider.setMaximum(1000);
		speedSlider.setValue(MainPanel.speed);
	}

	private void initActionListeners() {
		scrambleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("scramble");
				scrambleButtonPressed();
			}

		});

		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stopButtonPressed();
			}

		});

		sortButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sortButtonPressed();
			}

		});

		sortSelection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sortSelectionPressed(e);
			}

		});
	}

	private void sortSelectionPressed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		sortType = cb.getSelectedIndex();
	}

	private void scrambleButtonPressed() {
		
		
		numElementsSlider.setEnabled(true);
		panel.getLineList().scramble();
		sortButton.setEnabled(true);
		stopButton.setEnabled(false);
		stopButton.setText("Stop");
		timerManager.setScrambled(true);
		panel.refresh();
		MainPanel.writesToArray = 0;
		MainPanel.comparisons = 0;
		updateLabels();
	}

	private void stopButtonPressed() {
		if (timerManager.isRunning()) {
			timerManager.stop();
			stopButton.setText("Resume");
			scrambleButton.setEnabled(true);

		} else {
			stopButton.setText("Stop");
			scrambleButton.setEnabled(false);
			timerManager.start();
		}

	}

	private void sortButtonPressed() {
		numElementsSlider.setEnabled(false);
		timerManager.start();
		timerManager.setScrambled(false);

		new Thread(new Runnable() {

			@Override
			public void run() {
				

				scrambleButton.setEnabled(false);
				sortButton.setEnabled(false);
				stopButton.setEnabled(true);

				switch (sortType) {
				case 0:
					BogoSort bogoSort = new BogoSort(panel.getLineList(), timerManager);
					bogoSort.sort();
					break;
				case 1:
					SelectionSort selectionSort = new SelectionSort(panel.getLineList(), timerManager);
					selectionSort.sort();
					break;

				case 2:
					BubbleSort bubbleSort = new BubbleSort(panel.getLineList(), timerManager);
					bubbleSort.sort();
					break;

				case 3:
					MergeSort mergeSort = new MergeSort(panel.getLineList(), timerManager);
					mergeSort.sort(panel.getLineList(), 0, panel.getLineList().getSize() - 1);
					break;

				}
				if(!timerManager.getScrambled()) {
					SortManager.sortComplete(panel.getLineList(), timerManager);
				}
				stopButton.setEnabled(false);
				scrambleButton.setEnabled(true);
				sortButton.setEnabled(true);
				numElementsSlider.setEnabled(true);
				speedSlider.setEnabled(true);
			}

		}).start();
	}

	private void initChangeListeners() {
		speedSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("speed changed");
				speedSliderChanged();
			}

		});

		numElementsSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("num elements changed");
				numElementsSliderChanged();
			}
		});
	}

	private void speedSliderChanged() {
		MainPanel.speed = speedSlider.getValue();
		speedLabel.setText(Integer.toString(MainPanel.speed) + " ms");
		
	}

	private void numElementsSliderChanged() {
		MainPanel.numElements = numElementsSlider.getValue();
		numElementsLabel.setText(Integer.toString(MainPanel.numElements) + " elements");
		panel.refresh();
		panel.updateList();
	}
	
	public void updateLabels() {
		comparisonsLabel.setText(MainPanel.comparisons + " Comparisons");
		writesLabel.setText(Integer.toString(MainPanel.writesToArray) + " Writes to Main Array");
	}

}
