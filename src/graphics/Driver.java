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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import mathobjects.Polynomial;

public class Driver extends Application {
	
	// TODO create panel for user input
	// TODO create max_vel
	private int num_steps = 5;
	
	private Stage stage;
	private Scene scene;
	private Pane root;
	private GraphUI Graph = new GraphUI(new Stage());
	private Data data = new Data();
	
	@Override
	public void start(Stage stage) throws Exception {
		
		 Graph.show();
		 //Graph.run();
		
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
		root.add(addEquations(), 0, 1);
		 
        
	}
	
	
	private TitledPane addEquations() {
		GridPane grid = new GridPane();
		
		Text text = new Text("Input dimension :: Output dimension");
		
		Label x_to_x = new Label("x :: x");	
		TextField e_x_to_x = new TextField("Enter equation...");
		
		Label x_to_y = new Label("x :: y");	
		TextField e_x_to_y = new TextField("Enter equation...");
		
		Label y_to_x = new Label("y :: x");	
		TextField e_y_to_x = new TextField("Enter equation...");
		
		Label y_to_y = new Label("y :: y");	
		TextField e_y_to_y = new TextField("Enter equation...");		
		
		Button submit = new Button("Enter!");
		submit.setText("Graph!");
		 
		submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String _1 = e_x_to_x.getText();
            	String _2 = e_x_to_y.getText();
            	String _3 = e_y_to_x.getText();
            	String _4 = e_y_to_y.getText();
            	data.setPoly(_1, _2, _3, _4);
            	// TODO get strings from UI, convert to poly in Data
            	// transfer poly's from data to graph
            	// Graph.setPoly(data.getPoly());
            	
            }
        });
		
		
        
		// return new TitledPane(title, spinner);
		return null;
		
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
