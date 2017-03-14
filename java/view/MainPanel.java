package view;

import model.Sentences;
import model.SentencesManager;
import view.customized_widgets.CustomizedButton;
import view.sentences_manager.CreatePackagePanel;
import view.sentences_manager.ModifyPackagePanel;
import view.sentences_manager.RemovePackagePanel;
import view.sentences_manager.SentencesManagerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainPanel extends DefaultGridPanel {

	MainMenuPanel mainMenuPanel;
	SentencesManagerPanel sentenceManagerPanel;
	SentencesPanel sentencePanel;
	JPanel currentPanel;

	public MainPanel(Sentences sentences, SentencesManager sentencesManager) {

		super();
		setLayout(new GridBagLayout());
		setBackground(new Color(30, 30, 30));

		mainMenuPanel = new MainMenuPanel(this);
		sentenceManagerPanel = new SentencesManagerPanel(sentencesManager);
		sentencePanel = new SentencesPanel(sentences);
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

		changePanel(sentencePanel);
	}
}
