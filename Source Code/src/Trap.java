
public class Trap extends RiverObject {
	// Constructor
	public Trap(int pos, int s) {
		super(pos, s);
		setSymbol('#');
	}

	// toString method
	@Override
	public String toString() {
		return String.format("Oh no! You hit a trap :( Move back %s steps\n", super.toString());
	}
}
