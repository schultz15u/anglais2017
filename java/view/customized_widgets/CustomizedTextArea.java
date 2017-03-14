package view.customized_widgets;

import view.StyleParameters;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomizedTextArea extends JTextArea {

	JScrollPane scrollPane;

	public CustomizedTextArea(String text, int rows, int columns){
		super(text, rows, columns);
		initialize();
	}

	public CustomizedTextArea(String text){
		super(text);
		initialize();
	}

	private void initialize() {
		setFont(StyleParameters.defaultNormalFont);
		setCaretColor(StyleParameters.defaultTextColor);
		setForeground(StyleParameters.defaultTextColor);
		setBackground(StyleParameters.defaultWidgetBackgroundColor);

		scrollPane = new JScrollPane( this );
		scrollPane.setBorder(BorderFactory.createLineBorder(StyleParameters.defaultWidgetBackgroundColor, 2));
		scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
		scrollPane.setBackground(StyleParameters.defaultWidgetBackgroundColor);

	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	private class MyScrollBarUI extends BasicScrollBarUI {

		@Override
		protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
			g.setColor(StyleParameters.defaultWidgetBackgroundColor);
			g.fillRect(r.x, r.y, r.width, r.height);
		}

		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
			g.setColor(StyleParameters.defaultClickedWidgetBackgroundColor);
			if (r.width > r.height)
				g.fillRect(r.x + r.height / 4, r.y + r.height / 4, r.width - r.height / 2, r.height / 2);
			else
				g.fillRect(r.x + r.width / 4, r.y + r.height / 4, r.width / 2, r.height - r.width / 2);
		}

		protected JButton createDecreaseButton(int orientation) {
			return createZeroButton();
		}

		@Override
		protected JButton createIncreaseButton(int orientation) {
			return createZeroButton();
		}

		private JButton createZeroButton() {
			JButton jbutton = new JButton();
			jbutton.setPreferredSize(new Dimension(0, 0));
			jbutton.setMinimumSize(new Dimension(0, 0));
			jbutton.setMaximumSize(new Dimension(0, 0));
			return jbutton;
		}
	}
}
