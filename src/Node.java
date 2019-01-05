import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Node {

	private int number;
	private Map<String, ArrayList<Node>> connections = new HashMap<String, ArrayList<Node>>();
	private int[] location;

	public Node(int n, int x, int y) {
		this.number = n;
		initializeConnectionLists();
		location = new int[]{x, y};
	}

	/**
	 * Initializes the connection lists for this node; three lists, one for every
	 * type of route
	 */
	private void initializeConnectionLists() {
		connections.put("T", new ArrayList<Node>());
		connections.put("B", new ArrayList<Node>());
		connections.put("U", new ArrayList<Node>());
	}

	/**
	 * Finds the shortest route from this stop to the goal stop
	 * 
	 * @param goal
	 *            the goal stop
	 * @return list of shortest routes from this stop to the goal stop
	 */
	public ArrayList<ArrayList<Node>> pathTo(Node goal) {
		return pathTo(goal, 1000);
	}

	/**
	 * Finds the shortest route from this stop to the goal stop; considers n random
	 * paths
	 * 
	 * @param goal
	 *            the goal stop
	 * @param n
	 *            number of random paths tried
	 * @return list of shortest routes from this stop to the goal stop
	 */
	public ArrayList<ArrayList<Node>> pathTo(Node goal, int n) {
		ArrayList<ArrayList<Node>> routes = new ArrayList<ArrayList<Node>>();
		while (routes.size() < n) {
			ArrayList<Node> route = new ArrayList<Node>();
			route.add(this);
			routes.add(pathTo(goal, route));
		}
		int minLength = 999;
		ArrayList<ArrayList<Node>> shortestRoutes = new ArrayList<ArrayList<Node>>();
		for (ArrayList<Node> route : routes) {
			if (route.size() < minLength && route.size() > 0) {
				shortestRoutes.clear();
				shortestRoutes.add(route);
				minLength = route.size();
			}
			if (route.size() == minLength && route.size() > 0 && !inList(route, shortestRoutes)) {
				shortestRoutes.add(route);
			}
		}
		return shortestRoutes;
	}

	/**
	 * Checks if route is in shortest routes
	 * 
	 * @param route
	 *            the route
	 * @param shortestRoutes
	 *            the shortest routes
	 * @return is route in shortest routes
	 */
	private boolean inList(ArrayList<Node> route, ArrayList<ArrayList<Node>> shortestRoutes) {
		for (ArrayList<Node> routeInList : shortestRoutes) {
			if (routeInList.equals(route)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the stop is on the current route already
	 * 
	 * @param route
	 *            the route
	 * @param s
	 *            the stop
	 * @return is stop in routes
	 */
	private boolean onRoute(ArrayList<Node> route, Node s) {
		for (Node st : route) {
			if (st.equals(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds a random route to goal
	 * 
	 * @param goal
	 *            the goal
	 * @param route
	 *            the route
	 * @return
	 */
	private ArrayList<Node> pathTo(Node goal, ArrayList<Node> route) {
		ArrayList<Node> allConnections = route.get(route.size() - 1).getAllConnections();
		for (Node s : allConnections) {
			if (!onRoute(route, s)) {
				if (s == goal) {
					route.add(s);
					return route;
				} else {
					route.add(s);
					return pathTo(goal, route);
				}
			}
		}
		return new ArrayList<Node>();
	}

	/**
	 * Gets all stops that are connected in a way to this stop in a random oreder
	 * 
	 * @return all stops connected to this one in a random oreder
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Node> getAllConnections() {
		ArrayList<Node> tConnections = (ArrayList<Node>) this.getConnections().get("T").clone();
		ArrayList<Node> bConnections = (ArrayList<Node>) this.getConnections().get("B").clone();
		ArrayList<Node> uConnections = (ArrayList<Node>) this.getConnections().get("U").clone();
		ArrayList<Node> allConnections = new ArrayList<Node>();
		allConnections.addAll(tConnections);
		allConnections.addAll(bConnections);
		allConnections.addAll(uConnections);
		Collections.shuffle(allConnections);
		return allConnections;
	}

	/**
	 * info about this stop's connections
	 * 
	 * @return info about this stop's connections
	 */
	public String info() {
		String returnval = "----------\nStop number " + number + "\nT: ";
		for (int i = 0; i < connections.get("T").size(); i++) {
			returnval = returnval.concat(Integer.toString(connections.get("T").get(i).getNumber()));
			returnval = returnval.concat(", ");
		}
		returnval = returnval.concat("\nB: ");
		for (int i = 0; i < connections.get("B").size(); i++) {
			returnval = returnval.concat(Integer.toString(connections.get("B").get(i).getNumber()));
			returnval = returnval.concat(", ");
		}
		returnval = returnval.concat("\nM: ");
		for (int i = 0; i < connections.get("U").size(); i++) {
			returnval = returnval.concat(Integer.toString(connections.get("U").get(i).getNumber()));
			returnval = returnval.concat(", ");
		}
		returnval = returnval.concat("\n\n");
		return returnval;
	}

	public String toString() {
		return Integer.toString(number);
	}

	/**
	 * Adds a stop to the list of stops connected to this stop
	 * 
	 * @param key
	 *            how is the stop connected
	 * @param stop
	 *            which stop
	 */
	public void addConnection(String key, Node stop) {
		ArrayList<Node> list = connections.get(key);
		list.add(stop);
		connections.put(key, list);
	}

	ArrayList<Node> getSpecificConnections(String type) {
		@SuppressWarnings("unchecked")
		ArrayList<Node> connections = (ArrayList<Node>) this.getConnections().get(type).clone();
		return connections;
	}
		
	public int getNumber() {
		return number;
	}

	public Map<String, ArrayList<Node>> getConnections() {
		return connections;
	}
	
	public int[] getLocation() {
		return location;
	}
}