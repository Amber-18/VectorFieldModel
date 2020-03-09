package graphics;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import mathobjects.Vector;

public class GraphUI {
	
	private Stage stage;
	private Scene scene;
	private Pane root;
	private FieldAnimation animation;
	private Path path;
	private Data data;
	
	private Vector origin;
	private int lineLength;
	private int numGridLines;
	private int distBtwnGridLines;
	
	private int num_steps = 400;
	
	public GraphUI(Stage stage, Data data) {
		
		this.stage = stage;
		this.data = data;
		this.root = new Pane();
		
		extract(); // get the necessary data
		Grid grid = new Grid(root, origin, numGridLines, distBtwnGridLines, lineLength);
		
		animation = new FieldAnimation(root, data);
		
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
		fa.run(path, new Duration(18000));
	}
	
	/////////////////////////////////////// SETUP ////////////////////////
	
	public void setupAxes() {
		// (x, y)
		Vector startX = new Vector(-1*lineLength, 0);
		Vector endX = new Vector(lineLength, 0);
		Vector startY = new Vector(0, -1*lineLength);
		Vector endY = new Vector(0, lineLength);
		
		System.out.println("x-axis: " + startX.get(0) + 
				" " + startX.get(1) + 
				" " + endX.get(0) + 
				" " + endX.get(1));
		
		System.out.println("y-axis: " + startY.get(0) + 
				" " + startY.get(1) + 
				" " + endY.get(0) + 
				" " + endY.get(1));
		
		// convert to UI coordinates
		convertToUI(startX);
		convertToUI(endX);
		convertToUI(startY);
		convertToUI(endY);
		
		// (startX, endX, startY, endY)
		Line x_axis = new Line(startX.get(0), startX.get(1), endX.get(0), endX.get(1));
		Line y_axis = new Line(startY.get(0), startY.get(1), endY.get(0), endY.get(1));
		
		System.out.println("");
		System.out.println("x-axis: " + startX.get(0) + 
				" " + startX.get(1) + 
				" " + endX.get(0) + 
				" " + endX.get(1));
		
		System.out.println("y-axis: " + startY.get(0) + 
				" " + startY.get(1) + 
				" " + endY.get(0) + 
				" " + endY.get(1));
		
		x_axis.setStrokeWidth(5);
		y_axis.setStrokeWidth(5);
		x_axis.setFill(Color.BLACK);
		y_axis.setFill(Color.BLACK);
		
		root.getChildren().add(x_axis);
		root.getChildren().add(y_axis);
	}
	
	public void setupGridLines() {
		
		Line x_line;
		Line y_line;
		
		Vector start;
		Vector end;
		
		// add x-lines
		for(int i = 0; i < numGridLines; ++i) {
			
			start = new Vector(i*distBtwnGridLines, lineLength);
			end = new Vector(i*distBtwnGridLines, -1*lineLength);
			
			convertToUI(start);
			convertToUI(end);
			x_line = new Line(start.get(0), start.get(1), end.get(0), end.get(1));
			
			root.getChildren().add(x_line);
			
			// the above code adds a line to the right of the origin
			// the below code adds a line to the left of the origin
			start = new Vector(-1*i*distBtwnGridLines, lineLength);
			end = new Vector(-1*i*distBtwnGridLines, -1*lineLength);
			
			convertToUI(start);
			convertToUI(end);
			
			x_line = new Line(start.get(0), start.get(1), end.get(0), end.get(1));
			
			root.getChildren().add(x_line);
		}
		
		// add y-lines
		for(int i = 0; i < numGridLines; ++i) {
			
			start = new Vector(lineLength, i*distBtwnGridLines);
			end = new Vector(-1*lineLength, i*distBtwnGridLines);
			
			convertToUI(start);
			convertToUI(end);
			y_line = new Line(start.get(0), start.get(1), end.get(0), end.get(1));
			
			root.getChildren().add(y_line);
			
			// the above code adds a line above the origin
			// the below code adds a line below the origin
			start = new Vector(lineLength, -1*i*distBtwnGridLines);
			end = new Vector(-1*lineLength, -1*i*distBtwnGridLines);
			
			convertToUI(start);
			convertToUI(end);
			
			y_line = new Line(start.get(0), start.get(1), end.get(0), end.get(1));
			
			root.getChildren().add(y_line);
		}
		
		
		
	}
	
	public void setupOrigin() {
		Circle dot = new Circle();
		dot.setCenterX(origin.get(0));
		dot.setCenterY(origin.get(1));
		dot.setRadius(3);
		dot.setFill(Color.BLACK);
		
		root.getChildren().add(dot);
	}

	/////////////////////////////////////// helpful stuff /////////////////

	private void convertToUI(Vector v){
		double x,y;
		x = v.get(0);
		y = v.get(1);
		x = origin.get(0) + x;
		y = origin.get(1) - y;
		v.set(0, x);
		v.set(1, y);
	}
	
	private void extract() {
		
		lineLength = data.getLineLength();
		distBtwnGridLines = data.getDistBtwnGridLines();
		numGridLines = data.getNumGridLines();
		data.getInitialPosition();
		data.getInitialVelocity();
		data.getStepSize();
		data.getMaxVelocity();
		
	}

}
