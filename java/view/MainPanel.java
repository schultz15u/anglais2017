package view;

import model.Sentences;
import model.SentencesManager;
import view.sentences.SentencesPanel;
import view.sentences_manager.SentencesManagerPanel;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends DefaultGridPanel {

	MainMenuPanel mainMenuPanel;
	SentencesManagerPanel sentenceManagerPanel;
	Sentences sentences;
	SentencesPanel mcqModePanel;
	SentencesPanel mistakesModePanel;
	JPanel currentPanel;

	public MainPanel(Sentences sentences, SentencesManager sentencesManager) {

		super();
		setLayout(new GridBagLayout());
		setBackground(StyleParameters.defaultBackgroundColor);
		this.sentences = sentences;

		mainMenuPanel = new MainMenuPanel(this);
		sentenceManagerPanel = new SentencesManagerPanel(sentencesManager);
		mcqModePanel = new SentencesPanel(sentences, true);
		mistakesModePanel = new SentencesPanel(sentences, false);
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

			sentences.initialize();
			mcqModePanel.reset();
			changePanel(mcqModePanel);
		}
	}

	public void showMistakesModePanel() {

		if (currentPanel != mistakesModePanel) {

			sentences.initialize();
			mistakesModePanel.reset();
			changePanel(mistakesModePanel);
		}
	}
}
