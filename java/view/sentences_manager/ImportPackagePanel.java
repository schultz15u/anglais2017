package view.sentences_manager;

import model.SentencesManager;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.CustomizedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class ImportPackagePanel extends DefaultGridPanel {

	private SentencesManager sentencesManager;
	private JLabel messageLabel;
	private CustomizedButton importButton;

	public ImportPackagePanel(SentencesManager sentencesManager, JLabel messageLabel) {
		
		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		this.messageLabel = messageLabel;
		setBackground(StyleParameters.defaultBackgroundColor);
		
		importButton = new CustomizedButton("Import a package");
		importButton.addActionListener(new ImportListener());
		
		addComponent(importButton, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
	}

	private class ImportListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser fileChooser = new JFileChooser();

			try {
				LookAndFeel laf = UIManager.getLookAndFeel();
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				SwingUtilities.updateComponentTreeUI(fileChooser);
				UIManager.setLookAndFeel(laf);

			}
			catch (Exception ex) {
			}

			int returnVal = fileChooser.showOpenDialog(ImportPackagePanel.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {

				File file = fileChooser.getSelectedFile();

				if (!sentencesManager.importPackage(file.getAbsolutePath())) {
					messageLabel.setText("Error with database.");
					messageLabel.setForeground(Color.red);
				}
				else {
					messageLabel.setText("Your package have been imported");
					messageLabel.setForeground(Color.green);
				}
			}
		}
	}
}
