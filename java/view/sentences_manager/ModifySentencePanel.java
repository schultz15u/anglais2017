package view.sentences_manager;

import controller.DefaultMouseListener;
import model.SentencesManager;
import model.database.entries.RuleEntry;
import model.database.entries.SentenceEntry;
import view.DefaultGridPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;


public class ModifySentencePanel extends DefaultGridPanel {

	private SentencesManager sentencesManager;
	private JLabel sentenceChoiceLabel;
	private JComboBox sentenceChoiceCombo;
	private JLabel sentenceLabel;
	private JTextField sentenceField;
	private JLabel correctAnswerLabel;
	private JTextField correctAnswerField;
	private JLabel wrongAnswersLabel;
	private JTextField wrongAnswersField;
	private JLabel ruleNameLabel;
	private JTextField ruleNameField;
	private JLabel ruleDetailsLabel;
	private JTextArea ruleDetailsField;
	private JLabel ruleComboLabel;
	private JComboBox ruleCombo;
	private JButton validationButton;
	private JLabel messageLabel;
	private String packageName;
	private String ruleName;

	public ModifySentencePanel(SentencesManager sentencesManager, JLabel messageLabel) {
		
		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		this.messageLabel = messageLabel;
		packageName = "";
		ruleName = "-";

		sentenceChoiceLabel = new JLabel("Sentence to modify : ");
		sentenceChoiceCombo = new JComboBox(sentencesManager.getSentenceNames(packageName).toArray());
		sentenceChoiceCombo.addActionListener(new SentenceChoiceListener());
		sentenceLabel = new JLabel("Sentence (add \"@\" where the wrong word is placed) : ");
		sentenceField = new JTextField("", 30);
		correctAnswerLabel = new JLabel("Correct answer : ");
		correctAnswerField = new JTextField("", 30);
		wrongAnswersLabel = new JLabel("Wrong answers (separated with commas) : ");
		wrongAnswersField = new JTextField("", 30);
		ruleNameLabel = new JLabel("Rule name : ");
		ruleNameField = new JTextField("", 30);
		ruleDetailsLabel = new JLabel("Rule details : ");
		ruleDetailsField = new JTextArea("", 5, 30);
		JScrollPane scrollPane = new JScrollPane( ruleDetailsField );
		ruleComboLabel = new JLabel("Existing rule : ");
		ruleCombo = new JComboBox(sentencesManager.getRulesNames(packageName).toArray());
		ruleCombo.addActionListener(new RulesNamesListener());
		validationButton = new JButton("Update");
		validationButton.addMouseListener(new ValidationListener());

		addComponent(sentenceChoiceLabel, 0, 0, 1, 1);
		addComponent(sentenceChoiceCombo, 1, 0, 1, 1);
		addComponent(sentenceLabel, 0, 1, 1, 1);
		addComponent(sentenceField, 1, 1, 1, 1);
		addComponent(correctAnswerLabel, 0, 2, 1, 1);
		addComponent(correctAnswerField, 1, 2, 1, 1);
		addComponent(wrongAnswersLabel, 0, 3, 1, 1);
		addComponent(wrongAnswersField, 1, 3, 1, 1);
		addComponent(ruleNameLabel, 0, 4, 1, 1);
		addComponent(ruleNameField, 1, 4, 1, 1);
		addComponent(ruleDetailsLabel, 0, 5, 1, 1);
		addComponent(scrollPane, 1, 5, 1, 1);
		addComponent(ruleComboLabel, 0, 6, 1, 1);
		addComponent(ruleCombo, 1, 6, 1, 1);
		addComponent(validationButton, 0, 7, 2, 1);
	}

	public void setPackageName(String packageName) {

		System.out.println(packageName);
		this.packageName = packageName;
	}

	public void update() {



		remove(sentenceChoiceCombo);
		sentenceChoiceCombo = new JComboBox(sentencesManager.getSentenceNames(packageName).toArray());
		sentenceChoiceCombo.addActionListener(new SentenceChoiceListener());

		remove(ruleCombo);
		ruleCombo = new JComboBox(sentencesManager.getRulesNames(packageName).toArray());
		ruleCombo.addActionListener(new RulesNamesListener());

		addComponent(sentenceChoiceCombo, 1, 0, 1, 1);
		addComponent(ruleCombo, 1, 6, 1, 1);
		validate();
		repaint();

		if (sentenceChoiceCombo.getItemAt(0) != null)
		updateFields(sentenceChoiceCombo.getItemAt(0).toString());
	}

	private void updateFields(String sentenceName) {

		SentenceEntry sentence = sentencesManager.getSentence(packageName, sentenceName);
		sentenceField.setText(sentence.getDetail().replace('¤', '@'));
		correctAnswerField.setText(sentence.getPropOk());
		wrongAnswersField.setText(sentence.getPropNo());
		ruleNameField.setText("");
		ruleDetailsField.setText("");

		RuleEntry rule = sentencesManager.getRule(sentence.getIdRule());

		for (int i = 0; i < ruleCombo.getItemCount(); ++i) {
			if (rule.getName().equals(ruleCombo.getItemAt(i).toString())) {
				ruleCombo.setSelectedItem(ruleCombo.getItemAt(i));
				break;
			}
		}
	}

	public class SentenceChoiceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox) e.getSource();
			updateFields(cb.getSelectedItem().toString());
		}
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
			else if (!sentencesManager.setSentence(sentenceField.getText(), correctAnswerField.getText(), wrongAnswersField.getText(),
					!ruleName.equals("-") ? ruleName : ruleNameField.getText(), ruleDetailsField.getText(), packageName)) {
				messageLabel.setText("Error with database.");
				messageLabel.setForeground(Color.red);
			}
			else {
				messageLabel.setText("Sentence has been updated.");
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
