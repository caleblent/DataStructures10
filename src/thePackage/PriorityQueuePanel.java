package thePackage;

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
	private TextArea dis;
	private Button delete;
	private TextField task;
	private TextField priority;
	private Button addTask;
	
	public PriorityQueuePanel() {
		dis = new TextArea("Stuff");
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
		
		Label taskL = new Label("Add Another Hacking Task:");
		task = new TextField();
		temp.getChildren().add(taskL);
		temp.getChildren().add(task);
		getChildren().add(temp);
		
		temp = new HBox();
		temp.setSpacing(7);
		Label priorL = new Label("Priority of this expoit (1-100):");
		priority = new TextField();
		temp.getChildren().add(priorL);
		temp.getChildren().add(priority);
		getChildren().add(temp);
		
		addTask = new Button("Add New Hack!");
		addTask.setOnAction(this);
		getChildren().add(addTask);
	}
	private void clear() {
		task.setText("");
		priority.setText("");
	}
	private void changeDis(String mes) {
		dis.setText(mes);
	}
	
	
	@Override
	public void handle(Event e) {
		if (e.getSource() == delete) {
		//	changeDis(calebDeleteMethod());
		}if (e.getSource() == addTask) {
			Object t = task.getUserData();
			Object p = priority.getUserData();
		//  changeDis(calebAddMethod(t,p));
			clear();
		}
	}
}