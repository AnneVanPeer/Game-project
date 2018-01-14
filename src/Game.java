import java.util.ArrayList;

public class Game {

	ArrayList<Node> nodes = new ArrayList<Node>();
	ArrayList<Detective> detectives = new ArrayList<Detective>();
	Runner runner;
	boolean playing;
	ArrayList<Node> runnerLocations = new ArrayList<Node>();

	public ArrayList<Node> getDetectiveLocations(){
		ArrayList<Node> detectiveLocations = new ArrayList<Node>();
		for (Detective d : this.getDetectives()) {
			detectiveLocations.add(d.getLocation());
		}
		return detectiveLocations;
	}
	
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

	public ArrayList<Node> getNodes() {
		return nodes;
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
