package view.customized_widgets;

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
		setCaretColor(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(50, 50, 50));

		scrollPane = new JScrollPane( this );
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 5));
		scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
		scrollPane.setBackground(new Color(70, 70, 70));

	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	private class MyScrollBarUI extends BasicScrollBarUI {

		@Override
		protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
			g.setColor(new Color(30, 30, 30));
			g.fillRect(r.x, r.y, r.width, r.height);
		}

		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
			g.setColor(new Color(100, 100, 100));
			g.fillRect(r.x, r.y, r.width, r.height);
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
