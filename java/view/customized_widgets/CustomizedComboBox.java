package view.customized_widgets;

import java.awt.*;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import view.StyleParameters;

public class CustomizedComboBox extends JComboBox<String> {
	public CustomizedComboBox(List<String> items) {

		super(items.toArray(new String[] {}));
		setForeground(StyleParameters.defaultTextColor);
		setBackground(StyleParameters.defaultWidgetBackgroundColor);
		setFont(StyleParameters.defaultImportantFont);
		setBorder(BorderFactory.createLineBorder(StyleParameters.defaultBackgroundColor, 5));
		setFocusable(false);
		setMinimumSize(new Dimension(345, 40));
		setMaximumSize(new Dimension(345, 40));
		setPreferredSize(new Dimension(345, 40));
		setSize(new Dimension(345, 40));

		UIManager.put("ComboBox.selectionBackground",
				new ColorUIResource(StyleParameters.defaultSelectedWidgetBackgroundColor));
		UIManager.put("ComboBox.selectionForeground", new ColorUIResource(StyleParameters.defaultTextColor));
	}
}
