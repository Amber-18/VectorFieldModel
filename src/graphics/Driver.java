package graphics;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import objects.Polynomial;

public class Driver extends Application {
	
	// TODO create panel for user input
	// TODO create max_vel
	private int num_steps = 5;
	
	private Stage stage;
	private Scene scene;
	private Pane root;
	private GraphUI Graph = new GraphUI(new Stage());
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Graph.show();
		Graph.run();
		
		// this.stage = stage;
		// setup();
		// show();
	}
	
	public static void main(String args[]) {
		launch(args);
		
	}
	
	public void setup() {
		// create the root pane
		this.root = new GridPane();
		
		// add the objects to the pane
		addUserInputs((GridPane)root);
		addDisplayButton((GridPane)root);
		
		// add the pane to the scene and set scene size
		this.scene = new Scene(root, 1000, 600);
	}
	
	public void show() {
		// add scene to stage and display
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.setResizable(false);
		stage.show();
	}
	
	public void addUserInputs(GridPane root) {
		Spinner<Integer> spinner = new Spinner<Integer>();
		 
        
	}
	
	
	private TitledPane addSpinner(String title, Spinner<Integer> spinner, double valueToChange) {
				
		int initialValue = 0;
		 
        // Set spinner values
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99, initialValue);
        spinner.setValueFactory(valueFactory);
		
//        spinner.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//            	
//            	Graph = new GraphUI(new Stage());
//            	
//            }
//        });
        
        
		return new TitledPane(title, spinner);
		
	}
	
	
	
	public void addDisplayButton(GridPane root) {
		
		Button displayResults = new Button();
		displayResults.setText("Graph!");
 
		displayResults.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            	Graph = new GraphUI(new Stage());
            	
            }
        });
		
		root.add(displayResults, 0, 0);
	}
	
} 
