package view.customized_widgets;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CustomizedTextField extends JTextField {



	public CustomizedTextField(String text, int columns){
		super(text, columns);
		initialize();
	}

	public CustomizedTextField(String text){
		super(text);
		initialize();
	}

	private void initialize() {
		setCaretColor(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(50, 50, 50));
		setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 5));
	}
}
