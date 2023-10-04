
public class Player {

	// Attribute
	private String name;
	private int turn = 0;
	private Boat b;

	// Constructor
	public Player() {
		
	}

	public Player(String n) {
		setName(n);
		b = new Boat();
	}

	// Setters/Getters
	public String getName() {
		return name;
	}

	public void setName(String pName) {
		this.name = pName;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turnNumber) {
		this.turn = turnNumber;
	}
	
	public void setBoat(Boat b) {
		this.b = b;
	}
	
	public Boat getBoat() {
		return b;
	}
	
	// Other methods

	// toString method
	public String toString() {
		return String.format("\nPlayer %s's current boat position: %d, turns taken: %d", getName(), b.getBoatPosition(),
				getTurn());
	}

}
