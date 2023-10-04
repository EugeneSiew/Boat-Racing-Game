
public class Current extends RiverObject {
	// Constructor
	public Current(int pos, int s) {
		super(pos, s);
		setSymbol('C');
	}

	// toString method
	@Override
	public String toString() {
		return String.format("Yay! You hit a current ^_^ Move forward %s steps\n", super.toString());
	}

}
