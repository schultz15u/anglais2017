package view.sentences_manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.SentencesManager;
import view.customized_widgets.CustomizedButton;
import view.DefaultGridPanel;


public class SentencesManagerPanel extends DefaultGridPanel {
	
	private SentencesManager sentencesManager;
	private CustomizedButton importPackageButton;
	private CustomizedButton createPackageButton;
	private CustomizedButton modifyPackageButton;
	private CustomizedButton removePackageButton;
	private CreatePackagePanel createPackagePanel;
	private ModifyPackagePanel modifyPackagePanel;
	private RemovePackagePanel removePackagePanel;
	private JPanel currentPanel;
	private JLabel messageLabel;
	
	public SentencesManagerPanel(SentencesManager sentencesManager) {
		
		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		setBackground(new Color(30, 30, 30));

		importPackageButton = new CustomizedButton("Import package");
		createPackageButton = new CustomizedButton("Create package");
		createPackageButton.addActionListener(new CreatePackageListener());
		modifyPackageButton = new CustomizedButton("Modify package");
		modifyPackageButton.addActionListener(new ModifyPackageListener());
		removePackageButton = new CustomizedButton("Remove package");
		removePackageButton.addActionListener(new RemovePackageListener());
		messageLabel = new JLabel("");

		createPackagePanel = new CreatePackagePanel(sentencesManager, messageLabel);
		modifyPackagePanel = new ModifyPackagePanel(sentencesManager, messageLabel);
		removePackagePanel = new RemovePackagePanel(sentencesManager, messageLabel);
		currentPanel = createPackagePanel;
		
		addComponent(importPackageButton, 0, 0, 1, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(createPackageButton, 1, 0, 1, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(modifyPackageButton, 2, 0, 1, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(removePackageButton, 3, 0, 1, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(createPackagePanel, 0, 1, 4, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(messageLabel, 0, 2, 4, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);

		createPackageButton.setNormalColor(new Color(80, 80, 80));
	}

	private void changePanel(JPanel newPanel, int fill) {
		remove(currentPanel);
		currentPanel = newPanel;
		addComponent(currentPanel, 0, 1, 4, 1, 1, 1, GridBagConstraints.CENTER, fill);
		messageLabel.setText("");

		revalidate();
		repaint();
	}
	
	private class CreatePackageListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(createPackagePanel, GridBagConstraints.NONE);

			importPackageButton.setNormalColor(new Color(50, 50, 50));
			createPackageButton.setNormalColor(new Color(50, 50, 50));
			modifyPackageButton.setNormalColor(new Color(50, 50, 50));
			removePackageButton.setNormalColor(new Color(50, 50, 50));
			createPackageButton.setNormalColor(new Color(80, 80, 80));
		}
	}
	
	private class ModifyPackageListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(modifyPackagePanel, GridBagConstraints.BOTH);
			modifyPackagePanel.update();

			importPackageButton.setNormalColor(new Color(50, 50, 50));
			createPackageButton.setNormalColor(new Color(50, 50, 50));
			modifyPackageButton.setNormalColor(new Color(50, 50, 50));
			removePackageButton.setNormalColor(new Color(50, 50, 50));
			modifyPackageButton.setNormalColor(new Color(80, 80, 80));
		}
	}

	private class RemovePackageListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(removePackagePanel, GridBagConstraints.BOTH);
			removePackagePanel.update();

			importPackageButton.setNormalColor(new Color(50, 50, 50));
			createPackageButton.setNormalColor(new Color(50, 50, 50));
			modifyPackageButton.setNormalColor(new Color(50, 50, 50));
			removePackageButton.setNormalColor(new Color(50, 50, 50));
			removePackageButton.setNormalColor(new Color(80, 80, 80));
		}
	}
}
