import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class View {

	public View() {
		JFrame frame = new JFrame();
		frame.add(new JLabel(new ImageIcon("src/sy.png")));
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Shows the current game
	 * 
	 * @param game
	 *            the game
	 */
	public void showGameInfo(Game game) {
		/*
		 * String returnval =
		 * "--------------------\nAll stops:\n--------------------\n\n"; for (Stop s :
		 * game.getStops()) { returnval = returnval.concat(s.info()); }
		 */
		String returnval = "";
		returnval = returnval.concat("--------------------\nDetective locations:\n--------------------\n\n");
		for (Detective d : game.getDetectives()) {
			returnval = returnval.concat("Detective ");
			returnval = returnval.concat(Integer.toString(d.getId()));
			returnval = returnval.concat(":\n");
			returnval = returnval.concat(d.getLocation().info());
		}
		returnval = returnval.concat("--------------------\nRunner location:\n--------------------\n\n");
		returnval = returnval.concat(game.getRunner().getLocation().info());

		returnval = returnval.concat("Possible runner positions: \n");
		for (Stop s : game.getRunnerLocations()) {
			returnval = returnval.concat(Integer.toString(s.getNumber()));
			returnval = returnval.concat(", ");
		}
		returnval = returnval.concat("\n");

		System.out.println(returnval);
	}
}
