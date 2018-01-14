import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class MapPanel extends JPanel{
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private CoordSys coordSys;

	public MapPanel(Game game) {
		this.setBackground(Color.GREEN);
		this.setSize((int)screenSize.getWidth(), (int)(screenSize.getHeight()/2));
		System.out.println("MapPanel:" + this.getHeight() + "  " + this.getWidth());
		fillPanel(game);
	}

	private void fillPanel(Game game) {
		coordSys = new CoordSys(this, game);
		MyMouseListener mml = new MyMouseListener(coordSys);
		this.addMouseListener(mml);
		this.addMouseMotionListener(mml);
		this.addMouseWheelListener(mml);
		//add borders
		//add listeners for mouse (uses the coordsys).
		
		
	}
	/**
	 * Function used to update to visible items on the map, based on the coordinate system.
	 */
	public void updateMap() {
		
	}

}
