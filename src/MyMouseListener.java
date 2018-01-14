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
	private CoordSys cs;

	public MyMouseListener(CoordSys cs) {
		// TODO Auto-generated constructor stub
		this.cs = cs;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
			//TODO Used to zoom in and out.
        String message;
        int notches = e.getWheelRotation();
        if (notches < 0) {
            message = "Mouse wheel moved UP "
                    + -notches + " notch(es)" + NEWLINE;
        } else {
            message = "Mouse wheel moved DOWN "        
                    + notches + " notch(es)" + NEWLINE;
        }
        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            message += "    Scroll type: WHEEL_UNIT_SCROLL" + NEWLINE;
            message += "    Scroll amount: " + e.getScrollAmount()
            + " unit increments per notch" + NEWLINE;
            message += "    Units to scroll: " + e.getUnitsToScroll()
            + " unit increments" + NEWLINE;
           // message += "    Vertical unit increment: "
            //        + scrollPane.getVerticalScrollBar().getUnitIncrement(1)
            //        + " pixels" + NEWLINE;
        } else { //scroll type == MouseWheelEvent.WHEEL_BLOCK_SCROLL;
            message += "    Scroll type: WHEEL_BLOCK_SCROLL" + NEWLINE;
           // message += "    Vertical block increment: "
            //        + scrollPane.getVerticalScrollBar().getBlockIncrement(1)
            //        + " pixels" + NEWLINE;
        }
        System.out.println(message);
    }

	private int x;

    private int y;

    public void mousePressed(MouseEvent e) {
      x = e.getX();
      y = e.getY();
    }

    public void mouseDragged(MouseEvent e) {

      int dx = e.getX() - x;
      int dy = e.getY() - y;

      cs.setImageLocation(cs.getImageLocation().x + dx, cs.getImageLocation().y + dy);

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
