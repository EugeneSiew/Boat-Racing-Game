import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameClass {
	private Scanner input = new Scanner(System.in);
	
	// Print welcome message
	public void welcomeMsg () {
		System.out.println("\n    Welcome to the Boat Racing Game!\n");
		System.out.println("       "		
				+ "         ,/|\\,\r\n"
				+ "                ,/' |\\ \\,\r\n"
				+ "              ,/'   | |  \\\r\n"
				+ "            ,/'     | |   |\r\n"
				+ "          ,/'       |/    |\r\n"
				+ "        ,/__________|-----' ,\r\n"
				+ "       ___.....-----''-----/\r\n"
				+ "       \\                  /\r\n"
				+ "   ~~-~^~^~`~^~`~^^~^~-^~^~^~-~^~^\r\n"
				+ "     ~-^~^-`~^~-^~^`^~^-^~^`^~^-~^");
	}
	
	// Print rules of the game
	public void rules() {
		System.out.println("Here are the rules:");
		System.out.println("1. This is a 2 players game, so gather up a partner!");
		System.out.println("2. The dice is used to control how far your boat sails. \n"
				+ "    On every turn, hit [ENTER] to roll the dice. How far your boat sails depends on the dice value.");
		System.out.println("3. You can choose from 2 modes. The OG classic mode: 100 track, 40 obstacle and a max strength of 7. or "
				+ "    The Turbo Mode: 70 track, 40 obstacle and a max strength of 10.");
		System.out.println("4. There will be traps and currents scattered around the river.");
		System.out.println("5. If you hit a Current ('C'), your boat will be pushed forward based on the current strength.");
		System.out.println("6. If you hit a Trap ('#'), your boat will be pushed backward based on the trap strength.");
		System.out.println("7. The first player to reach the end of the track wins the game! If you are skillful enough, you might get into the top 5 leaderboard!");
		System.out.println("8. The top 5 leaderboard will be displayed before each game.");

	}
	
	// Pre game routine
	public void preGame() {		
		// Ask user if they want to read the rules
		System.out.println("Enter 1 to jump right into the game.\n");
		System.out.println("Enter 2 to read up on the rules.\n");
		int choice = input.nextInt();
			
		// Start game 
		if (choice == 1) {
			startGame();
		}
			
		else if (choice == 2) {
			rules();
			startGame();
		}
			
		else {
			System.out.println("Please enter 1 or 2 only.");
		}
	} 
		
	// Start the game
	public void startGame() {
		// Create player array list and dice object
		ArrayList<Player> players = new ArrayList<Player>();
		Dice dice = new Dice();
		
		// Choose Mode
		int[] values = chooseMode();
		// Set river length, river object strength and no. of river object
		River river = new River(values[0], values[1], 40);
		
		// Print top 5 score
		Score p = new Score();
		p.showScore(river.getRiverLength());
		
		// Start game 
		// Get players' name
		Scanner i = new Scanner(System.in);
		System.out.println("\nPlayer 1, Enter your name: ");
		
		String p1 = i.nextLine();
		players.add(new Player(p1));
		
		System.out.println("\nPlayer 2, Enter your name: ");
		String p2 = i.nextLine();
		players.add(new Player(p2));
		
		while ((players.get(0).getBoat().getBoatPosition() < river.getRiverLength()) 
				&& (players.get(1).getBoat().getBoatPosition() < river.getRiverLength()) ) {
			
			for (Player player: players) {
				if (player.getBoat().getBoatPosition() < river.getRiverLength()) {
					System.out.printf("\nPlayer %s, it's your turn!\n", player.getName());
					System.out.println("\nIf you're ready, press [ENTER] to sail.");
					
					// Boat sail
					Scanner ent = new Scanner(System.in);
					if (ent.hasNextLine()) {
						player.getBoat().sail(dice.roll());
						player.setTurn(player.getTurn() + 1);
						sailing(player, river);
					}
				}
				
				// Display river
				river.printRiver();
				river.displayRiver();
				System.out.print("\n");
				displayBoat(player, river);
				river.displayRiver();
				
				// Print the boat's position after moving
				System.out.print(player);
				
				// Add the winning player to the Scoreboard
				if (player.getBoat().getBoatPosition() >= river.getRiverLength()) {
					p.setName(player.getName());
					p.setScore(player.getTurn());
					break;
				}
			}
		}
		
		// Store the winner's score and print the score board again
		if (river.getRiverLength() == 100) {
			p.addScore(p.getName(), p.getScore(), river.getRiverLength());
			p.showScore(river.getRiverLength());
		}
		else if (river.getRiverLength() == 70) {
			p.addScore(p.getName(), p.getScore(), river.getRiverLength());
			p.showScore(river.getRiverLength());
		}
		
		// Inform the player who won congrats
		System.out.printf("Congrats %s, you have conquered the boat racing game!", p.getName());
		System.out.println("       ~; \\r\\n "
				+ "               ./|\\.\r\n"
				+ "             ./ /| `\\.\r\n"
				+ "            /  | |   `\\.\r\n"
				+ "           |   | |     `\\.\r\n"
				+ "           |    \\|       `\\.\r\n"
				+ "         .  `----|__________\\.\r\n"
				+ "          \\-----''----.....___\r\n"
				+ "           \\               \"\"/\r\n"
				+ "      ~^~^~^~^~^`~^~^`^~^~^`^~^~^\r\n"
				+ "       ~^~^~`~~^~^`~^~^~`~~^~^~\n");
		
	}
	
	
	// Set mode
	public int[] chooseMode() {
		int[] settings = new int[2];
		int rivLeng;
		int roStrength;
		Scanner m = new Scanner(System.in);
		System.out.println("Choose your mode (1 for Classic, 2 for Turbo): ");
		int mode = m.nextInt();
		
		// Classic
		if (mode == 1) {
			rivLeng = 100;
			roStrength = 7;
			settings[0] = rivLeng;
			settings[1] = roStrength;
			return settings;
		}
		// Turbo
		else if (mode == 2) {
			rivLeng = 70;
			roStrength = 10;
			settings[0] = rivLeng;
			settings[1] = roStrength;
			return settings;
		}
		
		else {
			System.out.print("Enter 1 or 2 only.");
			chooseMode();
		}
		
		return settings;
		
	}
	
	// Control boat movement
	public void sailing (Player pl, River r) {
		int i = 0;
		while (i < r.getRiverObjects().size()) {
			RiverObject ro = r.getRiverObjects().get(i);
			i++;
			
			if (pl.getBoat().getBoatPosition() == ro.getObjectPosition()) {
				// If current, push forward; if trap, push backward
				if (ro instanceof Current) {
					System.out.println(ro);
					pl.getBoat().setBoatPosition(pl.getBoat().getBoatPosition() + ro.getStrength()); 
					// Remove the current from the list
					r.getRiverObjects().remove(ro);
				}
				
				// Check for trap
				if (ro instanceof Trap) {
					System.out.println(ro);
					pl.getBoat().setBoatPosition(pl.getBoat().getBoatPosition() - ro.getStrength()); 
					// Remove the current from the list
					r.getRiverObjects().remove(ro);
				}
			}
		}
		
		// Check the boat to see if they are out of the river
		if (pl.getBoat().getBoatPosition() < 0) {
			pl.getBoat().setBoatPosition(0);
		}
		
		else if (pl.getBoat().getBoatPosition() > r.getRiverLength()) {
			pl.getBoat().setBoatPosition(r.getRiverLength());
		}
	}
	
	// Print the boat in the river 
	public void displayBoat(Player p, River r) {
		// Throw away the river objects that have been passed by
		for (int i = 0; i < p.getBoat().getBoatPosition(); i++) {
			
			for (RiverObject ro: r.getRiverObjects()) {
				if (ro.getObjectPosition() == i) {
					if (ro instanceof Trap) {
						System.out.print(" ");
					}
					else if (ro instanceof Current) {
						System.out.print(" ");
					}
				}
			}
			System.out.print("    ");
		}
		
		// Print the boat
		p.getBoat().printBoat();
	}
	
}
