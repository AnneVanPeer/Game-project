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
	
	public final double MINX = 0, MINY = 0, MAXX = 100, MAXY = 100, ZOOMSTEP = 10;
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
		setImage();
	}
	
	/**
	 *.Sets visible coords if min and max values are not exceeded.
	 * @param minx
	 * @param maxx
	 * @param miny
	 * @param maxy
	 */
	public void setVisibleCoords(double minx, double maxx, double miny, double maxy) {
		double width = maxx-minx;
		double height = maxy-miny;
		//System.out.println(miny + "   " + maxy);
		visibleCoords[0] = minx;
		visibleCoords[1] = maxx;
		visibleCoords[2] = miny;
		visibleCoords[3] = maxy;
		if(minx<MINX) {
			visibleCoords[0] = MINX;
			visibleCoords[1] = MINX+width;
		} else if(maxx>MAXX) {
			visibleCoords[0] = MAXX-width;
			visibleCoords[1] = MAXX;
		}
		if(miny<MINY) {
			visibleCoords[2] = MINY;
			visibleCoords[3] = MINY+height;
		} else if(maxy>MAXY) {
			visibleCoords[2] = MAXY-height;
			visibleCoords[3] = MAXY;
		}		
		System.out.println("visible: " + visibleCoords[0] + " " + visibleCoords[1] + " " + visibleCoords[2] + " " + visibleCoords[3]);
		//System.out.println("width " + (visibleCoords[1]-visibleCoords[0]) + "height " + (visibleCoords[3]-visibleCoords[2]));
		setImage();
	}
	
	private void setImage() {
		int imageX = - (int)(mp.getViewSize().getWidth()/MAXX*visibleCoords[0]);
		int imageY = - (int)(mp.getViewSize().getHeight()/MAXY*visibleCoords[2]);
		int tempw = (int)( (MAXX-visibleCoords[1]) * (mp.getViewSize().getWidth()/MAXX) );
		int temph = (int)( (MAXY-visibleCoords[3]) * (mp.getViewSize().getHeight()/MAXY) );
		int imageWidth = - imageX + tempw + (int)(mp.getViewSize().getWidth());
		int imageHeight = - imageY + temph + (int)(mp.getViewSize().getHeight());
		mp.setImageProperties(imageX, imageY, imageWidth, imageHeight);
		
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

	/**
	 * Compute the relative x location of a pixel to the coordsys.
	 * @param x
	 * @return
	 */
	public int getXcoord(int x) {
		int newX = (int)(mp.getViewSize().getWidth()/(visibleCoords[1]-visibleCoords[0]) *x);
		return newX;
	}

	/**
	 * Compute the relative y location of a pixel to the coordsys.
	 * @param y
	 * @return
	 */
	public int getYcoord(int y) {
		int newY = (int)(mp.getViewSize().getHeight()/(visibleCoords[3]-visibleCoords[2]) *y);
		return newY;
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
