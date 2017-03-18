package view.customized_widgets;

import view.StyleParameters;

import javax.swing.*;

public class CustomizedTextPane extends JTextPane {

	public CustomizedTextPane() {
		super();
		setForeground(StyleParameters.defaultTextColor);
		setBackground(StyleParameters.defaultBackgroundColor);
		setBorder(BorderFactory.createLineBorder(StyleParameters.defaultBackgroundColor, 20));
		setFont(StyleParameters.defaultImportantFont);
		setEditable(false);
	}
}
