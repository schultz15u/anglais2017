package view.customized_widgets;

import view.StyleParameters;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class CustomizedTextField extends JTextField {



	public CustomizedTextField(String text, int columns){
		super(text, columns);
		initialize();
	}

	private void initialize() {
		setFont(StyleParameters.defaultNormalFont);
		setCaretColor(StyleParameters.defaultTextColor);
		setForeground(StyleParameters.defaultTextColor);
		setBackground(StyleParameters.defaultWidgetBackgroundColor);
		setBorder(BorderFactory.createLineBorder(StyleParameters.defaultWidgetBackgroundColor, 2));
	}
}
