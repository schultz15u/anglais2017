package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Window that displays channels the user subscribed to
 * @author Bichette
 * not finished
 */
@SuppressWarnings("serial")
public class Subscribements extends JPanel {

	public Subscribements(){
			JLabel text = new JLabel();
			text.setText("Subscriments");
			add(text);
	}
}
