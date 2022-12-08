package thePackage;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MessagePanel extends VBox implements EventHandler{
	private TextField cur;
	private Button delCur;
	private TextField futureMes;
	private Button addMes;
	
	public MessagePanel() {
		this.setSpacing(20);
		HBox temp = new HBox();
		temp.setSpacing(6);
		
		Label curMes = new Label("Current I.P. :");
		cur = new TextField();
		cur.setEditable(false);
		delCur = new Button("DDoS this I.P.");
		delCur.setOnAction(this);
		temp.getChildren().addAll(curMes, cur, delCur);
		getChildren().add(temp);
		
		temp = new HBox();
		temp.setSpacing(6);
		Label newMes = new Label("Add new I.P. to hit list:");
		futureMes = new TextField();
		addMes = new Button("Add I.P.");
		addMes.setOnAction(this);
		temp.getChildren().addAll(newMes, futureMes, addMes);
		getChildren().add(temp);
	}
	private void clear() {
		futureMes.setText("");
	}
	private void changeCur(String mes) {
		cur.setText(mes);
	}

	@Override
	public void handle(Event e) {
		if (e.getSource() == delCur) {
		//	changeCur(calebDeleteMethod)
		}if (e.getSource() == addMes) {
			Object f = futureMes.getUserData();
		//	changeCur(calebAddMethod(f))
			clear();
		}
	}
}