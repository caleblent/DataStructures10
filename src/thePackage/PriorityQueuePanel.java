package thePackage;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PriorityQueuePanel extends VBox implements EventHandler{
	/**
	 * PriorityQueue Model data structure
	 */
	private PriorityQueue pq;
	/**
	 * Currently displayed message TextField attribute
	 */
	private TextArea displayedMessage;
	/**
	 * Button to delete currently displayed message attribute
	 */
	private Button deleteButton;
	/**
	 * Message to add TextField attribute
	 */
	private TextField descriptionTF;
	/**
	 * The priority of message to add TextField attribute
	 */
	private TextField priorityTF;
	/**
	 * Button to add a task attribute
	 */
	private Button addTaskButton;
	
	/**
	 * PriorityQueueController is the intermediary that communicates with the GUI and the model PriorityQueue
	 */
//	PriorityQueueController pqc = new PriorityQueueController();
	
	public PriorityQueuePanel(PriorityQueue priorityQueue) {
		this.pq = priorityQueue;
		
		displayedMessage = new TextArea();
		displayedMessage.setPrefSize(625,325);
		displayedMessage.setEditable(false);
		displayedMessage.setWrapText(true);
		getChildren().add(displayedMessage);
		
		BorderPane d = new BorderPane();
		deleteButton = new Button("Delete");
		deleteButton.setOnAction(this);
		d.setRight(deleteButton);
		getChildren().add(d);
		
		HBox temp = new HBox();
		temp.setSpacing(7);
		
		Label taskL = new Label("Add Hacking Task:");
		descriptionTF = new TextField();
		temp.getChildren().add(taskL);
		temp.getChildren().add(descriptionTF);
		getChildren().add(temp);
		
		temp = new HBox();
		temp.setSpacing(7);
		Label priorL = new Label("Priority (1-100):");
		priorityTF = new TextField();
		temp.getChildren().add(priorL);
		temp.getChildren().add(priorityTF);
		getChildren().add(temp);
		
		addTaskButton = new Button("Add Task");
		addTaskButton.setOnAction(this);
		getChildren().add(addTaskButton);
	}
	/**
	 * clears necessary TextFields
	 */
	private void clear() {
		descriptionTF.setText("");
		priorityTF.setText("");
	}
	/**
	 * changes the currently displayed message
	 * @param mes
	 */
	private void setDisplay(String mes) {
		displayedMessage.setText(mes);
	}
	
	@Override
	public void handle(Event e) {
		if (e.getSource() == deleteButton) {
			pq.deleteTopTask();
			setDisplay(pq.getTopTaskDescription());
		} if (e.getSource() == addTaskButton) {
			String description = descriptionTF.getText();
			String priority = priorityTF.getText();
			pq.add(description, Integer.parseInt(priority));
			setDisplay(pq.getTopTaskDescription());
			clear();
		}
	}
}