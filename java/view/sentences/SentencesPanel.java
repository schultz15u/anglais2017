package view.sentences;

import model.Sentences;
import model.SentencesManager;
import view.DefaultGridPanel;
import view.MainMenuPanel;
import view.StyleParameters;

import javax.swing.*;
import java.awt.*;


public class SentencesPanel extends DefaultGridPanel {

	private SentencesManager sentencesManager;
	private Sentences sentences;
	private MainMenuPanel mainMenuPanel;
	private SentencesHomePanel sentencesHomePanel;
	private QuestionsPanel questionsPanel;
	private SentencesEndPanel sentencesEndPanel;
	private JPanel currentPanel;

	public SentencesPanel(SentencesManager sentencesManager, boolean isMcq, MainMenuPanel mainMenuPanel) {

		super();
		this.sentencesManager = sentencesManager;
		this.sentences = new Sentences();
		this.mainMenuPanel = mainMenuPanel;
		setBackground(StyleParameters.defaultBackgroundColor);

		sentencesHomePanel = new SentencesHomePanel(sentencesManager, isMcq);
		questionsPanel = new QuestionsPanel(sentences, isMcq);
		sentencesEndPanel = new SentencesEndPanel(sentences, sentencesManager);
		currentPanel = sentencesHomePanel;

		addComponent(sentencesHomePanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
	}

	private void changePanel(JPanel newPanel) {
		remove(currentPanel);
		currentPanel = newPanel;
		addComponent(currentPanel, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		revalidate();
		repaint();
	}

	public void reset() {

		sentencesHomePanel.update();
		changePanel(sentencesHomePanel);
	}

	public void goToQuestionsPanel(String packageName) {

		mainMenuPanel.reduce();
		sentences.initialize(sentencesManager, packageName);
		questionsPanel.reset();
		changePanel(questionsPanel);
	}

	public void goToEndPanel() {

		mainMenuPanel.increase();
		sentencesEndPanel.updateScore();
		changePanel(sentencesEndPanel);
	}
}