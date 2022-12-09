package thePackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import thePackage.PriorityQueue.Task;

public class Driver extends Application implements EventHandler {
	private Button exitButton;
	private PriorityQueue pq;
	private MessageRotater mr;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// read in PriorityQueue data
			BufferedReader PQReader = new BufferedReader(new FileReader("PriorityQueueData.txt"));
			String line;
			pq = new PriorityQueue();
			while ((line = PQReader.readLine()) != null) {
				if (!line.isBlank() && !line.isEmpty()) {
					String[] params = line.split(",");
					pq.add(params[0], Integer.parseInt(params[1]));
				}
			}
			PQReader.close();
			
			// read in MessageRotater data
			BufferedReader MRReader = new BufferedReader(new FileReader("MessagesData.txt"));
			mr = new MessageRotater();
			mr.add("SENTINEL");
			while ((line = MRReader.readLine()) != null) {
				if (!line.isBlank() && !line.isEmpty()) {
					mr.add(line);
				}
			}
			PQReader.close();
			
			// open up the GUI window
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 625, 500);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Hacking Animals");
			primaryStage.setMinHeight(500);
			primaryStage.setMinWidth(625);
			
			TabPane addPane = new TabPane();
			root.setTop(addPane);
			
			PriorityQueuePanel priPan = new PriorityQueuePanel(pq);
			addPane.getTabs().add(new Tab("Hacking Tasks", priPan));
			
			MessagePanel mesPan = new MessagePanel(mr);
			addPane.getTabs().add(new Tab("DDoS Hitlist", mesPan));
			
			AnimalPanel malPan = new AnimalPanel();
			addPane.getTabs().add(new Tab("Animal Savagery", malPan));
			
			exitButton = new Button("Exit");
			exitButton.setOnAction(this);
			root.setPadding(new Insets(5, 5, 5, 5));
			root.setBottom(exitButton);
			
			primaryStage.show();
		} catch (IOException eRead){
			eRead.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void handle(Event e) {
		if (e.getSource() == exitButton) {
			try {
				// write PQ data to file
				BufferedWriter PQWriter = new BufferedWriter(new FileWriter("PriorityQueueData.txt"));
				ArrayList<Task> tasks = pq.getAllTasks();
				for (int i = 0; i < tasks.size(); i++) {
					PQWriter.write(tasks.get(i).description + "," + tasks.get(i).priority + "\n");
				}
				PQWriter.close();
				
				// write MR data to file
				BufferedWriter MRWriter = new BufferedWriter(new FileWriter("MessagesData.txt"));
				String[] messages = mr.getAllMessages();
				// start at i = 1 to skip the SENTINEL node
				for (int i = 1; i < messages.length; i++) {
					MRWriter.write(messages[i] + "\n");
				}
				MRWriter.close();
			} catch (IOException eWrite) {
				eWrite.printStackTrace();
			}
			
			Platform.exit();
			System.exit(0);
		}
	}
}