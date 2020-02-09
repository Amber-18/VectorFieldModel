package graphics;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Grid {
	
	private static double originX = 0;
	private static double originY = 0;
	private static double lineLength = 500;
	private static int numGridLines = 45;
	private static int distanceBetweenGridLines = 15;
	
	public Grid() {
		
	}
	
	public static void setupAxes(Pane root) {
		Line x_axis = new Line(lineLength, originY, -1*lineLength, originY);
		Line y_axis = new Line(originX, lineLength, originX, -1*lineLength);
		
		x_axis.setStrokeWidth(2);
		y_axis.setStrokeWidth(2);
		
		root.getChildren().add(x_axis);
		root.getChildren().add(y_axis);
	}
	
	public static void setupGridLines(Pane root) {
		
		GridPane gridPane = new GridPane();
		StackPane cell;
		gridPane.setAlignment(Pos.CENTER);
		int dist = distanceBetweenGridLines;
		Line x_line;
		Line y_line;
		
		for(int i = 0; i < numGridLines; ++i) {
			for(int j = 0; j < numGridLines; ++j) {
				x_line = new Line(dist, 0, -dist, 0);
				y_line = new Line(0, dist, 0, -dist);
				
				cell = new StackPane();
				cell.getChildren().add(x_line);
				cell.getChildren().add(y_line);
				gridPane.add(cell, i, j);
			}
		}
		
		root.getChildren().add(gridPane);
		
//		// x-axis grid lines
//		for(int i = 0; i < numGridLines; ++i) {
//			// new Line(startX, startY, endX, endY);
//			dashedLine = new Line(originX*i, lineLength, originX*(-1*i), -1*lineLength);
//			dashedLine.getStrokeDashArray().addAll(2d, 5d);
//			root.getChildren().add(dashedLine);
//		}
		
//		// y-axis grid lines
//		for(int i = 0; i < numGridLines; ++i) {
//			// new Line(startX, startY, endX, endY);
//			dashedLine = new Line(lineLength, originY*i, -1*lineLength, originX*(-1*i));
//			dashedLine.getStrokeDashArray().addAll(2d, 5d);
//			root.getChildren().add(dashedLine);
//		}
		
	}
	
	public static void setupOrigin(Pane root) {
		Circle origin = new Circle();
		origin.setCenterX(originX);
		origin.setCenterY(originY);
		origin.setRadius(3);
		origin.setFill(Color.BLACK);
		
		root.getChildren().add(origin);
	}
	
	
}
