import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Scanner;

public class Score {
	// Attributes
	private String name;
	private int score;
	private String fileName;

	// Constructors
	public Score () {
		
	}
	
	public Score(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	// Setters/Getters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	// Other Methods
	
	public void addScore(String n, int s, int riverLength) {
		ArrayList<Score> playerScores = new ArrayList<Score>();
		// Store player score in the list
		Score newScore = new Score(n, s);
		playerScores.add(newScore);
		
		// Check if the game mode is classic or turbo
		if (riverLength == 100) {
			setFileName("ClassicLeaderboard.txt");
		}
		else if (riverLength == 70) {
			setFileName("TurboLeaderboard.txt");
		}
		
		// Read file
		try {
			
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			
			// Read the data in the file for sorting
			while(scan.hasNext()) {
				String pName = scan.next();
				String pScore = scan.next();
				playerScores.add(new Score(pName, Integer.parseInt(pScore)));
			}
			
			if (scan != null) {
				scan.close();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Error occured.");
			e.printStackTrace();
			
		} catch (IOException e) {
			System.out.println("Error occured.");
			e.printStackTrace();		}
		
		
		// Sort players score
		Comparator<Score> compScore = Comparator.comparing(Score::getScore);
		Collections.sort(playerScores, compScore);		
		
		
		// Delete scores after 5 records
		for (int i = 0; i < playerScores.size(); i++) {
			if (playerScores.size() > 5) {
				playerScores.remove(5);
			}
		}
		
		// Inform user they made it into top 5
		if (playerScores.contains(newScore)) {
			System.out.println("Congratulations! You made it into the top 5!");
		}
		else {
			System.out.println("You didn't make it into the top 5. Better luck next time!");
		}
		
		// Write the data to the file
		try {
			
			FileWriter fw = new FileWriter(fileName);

			for (Score sc: playerScores) {
				fw.write(sc.toString());
			}
			
			fw.close();
			
		} 
		catch (IOException e) {
			System.out.println("An error occured.");
			e.printStackTrace();
		}
		
		
	}
	
	// Print the top 5 score 
	public void showScore(int riverLength) {
		String mode = "";
		// Check if the game mode is classic or turbo
		if (riverLength == 100) {
			setFileName("ClassicLeaderboard.txt");
			mode = "Classic";
		}
		else if (riverLength == 70) {
			setFileName("TurboLeaderboard.txt");
			mode = "Turbo";
		}
		
		// Print the scores
		System.out.printf("Top 5 Score for %s Mode\n", mode);
		System.out.println("-------------------------");
		System.out.println("Name      Score");
		System.out.println("-------------------------");
		
		// Inform user no top score has been made yet
		try {
			
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			
			// Check if file is empty
			if (file.length() == 0) {
				System.out.println("	There is no top score");
			}
			else {
				// Print scores
				while(scan.hasNextLine()) {
					System.out.println(scan.nextLine());
				}
				
			scan.close();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		} 
		
	}
	
	
	// toString

	@Override
	public String toString() {
		return String.format("%-8s %3d \n", getName(), getScore());
	}
	

}

	
