import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class MapPanel extends JPanel{
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public MapPanel() {
		this.setBackground(Color.RED);
		this.setSize((int)screenSize.getWidth(), (int)(screenSize.getHeight()/2));
		System.out.println("MapPanel:" + this.getHeight() + "  " + this.getWidth());
	}

}
