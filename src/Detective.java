
public class Detective {

	private int id;
	private Node location;

	public Detective(int id, Node location) {
		this.id = id;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public Node getLocation() {
		return location;
	}

	public void setLocation(Node location) {
		this.location = location;
	}
}