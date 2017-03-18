package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import model.SentencesManager;
import view.sentences.SentencesPanel;
import view.sentences_manager.SentencesManagerPanel;

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
		mcqModePanel = new SentencesPanel(sentencesManager, true, mainMenuPanel);
		mistakesModePanel = new SentencesPanel(sentencesManager, false, mainMenuPanel);
		currentPanel = sentenceManagerPanel;

		addComponent(mainMenuPanel, 0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(sentenceManagerPanel, 1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
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
