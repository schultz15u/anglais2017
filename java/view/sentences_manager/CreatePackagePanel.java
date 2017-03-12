package view.sentences_manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.SentencesManager;
import view.DefaultGridPanel;


public class CreatePackagePanel extends DefaultGridPanel {
	
	private SentencesManager sentencesManager;
	private JLabel messageLabel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JCheckBox canBeModifyCheckBox;
	private JButton validationButton;
	
	public CreatePackagePanel(SentencesManager sentencesManager, JLabel messageLabel) {
		
		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		this.messageLabel = messageLabel;
		
		nameLabel = new JLabel("Package name : ");
		nameField = new JTextField("", 30);
		canBeModifyCheckBox = new JCheckBox("Package can be modified by someone else");
		validationButton = new JButton("Create");
		validationButton.addActionListener(new CreateListener());
		
		addComponent(nameLabel, 0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
		addComponent(nameField, 1, 0, 1, 1, 1,1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
		addComponent(canBeModifyCheckBox, 0, 1, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
		addComponent(validationButton, 0, 2, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
	}

	private class CreateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (nameField.getText().isEmpty()) {
				messageLabel.setText("The package name is empty.");
				messageLabel.setForeground(Color.red);
			}
			else if (!sentencesManager.addPackage(nameField.getText(), canBeModifyCheckBox.isSelected())) {
				messageLabel.setText("Problem with the database.");
				messageLabel.setForeground(Color.red);
			}
			else {
				messageLabel.setText("The package have been created.");
				messageLabel.setForeground(Color.green);
			}
		}
	}
}
