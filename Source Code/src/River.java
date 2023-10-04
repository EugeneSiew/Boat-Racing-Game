import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class River {
	// Attributes
	// Create array to store array objects and their position
	private ArrayList<RiverObject> riverObjects = new ArrayList<RiverObject>();;
	private ArrayList<Integer> positions = new ArrayList<Integer>();
	private int riverLength;
	Random r = new Random();
	
	// Constructor
	// Take n integer as the number of river objects to be created
	public River (int l, int s, int n) {
		
		setRiverLength(l);
		//Count number of currents and traps created
		int cOccurences = 0;
		int tOccurences = 0;
		
		// Create current object
		while (cOccurences < n / 2) {
			// Create current
			int cPosition = createPosition(); 
			
			// Check if current position has been made before
			if (!positions.contains(cPosition)) {
				positions.add(cPosition);
				riverObjects.add(new Current(cPosition, s));
				cOccurences += 1;
			} 
		}
		
		// Create trap object
		while (tOccurences < n / 2) {
			// Create trap
			int tPosition = createPosition(); 

			// Check if trap position has been made before
			if (!positions.contains(tPosition)) {
				positions.add(tPosition);
				riverObjects.add(new Trap(tPosition, s));
				tOccurences += 1;
			}

		}
		
	}
	
	// Setters/Getters
	
	public ArrayList<RiverObject> getRiverObjects() {
		return riverObjects;
	}
	
	public void setRiverObjects(ArrayList<RiverObject> riverObjects) {
		this.riverObjects = riverObjects;
	}
	
	public ArrayList<Integer> getPositions() {
		return positions;
	}
	
	public void setPositions(ArrayList<Integer> positions) {
		this.positions = positions;
	}

	public int getRiverLength() {
		return riverLength;
	}
	
	public void setRiverLength(int riverLength) {
		this.riverLength = riverLength;
	}
	
	// Other Methods
	public int createPosition() {
		return (r.nextInt(riverLength - 1) + 1); 
	}
	
	// Print positions on river
	public void printRiver () {
		
		// Print Start and End
				System.out.print("Start");
				for (int i = 0; i < getRiverLength(); i++) {
					System.out.print("     ");
				}
				System.out.print("    ");
				System.out.print("End\n");
				
				// Print the positions
				for (int i = 0; i <= getRiverLength(); i++) {
					System.out.printf("%3d", i);
					
					// Print currents and traps
					for (RiverObject o: riverObjects) {
						if (o.getObjectPosition() == i) {
							
							if(o instanceof Current) {
								System.out.print(o.getSymbol());
							} 
							
							else if (o instanceof Trap) {
								System.out.print(o.getSymbol());
							}
						}
					}
					
					//Print divider
					System.out.print("|");
				}
				
			}
			
			//Print the river sides
			public void displayRiver () {
				System.out.println("");
				for (int i = 0; i < getRiverLength() + 6; i++) {
					System.out.print("======");
				}
			}
			
			
			//toString
			@Override
			public String toString() {
				return String.format("River [riverLength=%d]", riverLength);
			}
			
		}