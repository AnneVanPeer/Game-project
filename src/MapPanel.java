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
	private final double panelSizeRatio = 3;
	private int imageX = 0;
	private int imageY = 0;
	private int imageW = 0;
	private int imageH = 0;

	public MapPanel() {
		this.setBackground(Color.WHITE);
		viewSize = new Dimension((int)(screenSize.getWidth()*(2/panelSizeRatio)), (int)screenSize.getHeight());
		this.setPreferredSize(viewSize);
        
		fillPanel();
	}

	private void fillPanel() {
		try {
			image = ImageIO.read(new File("src/europe.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.imageW = image.getWidth();
		this.imageH = image.getHeight();
		coordSys = new CoordSys(this);
		MyMouseListener mml = new MyMouseListener(this, coordSys);
		this.addMouseListener(mml);
		this.addMouseMotionListener(mml);
		this.addMouseWheelListener(mml);
		//add borders
		//add listeners for mouse (uses the coordsys).
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g);
	    g2D.drawImage(image, imageX, imageY, imageW, imageH, this);  
    }
	
	public void setImageProperties(int x, int y, int w, int h) {
		this.imageX = x;
		this.imageY = y;
		this.imageW = w;
		this.imageH = h;
		repaint();
	}
	
	public Dimension getViewSize() {
		return this.getSize();
	}
	
	public Image getImageComp() {
		return image;
	}

	public int getImageY() {
		return imageY;
	}

	public int getImageX() {
		return imageX;
	}
	
	public int getImageWidth() {
		return imageW;
	}

	public int getImageHeight() {
		return imageH;
	}
	
	public double getPanelSizeRatio() {
		return panelSizeRatio;
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
