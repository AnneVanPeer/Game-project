import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window{
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Window(Game game) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		JFrame frame = new JFrame("map");
		
		this.fillFrame(frame);
		frame.pack();
		frame.setSize(screenSize);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
				
	}

	private void fillFrame(JFrame frame) {
		frame.setBackground(Color.WHITE);
		frame.add(new JLabel("hoii"));
	}

}
