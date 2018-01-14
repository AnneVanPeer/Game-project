import java.util.ArrayList;

public class CoordSys {
	
	public final double MINX = -1000;
	public final double MINY = -1000;
	public final double MAXX = 1000;
	public final double MAXY = 1000;
	private ArrayList<Node> nodes  = new ArrayList<>();
	private MapPanel mp;

	public CoordSys(MapPanel mp, Game game) {
		this.mp = mp;
		nodes = game.getNodes();
	}

}
