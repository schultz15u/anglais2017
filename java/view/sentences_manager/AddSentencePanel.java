package view.sentences_manager;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

import controller.DefaultMouseListener;
import model.SentencesManager;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.*;


public class AddSentencePanel extends DefaultGridPanel {
	
	private SentencesManager sentencesManager;
	private CustomizedLabel sentenceLabel;
	private CustomizedTextField sentenceField;
	private CustomizedLabel correctAnswerLabel;
	private CustomizedTextField correctAnswerField;
	private CustomizedLabel wrongAnswersLabel;
	private CustomizedTextField wrongAnswersField;
	private CustomizedLabel ruleNameLabel;
	private CustomizedTextField ruleNameField;
	private CustomizedLabel ruleDetailsLabel;
	private CustomizedTextArea ruleDetailsField;
	private CustomizedLabel ruleComboLabel;
	private CustomizedComboBox ruleCombo;
	private CustomizedButton validationButton;
	private JLabel messageLabel;
	private String packageName;
	private String ruleName;
	
	public AddSentencePanel(SentencesManager sentencesManager, JLabel messageLabel) {

		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		this.messageLabel = messageLabel;
		packageName = "";
		ruleName = "-";
		setBackground(StyleParameters.defaultBackgroundColor);

		sentenceLabel = new CustomizedLabel("Sentence (add \"@\" where the wrong word is placed) : ");
		sentenceField = new CustomizedTextField("", 30);
		correctAnswerLabel = new CustomizedLabel("Correct answer : ");
		correctAnswerField = new CustomizedTextField("", 30);
		wrongAnswersLabel = new CustomizedLabel("Wrong answers (separated with commas) : ");
		wrongAnswersField = new CustomizedTextField("", 30);
		ruleNameLabel = new CustomizedLabel("Rule name : ");
		ruleNameField = new CustomizedTextField("", 30);
		ruleDetailsLabel = new CustomizedLabel("Rule details : ");
		ruleDetailsField = new CustomizedTextArea("", 5, 30);
		ruleComboLabel = new CustomizedLabel("Existing rule : ");
		ruleCombo = new CustomizedComboBox(sentencesManager.getRulesNames(packageName).toArray());
		ruleCombo.addActionListener(new RulesNamesListener());
		validationButton = new CustomizedButton("Add");
		validationButton.addMouseListener(new ValidationListener());

		addComponent(sentenceLabel, 0, 0, 1, 1);
		addComponent(sentenceField, 1, 0, 1, 1);
		addComponent(correctAnswerLabel, 0, 1, 1, 1);
		addComponent(correctAnswerField, 1, 1, 1, 1);
		addComponent(wrongAnswersLabel, 0, 2, 1, 1);
		addComponent(wrongAnswersField, 1, 2, 1, 1);
		addComponent(ruleNameLabel, 0, 3, 1, 1);
		addComponent(ruleNameField, 1, 3, 1, 1);
		addComponent(ruleDetailsLabel, 0, 4, 1, 1);
		addComponent(ruleDetailsField.getScrollPane(), 1, 4, 1, 1);
		addComponent(ruleComboLabel, 0, 5, 1, 1);
		addComponent(ruleCombo, 1, 5, 1, 1);
		addComponent(validationButton, 0, 6, 2, 1);
	}

	public void setPackageName(String packageName) {

		this.packageName = packageName;
	}

	public void update() {

		remove(ruleCombo);
		ruleCombo = new CustomizedComboBox(sentencesManager.getRulesNames(packageName).toArray());
		ruleCombo.addActionListener(new RulesNamesListener());

		addComponent(ruleCombo, 1, 5, 1, 1);
		validate();
		repaint();
	}

	public class RulesNamesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox) e.getSource();
			ruleName = cb.getSelectedItem().toString();
		}
	}
	
	public class ValidationListener extends DefaultMouseListener
	{
		@Override
		public void mouseClicked(MouseEvent event) {

			System.out.println(ruleCombo.getSelectedItem());
			if (sentenceField.getText().isEmpty() || correctAnswerField.getText().isEmpty() || wrongAnswersField.getText().isEmpty()
					|| ((ruleNameField.getText().isEmpty() || ruleDetailsField.getText().isEmpty()) && ruleName.equals("-"))) {
				messageLabel.setText("One field is missing.");
				messageLabel.setForeground(Color.red);
			}
			else if (sentenceField.getText().length() - sentenceField.getText().replace("@", "").length() == 0) {    // zero "@" in the sentence
				messageLabel.setText("The sentence must include one \"@\".");
				messageLabel.setForeground(Color.red);
			}
			else if (sentenceField.getText().length() - sentenceField.getText().replace("@", "").length() > 1) {    // more than one "@" in the sentence
				messageLabel.setText("The sentence must include only one \"@\".");
				messageLabel.setForeground(Color.red);
			}
			else if (!sentencesManager.addSentence(sentenceField.getText(), correctAnswerField.getText(), wrongAnswersField.getText(),
					!ruleName.equals("-") ? ruleName : ruleNameField.getText(), ruleDetailsField.getText(), packageName)) {
				messageLabel.setText("Error with database.");
				messageLabel.setForeground(Color.red);
			}
			else {
				messageLabel.setText("Sentence has been added.");
				messageLabel.setForeground(Color.green);
				sentenceField.setText("");
				correctAnswerField.setText("");
				wrongAnswersField.setText("");
				ruleNameField.setText("");
				ruleDetailsField.setText("");

				update();
			}
		}
	}
}
