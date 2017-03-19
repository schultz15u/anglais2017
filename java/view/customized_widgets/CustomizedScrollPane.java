package view.customized_widgets;

import view.StyleParameters;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomizedScrollPane extends JScrollPane {

	public CustomizedScrollPane(JComponent component){
		super(component);
		initialize();
		component.setBackground(StyleParameters.defaultBackgroundColor);
		component.setOpaque(true);
		component.setBorder(null);
	}

	private void initialize() {
		setBorder(BorderFactory.createLineBorder(StyleParameters.defaultBackgroundColor, 20));
		getVerticalScrollBar().setUI(new MyScrollBarUI());
		getHorizontalScrollBar().setUI(new MyScrollBarUI());
	}


	private class MyScrollBarUI extends BasicScrollBarUI {

		@Override
		protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
			g.setColor(StyleParameters.defaultBackgroundColor);
			g.fillRect(r.x, r.y, r.width, r.height);
		}

		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
			g.setColor(StyleParameters.defaultWidgetBackgroundColor);
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
