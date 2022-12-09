package thePackage;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MessagePanel extends VBox implements EventHandler{
	/**
	 * current messege TextField attribute
	 */
	private TextField cur;
	/**
	 * Button to delete current message attribute
	 */
	private Button delCur;
	/**
	 * message to add to the rotater attribute
	 */
	private TextField futureMes;
	/**
	 * Button to add a message to the rotater attribute
	 */
	private Button addMes;
	
	MessageRotaterController mrc = new MessageRotaterController();
	
	public MessagePanel() {
		this.setSpacing(20);
		HBox temp = new HBox();
		temp.setSpacing(6);
		
		Label curMes = new Label("Current I.P.:");
		cur = new TextField();
		cur.setEditable(false);
		delCur = new Button("DDos this I.P.");
		delCur.setOnAction(this);
		temp.getChildren().addAll(curMes, cur, delCur);
		getChildren().add(temp);
		
		temp = new HBox();
		temp.setSpacing(6);
		Label newMes = new Label("Add new I.P. to hitlist:");
		futureMes = new TextField();
		addMes = new Button("Add I.P.");
		addMes.setOnAction(this);
		temp.getChildren().addAll(newMes, futureMes, addMes);
		getChildren().add(temp);
	}
	/**
	 * clears necessary TextFields
	 */
	private void clear() {
		futureMes.setText("");
	}
	/**
	 * Changes the currently displayed message
	 * @param mes
	 */
	private void changeCur(String mes) {
		cur.setText(mes);
	}

	@Override
	public void handle(Event e) {
		if (e.getSource() == delCur) {
			changeCur(mrc.deleteCur());
		}if (e.getSource() == addMes) {
			Object f = futureMes.getUserData();
			changeCur(mrc.addMes((String) f));
			clear();
		}
	}
}