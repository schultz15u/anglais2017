package view.customized_widgets;

import view.StyleParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomizedRadioButton extends JRadioButton {

	public CustomizedRadioButton(String text){
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

			g.drawOval(1, 1 + 10, 20,20);
			if (checked)
				g.fillOval(6, 6 + 10, 10,10);
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
