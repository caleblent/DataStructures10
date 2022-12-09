package thePackage;

public class AnimalController {
	
	private AnimalPanel aPanel;
	private AnimalNode rootNode;
	private boolean coin;
	
	public AnimalController(AnimalPanel animalPanel, AnimalNode rootNode) {
		this.aPanel = animalPanel;
		this.rootNode = rootNode;
		this.coin = true;
	}
	
	public enum UserInputType{
		YES,
		NO,
		YESFINAL,
		NOFINAL,
		SUBMIT
	}
	
	public void startGame() {
		aPanel.clearUserResponse();
		aPanel.setGameResponse("Think of an animal. Let's see if I can guess it!");
		aPanel.setCurrentNode(rootNode);
	}
	
	public void handleNextPrompt(AnimalNode node, UserInputType userInputType, String[] inputs) {
		if (userInputType == UserInputType.YES) {
			
			node = node.getLeft();
			aPanel.setPromptMessage(node.getData() + " (Y/N)");
			
		} else if (userInputType == UserInputType.NO) {
			
			node = node.getRight();
			aPanel.setPromptMessage(node.getData() + " (Y/N)");
			
		} else if (userInputType == UserInputType.YESFINAL) {
			
			aPanel.setGameResponse("Hurray! I got it. Let's play again...");
			startGame();
			
		} else if (userInputType == UserInputType.NOFINAL) {
			
			aPanel.setGameResponse("Darn! What was it?");
			if (coin) {
				aPanel.setGameResponse("What is a YES for that but a NO for " + node.getData() + "?");
			} else {
				aPanel.setGameResponse("What is a NO for that but a YES for " + node.getData() + "?");
			}
			
		} else if (userInputType == UserInputType.SUBMIT) {
			
			if (coin) {
				node.insertRight(inputs[0], inputs[1]);
			} else {
				node.insertLeft(inputs[0], inputs[1]);
			}
			coin = !coin;
			startGame();
		}
	}
	
	private String getUserInput() {
		
		return null;
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
