package view.customized_widgets;

import view.StyleParameters;

import javax.swing.*;
import java.awt.*;

public class CustomizedLabel extends JLabel {

	public CustomizedLabel(String text){
		super(text);
		setForeground(StyleParameters.defaultTextColor);
		setBorder(BorderFactory.createLineBorder(StyleParameters.defaultBackgroundColor, 5));
		setFont(StyleParameters.defaultImportantFont);
	}
}
