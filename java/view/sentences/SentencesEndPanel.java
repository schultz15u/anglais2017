package view.sentences;

import java.awt.GridBagConstraints;
import java.lang.reflect.Parameter;
import java.util.List;

import model.Sentences;
import model.SentencesManager;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.CustomizedLabel;
import view.customized_widgets.CustomizedTextPane;

import javax.swing.*;
import javax.swing.text.Style;

public class SentencesEndPanel extends DefaultGridPanel {

	private Sentences sentences;
	private CustomizedLabel sentenceLabel;
	private CustomizedLabel knownRulesLabel;
	private CustomizedLabel rulesToReviewLabel;
	private CustomizedTextPane knownRulesContentLabel;
	private CustomizedTextPane  rulesToReviewContentLabel;
	private SentencesManager sentencesManager;

	public SentencesEndPanel(Sentences sentences, SentencesManager sentencesManager) {

		super();
		this.sentences = sentences;
		this.sentencesManager = sentencesManager;

		sentenceLabel = new CustomizedLabel("");
		sentenceLabel.setBorder(BorderFactory.createLineBorder(StyleParameters.defaultBackgroundColor, 20));
		knownRulesLabel = new CustomizedLabel("<html><center>Known rules</center></html>");
		rulesToReviewLabel = new CustomizedLabel("<html><center>Rules to review</center></html>");
		knownRulesContentLabel = new CustomizedTextPane();
		knownRulesContentLabel.setFont(StyleParameters.defaultNormalFont);
		rulesToReviewContentLabel = new CustomizedTextPane();
		rulesToReviewContentLabel.setFont(StyleParameters.defaultNormalFont);

		addComponent(sentenceLabel, 0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(knownRulesLabel, 0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(rulesToReviewLabel, 1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(knownRulesContentLabel, 0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(rulesToReviewContentLabel, 1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
	}

	public void updateScore() {

		sentencesManager.addScore(sentences.getPackageName(), sentences.getScore() * 100 / sentences.getSentencesCount());
		sentenceLabel.setText("<html><center>You have finished !<br><br>You score : " + (sentences.getScore() * 100 / sentences.getSentencesCount()) + "%<br>Best score : " + (int)sentencesManager.getBestScore(sentences.getPackageName()) + " %</center></html>");

		List<String> knownRules = sentences.getRulesNames(sentencesManager, true);
		List<String> rulesToReview = sentences.getRulesNames(sentencesManager, false);
		String knownRulesRender = "";
		String rulesToReviewRender = "";

		for (String rule : knownRules)
			//if (!rulesToReview.contains(rule))
				knownRulesRender += rule + "\n";
		for (String rule : rulesToReview)
			rulesToReviewRender += rule + "\n";

		System.out.println();

		knownRulesContentLabel.setText(knownRulesRender);
		rulesToReviewContentLabel.setText(rulesToReviewRender);
	}
}