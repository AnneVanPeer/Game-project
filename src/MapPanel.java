import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapPanel extends JPanel{
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private CoordSys coordSys;
	private BufferedImage image;
	private Dimension viewSize;
	private double viewRatio;
	private int x = 0;
	private int y = 0;
	private int w = 0;
	private int h = 0;

	public MapPanel(Game game) {
		this.setBackground(Color.GREEN);
		viewSize = new Dimension((int)screenSize.getWidth(), (int)(screenSize.getHeight()*.65));
		viewRatio = viewSize.getWidth() / viewSize.getHeight();
		this.setPreferredSize(viewSize);
		//this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));
        
		fillPanel(game);
	}

	private void fillPanel(Game game) {
		try {
			image = ImageIO.read(new File("src/node.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.w = image.getWidth();
		this.h = image.getHeight();
		coordSys = new CoordSys(this, game);
		MyMouseListener mml = new MyMouseListener(this, coordSys);
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
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g);
	    g2D.drawImage(image, x, y, w, h, this);  
    }
	
	public void setImageProperties(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		repaint();
	}
	
	public Dimension getViewSize() {
		return this.getSize();
	}
	
	public Image getImageComp() {
		return image;
	}

	public int getImageY() {
		return y;
	}

	public int getImageX() {
		return x;
	}
	
	public int getImageWidth() {
		return w;
	}

	public int getImageHeight() {
		return h;
	}
	
	public double getViewRatio() {
		return viewRatio;
	}
	
	/**
	 * 
	 * @return width, height
	 */
	public int[] getImageProp() {
		int[] A = new int[] {image.getWidth(), image.getHeight()};
		return A;
	}
}
