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
	 * Currently displayed message TextField attribute
	 */
	private TextArea dis;
	/**
	 * Button to delete currently displayed message attribute
	 */
	private Button delete;
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
	
	PriorityQueueController pqc = new PriorityQueueController();
	
	public PriorityQueuePanel() {
		dis = new TextArea();
		dis.setPrefSize(625,325);
		dis.setEditable(false);
		dis.setWrapText(true);
		getChildren().add(dis);
		
		BorderPane d = new BorderPane();
		delete = new Button("Delete");
		delete.setOnAction(this);
		d.setRight(delete);
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
		Label priorL = new Label("Priority(1-100):");
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
	private void changeDis(String mes) {
		dis.setText(mes);
	}
	
	@Override
	public void handle(Event e) {
		if (e.getSource() == delete) {
			changeDis(pqc.deleteCurTask());
		}if (e.getSource() == addTaskButton) {
			String description = descriptionTF.getText();
			String priority = priorityTF.getText();
			changeDis(pqc.addTask(description, Integer.parseInt(priority)));
			clear();
		}
	}
}