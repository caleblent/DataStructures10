package thePackage;

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

public class Driver extends Application implements EventHandler{
	private Button exit;
	@Override
	public void start(Stage primaryStage) {
		try {
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
			
			primaryStage.show();
		}catch(Exception e) {
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