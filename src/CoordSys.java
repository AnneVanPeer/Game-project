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
	
	public final double MINX = 0, MINY = 0, MAXX = 1000, MAXY = 1000;
	private ArrayList<Node> nodes  = new ArrayList<>();
	private MapPanel mp;
	private double[] visibleCoords = new double[4];
	private double panelCoordRatio;

	public CoordSys(MapPanel mp, Game game) {
		this.mp = mp;
		//nodes = game.getNodes(); Turned of for testing
		nodes.add(new Node(1,1,1));
		nodes.add(new Node(2,100,100));
		setVisibleCoords(MINX, MAXX, MINY, MAXY/mp.getViewRatio()); 
		System.out.println("visible: " + visibleCoords[0] + " " + visibleCoords[1] + " " + visibleCoords[2] + " " + visibleCoords[3]);
	}
	
	/**
	 *.Sets visible coords if min and max values are not exceeded.
	 * @param minx
	 * @param maxx
	 * @param miny
	 * @param maxy
	 */
	public void setVisibleCoords(double minx, double maxx, double miny, double maxy) {
		if(minx<MINX || maxx>MAXX) { //TODO: moet wel geval onderschijding boven en onder!
			//Voorbeeld:Als minx<MINX dan [0]=MINX
			if(miny<MINY || maxy>MAXY) {
				visibleCoords[0] = MINX;
				visibleCoords[1] = MAXX;
			}
			else {
				visibleCoords[0] = MINX;
				visibleCoords[1] = MAXX;
				visibleCoords[2] = miny;
				visibleCoords[3] = maxy;
			}
		}
		else if(miny<MINY || maxy>MAXY) {
			if(minx<MINX || maxx>MAXX) {}
			else {
				visibleCoords[0] = minx;
				visibleCoords[1] = maxx;
				visibleCoords[2] = MINY;
				visibleCoords[3] = MAXY;
			}
		}
		else {
			visibleCoords[0] = minx;
			visibleCoords[1] = maxx;
			visibleCoords[2] = miny;
			visibleCoords[3] = maxy;
		}
		System.out.println("visible: " + visibleCoords[0] + " " + visibleCoords[1] + " " + visibleCoords[2] + " " + visibleCoords[3]);
	}
	
	/**
	 * double minx, double maxx, double miny, double maxy
	 * @return
	 */
	public double[] getVisibleCoords() {
		return visibleCoords;
	}
	
	public double getPanelCoordRatio() {
		return panelCoordRatio;
	}
	
	public void setPanelCoordRatio(double ratio) {
		panelCoordRatio = ratio;
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
