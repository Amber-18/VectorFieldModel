package graphics;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GraphUI {
	
	private Stage stage;
	private Scene scene;
	private Pane root;
	private FieldAnimation fa;
	private Path path;
	
	private int num_steps = 200;
	
	public GraphUI(Stage stage) {
		
		this.stage = stage;
		
		// create the root pane
		this.root = new StackPane();
		
		// add the objects to the pane
		Grid.setupAxes(root);
		Grid.setupOrigin(root);
		Grid.setupGridLines(root);
		
		fa = new FieldAnimation(root);
		
		this.path = fa.setupPath(root, num_steps);
		
		// add the pane to the scene and set scene size
		this.scene = new Scene(root, 1000, 600);
		
	}
	
	public void show() {
		// add scene to stage and display
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.setResizable(true);
		stage.show();
	}
	
	public void close() {
		stage.close();
	}
	
	public void run() {
		// run animations
		fa.run(path, new Duration(10000));
	}
	
	
	

}
