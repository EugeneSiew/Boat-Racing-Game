import java.util.Random;

public class Dice {
	// Attributes
	private int value;
	Random r = new Random();
	// Constructor
	public Dice() {
		
	}
	
	// Setters/Getters
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	// Other Methods
	// Roll dice
	public int roll() {
		// Set dice value
		setValue(r.nextInt(6) + 1);
		System.out.println("=============");
		System.out.printf("You have rolled %d. Ship sails forward by %d.\n", getValue(), getValue());
		return getValue();
	}
	
	
	
}
