package view;

import model.SentencesManager;
import view.customized_widgets.CustomizedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenuPanel extends DefaultGridPanel {

	CustomizedButton sentencesManagerButton;
	CustomizedButton mcqModeButton;
	CustomizedButton mistakesModeButton;
	JPanel emptyPanel;
	MainPanel mainPanel;

	public MainMenuPanel(MainPanel mainPanel) {

		super();
		setBackground(StyleParameters.mainMenuNormalButtonColor);
		this.mainPanel = mainPanel;

		sentencesManagerButton = new CustomizedButton("Sentence manager");
		sentencesManagerButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		sentencesManagerButton.addActionListener(new SentenceManagerListener());
		mcqModeButton = new CustomizedButton("MCQ mode");
		mcqModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		mcqModeButton.addActionListener(new McqModeListener());
		mistakesModeButton = new CustomizedButton("Mistakes mode");
		mistakesModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		mistakesModeButton.addActionListener(new MistakesModeListener());
		emptyPanel = new JPanel();
		emptyPanel.setBackground(getBackground());

		addComponent(sentencesManagerButton, 0, 0, 1, 1, 1, 0.1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
		addComponent(mcqModeButton, 0, 1, 1, 1, 1, 0.1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
		addComponent(mistakesModeButton, 0, 2, 1, 1, 1, 0.1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
		addComponent(emptyPanel, 0, 10, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);

		sentencesManagerButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
		mcqModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		mistakesModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
	}

	@Override
	public void setMaximumSize(Dimension dimension) {
		super.setMaximumSize(new Dimension(150, (int)dimension.getHeight()));
	}

	@Override
	public void setMinimumSize(Dimension dimension) {
		super.setMinimumSize(new Dimension(150, (int)dimension.getHeight()));
	}

	@Override
	public void setPreferredSize(Dimension dimension) {
		super.setPreferredSize(new Dimension(150, (int)dimension.getHeight()));
	}

	@Override
	public void setSize(Dimension dimension) {
		super.setSize(new Dimension(150, (int)dimension.getHeight()));
	}

	private class SentenceManagerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainPanel.showSentencesManagerPanel();
			sentencesManagerButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			mcqModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			mistakesModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
		}
	}

	private class McqModeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainPanel.showMcqModePanel();
			sentencesManagerButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			mcqModeButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			mistakesModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);

		}
	}

	private class MistakesModeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainPanel.showMistakesModePanel();
			sentencesManagerButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			mcqModeButton.setNormalColor(StyleParameters.mainMenuNormalButtonColor);
			mistakesModeButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
		}
	}
}
