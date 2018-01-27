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
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(new Dimension((int)(screenSize.getWidth()/2), (int)screenSize.getHeight()));
		frame.setLocation(0,0);
		frame.setVisible(true);

	}

	private void fillFrame(JFrame frame) {
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		frame.add(new MapPanel(game));
		frame.add(new GamePanel());
	}
}
