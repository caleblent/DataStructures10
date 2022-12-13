package thePackage;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
	 * Constructor method, accepts 
	 * @param priorityQueue
	 */
	public PriorityQueuePanel(PriorityQueue priorityQueue) {
		this.pq = priorityQueue;
		
		displayedMessage = new TextArea();
		displayedMessage.setPrefSize(325,150);
		displayedMessage.setEditable(false);
		displayedMessage.setText(pq.getTopTaskDescription());
		displayedMessage.setPadding(new Insets(10, 10, 0, 10));
		getChildren().add(displayedMessage);
		
		BorderPane d = new BorderPane();
		deleteButton = new Button("Complete Task");
		deleteButton.setOnAction(this);
		d.setPadding(new Insets(5, 5, 5, 5));
		d.setRight(deleteButton);
		getChildren().add(d);
		
		HBox temp = new HBox();
		temp.setSpacing(6);
		temp.setPadding(new Insets(5, 10, 5, 10));
		
		Label taskL = new Label("Add Hacking Task:");
		descriptionTF = new TextField();
		descriptionTF.setMinWidth(300);
		temp.getChildren().add(taskL);
		temp.getChildren().add(descriptionTF);
		getChildren().add(temp);
		
		temp = new HBox();
		temp.setSpacing(6);
		temp.setPadding(new Insets(5, 10, 5, 10));
		Label priorL = new Label("Priority (1-100):");
		priorityTF = new TextField();
		priorityTF.setMinWidth(100);
		temp.getChildren().add(priorL);
		temp.getChildren().add(priorityTF);
		getChildren().add(temp);
		
		temp = new HBox();
		temp.setPadding(new Insets(5, 5, 5, 10));
		addTaskButton = new Button("Add Task");
		addTaskButton.setOnAction(this);
		temp.getChildren().add(addTaskButton);
		getChildren().add(temp);
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
			try {
				int priorityInt = Integer.parseInt(priority);
				if (priorityInt < 1 || priorityInt > 100)
					throw new IllegalArgumentException("priority can only be 1-100");
				pq.add(description, priorityInt);
				setDisplay(pq.getTopTaskDescription());
			} catch (NumberFormatException eNum) {
				setDisplay(pq.getTopTaskDescription() + "\n\n" +
				"PRIORITY MUST BE A VALID INTEGER");
			} catch (IllegalArgumentException eIll) {
				setDisplay(pq.getTopTaskDescription() + "\n\n" +
				"PRIORITY MUST BE AN INTEGER BETWEEN 1 AND 100 (inclusive)");
			}
			clear();
		}
	}
}