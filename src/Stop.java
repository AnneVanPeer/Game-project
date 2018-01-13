import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Stop {

	private int number;
	private Map<String, ArrayList<Stop>> connections = new HashMap<String, ArrayList<Stop>>();

	public Stop(int n) {
		this.number = n;
		initializeConnectionLists();
	}

	/**
	 * Initializes the connection lists for this node; three lists, one for every
	 * type of route
	 */
	private void initializeConnectionLists() {
		connections.put("T", new ArrayList<Stop>());
		connections.put("B", new ArrayList<Stop>());
		connections.put("U", new ArrayList<Stop>());
	}

	/**
	 * Finds the shortest route from this stop to the goal stop
	 * 
	 * @param goal
	 *            the goal stop
	 * @return list of shortest routes from this stop to the goal stop
	 */
	public ArrayList<ArrayList<Stop>> pathTo(Stop goal) {
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
	public ArrayList<ArrayList<Stop>> pathTo(Stop goal, int n) {
		ArrayList<ArrayList<Stop>> routes = new ArrayList<ArrayList<Stop>>();
		while (routes.size() < n) {
			ArrayList<Stop> route = new ArrayList<Stop>();
			route.add(this);
			routes.add(pathTo(goal, route));
		}
		int minLength = 999;
		ArrayList<ArrayList<Stop>> shortestRoutes = new ArrayList<ArrayList<Stop>>();
		for (ArrayList<Stop> route : routes) {
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
	private boolean inList(ArrayList<Stop> route, ArrayList<ArrayList<Stop>> shortestRoutes) {
		for (ArrayList<Stop> routeInList : shortestRoutes) {
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
	private boolean onRoute(ArrayList<Stop> route, Stop s) {
		for (Stop st : route) {
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
	private ArrayList<Stop> pathTo(Stop goal, ArrayList<Stop> route) {
		ArrayList<Stop> allConnections = route.get(route.size() - 1).getAllConnections();
		for (Stop s : allConnections) {
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
		return new ArrayList<Stop>();
	}

	/**
	 * Gets all stops that are connected in a way to this stop in a random oreder
	 * 
	 * @return all stops connected to this one in a random oreder
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Stop> getAllConnections() {
		ArrayList<Stop> tConnections = (ArrayList<Stop>) this.getConnections().get("T").clone();
		ArrayList<Stop> bConnections = (ArrayList<Stop>) this.getConnections().get("B").clone();
		ArrayList<Stop> uConnections = (ArrayList<Stop>) this.getConnections().get("U").clone();
		ArrayList<Stop> allConnections = new ArrayList<Stop>();
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
	public void addConnection(String key, Stop stop) {
		ArrayList<Stop> list = connections.get(key);
		list.add(stop);
		connections.put(key, list);
	}

	public int getNumber() {
		return number;
	}

	public Map<String, ArrayList<Stop>> getConnections() {
		return connections;
	}
}