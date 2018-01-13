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
			game.getStops().add(new Stop(i));
		}

		for (int i = 0; i < numD; i++) {
			game.getDetectives()
					.add(new Detective(i, game.getStops().get(new Random().nextInt(numS / numD) + i * (numS / numD))));
		}

		Stop runnerLocation;
		do {
			runnerLocation = game.getStops().get(new Random().nextInt(numS));
		} while (inList(runnerLocation, game.getDetectives()));

		game.setRunner(new Runner(runnerLocation));

		game.setPlaying(true);

		game.setRunnerLocations((ArrayList<Stop>) game.getStops().clone());

		new Connecter(game).connect();
	}

	/**
	 * Updates the view
	 */
	public void updateView() {
		view.showGameInfo(game);
	}

	/**
	 * Checks if the runner is standing on the same stop as a detective
	 * 
	 * @param runnerLocation
	 *            current location of the runner
	 * @param detectives
	 *            list of the detectives
	 * @return true if the location of the runner is the same as the location of a
	 *         detective
	 */
	private boolean inList(Stop runnerLocation, ArrayList<Detective> detectives) {
		ArrayList<Stop> detectiveLocations = new ArrayList<Stop>();
		for (Detective d : game.getDetectives()) {
			detectiveLocations.add(d.getLocation());
		}
		for (Stop d : detectiveLocations) {
			if (runnerLocation.equals(d)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the runner has been caught, updates the playing state accordingly
	 */
	public void updatePlaying() {
		if (inList(game.getRunner().getLocation(), game.getDetectives())) {
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
		game.getRunner().setLocation(game.getStops().get(n));
		this.updateRunnerLocations(turn + 1);
	}

	/**
	 * Moves the detectives across the map
	 */
	public void moveDetectives() {
		Stop goal = game.getRunner().getLocation();
		for (Detective d : game.getDetectives()) {
			ArrayList<ArrayList<Stop>> routes = d.getLocation().pathTo(goal);
			ArrayList<Stop> chosenRoute = routes.get(new Random().nextInt(routes.size()));
			boolean free = true;
			for (Detective det : game.getDetectives()) {
				if (det.getLocation().equals(chosenRoute.get(1))) {
					free = false;
				}
			}
			if (free) {
				d.setLocation(chosenRoute.get(1));
			} else {
				Stop destination;
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
			ArrayList<Stop> locations = new ArrayList<Stop>();
			locations.add(game.getRunner().getLocation());
			game.setRunnerLocations(locations);
		} else {
			ArrayList<Stop> locations = new ArrayList<Stop>();
			for (Stop s : game.getRunnerLocations()) {
				System.out.println(s);
				ArrayList<Stop> connectionsS = s.getAllConnections();
				for (Stop cs : connectionsS) {
					locations.add(cs);
				}
			}
			game.setRunnerLocations((ArrayList<Stop>) locations.stream().distinct().collect(Collectors.toList()));
		}
	}
}
