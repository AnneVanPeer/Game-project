import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Window {

	private Game game;

	public Window(Game game) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.game = game;
		JFrame frame = new JFrame("map");

		this.fillFrame(frame);
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//frame.setSize(new Dimension((int)(screenSize.getWidth()/2), (int)screenSize.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	private void fillFrame(JFrame frame) {
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
		frame.add(new GamePanel());
		frame.add(new MapPanel());
	}
}
