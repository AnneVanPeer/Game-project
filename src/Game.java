import java.util.ArrayList;

public class Game {

	ArrayList<Node> stops = new ArrayList<Node>();
	ArrayList<Detective> detectives = new ArrayList<Detective>();
	Runner runner;
	boolean playing;
	ArrayList<Node> runnerLocations = new ArrayList<Node>();

	public ArrayList<Node> getRunnerLocations() {
		return runnerLocations;
	}

	public void setRunnerLocations(ArrayList<Node> runnerLocations) {
		this.runnerLocations = runnerLocations;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public ArrayList<Node> getStops() {
		return stops;
	}

	public ArrayList<Detective> getDetectives() {
		return detectives;
	}

	public Runner getRunner() {
		return runner;
	}

	public void setRunner(Runner runner) {
		this.runner = runner;
	}
}
