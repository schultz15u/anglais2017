package view.customized_widgets;

import view.StyleParameters;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomizedComboBox extends JComboBox {

	public CustomizedComboBox(Object[] items){
		super(items);
		setForeground(StyleParameters.defaultTextColor);
		setBackground(StyleParameters.defaultWidgetBackgroundColor);
		setFont(StyleParameters.defaultImportantFont);
		setBorder(BorderFactory.createLineBorder(StyleParameters.defaultBackgroundColor, 5));
	}
}
