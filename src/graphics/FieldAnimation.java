package graphics;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import objects.Dimension;
import objects.Field;
import objects.FieldObject;
import objects.LinkedList;
import objects.Polynomial;
import objects.Vector;

public class FieldAnimation {
	
	private Vector initial_position = new Vector(70, 70);
	private Vector initial_velocity = new Vector(0,-30);
	private double step = 0.1;
	
	private FieldObject object;
	private Circle dot;
		
	public FieldAnimation(Pane root) {
		dot = new Circle();
		dot.setCenterX(0);
		dot.setCenterY(0);
		dot.setRadius(6);
		dot.setFill(Color.DARKBLUE);
		
		root.getChildren().add(dot);
		
		object = null;
		setup();
		
		
	}
	
	private void setup() {
		
		Field field = new Field(2);
		
		Polynomial x_to_x = new Polynomial(0, -3);
		Polynomial x_to_y = new Polynomial(0, 2);
		Polynomial y_to_x = new Polynomial(0, 1);
		Polynomial y_to_y = new Polynomial(0, -3);
		
		field.addPolynomial(Dimension.X, Dimension.X, x_to_x);
		field.addPolynomial(Dimension.Y, Dimension.X, x_to_y);
		field.addPolynomial(Dimension.X, Dimension.Y, y_to_x);
		field.addPolynomial(Dimension.Y, Dimension.Y, y_to_y);
		
		object = new FieldObject(field);
		
		object.setPosition(initial_position);
		object.setVelocity(initial_velocity);
		object.setStep(step);
		
	}
	
	public Path setupPath(Pane root, int steps) {

		Path path = new Path();
		Path trace = new Path();
		
		path.getElements().add(new MoveTo(initial_position.get(0), initial_position.get(1)));
		trace.getElements().add(new MoveTo(initial_position.get(0), initial_position.get(1)));
		
		double x,y;
		for(int i = 0; i < steps; ++i) {
			x = object.getPosition().get(0);
			y = object.getPosition().get(1);
			path.getElements().add(new LineTo(x,y));
			
			System.out.println("x=" + x + " ||y=" + y);

			trace.getElements().add(new LineTo(x,y));
			trace.setStroke(Color.ORANGERED);

			object.update();
			
		}
		
		root.getChildren().add(trace);
		
		return path;
	}
	
	
	public void run(Path path, Duration duration) {
		
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(duration);
		pathTransition.setPath(path);
		pathTransition.setNode(dot);
		pathTransition.setOrientation(PathTransition.OrientationType.NONE);
		pathTransition.setCycleCount(Timeline.INDEFINITE);
		pathTransition.setAutoReverse(false);
		
		pathTransition.play();
		
	}

}
