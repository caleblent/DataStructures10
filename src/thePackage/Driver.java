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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import thePackage.PriorityQueue.Task;

public class Driver extends Application implements EventHandler {
	private Button exit;
	@Override
	public void start(Stage primaryStage) {
		try {
			// read in PriorityQueue data
			BufferedReader PQReader = new BufferedReader(new FileReader("PriorityQueueData.txt"));
			String line;
			PriorityQueue pq = new PriorityQueue();
			while ((line = PQReader.readLine()) != null) {
				if (!line.isBlank() && !line.isEmpty()) {
					String[] params = line.split(",");
					pq.add(params[0], Integer.parseInt(params[1]));
				}
			}
			PQReader.close();
			
			MessageRotaterController mrc = new MessageRotaterController();
			mrc.doTheThing();
			
			// new PQ data
//			pq.add("pirate swing dancing video lessons", 8);
//			pq.add("release revolutionary new incriminating evidence", 13);
//			pq.add("airdrop 3D printable gun models", 7);
//			pq.add("swap wireless mouse with exploding alarm clock", 5);
//			pq.add("0 Day vulnerability discovered", 0);
			
			// write PQ data to file
			BufferedWriter PQWriter = new BufferedWriter(new FileWriter("PriorityQueueData.txt"));
			ArrayList<Task> tasks = pq.getAllTasks();
			for (int i = 0; i < tasks.size(); i++) {
				PQWriter.write(tasks.get(i).description + "," + tasks.get(i).priority + "\n");
			}
			PQWriter.close();
			
			
			
			
			
			
			
			
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 625, 500);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Hacking Animals");
			
			TabPane addPane = new TabPane();
			root.setTop(addPane);
			
			PriorityQueuePanel priPan = new PriorityQueuePanel();
			addPane.getTabs().add(new Tab("Hacking Tasks", priPan));
			
			MessagePanel mesPan = new MessagePanel();
			addPane.getTabs().add(new Tab("DDoS Hitlist", mesPan));
			
			AnimalPanel malPan = new AnimalPanel();
			addPane.getTabs().add(new Tab("Animal Savagery", malPan));
			
			exit = new Button("Exit");
			exit.setOnAction(this);
			root.setBottom(exit);
			
//			primaryStage.show();
		} catch (IOException e){
			System.out.println("IOException detected: " + e.toString());
		} catch (Exception e) {
			System.out.println("Some exception occurred: " + e.toString());
			System.out.println("----------------------------------------------------");
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void handle(Event e) {
		if (e.getSource() == exit) {
			Platform.exit();
		}
	}
}