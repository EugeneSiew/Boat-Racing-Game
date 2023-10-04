
import java.util.ArrayList;
import java.util.Random;

public class RiverObject {
	// Attributes
	private char symbol;
	private int objectPosition;
	private int strength;

	Random r = new Random();

	// Constructor 
	// Receive an integer n to determine the position of the river object
	public RiverObject(int pos, int s) {
		this.objectPosition = pos;
		this.strength = randomStrength(s);
	}

	// Setters/Getters
	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	
	public int getObjectPosition() {
		return objectPosition;
	}

	
	public void setObjectPosition(int objectPosition) {
		this.objectPosition = objectPosition;
	}

	
	public int getStrength() {
		return strength;
	}

	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	// Other Methods
	
	// Return random strength
	public int randomStrength(int s) {
		return (r.nextInt(s) + 1);
	}

	//toString 
	@Override
	public String toString() {
		return String.format("%d", getStrength());
	}
	
	
}
