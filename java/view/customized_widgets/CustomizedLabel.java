package view.customized_widgets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import view.StyleParameters;

public class CustomizedLabel extends JLabel {

	public CustomizedLabel(String text) {
		super(text);
		setForeground(StyleParameters.defaultTextColor);
		setBorder(BorderFactory.createLineBorder(StyleParameters.defaultBackgroundColor, 5));
		setFont(StyleParameters.defaultImportantFont);
	}
}
