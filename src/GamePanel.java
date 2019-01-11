import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage image;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public GamePanel() {
		this.setBackground(new Color(36,26,18));
		this.setBorder(BorderFactory.createMatteBorder(7, 10, 7, 7, new Color(189,133,57)));
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("src/personasCrop.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel picLabel = new JLabel(new ImageIcon(new ImageIcon("src/personasCrop.png").getImage().getScaledInstance(350, 200, myPicture.SCALE_DEFAULT)));
		add(picLabel);
		
	}

}
