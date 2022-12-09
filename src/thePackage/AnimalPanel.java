package thePackage;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import thePackage.AnimalController.UserInputType;

public class AnimalPanel extends VBox implements EventHandler{
	
//	AnimalGame aGame;
	AnimalController aController;
	AnimalNode currentAnimalNode;
	AnimalNode rootNode;
	
	private TextField prompt1TF;
	public Button yesButton;
	public Button noButton;
	private TextField prompt2TF;
	public TextField userAnimalTF;
	private TextField prompt3TF;
	public TextField userQuestionTF;
	public Button submitButton;
	
	public AnimalPanel() {
//		aGame = new AnimalGame();
		AnimalNode mouse = new AnimalNode(null, "mouse", null);
		AnimalNode skunk = new AnimalNode(null, "skunk", null);
		AnimalNode first = new AnimalNode(mouse, "Does this animal love cheese?", skunk);
		currentAnimalNode = first;
		
		aController = new AnimalController(this, rootNode);
		
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
	}
	public void clearUserResponse() {
		userAnimalTF.setText("");
	}
	public void setPromptMessage(String mes) {
		prompt1TF.setText(mes);
	}
	public void setGameResponse(String mes) {
		prompt2TF.setText(mes);
	}
	public void setUserResponse(String mes) {
		userAnimalTF.setText(mes);
	}
	public void setCurrentNode(AnimalNode curr) {
		this.currentAnimalNode = curr;
	}
	
	@Override
	public void handle(Event e) {
		if (e.getSource() == yesButton) {
			if (currentAnimalNode.isQuestion()) {
				aController.handleNextPrompt(currentAnimalNode, UserInputType.YES, null);
			} else {
				aController.handleNextPrompt(rootNode, UserInputType.YESFINAL, null);
			}
		} else if (e.getSource() == noButton) {
			if (currentAnimalNode.isQuestion()) {
				aController.handleNextPrompt(currentAnimalNode, UserInputType.NO, null);
			} else {
				aController.handleNextPrompt(currentAnimalNode, UserInputType.NOFINAL, null);
			}
		} else if (e.getSource() == submitButton) {
			aController.handleNextPrompt(currentAnimalNode, UserInputType.SUBMIT,
				new String[] {userAnimalTF.getText(), userAnimalTF.getText()}
			);
		}
	}
}