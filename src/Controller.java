import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Controller {

	private Game game;
	private View view;

	public Controller(Game game, View view) {
		this.game = game;
		this.view = view;
		new Window(game);
	}

	/**
	 * Initialises the game; adds stops and connects them, adds detectives and
	 * places them, adds a runner and places him
	 * 
	 * @param numS
	 *            number of stops
	 * @param numD
	 *            number of detectives
	 */
	@SuppressWarnings("unchecked")
	public void init(int numS, int numD) {
		for (int i = 0; i < numS; i++) {
			game.getNodes().add(new Node(i, i, i)); //TODO:last two variabes as dummy for the node location on the coordinate system.
		}

		for (int i = 0; i < numD; i++) {
			game.getDetectives()
					.add(new Detective(i, game.getNodes().get(new Random().nextInt(numS / numD) + i * (numS / numD))));
		}

		Node runnerLocation;
		do {
			runnerLocation = game.getNodes().get(new Random().nextInt(numS));
		} while (inList(runnerLocation, game.getDetectiveLocations()));

		game.setRunner(new Runner(runnerLocation));

		game.setPlaying(true);

		game.setRunnerLocations((ArrayList<Node>) game.getNodes().clone());

		new Connecter(game).connect();
	}

	/**
	 * Updates the view
	 */
	public void updateView() {
		view.showGameInfo(game);
	}

	/**
	 * Checks if a locaon is in a list of locations
	 * 
	 * @param Location
	 *            the location
	 * @param Locations
	 *            list of the locations
	 * @return true if the location is in the list of locations
	 */
	private boolean inList(Node Location, ArrayList<Node> Locations) {
		for (Node l : Locations) {
			if (Location.equals(l)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the runner has been caught, updates the playing state accordingly
	 */
	public void updatePlaying() {
		if (inList(game.getRunner().getLocation(), game.getDetectiveLocations())) {
			game.setPlaying(false);
			System.out.println("\n\n----------\nRunner caught!\n----------");
		}
	}

	/**
	 * Asks for a new location for the runner and updates the runner's location
	 * accordingly
	 * 
	 * @param turn
	 *            current turn
	 */
	public void moveRunner(int turn) {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("Move to: ");
		int n;
		n = reader.nextInt();
		Node prevLoc = game.getRunner().getLocation();
		// Only valid if next to current location and not at a detective location
		while(!inList(game.getNodes().get(n), game.getRunner().getLocation().getAllConnections()) && !inList(game.getNodes().get(n), game.getDetectiveLocations())) {
			System.out.println("Invalid location, choose again: ");
			n = reader.nextInt();
		}
		Node nextLoc = game.getNodes().get(n);
		game.getRunner().setLocation(nextLoc);
		// updates the runner's path
		ArrayList<String> rp = game.getRunnerPath();
		if (inList(nextLoc, prevLoc.getSpecificConnections("T"))) {
			rp.add("T");
		} else if (inList(nextLoc, prevLoc.getSpecificConnections("B"))) {
			rp.add("B");
		} else {
			rp.add("U");
		}
		game.setRunnerPath(rp);
		this.updateRunnerLocations(turn + 1);
	}

	/**
	 * Moves the detectives across the map
	 */
	public void moveDetectives() {
		Node goal = game.getRunnerLocations().get(new Random().nextInt(game.getRunnerLocations().size()));
		for (Detective d : game.getDetectives()) {
			ArrayList<ArrayList<Node>> routes = d.getLocation().pathTo(goal);
			ArrayList<Node> chosenRoute = routes.get(new Random().nextInt(routes.size()));
			boolean free = true;
			for (Detective det : game.getDetectives()) {
				if (det.getLocation().equals(chosenRoute.get(1))) {
					free = false;
				}
			}
			if (free) {
				d.setLocation(chosenRoute.get(1));
			} else {
				Node destination;
				do {
					destination = d.getLocation().getAllConnections()
							.get(new Random().nextInt(d.getLocation().getAllConnections().size()));
				} while (destination.equals(chosenRoute.get(1)));
				d.setLocation(destination);
			}
		}
	}

	/**
	 * Updates the list of locations where the runner can currently be
	 * 
	 * @param turn
	 *            the current turn
	 */
	private void updateRunnerLocations(int turn) {
		if (turn < 3) {
			return;
		}
		if (turn % 3 == 0) {
			ArrayList<Node> possibleRunnerLocations = new ArrayList<Node>();
			possibleRunnerLocations.add(game.getRunner().getLocation());
			possibleRunnerLocations.removeAll(game.getDetectiveLocations());
			game.setRunnerLocations(possibleRunnerLocations);
			game.setRunnerPath(new ArrayList<String>());
		} else {
			ArrayList<Node> locations = new ArrayList<Node>();
			ArrayList<Node> rl = game.getRunnerLocations();
			rl.removeAll(game.getDetectiveLocations());
			for (Node s : rl) {
				System.out.println(s);
				ArrayList<Node> connectionsS = s.getAllConnections();
				for (Node cs : connectionsS) {
					locations.add(cs);
				}
			}
			ArrayList<Node> possibleRunnerLocations = (ArrayList<Node>) locations.stream().distinct().collect(Collectors.toList());
			possibleRunnerLocations.removeAll(game.getDetectiveLocations());
			game.setRunnerLocations(possibleRunnerLocations);
		}
	}
}
