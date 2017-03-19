package view.customized_widgets;

import javax.swing.*;

import view.StyleParameters;

public class CustomizedLabel extends JLabel {

	public CustomizedLabel(String text) {
		super(text);
		initialize();
	}

	public CustomizedLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		initialize();
	}

	public void initialize() {

		setBackground(StyleParameters.defaultBackgroundColor);
		setForeground(StyleParameters.defaultTextColor);
		setBorder(BorderFactory.createLineBorder(StyleParameters.defaultBackgroundColor, 5));
		setFont(StyleParameters.defaultImportantFont);
	}
}
