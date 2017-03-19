package view.sentences_manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.SentencesManager;
import view.StyleParameters;
import view.customized_widgets.CustomizedButton;
import view.customized_widgets.CustomizedCheckBox;
import view.customized_widgets.CustomizedLabel;
import view.DefaultGridPanel;
import view.customized_widgets.CustomizedTextField;


public class CreatePackagePanel extends DefaultGridPanel {
	
	private SentencesManager sentencesManager;
	private JLabel messageLabel;
	private CustomizedLabel nameLabel;
	private CustomizedTextField nameField;
	private CustomizedCheckBox canBeModifyCheckBox;
	private CustomizedButton validationButton;
	
	public CreatePackagePanel(SentencesManager sentencesManager, JLabel messageLabel) {
		
		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		this.messageLabel = messageLabel;

		nameLabel = new CustomizedLabel("Package name : ");
		nameField = new CustomizedTextField("", 30);
		canBeModifyCheckBox = new CustomizedCheckBox("Package can be modified by someone else");
		validationButton = new CustomizedButton("Create");
		validationButton.addActionListener(new CreateListener());
		
		addComponent(nameLabel, 0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
		addComponent(nameField, 1, 0, 1, 1, 1,1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
		//addComponent(canBeModifyCheckBox, 0, 1, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
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
