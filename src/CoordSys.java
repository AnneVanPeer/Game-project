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
	private JLabel image;

	public CoordSys(MapPanel mp, Game game) {
		this.mp = mp;
		//nodes = game.getNodes(); Turned of for testing
		nodes.add(new Node(1,1,1));
		nodes.add(new Node(2,10,10));
		try {
            BufferedImage img = ImageIO.read(new File(
            		"src/sy.png"));
            image = new JLabel(new ImageIcon(img));
            image.setSize(image.getPreferredSize());
           // MouseHandler mh  = new MouseHandler();
           // label.addMouseListener(mh);
            //label.addMouseMotionListener(mh);
            mp.add(image);
        } catch (IOException exp) {
            exp.printStackTrace();
        }
		new JLabel(new ImageIcon("src/sy.png"));
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
	
	public void setImageLocation(int x, int y) {
		image.setLocation(x, y);
	}
	
	public JLabel getImageComp() {
		return image;
	}
	
	public Point getImageLocation() {
		return image.getLocation();
	}

}
