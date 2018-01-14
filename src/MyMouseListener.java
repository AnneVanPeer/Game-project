import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;

public class MyMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	static final String NEWLINE = System.getProperty("line.separator");
	private MapPanel mp;
	private int x;
    private int y;

	public MyMouseListener(MapPanel mp) {
		this.mp = mp;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {		
		int x = e.getX();
	    int y = e.getY();

	    if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
	    	if(e.getWheelRotation() <0)
	    		mp.setImageSize(true);
	    	else mp.setImageSize(false);
	    }
	    System.out.println("scroll");
    }

    public void mousePressed(MouseEvent e) {
      x = e.getX();
      y = e.getY();
    }

    public void mouseDragged(MouseEvent e) {
      int dx = e.getX() - x;
      int dy = e.getY() - y;

      mp.setImageLocation(mp.getImageX() + dx, mp.getImageY() + dy);

      x += dx;
      y += dy;
    }

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Used to press a node.
	}		

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
