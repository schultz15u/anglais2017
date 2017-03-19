package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import model.SentencesManager;
import view.rules.RulesPanel;
import view.sentences.SentencesPanel;
import view.sentences_manager.SentencesManagerPanel;

public class MainPanel extends DefaultGridPanel {

	MainMenuPanel mainMenuPanel;
	HomePanel homePanel;
	SentencesManagerPanel sentenceManagerPanel;
	SentencesPanel mcqModePanel;
	RulesPanel rulesPanel;
	SentencesPanel mistakesModePanel;
	JPanel currentPanel;

	public MainPanel(SentencesManager sentencesManager) {

		super();
		setLayout(new GridBagLayout());

		homePanel = new HomePanel();
		mainMenuPanel = new MainMenuPanel(this);
		sentenceManagerPanel = new SentencesManagerPanel(sentencesManager);
		mcqModePanel = new SentencesPanel(sentencesManager, true, mainMenuPanel);
		mistakesModePanel = new SentencesPanel(sentencesManager, false, mainMenuPanel);
		rulesPanel = new RulesPanel(sentencesManager);
		currentPanel = homePanel;

		addComponent(mainMenuPanel, 0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(homePanel, 1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
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

	public void showReadRulesPanel() {

		if (currentPanel != mistakesModePanel) {

			rulesPanel.reset();
			changePanel(rulesPanel);
		}
	}

	public void showMistakesModePanel() {

		if (currentPanel != mistakesModePanel) {

			mistakesModePanel.reset();
			changePanel(mistakesModePanel);
		}
	}

	public void showHomePanel() {

		if (currentPanel != homePanel) {

			changePanel(homePanel);
		}
	}
}
