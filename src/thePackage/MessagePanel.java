package thePackage;

import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import thePackage.MessageRotater.Node;

public class MessagePanel extends VBox implements EventHandler{
	/**
	 * MessageRotater model data structure
	 */
	private MessageRotater mr;
	/**
	 * used for rotating through the messages
	 */
	private Node cursor;
	private Timer timer;
	/**
	 * current messege TextField attribute
	 */
	private TextField currMessageTF;
	/**
	 * Button to delete current message attribute
	 */
	private Button deleteCurrentMessageButton;
	/**
	 * message to add to the rotater attribute
	 */
	private TextField newMessageTF;
	/**
	 * Button to add a message to the rotater attribute
	 */
	private Button addMessageButton;
	
	public MessagePanel(MessageRotater messageRotater) {
		this.mr = messageRotater;
		cursor = mr.getHeadNode();
		initializeCursorIteration();
		
		HBox temp = new HBox();
		temp.setSpacing(6);
		temp.setPadding(new Insets(15, 0, 0, 10));
		
		Label currMessage = new Label("Current I.P. ");
		currMessageTF = new TextField();
		currMessageTF.setEditable(false);
		currMessageTF.setMinWidth(300);
		deleteCurrentMessageButton = new Button("DDoS this I.P.");
		deleteCurrentMessageButton.setOnAction(this);
		temp.getChildren().addAll(currMessage, currMessageTF, deleteCurrentMessageButton);
		getChildren().add(temp);
		
		temp = new HBox();
		temp.setSpacing(6);
		Label newMessage = new Label("Add new I.P. to hitlist:");
		newMessageTF = new TextField();
		newMessageTF.setMinWidth(300);
		addMessageButton = new Button("Add I.P.");
		addMessageButton.setOnAction(this);
		temp.setPadding(new Insets(20, 0, 0, 10));
		temp.getChildren().addAll(newMessage, newMessageTF, addMessageButton);
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
	 * @param message
	 */
	private void setCurrentMessage(String message) {
		if (message == null || message.isBlank() || message.isEmpty())
			return;
		else
			currMessageTF.setText(message);
	}

	@Override
	public void handle(Event e) {
		if (e.getSource() == deleteCurrentMessageButton) {
			Node temp = cursor;
			mr.remove(cursor);
			cursor = temp.next;
		} if (e.getSource() == addMessageButton) {
			String newMessage = newMessageTF.getText();
			mr.add(newMessage);
			clear();
		}
	}
	
	public void initializeCursorIteration() {
		CursorSwap cursorSwap = new CursorSwap();
		timer = new Timer();
		// 1ms delay (to account for asynchronous tasks), then 3s fixed looping interval
		timer.schedule(cursorSwap, 50, 5000); 
	}
	
	public void cancelTimer() {
		timer.cancel();
	}
	
	// used for the timer on the cursor
	public class CursorSwap extends TimerTask {
		@Override
		public void run() {
			cursor = cursor.next;
			// if cursor is SENTINEL, go to next node in list
			if (cursor.data.equalsIgnoreCase("SENTINEL")) {
				cursor = cursor.next;
				// if cursor is SENTINEL and next is also SENTINEL, then the MR object is empty
				if (cursor.data.equalsIgnoreCase("SENTINEL")) {
					setCurrentMessage("*No more valid I.P.'s to DDoS*");
				} else {
					setCurrentMessage(cursor.data);
				}
			} else {
				setCurrentMessage(cursor.data);
			}
		}
	}
}