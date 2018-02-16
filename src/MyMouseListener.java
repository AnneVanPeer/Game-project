import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;

public class MyMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

	static final String NEWLINE = System.getProperty("line.separator");
	public final double scaleSpeed = .05;
	private MapPanel mp;
	private CoordSys cs;
	private int x;
	private int y;

	public MyMouseListener(MapPanel mp, CoordSys cs) {
		this.mp = mp;
		this.cs = cs;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int x = e.getX();
		int y = e.getY();
		double MouseXCoord = cs.getXcoord(x); //compute mouse location in coordsys.
		double MouseYCoord = cs.getYcoord(y);
		System.out.println("mouse at: " + MouseXCoord + ", " + MouseYCoord);

		if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
			//double changeW = 20.0; // mp.getImageWidth()*scaleSpeed;
			//double changeH = 20.0; // mp.getImageHeight()*scaleSpeed;
			double visibleW = cs.getVisibleCoords()[1] - cs.getVisibleCoords()[0];
			double visibleH = cs.getVisibleCoords()[3] - cs.getVisibleCoords()[2];
			double newVisibleW, newVisibleH;
			double minx, miny;
			if (e.getWheelRotation() < 0){
				//mp.setImageProperties((int) (mp.getImageX() - (0.5 * changeW)),
				//		(int) (mp.getImageY() - (0.5 * changeH)), (int) (mp.getImageWidth() + changeW),
				//		(int) (mp.getImageHeight() + changeH));
				newVisibleW = visibleW-cs.ZOOMSTEP*mp.getImageProp()[0];
				newVisibleH = visibleH-cs.ZOOMSTEP*mp.getImageProp()[1];
				double MinX = cs.getVisibleCoords()[0];
				double MaxX = cs.getVisibleCoords()[1];
				double MinY = cs.getVisibleCoords()[2];
				double MaxY = cs.getVisibleCoords()[3];
				minx = MinX + (cs.ZOOMSTEP*mp.getImageProp()[0]*((MouseXCoord-MinX)/visibleW));
				miny = MinY + (cs.ZOOMSTEP*mp.getImageProp()[1]*((MouseYCoord-MinY)/visibleH));
				//minx = cs.getVisibleCoords()[0] +0.5*cs.ZOOMSTEP*mp.getImageProp()[0]; 
				//miny = cs.getVisibleCoords()[2] +0.5*cs.ZOOMSTEP*mp.getImageProp()[1]; 
				//minx = (MouseXCoord-cs.getVisibleCoords()[0])*(newVisibleW/visibleW)+MouseXCoord;
				//miny = (MouseYCoord-cs.getVisibleCoords()[2])*(newVisibleH/visibleH)+MouseYCoord;
				cs.setVisibleCoords(minx, (minx+newVisibleW), miny, (miny+newVisibleH));
			}
			else {
				//mp.setImageProperties((int) (mp.getImageX() + (0.5 * changeW)),
				//		(int) (mp.getImageY() + (0.5 * changeH)), (int) (mp.getImageWidth() - changeW),
				//		(int) (mp.getImageHeight() - changeH));
				newVisibleW = visibleW+cs.ZOOMSTEP*mp.getImageProp()[0];
				newVisibleH = visibleH+cs.ZOOMSTEP*mp.getImageProp()[1];
				minx = cs.getVisibleCoords()[0] - (cs.ZOOMSTEP*mp.getImageProp()[0]*((MouseXCoord-cs.getVisibleCoords()[0])/visibleW));
				miny = cs.getVisibleCoords()[1] - (cs.ZOOMSTEP*mp.getImageProp()[1]*((MouseYCoord-cs.getVisibleCoords()[2])/visibleH));
				//minx = cs.getVisibleCoords()[0] -0.5*cs.ZOOMSTEP*mp.getImageProp()[0]; 
				//miny = cs.getVisibleCoords()[2] -0.5*cs.ZOOMSTEP*mp.getImageProp()[1];
				//minx = (MouseXCoord-cs.getVisibleCoords()[0])*(newVisibleW/visibleW)+MouseXCoord;
				//miny = (MouseYCoord-cs.getVisibleCoords()[2])*(newVisibleH/visibleH)+MouseYCoord;
				cs.setVisibleCoords(minx, (minx+newVisibleW), miny, (miny+newVisibleH));			
			}
		}
		System.out.println("scroll at " + x + " " + y);
		
	}

	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	public void mouseDragged(MouseEvent e) {
		int dx = e.getX() - x;
		int dy = e.getY() - y;
		double dxCorrected = - dx / mp.getViewSize().getWidth(); // Compute the corrected change in x and y as a ratio
																// movement of the mouse compared to the size of the
																// real-view.
		double dyCorrected = - dy / mp.getViewSize().getHeight();
		x += dx;
		y += dy;
		
		cs.setVisibleCoords(cs.getVisibleCoords()[0] + (cs.MAXX * dxCorrected),
				cs.getVisibleCoords()[1] + (cs.MAXX * dxCorrected), cs.getVisibleCoords()[2] + (cs.MAXY * dyCorrected),
				cs.getVisibleCoords()[3] + (cs.MAXY * dyCorrected));
		}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Used to press a node.
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}