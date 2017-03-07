package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.DefaultMouseListener;
import model.SentencesManager;


public class SentencesManagerPanel extends JPanel {
	
	private SentencesManager sentencesManager;
	private GridBagConstraints gbc;
	private JLabel sentenceLabel;
	private JTextField sentenceField;
	private JLabel correctAnswerLabel;
	private JTextField correctAnswerField;
	private JLabel wrongAnswersLabel;
	private JTextField wrongAnswersField;
	private JLabel ruleLabel;
	private JTextField ruleField;
	private JButton validationButton;
	private JButton removeAllSentencesButton;
	private JLabel messageLabel;
	
	public SentencesManagerPanel(SentencesManager sentencesManager) {
		
		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		sentenceLabel = new JLabel("Sentence (add \"@\" where the wrong word is placed) : ");
		sentenceField = new JTextField("", 30);
		correctAnswerLabel = new JLabel("Correct answer : ");
		correctAnswerField = new JTextField("", 30);
		wrongAnswersLabel = new JLabel("Wrong answers (separated with commas) : ");
		wrongAnswersField = new JTextField("", 30);
		ruleLabel = new JLabel("Rule : ");
		ruleField = new JTextField("", 30);
		validationButton = new JButton("Ajouter");
		validationButton.addMouseListener(new ValidationListener());
		removeAllSentencesButton = new JButton("Remove all sentences");
		removeAllSentencesButton.setBackground(Color.red);
		removeAllSentencesButton.setForeground(Color.white);
		removeAllSentencesButton.addMouseListener(new RemoveAllSentencesListener());
		messageLabel = new JLabel("");
		
		addComponent(sentenceLabel, 0, 0, 1, 1);
		addComponent(sentenceField, 1, 0, 1, 1);
		addComponent(correctAnswerLabel, 0, 1, 1, 1);
		addComponent(correctAnswerField, 1, 1, 1, 1);
		addComponent(wrongAnswersLabel, 0, 2, 1, 1);
		addComponent(wrongAnswersField, 1, 2, 1, 1);
		addComponent(ruleLabel, 0, 3, 1, 1);
		addComponent(ruleField, 1, 3, 1, 1);
		addComponent(validationButton, 0, 4, 2, 1);
		addComponent(removeAllSentencesButton, 0, 5, 2, 1);
		addComponent(messageLabel, 0, 6, 2, 1);
	}
	
	private void addComponent(JComponent component, int gridX, int gridY, int sizeX, int sizeY) {
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		gbc.gridwidth = sizeX;
		gbc.gridheight = sizeY;
		add(component, gbc);
	}
	
	public class ValidationListener extends DefaultMouseListener
	{
		@Override
		public void mouseClicked(MouseEvent event) {
			
			if (sentenceField.getText().isEmpty() || correctAnswerField.getText().isEmpty() || wrongAnswersField.getText().isEmpty())
				messageLabel.setText("One field is missing");
			else if (sentenceField.getText().length() - sentenceField.getText().replace("@", "").length() == 0)	// zero "@" in the sentence
				messageLabel.setText("The sentence must include one \"@\"");
			else if (sentenceField.getText().length() - sentenceField.getText().replace("@", "").length() > 1)	// more than one "@" in the sentence
				messageLabel.setText("The sentence must include only one \"@\"");
			else if (!sentencesManager.addSentence(sentenceField.getText(), correctAnswerField.getText(), wrongAnswersField.getText(), ruleField.getText()))
				messageLabel.setText("Error with database");
			else {
				messageLabel.setText("Sentence has been added");
				sentenceField.setText("");
				correctAnswerField.setText("");
				wrongAnswersField.setText("");
				ruleField.setText("");
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
