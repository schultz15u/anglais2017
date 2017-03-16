package view.sentences_manager;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import model.SentencesManager;
import view.DefaultGridPanel;
import view.StyleParameters;
import view.customized_widgets.CustomizedButton;
import view.customized_widgets.CustomizedComboBox;

public class RemovePackagePanel extends DefaultGridPanel {

	private SentencesManager sentencesManager;
	private CustomizedComboBox packagesCombo;
	private CustomizedButton exportSentenceButton;
	private CustomizedButton removeSentenceButton;
	private JLabel messageLabel;

	public RemovePackagePanel(SentencesManager sentencesManager, JLabel messageLabel) {

		super();
		this.sentencesManager = sentencesManager;
		setLayout(new GridBagLayout());
		this.messageLabel = messageLabel;
		setBackground(StyleParameters.defaultBackgroundColor);

		packagesCombo = new CustomizedComboBox(sentencesManager.getPackagesNames().toArray());
		exportSentenceButton = new CustomizedButton("Export package");
		exportSentenceButton.addActionListener(new ExportListener());
		removeSentenceButton = new CustomizedButton("Remove package");
		removeSentenceButton.addActionListener(new RemoveListener());

		addComponent(packagesCombo, 0, 0, 3, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponent(exportSentenceButton, 2, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addComponent(removeSentenceButton, 2, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		removeSentenceButton.setNormalColor(new Color(255, 0, 0));
		removeSentenceButton.setSelectedColor(new Color(255, 80, 80));
		removeSentenceButton.setClickedColor(new Color(255, 160, 160));
	}

	public void update() {
		remove(packagesCombo);
		packagesCombo = new CustomizedComboBox(sentencesManager.getPackagesNames().toArray());
		addComponent(packagesCombo, 0, 0, 3, 1, 1, 0.05, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		revalidate();
		repaint();
	}

	private class ExportListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser fileChooser = new JFileChooser();

			try {
				LookAndFeel laf = UIManager.getLookAndFeel();
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				SwingUtilities.updateComponentTreeUI(fileChooser);
				UIManager.setLookAndFeel(laf);

			} catch (Exception ex) {
			}

			int returnVal = fileChooser.showSaveDialog(RemovePackagePanel.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {

				File file = fileChooser.getSelectedFile();
				System.out.println(file.getAbsolutePath());

				if (!sentencesManager.exportPackage(packagesCombo.getSelectedItem().toString(),
						file.getAbsolutePath())) {
					messageLabel.setText("Error with database.");
					messageLabel.setForeground(Color.red);
				} else {
					messageLabel.setText("Your package have been exported in " + file.getAbsolutePath());
					messageLabel.setForeground(Color.green);
				}
			} else {
				messageLabel.setText("Error with disk access");
				messageLabel.setForeground(Color.red);
			}
		}
	}

	private class RemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (packagesCombo.getSelectedItem() == null
					|| !sentencesManager.removePackage(packagesCombo.getSelectedItem().toString())) {
				messageLabel.setText("Error with database.");
				messageLabel.setForeground(Color.red);
			} else {
				messageLabel.setText("Package has been removed.");
				messageLabel.setForeground(Color.green);
			}

			update();
		}
	}
}
