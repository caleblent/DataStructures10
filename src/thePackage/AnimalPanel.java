package thePackage;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import thePackage.AnimalController.UserInputType;

public class AnimalPanel extends VBox implements EventHandler {
	
	public AnimalController aController;
	public AnimalNode currentAnimalNode;
	
	private TextField prompt1TF;
	private Button yesButton;
	private Button noButton;
	private TextField prompt2TF;
	private TextField userAnimalTF;
	private TextField prompt3TF;
	private TextField userQuestionTF;
	private Button submitButton;
	
	public AnimalPanel() {
		AnimalNode mouse = new AnimalNode(null, "mouse", null);
		AnimalNode skunk = new AnimalNode(null, "skunk", null);
		AnimalNode horse = new AnimalNode(null, "horse", null);
		AnimalNode second = new AnimalNode(horse, "Does it gallop?", skunk);
		AnimalNode first = new AnimalNode(mouse, "Does this animal love cheese?", second);
		this.currentAnimalNode = first;
		
		aController = new AnimalController(this, currentAnimalNode);
		
		this.setSpacing(10);
		prompt1TF = new TextField("Prompt 1 TextField");
		prompt1TF.setEditable(false);
		getChildren().add(prompt1TF);
		
		HBox temp = new HBox();
		temp.setSpacing(10);
		yesButton = new Button("Yes");
		yesButton.setOnAction(this);
		noButton = new Button("No");
		noButton.setOnAction(this);
		temp.getChildren().addAll(yesButton, noButton);
		getChildren().add(temp);
		
		prompt2TF = new TextField("Prompt 2 TextField");
		prompt2TF.setEditable(false);
		getChildren().add(prompt2TF);
		
		userAnimalTF = new TextField("user animal");
		userAnimalTF.setMinWidth(500);
		getChildren().add(userAnimalTF);
		
		prompt3TF = new TextField("Prompt 3 TextField");
		prompt3TF.setEditable(false);
		getChildren().add(prompt3TF);
		
		userQuestionTF = new TextField("question");
		userQuestionTF.setMinWidth(500);
		getChildren().add(userQuestionTF);
		
		submitButton = new Button("Submit");
		submitButton.setOnAction(this);
		getChildren().add(submitButton);
		
		aController.startGame();
	}
	public void clearStuff() {
		clearUserResponses();
		clearPrompts2and3();
	}
	public void clearUserResponses() {
		userAnimalTF.clear();
		userQuestionTF.clear();
	}
	public void setPrompt1(String mes) {
		prompt1TF.setText(mes);
	}
	public void setPrompt2(String mes) {
		prompt2TF.setText(mes);
	}
	public void setPrompt3(String mes) {
		prompt3TF.setText(mes);
	}
	public void clearPrompts2and3() {
		prompt2TF.clear();
		prompt3TF.clear();
	}
	public void setCurrentNode(AnimalNode curr) {
		this.currentAnimalNode = curr;
	}
	
	@Override
	public void handle(Event e) {
		if (e.getSource() == yesButton) {
			if (currentAnimalNode.isQuestion()) {
				System.out.println("The node " + currentAnimalNode.toString() + " with data " + currentAnimalNode.getData() + " is a question.");
				System.out.println("YES : ");
				aController.handleNextPrompt(currentAnimalNode, UserInputType.YES, null);
			} else {
				System.out.println("The node " + currentAnimalNode.toString() + " with data " + currentAnimalNode.getData() + " is an animal.");
				System.out.println("YESFINAL : ");
				aController.handleNextPrompt(currentAnimalNode, UserInputType.YESFINAL, null);
			}
		} else if (e.getSource() == noButton) {
			if (currentAnimalNode.isQuestion()) {
				System.out.println("The node " + currentAnimalNode.toString() + " with data " + currentAnimalNode.getData() + " is a question.");
				System.out.println("NO : ");
				aController.handleNextPrompt(currentAnimalNode, UserInputType.NO, null);
			} else {
				System.out.println("The node " + currentAnimalNode.toString() + " with data " + currentAnimalNode.getData() + " is an animal.");
				System.out.println("NOFINAL : ");
				aController.handleNextPrompt(currentAnimalNode, UserInputType.NOFINAL, null);
			}
		} else if (e.getSource() == submitButton) {
			System.out.println("-SUBMIT-");
			String animal = userAnimalTF.getText();
			String question = userAnimalTF.getText();
			if (animal == null || animal.isBlank() || animal.isEmpty() || 
				question == null || question.isBlank() || question.isEmpty()) {
				prompt1TF.setText("Must have valid entries for both inputs");
			} else {
				aController.handleNextPrompt(currentAnimalNode, UserInputType.SUBMIT,
					new String[] {animal, question}
				);
			}
		}
	}
}