package thePackage;

import java.util.Scanner;

public class AnimalGame {
	
	static Scanner console = new Scanner(System.in);
	
	public static void playthroughOnce(AnimalNode root) {
		console.useDelimiter(System.lineSeparator());
		AnimalNode node = root;
		System.out.println("Think of an animal. Let's see if I can guess it!");
		
		while(node.isQuestion()) {
			System.out.println(node.getData() + " (Y/N)");
			if (getAnswer()) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}
		
		// exits the while loop once it gets to an AnimalNode that contains data
		// which is NOT a question (i.e. it's an animal)
		
		System.out.print("Were you thinking of: a");
		if (isVowel(node.getData().charAt(0))) {
			System.out.print("n");
		}
		System.out.print(" " + node.getData() + "?\n");
		if (getAnswer()) {
			System.out.println("Hurray! I got it");
			System.out.println("Let's play again...");
		} else {
			System.out.println("Darn! what was it?");
			String newAnimal = console.next();
			if (flipCoin()) {
				System.out.println("What is a question that is a YES for " + newAnimal + " but a NO for " + node.getData() + "?");
				String newQuestion = console.next();
				console.nextLine();
				node.insertRight(newAnimal, newQuestion);
				System.out.println("Let's try again!");
			} else {
				System.out.println("What is a question that is a NO for " + newAnimal + " but a YES for " + node.getData() + "?");
				String newQuestion = console.next();
				console.nextLine();
				node.insertLeft(newAnimal, newQuestion);
				System.out.println("Let's try again!");
			}
			
		}
	}
	
	/**
	 * Helper function that flips a coin and return heads (TRUE) or tails (FALSE)
	 * 
	 * @return boolean
	 */
	public static boolean flipCoin() {
		return Math.random() > 0.5;
	}
	
	
	/**
	 * Helper method that prompts the user for Y/N input and continues to do so 
	 * until the user gives a valid answer
	 * 
	 * @return true IF answer == yes
	 * @return false IF answer == no
	 */
	public static boolean getAnswer() {
		String input = "";
		char answer = ' ';
		while(true) {
			input = console.next();
			answer = input.charAt(0);
			if (answer == 'Y' || answer == 'y') {
				return true;
			}
			if (answer == 'N' || answer == 'n') {
				return false;
			}
		}
	}
	
	/**
	 * Helper function to determine if the character is a vowel
	 * @param character
	 * @return boolean
	 */
	public static boolean isVowel(char character) {
		return character == 'a' 
			|| character == 'e' 
			|| character == 'i' 
			|| character == 'o' 
			|| character == 'u';
	}
}
