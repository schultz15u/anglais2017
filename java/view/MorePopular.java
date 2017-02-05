package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Window that displays the more popular videos
 * 
 * @author Menou not finished
 */
@SuppressWarnings("serial")
public class MorePopular extends JPanel {

	public MorePopular() {
		JLabel text = new JLabel();
		text.setText("MorePopular");
		add(text);
	}

}
