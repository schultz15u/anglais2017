package view.sentences_manager;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.SentencesManager;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.CustomizedButton;

public class SentencesManagerPanel extends DefaultGridPanel {

	private CustomizedButton importPackageButton;
	private CustomizedButton createPackageButton;
	private CustomizedButton modifyPackageButton;
	private CustomizedButton removePackageButton;
	private ImportPackagePanel importPackagePanel;
	private CreatePackagePanel createPackagePanel;
	private ModifyPackagePanel modifyPackagePanel;
	private RemovePackagePanel removePackagePanel;
	private JPanel currentPanel;
	private JLabel messageLabel;

	public SentencesManagerPanel(SentencesManager sentencesManager) {

		super();
		setLayout(new GridBagLayout());

		importPackageButton = new CustomizedButton("Import package");
		importPackageButton.setPreferredSize(new Dimension(importPackageButton.getWidth(), 40));
		importPackageButton.addActionListener(new ImportPackageListener());
		createPackageButton = new CustomizedButton("Create package");
		createPackageButton.addActionListener(new CreatePackageListener());
		createPackageButton.setPreferredSize(new Dimension(createPackageButton.getWidth(), 40));
		modifyPackageButton = new CustomizedButton("Modify package");
		modifyPackageButton.addActionListener(new ModifyPackageListener());
		modifyPackageButton.setPreferredSize(new Dimension(modifyPackageButton.getWidth(), 40));
		removePackageButton = new CustomizedButton("Other operations");
		removePackageButton.addActionListener(new RemovePackageListener());
		removePackageButton.setPreferredSize(new Dimension(removePackageButton.getWidth(), 40));
		messageLabel = new JLabel("");

		importPackagePanel = new ImportPackagePanel(sentencesManager, messageLabel);
		createPackagePanel = new CreatePackagePanel(sentencesManager, messageLabel);
		modifyPackagePanel = new ModifyPackagePanel(sentencesManager, messageLabel);
		removePackagePanel = new RemovePackagePanel(sentencesManager, messageLabel);
		currentPanel = createPackagePanel;

		addComponent(importPackageButton, 0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(createPackageButton, 1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(modifyPackageButton, 2, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(removePackageButton, 3, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(createPackagePanel, 0, 1, 4, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(messageLabel, 0, 2, 4, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);

		importPackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
		createPackageButton.setNormalColor(StyleParameters.defaultWidgetBackgroundColor);
		modifyPackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
		removePackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
	}

	private void changePanel(JPanel newPanel, int fill) {
		remove(currentPanel);
		currentPanel = newPanel;
		addComponent(currentPanel, 0, 1, 4, 1, 1, 1, GridBagConstraints.CENTER, fill);
		messageLabel.setText("");

		revalidate();
		repaint();
	}

	private class ImportPackageListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(importPackagePanel, GridBagConstraints.NONE);

			importPackageButton.setNormalColor(StyleParameters.defaultWidgetBackgroundColor);
			createPackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			modifyPackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			removePackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
		}
	}

	private class CreatePackageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(createPackagePanel, GridBagConstraints.NONE);

			importPackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			createPackageButton.setNormalColor(StyleParameters.defaultWidgetBackgroundColor);
			modifyPackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			removePackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
		}
	}

	private class ModifyPackageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(modifyPackagePanel, GridBagConstraints.BOTH);
			modifyPackagePanel.update();

			importPackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			createPackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			modifyPackageButton.setNormalColor(StyleParameters.defaultWidgetBackgroundColor);
			removePackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
		}
	}

	private class RemovePackageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			changePanel(removePackagePanel, GridBagConstraints.BOTH);
			removePackagePanel.update();

			importPackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			createPackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			modifyPackageButton.setNormalColor(StyleParameters.mainMenuActiveButtonColor);
			removePackageButton.setNormalColor(StyleParameters.defaultWidgetBackgroundColor);
		}
	}
}
