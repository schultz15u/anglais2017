package view.sentences_manager;

import model.SentencesManager;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.CustomizedButton;
import view.customized_widgets.CustomizedComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RemovePackagePanel extends DefaultGridPanel {

	private SentencesManager sentencesManager;
	private CustomizedComboBox packagesCombo;
	private CustomizedButton removeSentenceButton;
	private JLabel messageLabel;

	public RemovePackagePanel(SentencesManager sentencesManager, JLabel messageLabel) {
		
		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		this.messageLabel = messageLabel;
		setBackground(StyleParameters.defaultBackgroundColor);

		packagesCombo = new CustomizedComboBox(sentencesManager.getPackagesNames().toArray());
		removeSentenceButton = new CustomizedButton("Remove sentence");
		removeSentenceButton.addActionListener(new RemoveListener());

		addComponent(packagesCombo, 0, 0, 3, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(removeSentenceButton, 2, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
	}

	public void update() {
		remove(packagesCombo);
		packagesCombo = new CustomizedComboBox(sentencesManager.getPackagesNames().toArray());
		addComponent(packagesCombo, 0, 0, 3, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		validate();
		repaint();
	}

	private class RemoveListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (packagesCombo.getSelectedItem() == null || !sentencesManager.removePackage(packagesCombo.getSelectedItem().toString())) {
				messageLabel.setText("Error with database.");
				messageLabel.setForeground(Color.red);
			}
			else {
				messageLabel.setText("Package has been removed.");
				messageLabel.setForeground(Color.green);
			}


			update();
		}
	}
}
