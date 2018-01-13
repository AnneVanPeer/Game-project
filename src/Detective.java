
public class Detective {

	private int id;
	private Stop location;

	public Detective(int id, Stop location) {
		this.id = id;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public Stop getLocation() {
		return location;
	}

	public void setLocation(Stop location) {
		this.location = location;
	}
}