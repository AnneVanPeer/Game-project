import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CoordSys {
	
	public final double MINX = 0, MINY = 0, MAXX = 2673, MAXY = 3000;
	private ArrayList<Node> nodes  = new ArrayList<>();
	private MapPanel mp;
	private double[] visibleCoords = new double[4];
	private double panelCoordRatio, visibleWidth, visibleHeight;

	public CoordSys(MapPanel mp) {
		this.mp = mp;
		this.visibleHeight = MAXY/mp.getPanelSizeRatio() - MINY;		//width and height are now fixed
		this.visibleWidth = MAXX/2 - MINX;
		setVisibleCoords(MINX, MAXX/2, MINY, MAXY/mp.getPanelSizeRatio());
	}
	
	/**
	 *.Sets visible coords if min and max values are not exceeded.
	 * @param minx current minimal x-pixel coord
	 * @param maxx current maximal x-pixel coord
	 * @param miny current minimal y-pixel coord
	 * @param maxy current maximal y-pixel coord
	 */
	public void setVisibleCoords(double minx, double maxx, double miny, double maxy) {
		visibleCoords[0] = minx;
		visibleCoords[1] = maxx;
		visibleCoords[2] = miny;
		visibleCoords[3] = maxy;
		if(minx<MINX) {
			visibleCoords[0] = MINX;
			visibleCoords[1] = MINX+visibleWidth;
		} if(maxx>MAXX) {
			visibleCoords[0] = MAXX-visibleWidth;
			visibleCoords[1] = MAXX;
		}
		if(miny<MINY) {
			visibleCoords[2] = MINY;
			visibleCoords[3] = MINY+visibleHeight;
		} if(maxy>MAXY) {
			visibleCoords[2] = MAXY-visibleHeight;
			visibleCoords[3] = MAXY;
		}
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
	public double getXcoord(int x) {
		double newX = (int)(visibleCoords[0] + ((visibleCoords[1]-visibleCoords[0])*(x/mp.getViewSize().getWidth())));
		return newX;
	}

	/**
	 * Compute the relative y location of a pixel to the coordsys.
	 * @param y
	 * @return
	 */
	public double getYcoord(int y) {
		double newY = (int)(visibleCoords[2] + ((visibleCoords[3]-visibleCoords[2])*(y/mp.getViewSize().getHeight())));
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