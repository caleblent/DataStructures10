package thePackage;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AnimalPanel extends VBox implements EventHandler{
	private TextField startMes;
	private Button yes;
	private Button no;
	private TextField endingMes;
	private TextField enter;
	private Button submit;
	
	public AnimalPanel() {
		this.setSpacing(10);
		startMes = new TextField("Stuff");
		startMes.setEditable(false);
		getChildren().add(startMes);
		
		HBox temp = new HBox();
		temp.setSpacing(10);
		yes = new Button("Yes");
		yes.setOnAction(this);
		no = new Button("No");
		no.setOnAction(this);
		temp.getChildren().addAll(yes, no);
		getChildren().add(temp);
		
		endingMes = new TextField("More Stuff");
		endingMes.setEditable(false);
		getChildren().add(endingMes);
		
		temp = new HBox();
		enter = new TextField();
		submit = new Button("Submit");
		submit.setOnAction(this);
		temp.getChildren().addAll(enter, submit);
		getChildren().add(temp);
	}
	private void clear() {
		enter.setText("");
	}
	private void changeStartMes(String mes) {
		startMes.setText(mes);
	}
	private void changeEndingMes(String mes) {
		endingMes.setText(mes);
	}
	
	@Override
	public void handle(Event e) {
		if (e.getSource() == yes) {
		//	changeStartMes(calebMethod(true))
		}if (e.getSource() == no) {
		//	changeStartMes(calebMethod(false))
		}if (e.getSource() == submit) {
			Object en = enter.getUserData();
		//	changeEndingMes(calebDifMethod(en))
			clear();
		}
	}
}