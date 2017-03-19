package view.rules;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import model.Sentences;
import model.SentencesManager;
import view.DefaultGridPanel;
import view.MainMenuPanel;

public class RulesPanel extends DefaultGridPanel {

	private SentencesManager sentencesManager;
	private Sentences sentences;
	private MainMenuPanel mainMenuPanel;
	private RulesHomePanel rulesHomePanel;
	private ReadingPanel readingPanel;
	private ReadingEndPanel readingEndPanel;
	private JPanel currentPanel;

	public RulesPanel(SentencesManager sentencesManager) {

		super();
		this.sentencesManager = sentencesManager;
		this.sentences = new Sentences();
		this.mainMenuPanel = mainMenuPanel;

		rulesHomePanel = new RulesHomePanel();
		readingPanel = new ReadingPanel();
		readingEndPanel = new ReadingEndPanel();
		currentPanel = rulesHomePanel;

		addComponent(rulesHomePanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
	}

	private void changePanel(JPanel newPanel) {
		remove(currentPanel);
		currentPanel = newPanel;
		addComponent(currentPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		revalidate();
		repaint();
	}

	public void reset() {

		// rulesHomePanel.update();
		changePanel(rulesHomePanel);
	}

	public void goToQuestionsPanel(String packageName) {

		sentences.initialize(sentencesManager, packageName);
		// readingEndPanel.reset();
		mainMenuPanel.reduce();
		changePanel(readingEndPanel);
	}

	public void goToEndPanel() {

		// readingEndPanel.updateScore();
		mainMenuPanel.increase();
		changePanel(readingEndPanel);
	}
}