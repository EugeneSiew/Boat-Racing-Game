
public class Boat {
	private int boatPosition = 0;
	
	public Boat() {
		
	}
	
	public int getBoatPosition() {
		return boatPosition;
	}

	public void setBoatPosition(int boatPosition) {
		this.boatPosition = boatPosition;
	}
	
	public void sail(int d) {
		System.out.printf("Your boat is peacully sailing forward %d steps\n", d);
		boatPosition = boatPosition + d;
		System.out.println("\n");
	}

	public void printBoat() {
		System.out.println("B");
	}
}