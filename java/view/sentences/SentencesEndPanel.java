package view.sentences;

import model.Sentences;
import model.SentencesManager;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.CustomizedButton;
import view.customized_widgets.CustomizedComboBox;
import view.customized_widgets.CustomizedLabel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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