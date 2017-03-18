package view.sentences;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SentencesManager;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.CustomizedButton;
import view.customized_widgets.CustomizedComboBox;
import view.customized_widgets.CustomizedLabel;

public class SentencesHomePanel extends DefaultGridPanel {

	private SentencesManager sentencesManager;
	private CustomizedLabel sentenceLabel;
	private CustomizedButton nextButton;
	private CustomizedComboBox packagesCombo;

	private static String mcqMessage = "<html>For each sentence, you must choose the best answer.<br />Ready ?</html>";
	private static String mistakesMessage = "<html>For each sentence, you have to click on the mistake.<br />Ready ?</html>";

	public SentencesHomePanel(SentencesManager sentencesManager, boolean isMcq) {

		super();
		this.sentencesManager = sentencesManager;

		packagesCombo = new CustomizedComboBox(sentencesManager.getPackagesNames());
		sentenceLabel = new CustomizedLabel(isMcq ? mcqMessage : mistakesMessage);
		nextButton = new CustomizedButton("Start");
		nextButton.addActionListener(new StartListener());

		addComponent(packagesCombo, 1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(sentenceLabel, 0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(nextButton, 0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
	}

	public void update() {
		remove(packagesCombo);
		packagesCombo = new CustomizedComboBox(sentencesManager.getPackagesNames());
		addComponent(packagesCombo, 0, 0, 3, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		revalidate();
		repaint();
	}

	public class StartListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			((SentencesPanel) getParent()).goToQuestionsPanel(packagesCombo.getSelectedItem().toString());
		}
	}
}