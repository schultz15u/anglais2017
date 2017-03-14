package view.customized_widgets;

import controller.DefaultMouseListener;
import view.StyleParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class CustomizedCheckBox extends JCheckBox {

	public CustomizedCheckBox(String text){
		super(text);
		setBackground(StyleParameters.defaultBackgroundColor);
		setForeground(StyleParameters.defaultTextColor);
		setIcon(new CustomizedCheckBoxIcon(false));
		setFocusPainted(false);
		addActionListener(new CustomizedListener());
		setFont(StyleParameters.defaultImportantFont);
	}

	private class CustomizedCheckBoxIcon implements Icon {

		boolean checked = false;

		public CustomizedCheckBoxIcon(boolean checked) {
			this.checked = checked;
		}

		public void paintIcon(Component component, Graphics g, int x, int y) {

			g.setColor(StyleParameters.defaultSelectedWidgetBackgroundColor);

			g.drawRect(1, 1 + 10, 20,20);
			if (checked)
				g.fillRect(6, 6 + 10, 11,11);
		}

		public int getIconWidth() {
			return 20;
		}

		public int getIconHeight() {
			return 40;
		}
	}

	private class CustomizedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setIcon(new CustomizedCheckBoxIcon(isSelected()));
		}
	}
}
