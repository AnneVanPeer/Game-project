//hoii
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

		ArrayList<Stop> stops = game.getStops();

		// 0
		stops.get(0).addConnection("T", stops.get(1));
		stops.get(0).addConnection("T", stops.get(2));
		stops.get(0).addConnection("B", stops.get(3));
		stops.get(0).addConnection("B", stops.get(5));
		stops.get(0).addConnection("U", stops.get(9));
		stops.get(0).addConnection("U", stops.get(14));

		// 1
		stops.get(1).addConnection("T", stops.get(0));
		stops.get(1).addConnection("T", stops.get(3));
		stops.get(1).addConnection("T", stops.get(4));

		// 2
		stops.get(2).addConnection("T", stops.get(0));
		stops.get(2).addConnection("T", stops.get(4));
		stops.get(2).addConnection("T", stops.get(5));

		// 3
		stops.get(3).addConnection("T", stops.get(1));
		stops.get(3).addConnection("T", stops.get(6));
		stops.get(3).addConnection("T", stops.get(9));
		stops.get(3).addConnection("B", stops.get(0));
		stops.get(3).addConnection("B", stops.get(9));
		stops.get(3).addConnection("B", stops.get(12));

		// 4
		stops.get(4).addConnection("T", stops.get(1));
		stops.get(4).addConnection("T", stops.get(2));
		stops.get(4).addConnection("T", stops.get(7));

		// 5
		stops.get(5).addConnection("T", stops.get(2));
		stops.get(5).addConnection("T", stops.get(8));
		stops.get(5).addConnection("B", stops.get(0));
		stops.get(5).addConnection("B", stops.get(14));

		// 6
		stops.get(6).addConnection("T", stops.get(3));
		stops.get(6).addConnection("T", stops.get(9));
		stops.get(6).addConnection("T", stops.get(10));

		// 7
		stops.get(7).addConnection("T", stops.get(4));
		stops.get(7).addConnection("T", stops.get(8));
		stops.get(7).addConnection("T", stops.get(10));
		stops.get(7).addConnection("T", stops.get(13));

		// 8
		stops.get(8).addConnection("T", stops.get(5));
		stops.get(8).addConnection("T", stops.get(7));
		stops.get(8).addConnection("T", stops.get(14));

		// 9
		stops.get(9).addConnection("T", stops.get(3));
		stops.get(9).addConnection("T", stops.get(6));
		stops.get(9).addConnection("T", stops.get(11));
		stops.get(9).addConnection("B", stops.get(3));
		stops.get(9).addConnection("B", stops.get(12));
		stops.get(9).addConnection("U", stops.get(0));
		stops.get(9).addConnection("U", stops.get(14));

		// 10
		stops.get(10).addConnection("T", stops.get(6));
		stops.get(10).addConnection("T", stops.get(7));
		stops.get(10).addConnection("T", stops.get(12));

		// 11
		stops.get(11).addConnection("T", stops.get(9));
		stops.get(11).addConnection("T", stops.get(12));

		// 12
		stops.get(12).addConnection("T", stops.get(10));
		stops.get(12).addConnection("T", stops.get(11));
		stops.get(12).addConnection("T", stops.get(13));
		stops.get(12).addConnection("B", stops.get(3));
		stops.get(12).addConnection("B", stops.get(9));
		stops.get(12).addConnection("B", stops.get(14));

		// 13
		stops.get(13).addConnection("T", stops.get(7));
		stops.get(13).addConnection("T", stops.get(12));
		stops.get(13).addConnection("T", stops.get(14));

		// 14
		stops.get(14).addConnection("T", stops.get(8));
		stops.get(14).addConnection("T", stops.get(13));
		stops.get(14).addConnection("B", stops.get(5));
		stops.get(14).addConnection("B", stops.get(12));
		stops.get(14).addConnection("U", stops.get(0));
		stops.get(14).addConnection("U", stops.get(9));
	}
}