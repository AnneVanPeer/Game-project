public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		View view = new View();
		Controller controller = new Controller(game, view);
		controller.init(15, 1);
		controller.updateView();

		int turn = 0;
		while (game.isPlaying()) {
			controller.moveRunner(turn + 1);
			controller.updateView();
			controller.moveDetectives();
			controller.updateView();
			controller.updatePlaying();
			turn++;
		}
	}
}