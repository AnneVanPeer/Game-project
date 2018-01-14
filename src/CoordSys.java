import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CoordSys {
	
	public final double MINX = -100;
	public final double MINY = -100;
	public final double MAXX = 100;
	public final double MAXY = 100;
	private ArrayList<Node> nodes  = new ArrayList<>();
	private MapPanel mp;
	private double[] visibleCoords = {MINX, MAXX, MINY, MAXY};

	public CoordSys(MapPanel mp, Game game) {
		this.mp = mp;
		//nodes = game.getNodes(); Turned of for testing
		nodes.add(new Node(1,1,1));
		nodes.add(new Node(2,10,10));
	}
	
	public void setVisibleCoords(double minx, double maxx, double miny, double maxy) {
		visibleCoords[0] = minx;
		visibleCoords[1] = maxx;
		visibleCoords[2] = miny;
		visibleCoords[3] = maxy;
	}
	
	public double[] getVisibleCoords() {
		return visibleCoords;
	}
	
	/*public void setImageLocation(int x, int y) {
		image.setLocation(x, y);
	}
	
	public void setImageSize(float amount) {
		int newWidth = (int) (image.getSize().width + amount);
		int newHeight = (int) (image.getSize().height + amount);
		image.setSize(new Dimension(newWidth, newHeight));
	}
	
	public JLabel getImageComp() {
		return image;
	}
	
	public Point getImageLocation() {
		return image.getLocation();
	}*/

}
