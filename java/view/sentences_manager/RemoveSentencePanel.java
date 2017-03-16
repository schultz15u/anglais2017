package view.sentences_manager;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import model.SentencesManager;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.CustomizedButton;
import view.customized_widgets.CustomizedComboBox;
import view.customized_widgets.CustomizedLabel;

public class RemoveSentencePanel extends DefaultGridPanel {

	private SentencesManager sentencesManager;
	private CustomizedLabel sentenceChoiceLabel;
	private CustomizedComboBox sentenceChoiceCombo;
	private CustomizedButton validationButton;
	private JLabel messageLabel;
	private String packageName;
	private String sentence;

	public RemoveSentencePanel(SentencesManager sentencesManager, JLabel messageLabel) {

		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		this.messageLabel = messageLabel;
		packageName = "";
		sentence = "";
		setBackground(StyleParameters.defaultBackgroundColor);

		sentenceChoiceLabel = new CustomizedLabel("Sentence to remove : ");
		sentenceChoiceCombo = new CustomizedComboBox(sentencesManager.getSentenceNames(packageName));
		sentenceChoiceCombo.addActionListener(new SentenceChoiceListener());
		validationButton = new CustomizedButton("Remove");
		validationButton.addActionListener(new ValidationListener());

		addComponent(sentenceChoiceLabel, 0, 0, 1, 1);
		addComponent(sentenceChoiceCombo, 1, 0, 1, 1);
		addComponent(validationButton, 0, 1, 2, 1);
	}

	public void setPackageName(String packageName) {

		System.out.println(packageName);
		this.packageName = packageName;
	}

	public void update() {

		remove(sentenceChoiceCombo);
		sentenceChoiceCombo = new CustomizedComboBox(sentencesManager.getSentenceNames(packageName));
		sentenceChoiceCombo.addActionListener(new SentenceChoiceListener());

		addComponent(sentenceChoiceCombo, 1, 0, 1, 1);
		revalidate();
		repaint();
	}

	public class SentenceChoiceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CustomizedComboBox cb = (CustomizedComboBox) e.getSource();
			sentence = cb.getSelectedItem().toString();
		}
	}

	public class ValidationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (!sentencesManager.removeSentence(sentence, packageName)) {
				messageLabel.setText("Error with database.");
				messageLabel.setForeground(Color.red);
			} else {
				messageLabel.setText("Sentence has been removed.");
				messageLabel.setForeground(Color.green);

				update();
			}
		}
	}
}
