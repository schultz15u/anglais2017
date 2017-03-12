package view.customized_widgets;

import javax.swing.*;
import java.awt.*;

public class CustomizedLabel extends JLabel {

	public CustomizedLabel(String text){
		super(text);
		setForeground(new Color(255, 255, 255));
		setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 5));
	}
}
