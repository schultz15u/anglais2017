package view.sentences;

import java.awt.GridBagConstraints;

import model.Sentences;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.CustomizedLabel;

public class SentencesEndPanel extends DefaultGridPanel {

	private Sentences sentences;
	private CustomizedLabel sentenceLabel;

	public SentencesEndPanel(Sentences sentences) {

		super();
		this.sentences = sentences;
		setBackground(StyleParameters.defaultBackgroundColor);

		sentenceLabel = new CustomizedLabel("Vous avez terminé !");

		addComponent(sentenceLabel, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
	}

	public void updateScore() {
		sentenceLabel.setText("<html>Vous avez terminé !<br>Votre score : " + sentences.getScore() + "</html>");
	}
}