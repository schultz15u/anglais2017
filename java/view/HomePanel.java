package view;

import view.customized_widgets.CustomizedButton;
import view.customized_widgets.CustomizedLabel;
import view.customized_widgets.CustomizedPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends DefaultGridPanel {

	CustomizedLabel textLabel;

	public HomePanel() {

		super();

		textLabel = new CustomizedLabel("<html><p style='width: 500px'>Welcome !<br><br>" +
				"Shake S'Pear is an application to learn or improve your english.<br>" +
				"First of all you have on your left two trainings modes: MCQ and mistakes.<br>" +
				"Secondly you have the possibility to read all the rules in order to learn them.<br>" +
				"Thirdly you have the sentence manager which allows you to create, modify and also delete package, rules and sentences.<br>" +
				"Enjoy working !!</p></html>");

		addComponent(textLabel, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
	}
}
