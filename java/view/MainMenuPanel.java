package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.customized_widgets.CustomizedButton;

public class MainMenuPanel extends DefaultGridPanel {

	CustomizedButton logoButton;
	CustomizedButton mcqModeButton;
	CustomizedButton mistakesModeButton;
	CustomizedButton readRulesButton;
	CustomizedButton sentencesManagerButton;
	CustomizedButton exitButton;
	JPanel emptyPanel;
	MainPanel mainPanel;

	Timer widthModificationTimer;
	private long startTime;
	double width;
	boolean isOpened;

	private static final int DELAY = 1;
	private static final int ANIMATION_TIME = 500;

	public MainMenuPanel(MainPanel mainPanel) {

		super();
		setBackground(StyleParameters.mainMenuNormalButtonColor);
		this.mainPanel = mainPanel;
		width = 200;
		isOpened = true;
		setMaximumSize(new Dimension((int)width, (int) getPreferredSize().getHeight()));
		setMinimumSize(new Dimension((int)width, (int) getPreferredSize().getHeight()));
		setPreferredSize(new Dimension((int)width, (int) getPreferredSize().getHeight()));
		setSize(new Dimension((int)width, (int) getPreferredSize().getHeight()));

		logoButton = new CustomizedButton(this.getClass().getResource("/logoAnglais.png"));
		logoButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		logoButton.addActionListener(new HomeListener());
		mcqModeButton = new CustomizedButton("MCQ mode");
		mcqModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		mcqModeButton.addActionListener(new McqModeListener());
		mistakesModeButton = new CustomizedButton("Mistakes mode");
		mistakesModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		mistakesModeButton.addActionListener(new MistakesModeListener());
		sentencesManagerButton = new CustomizedButton("Sentence manager");
		sentencesManagerButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		sentencesManagerButton.addActionListener(new SentenceManagerListener());
		readRulesButton = new CustomizedButton("Read rules");
		readRulesButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		readRulesButton.addActionListener(new ReadRulesListener());
		exitButton = new CustomizedButton("Exit");
		exitButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		exitButton.addActionListener(new ExitListener());
		emptyPanel = new JPanel();
		emptyPanel.setBackground(getBackground());

		addComponent(logoButton, 0, 0, 1, 1, 1, 0.1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
		addComponent(mcqModeButton, 0, 1, 1, 1, 1, 0.1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
		addComponent(mistakesModeButton, 0, 2, 1, 1, 1, 0.1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
		//addComponent(readRulesButton, 0, 3, 1, 1, 1, 0.1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
		addComponent(sentencesManagerButton, 0, 4, 1, 1, 1, 0.1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
		addComponent(exitButton, 0, 5, 1, 1, 1, 0.1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
		addComponent(emptyPanel, 0, 10, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);

		logoButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
		mcqModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		mistakesModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		sentencesManagerButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		readRulesButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);

		startTime = 0;
		widthModificationTimer = new Timer(DELAY, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (isOpened) {

					width = 200 * (double)(System.nanoTime() - startTime) / ANIMATION_TIME / 1000000;
				}
				else {

					width = 200 - 200 * (double)(System.nanoTime() - startTime) / ANIMATION_TIME / 1000000;
				}

				setMaximumSize(new Dimension((int)width, (int) getPreferredSize().getHeight()));
				setMinimumSize(new Dimension((int)width, (int) getPreferredSize().getHeight()));
				setPreferredSize(new Dimension((int)width, (int) getPreferredSize().getHeight()));
				setSize(new Dimension((int)width, (int) getPreferredSize().getHeight()));
				revalidate();
				repaint();

				if (width <= 0 || width >= 200)
					stopTimer();
			}
		});
	}

	private void startTimer() {
		startTime = System.nanoTime();
		widthModificationTimer.start();
	}

	private void stopTimer() {
		widthModificationTimer.stop();
	}

	public void reduce() {
		isOpened = false;
		startTimer();
	}

	public void increase() {
		isOpened = true;
		startTimer();
	}

	@Override
	public void setMaximumSize(Dimension dimension) {
		super.setMaximumSize(new Dimension((int)width, (int) dimension.getHeight()));
	}

	@Override
	public void setMinimumSize(Dimension dimension) {
		super.setMinimumSize(new Dimension((int)width, (int) dimension.getHeight()));
	}

	@Override
	public void setPreferredSize(Dimension dimension) {
		super.setPreferredSize(new Dimension((int)width, (int) dimension.getHeight()));
	}

	@Override
	public void setSize(Dimension dimension) {
		super.setSize(new Dimension((int)width, (int) dimension.getHeight()));
	}

	private class SentenceManagerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainPanel.showSentencesManagerPanel();
			logoButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			mcqModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			mistakesModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			sentencesManagerButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			readRulesButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		}
	}

	private class McqModeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainPanel.showMcqModePanel();
			logoButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			mcqModeButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			mistakesModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			sentencesManagerButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			readRulesButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);

		}
	}

	private class MistakesModeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainPanel.showMistakesModePanel();
			logoButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			mcqModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			mistakesModeButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			sentencesManagerButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			readRulesButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		}
	}

	private class ReadRulesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainPanel.showReadRulesPanel();
			logoButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			mcqModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			mistakesModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			sentencesManagerButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			readRulesButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
		}
	}

	private class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.exit(0);
		}
	}

	private class HomeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			mainPanel.showHomePanel();
			logoButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			mcqModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			mistakesModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			sentencesManagerButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			readRulesButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		}
	}
}
