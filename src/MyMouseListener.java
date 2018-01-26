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

		if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
			double changeW = 20.0; // mp.getImageWidth()*scaleSpeed;
			double changeH = 20.0; // mp.getImageHeight()*scaleSpeed;
			if (e.getWheelRotation() < 0)
				mp.setImageProperties((int) (mp.getImageX() - (0.5 * changeW)),
						(int) (mp.getImageY() - (0.5 * changeH)), (int) (mp.getImageWidth() + changeW),
						(int) (mp.getImageHeight() + changeH));
			else
				mp.setImageProperties((int) (mp.getImageX() + (0.5 * changeW)),
						(int) (mp.getImageY() + (0.5 * changeH)), (int) (mp.getImageWidth() - changeW),
						(int) (mp.getImageHeight() - changeH));
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
		double dxCorrected = dx / mp.getViewSize().getWidth(); // Compute the corrected change in x and y as a ratio
																// movement of the mouse compared to the size of the
																// real-view.
		double dyCorrected = dy / mp.getViewSize().getHeight();
		//mp.setImageProperties(mp.getImageX() + dx, mp.getImageY() + dy, mp.getImageWidth(), mp.getImageHeight());
		x += dx;
		y += dy;
		double visibleWidth = cs.getVisibleCoords()[1] - cs.getVisibleCoords()[0];
		double visibleHeight = cs.getVisibleCoords()[3] - cs.getVisibleCoords()[2];
		
		//System.out.print("dx corrected: " + dxCorrected +" dy corrected: " + dyCorrected);
		//System.out.printf("dx corrected: %f dy corrected: %f width: %f height: %f \n", dxCorrected, dyCorrected, visibleWidth, visibleHeight);
		//System.out.printf("height*dy: %f current miny: %f current maxy: %f new miny: %f new maxy: %f \n", (visibleHeight * dyCorrected), cs.getVisibleCoords()[2], cs.getVisibleCoords()[3], cs.getVisibleCoords()[2] + (visibleHeight * dyCorrected), cs.getVisibleCoords()[3] + (visibleHeight * dyCorrected));
		
		cs.setVisibleCoords(cs.getVisibleCoords()[0] + (visibleWidth * dxCorrected),
				cs.getVisibleCoords()[1] + (visibleWidth * dxCorrected), cs.getVisibleCoords()[2] + (visibleHeight * dyCorrected),
				cs.getVisibleCoords()[3] + (visibleHeight * dyCorrected));
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
