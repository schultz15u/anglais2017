package view.sentences;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.Sentences;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.*;


public class QuestionsPanel extends DefaultGridPanel {

	private CustomizedLabel sentenceLabel;
	private CustomizedLabel informationLabel;
	private CustomizedScrollPane informationScrollPane;
	private CustomizedButton nextButton;
	private Sentences sentences;
	private int sentenceIsCorrect; // 0 : no, 1 : yes, 2 : question have not been answered
	private boolean isMcq = true;
	private ButtonGroup radioGroup;
	private List<CustomizedRadioButton> choicesRadio;
	private CustomizedPanel panel;
	private CustomizedButton exitButton;


	public QuestionsPanel(Sentences sentences, boolean isMcq) {
		super();
		this.sentences = sentences;
		this.isMcq = isMcq;

		sentenceLabel = new CustomizedLabel(sentences.getWrongSentence(), (int)Component.CENTER_ALIGNMENT);
		sentenceLabel.setFont(StyleParameters.defaultSentenceFont);
		sentenceLabel.addMouseListener(new SentenceLabelListener());
		informationLabel = new CustomizedLabel("", (int)Component.CENTER_ALIGNMENT);
		informationLabel.setBorder(BorderFactory.createLineBorder(StyleParameters.defaultBackgroundColor, 20));
		JPanel informationPanel = new JPanel();
		informationPanel.add(informationLabel);
		informationLabel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
		informationScrollPane = new CustomizedScrollPane(informationPanel);
		nextButton = new CustomizedButton("Next sentence");
		nextButton.addActionListener(new NextButtonListener());
		panel = new CustomizedPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		exitButton = new CustomizedButton("Exit");
		exitButton.addActionListener(new ExitListener());
		exitButton.setMinimumSize(new Dimension(100, exitButton.getMinimumSize().height));
		exitButton.setMaximumSize(new Dimension(100, exitButton.getMaximumSize().height));
		exitButton.setPreferredSize(new Dimension(100, exitButton.getPreferredSize().height));

		addComponent(exitButton, 1, 0, 1, 1, 0, 0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE);
		addComponent(sentenceLabel, 0, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComponent(informationScrollPane, 0, 2, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		if (!isMcq)
			addComponent(nextButton, 0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(panel, 1, 1, 1, 3, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		goToNextQuestion();
		setVisible(true);
	}
	
	public void reset() {
		
		goToNextQuestion();
	}
	
	public void goToNextQuestion() {

		informationLabel.setText("");
		sentenceIsCorrect = 2;
		nextButton.setVisible(false);
		informationScrollPane.setVisible(false);
		
		if (!isMcq) {

			String string = sentences.getWrongSentence();
			int offset = 0;
			for (int i = 50; i < string.length(); i += 50) {
				System.out.println(i + " / " + string.length() + " / " + offset);
				string = string.substring(0, i + offset) + "<br>" + string.substring(i + offset, string.length());
				offset+=4;
			}


			sentenceLabel.setText("<html><p style='width:700px'>" + string + "</p></html>");
		}
		else {
			if (choicesRadio != null)
				for (CustomizedRadioButton radio : choicesRadio)
					panel.remove(radio);
			panel.remove(nextButton);

			sentenceLabel.setText("<html><center style='width:500px'>" + sentences.getIncompleteWrongSentence() + "</center></html>");
			choicesRadio = new ArrayList<>();
			
			ArrayList<String> choices = sentences.getChoices();
			radioGroup = new ButtonGroup();
			
			for (String choice : choices) {

				CustomizedRadioButton radio = new CustomizedRadioButton(choice);
				radio.addActionListener(new ChoiceListener(radio));
				
				choicesRadio.add(radio);
				radioGroup.add(radio);
				panel.add(radio);
			}

			panel.add(nextButton);

			revalidate();
			repaint();
		}


		if (sentences.isFinished() && getParent() != null)
			((SentencesPanel)getParent()).goToEndPanel();
	}
	
	public class SentenceLabelListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent event) {
			
			if (sentenceIsCorrect == 2 && !isMcq) {

				int characterWidth = 16;
				int index = event.getX() / characterWidth + 50 * (event.getY() / 40);	// ID of the clicked character

				if (!sentences.characterIsFromWrongWord(index)) {
					informationLabel.setText("<html><center>Bad answer</center><br>Good sentence : <i>" + sentences.getCorrectSentence().replaceAll("colorToChange", "red") + "</i>");
					sentenceIsCorrect = 0;
				}
				else {
					informationLabel.setText("<html><center>Well done !</center><br>Good sentence : <i>" + sentences.getCorrectSentence().replaceAll("colorToChange", "green") + "</i>");
					sentenceIsCorrect = 1;
				}

				informationLabel.setText(informationLabel.getText() + "<br><br>Rule : " + sentences.getRuleName() + "<br><br><p style='padding-left: 20px'><i>" + sentences.getRule().replaceAll("\n", "<br>") + "</i></p></html>");
				nextButton.setVisible(true);
				informationScrollPane.setVisible(true);
				//informationLabel.setPreferredSize(new Dimension(informationScrollPane.getWidth(), informationLabel.getPreferredSize().height));
				informationScrollPane.setMinimumSize(new Dimension(informationScrollPane.getMinimumSize().width, 200));
				informationScrollPane.setMaximumSize(new Dimension(informationScrollPane.getMaximumSize().width, 200));
				informationScrollPane.setPreferredSize(new Dimension(informationScrollPane.getPreferredSize().width, 200));
				informationScrollPane.setSize(new Dimension(informationScrollPane.getSize().width, 200));
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
	
	public class NextButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {

			sentences.validateSentence(sentenceIsCorrect == 1);
			goToNextQuestion();
		}
	}
	
	public class ChoiceListener implements ActionListener
	{
		JRadioButton button;
		
		public ChoiceListener(JRadioButton button) {
			super();
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (sentenceIsCorrect == 2 && isMcq) {

				if (sentences.getCorrectWord().equals(button.getText())) {
					informationLabel.setText("<html><center>Well done !</center><br>Good sentence : <i>" + sentences.getCorrectSentence().replaceAll("colorToChange", "green") + "</i>");
					sentenceIsCorrect = 1;
				}
				else {
					informationLabel.setText("<html><center>Bad answer</center><br>Good sentence : <i>" + sentences.getCorrectSentence().replaceAll("colorToChange", "red") + "</i>");
					sentenceIsCorrect = 0;
				}

				informationLabel.setText(informationLabel.getText() + "<br><br>Rule : " + sentences.getRuleName() + "<br><br><p style='padding-left: 20px'><i>" + sentences.getRule().replaceAll("\n", "<br>") + "</i></p></html>");
				nextButton.setVisible(true);
				informationScrollPane.setVisible(true);
				//informationLabel.setPreferredSize(new Dimension(informationScrollPane.getWidth(), informationLabel.getPreferredSize().height));
				informationScrollPane.setMinimumSize(new Dimension(informationScrollPane.getMinimumSize().width, 200));
				informationScrollPane.setMaximumSize(new Dimension(informationScrollPane.getMaximumSize().width, 200));
				informationScrollPane.setPreferredSize(new Dimension(informationScrollPane.getPreferredSize().width, 200));
				informationScrollPane.setSize(new Dimension(informationScrollPane.getSize().width, 200));
			}
		}
	}

	public class ExitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			((SentencesPanel)getParent()).goToEndPanel();
		}
	}
}