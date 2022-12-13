package thePackage;

/**
 * This class guides the computer through the animal game.
 * 
 * MachineLearning was the console implementation of the game.
 * AnimalController is the (attempt of a) GUI implementation of the game.
 * 
 * @author Caleb Lent
 */
public class AnimalController {
	
	private AnimalPanel aPanel;
	private AnimalNode rootNode;
	private boolean coin; // used for determining the order of the question asked (when inserting a new node)
	
	/**
	 * Constructor takes the AnimalPanel and the starting AnimalNode as parameters,
	 * allowing the AnimalController to make direct references to their attributes
	 * 
	 * @param animalPanel
	 * @param rootNode
	 */
	public AnimalController(AnimalPanel animalPanel, AnimalNode rootNode) {
		this.aPanel = animalPanel;
		this.rootNode = rootNode;
		this.coin = true;
	}
	
	/**
	 * Custom enum object that tells the handleNextPrompt() method what type
	 * of input is is receiving
	 * 
	 * @author Caleb Lent
	 */
	public enum UserInputType {
		YES,
		NO,
		YESFINAL,
		NOFINAL,
		SUBMIT
	}
	
	/**
	 * Called once at the start of the game
	 */
	public void startGame() {
		aPanel.clearPrompts2and3();
		restartGame();
	}
	
	/**
	 * Called every time the game "restarts"
	 */
	public void restartGame() {
		aPanel.clearUserResponses();
		aPanel.setPrompt3("Think of an animal. Let's see if I can guess it!");
		aPanel.currentAnimalNode = rootNode;
		aPanel.setPrompt1(aPanel.currentAnimalNode.getData() + " (Y/N)");
	}
	
	/**
	 * Receives the UserInputType to know how to handle the inputs, then does so
	 * 
	 * @param node
	 * @param userInputType
	 * @param inputs
	 */
	public void handleNextPrompt(AnimalNode node, UserInputType userInputType, String[] inputs) {
		if (userInputType == UserInputType.YES) {
			
			node = node.getLeft();
			if (node.isQuestion()) {
				aPanel.setPrompt1(node.getData() + " (Y/N)"); // if it's a question
			} else {
				aPanel.setPrompt1("Is it a " + node.getData() + "? (Y/N)"); // if it's a guess
			}
			aPanel.currentAnimalNode = node;
			aPanel.clearStuff();
			
		} else if (userInputType == UserInputType.NO) {
			
			node = node.getRight();
			if (node.isQuestion()) {
				aPanel.setPrompt1(node.getData() + " (Y/N)"); // if it's a question
			} else {
				aPanel.setPrompt1("Is it a " + node.getData() + "? (Y/N)"); // if it's a guess
			}
			aPanel.currentAnimalNode = node;
			aPanel.clearStuff();
			
		} else if (userInputType == UserInputType.YESFINAL) {
			
			aPanel.setPrompt2("Hurray! I got it. Let's play again...");
			restartGame();
			
		} else if (userInputType == UserInputType.NOFINAL) {
			
			aPanel.setPrompt2("Darn! What animal was it?");
			coin = flipCoin();
			if (coin) {
				aPanel.setPrompt3("What is a YES for that but a NO for " + node.getData() + "?");
			} else {
				aPanel.setPrompt3("What is a NO for that but a YES for " + node.getData() + "?");
			}
			
		} else if (userInputType == UserInputType.SUBMIT) {
			
			if (coin) {
				node.insertRight(inputs[0], inputs[1]);
			} else {
				node.insertLeft(inputs[0], inputs[1]);
			}
			aPanel.setPrompt2("Let's try again!");
			restartGame();
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
