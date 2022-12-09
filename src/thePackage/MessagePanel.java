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
	private TextField currMessageTF;
	/**
	 * Button to delete current message attribute
	 */
	private Button delCurrMessButton;
	/**
	 * message to add to the rotater attribute
	 */
	private TextField newMessageTF;
	/**
	 * Button to add a message to the rotater attribute
	 */
	private Button addMessage;
	
	MessageRotaterController mrc = new MessageRotaterController();
	
	public MessagePanel() {
		this.setSpacing(20);
		HBox temp = new HBox();
		temp.setSpacing(6);
		
		Label currMessage = new Label("Current I.P.:");
		currMessageTF = new TextField();
		currMessageTF.setEditable(false);
		delCurrMessButton = new Button("DDos this I.P.");
		delCurrMessButton.setOnAction(this);
		temp.getChildren().addAll(currMessage, currMessageTF, delCurrMessButton);
		getChildren().add(temp);
		
		temp = new HBox();
		temp.setSpacing(6);
		Label newMessage = new Label("Add new I.P. to hitlist:");
		newMessageTF = new TextField();
		addMessage = new Button("Add I.P.");
		addMessage.setOnAction(this);
		temp.getChildren().addAll(newMessage, newMessageTF, addMessage);
		getChildren().add(temp);
	}
	/**
	 * clears necessary TextFields
	 */
	private void clear() {
		newMessageTF.setText("");
	}
	/**
	 * Changes the currently displayed message
	 * @param mes
	 */
	private void changeCur(String mes) {
		currMessageTF.setText(mes);
	}

	@Override
	public void handle(Event e) {
		if (e.getSource() == delCurrMessButton) {
			changeCur(mrc.deleteCur());
		} if (e.getSource() == addMessage) {
			Object f = newMessageTF.getUserData();
			changeCur(mrc.addMes((String) f));
			clear();
		}
	}
}