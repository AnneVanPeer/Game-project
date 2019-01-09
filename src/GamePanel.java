import java.awt.Color;
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

	public GamePanel() {
		this.setBackground(new Color(139,69,19));
		this.setBorder(BorderFactory.createMatteBorder(7, 10, 7, 7, new Color(212,175,55)));
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("src/personasCrop.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel picLabel = new JLabel(new ImageIcon(new ImageIcon("src/personasCrop.png").getImage().getScaledInstance(20, 20, myPicture.SCALE_DEFAULT)));
		add(picLabel);
		
	}

}
