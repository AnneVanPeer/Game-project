import java.util.ArrayList;

public class Game {

	ArrayList<Stop> stops = new ArrayList<Stop>();
	ArrayList<Detective> detectives = new ArrayList<Detective>();
	Runner runner;
	boolean playing;
	ArrayList<Stop> runnerLocations = new ArrayList<Stop>();

	public ArrayList<Stop> getRunnerLocations() {
		return runnerLocations;
	}

	public void setRunnerLocations(ArrayList<Stop> runnerLocations) {
		this.runnerLocations = runnerLocations;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public ArrayList<Stop> getStops() {
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
