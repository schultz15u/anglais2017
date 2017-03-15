package view;

import model.Sentences;
import model.SentencesManager;
import view.sentences.QuestionsPanel;
import view.sentences.SentencesPanel;
import view.sentences_manager.SentencesManagerPanel;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends DefaultGridPanel {

	MainMenuPanel mainMenuPanel;
	SentencesManagerPanel sentenceManagerPanel;
	SentencesPanel mcqModePanel;
	SentencesPanel mistakesModePanel;
	JPanel currentPanel;

	public MainPanel(SentencesManager sentencesManager) {

		super();
		setLayout(new GridBagLayout());
		setBackground(StyleParameters.defaultBackgroundColor);

		mainMenuPanel = new MainMenuPanel(this);
		sentenceManagerPanel = new SentencesManagerPanel(sentencesManager);
		mcqModePanel = new SentencesPanel(sentencesManager, true);
		mistakesModePanel = new SentencesPanel(sentencesManager, false);
		currentPanel = sentenceManagerPanel;

		addComponent(mainMenuPanel, 0, 0, 1,  1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(sentenceManagerPanel, 1, 0, 1,  1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	}

	private void changePanel(JPanel newPanel) {
		remove(currentPanel);
		currentPanel = newPanel;
		addComponent(currentPanel, 1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		revalidate();
		repaint();
	}

	public void showSentencesManagerPanel() {

		changePanel(sentenceManagerPanel);
	}

	public void showMcqModePanel() {

		if (currentPanel != mcqModePanel) {

			mcqModePanel.reset();
			changePanel(mcqModePanel);
		}
	}

	public void showMistakesModePanel() {

		if (currentPanel != mistakesModePanel) {

			mistakesModePanel.reset();
			changePanel(mistakesModePanel);
		}
	}
}
