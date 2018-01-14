import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window{
	
	private Game game;
		
	public Window(Game game) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.game = game;
		JFrame frame = new JFrame("map");
		
		this.fillFrame(frame);
		frame.pack();
		frame.setSize(screenSize);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
				
	}

	private void fillFrame(JFrame frame) {
		frame.setBackground(Color.WHITE);
		frame.setLayout(new BorderLayout());
		frame.add(new MapPanel(game), BorderLayout.CENTER);
		frame.add(new GamePanel(), BorderLayout.SOUTH);
	}
}
