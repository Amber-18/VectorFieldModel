package graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Driver extends Application {
	
	private int num_steps = 5;
	private boolean max_velocity = true;
	
	@Override
	public void start(Stage stage) throws Exception {
		// create the root pane
		StackPane root = new StackPane();
		
		// add the objects to the pane
		Grid.setupAxes(root);
		Grid.setupOrigin(root);
		Grid.setupGridLines(root);
		
		FieldAnimation fa = new FieldAnimation(root);
		
		Path path = fa.setupPath(root, num_steps);
		
		// add the pane to the scene and set scene size
		Scene scene = new Scene(root, 1000, 600);
		
		// add scene to stage and display
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.setResizable(true);
		stage.show();
		
		
		// run animations
		fa.run(path, new Duration(5000));
		
	}
	
	public static void main(String args[]) {
		launch(args);
		
	}
	
} 
