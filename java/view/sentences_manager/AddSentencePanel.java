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


public class AddSentencePanel extends DefaultGridPanel {
	
	private SentencesManager sentencesManager;
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
	private JButton removeAllSentencesButton;
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
		validationButton = new JButton("Add");
		validationButton.addMouseListener(new ValidationListener());
		removeAllSentencesButton = new JButton("Remove all sentences");
		removeAllSentencesButton.setBackground(Color.red);
		removeAllSentencesButton.setForeground(Color.white);
		removeAllSentencesButton.addMouseListener(new RemoveAllSentencesListener());

		addComponent(sentenceLabel, 0, 0, 1, 1);
		addComponent(sentenceField, 1, 0, 1, 1);
		addComponent(correctAnswerLabel, 0, 1, 1, 1);
		addComponent(correctAnswerField, 1, 1, 1, 1);
		addComponent(wrongAnswersLabel, 0, 2, 1, 1);
		addComponent(wrongAnswersField, 1, 2, 1, 1);
		addComponent(ruleNameLabel, 0, 3, 1, 1);
		addComponent(ruleNameField, 1, 3, 1, 1);
		addComponent(ruleDetailsLabel, 0, 4, 1, 1);
		addComponent(scrollPane, 1, 4, 1, 1);
		addComponent(ruleComboLabel, 0, 5, 1, 1);
		addComponent(ruleCombo, 1, 5, 1, 1);
		addComponent(validationButton, 0, 6, 2, 1);
		addComponent(removeAllSentencesButton, 0, 7, 2, 1);
		addComponent(messageLabel, 0, 8, 2, 1);
	}

	public void setPackageName(String packageName) {

		this.packageName = packageName;
	}

	public void update() {

		remove(ruleCombo);
		ruleCombo = new JComboBox(sentencesManager.getRulesNames(packageName).toArray());
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
	
	public class RemoveAllSentencesListener extends DefaultMouseListener
	{
		@Override
		public void mouseClicked(MouseEvent event) {
			
			if (!sentencesManager.removeAllSentences())
				messageLabel.setText("Error with database");
			else
				messageLabel.setText("All sentence have been removed");
		}
	}
}
