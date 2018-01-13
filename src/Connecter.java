
import java.util.ArrayList;

public class Connecter {

	private Game game;

	public Connecter(Game game) {
		this.game = game;
	}

	/**
	 * Creates the structure of the map; connects stops with each other using
	 * different connection types
	 */
	public void connect() {

		ArrayList<Node> nodes = game.getNodes();

		// 0
		nodes.get(0).addConnection("T", nodes.get(1));
		nodes.get(0).addConnection("T", nodes.get(2));
		nodes.get(0).addConnection("B", nodes.get(3));
		nodes.get(0).addConnection("B", nodes.get(5));
		nodes.get(0).addConnection("U", nodes.get(9));
		nodes.get(0).addConnection("U", nodes.get(14));

		// 1
		nodes.get(1).addConnection("T", nodes.get(0));
		nodes.get(1).addConnection("T", nodes.get(3));
		nodes.get(1).addConnection("T", nodes.get(4));

		// 2
		nodes.get(2).addConnection("T", nodes.get(0));
		nodes.get(2).addConnection("T", nodes.get(4));
		nodes.get(2).addConnection("T", nodes.get(5));

		// 3
		nodes.get(3).addConnection("T", nodes.get(1));
		nodes.get(3).addConnection("T", nodes.get(6));
		nodes.get(3).addConnection("T", nodes.get(9));
		nodes.get(3).addConnection("B", nodes.get(0));
		nodes.get(3).addConnection("B", nodes.get(9));
		nodes.get(3).addConnection("B", nodes.get(12));

		// 4
		nodes.get(4).addConnection("T", nodes.get(1));
		nodes.get(4).addConnection("T", nodes.get(2));
		nodes.get(4).addConnection("T", nodes.get(7));

		// 5
		nodes.get(5).addConnection("T", nodes.get(2));
		nodes.get(5).addConnection("T", nodes.get(8));
		nodes.get(5).addConnection("B", nodes.get(0));
		nodes.get(5).addConnection("B", nodes.get(14));

		// 6
		nodes.get(6).addConnection("T", nodes.get(3));
		nodes.get(6).addConnection("T", nodes.get(9));
		nodes.get(6).addConnection("T", nodes.get(10));

		// 7
		nodes.get(7).addConnection("T", nodes.get(4));
		nodes.get(7).addConnection("T", nodes.get(8));
		nodes.get(7).addConnection("T", nodes.get(10));
		nodes.get(7).addConnection("T", nodes.get(13));

		// 8
		nodes.get(8).addConnection("T", nodes.get(5));
		nodes.get(8).addConnection("T", nodes.get(7));
		nodes.get(8).addConnection("T", nodes.get(14));

		// 9
		nodes.get(9).addConnection("T", nodes.get(3));
		nodes.get(9).addConnection("T", nodes.get(6));
		nodes.get(9).addConnection("T", nodes.get(11));
		nodes.get(9).addConnection("B", nodes.get(3));
		nodes.get(9).addConnection("B", nodes.get(12));
		nodes.get(9).addConnection("U", nodes.get(0));
		nodes.get(9).addConnection("U", nodes.get(14));

		// 10
		nodes.get(10).addConnection("T", nodes.get(6));
		nodes.get(10).addConnection("T", nodes.get(7));
		nodes.get(10).addConnection("T", nodes.get(12));

		// 11
		nodes.get(11).addConnection("T", nodes.get(9));
		nodes.get(11).addConnection("T", nodes.get(12));

		// 12
		nodes.get(12).addConnection("T", nodes.get(10));
		nodes.get(12).addConnection("T", nodes.get(11));
		nodes.get(12).addConnection("T", nodes.get(13));
		nodes.get(12).addConnection("B", nodes.get(3));
		nodes.get(12).addConnection("B", nodes.get(9));
		nodes.get(12).addConnection("B", nodes.get(14));

		// 13
		nodes.get(13).addConnection("T", nodes.get(7));
		nodes.get(13).addConnection("T", nodes.get(12));
		nodes.get(13).addConnection("T", nodes.get(14));

		// 14
		nodes.get(14).addConnection("T", nodes.get(8));
		nodes.get(14).addConnection("T", nodes.get(13));
		nodes.get(14).addConnection("B", nodes.get(5));
		nodes.get(14).addConnection("B", nodes.get(12));
		nodes.get(14).addConnection("U", nodes.get(0));
		nodes.get(14).addConnection("U", nodes.get(9));
	}
}