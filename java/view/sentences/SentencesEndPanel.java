package view.sentences;

import java.awt.GridBagConstraints;

import model.Sentences;
import model.SentencesManager;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.CustomizedLabel;

public class SentencesEndPanel extends DefaultGridPanel {

	private Sentences sentences;
	private CustomizedLabel sentenceLabel;
	private SentencesManager sentencesManager;

	public SentencesEndPanel(Sentences sentences, SentencesManager sentencesManager) {

		super();
		this.sentences = sentences;
		this.sentencesManager = sentencesManager;
		setBackground(StyleParameters.defaultBackgroundColor);

		sentenceLabel = new CustomizedLabel("Vous avez terminé !");

		addComponent(sentenceLabel, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
	}

	public void updateScore() {

		sentencesManager.addScore(sentences.getPackageName(), sentences.getScore() * 100 / sentences.getSentencesCount());
		sentenceLabel.setText("<html>Vous avez terminé !<br>Votre score : " + (sentences.getScore() * 100 / sentences.getSentencesCount()) + "%<br>Score maximum : " + (int)sentencesManager.getBestScore(sentences.getPackageName()) + " %</html>");
	}
}