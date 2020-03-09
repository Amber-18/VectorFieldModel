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
import list.LinkedList;
import mathobjects.Polynomial;
import mathobjects.Vector;
import objects.Dimension;
import objects.Field;
import objects.FieldObject;

public class FieldAnimation {
	
	private Data data;
	private Pane root;
	private PathTransition pathTransition;
	
	private FieldObject object;
	private Field field;
	private Vector initial_position;
	private Vector initial_velocity;
	private double step;
	private int num_steps;
	private int duration;
	private double max_velocity;
		
	public FieldAnimation(Pane root, Data data) {
		extract();
		setupField();
		setupGraphics();
	}
	
	private void setupGraphics(){
		Circle dot = new Circle(0, 0, 5, Color.DARKBLUE);
		root.getChildren().add(dot);
		
		Path path = new Path(); // path of dot to follow
		Path trace = new Path(); // copy of path to put on graph
		
		path.getElements().add(new MoveTo(initial_position.get(0), initial_position.get(1)));
		trace.getElements().add(new MoveTo(initial_position.get(0), initial_position.get(1)));
		
		double x,y;
		for(int i = 0; i < num_steps; ++i) {
			x = object.getPosition().get(0);
			y = object.getPosition().get(1);
			path.getElements().add(new LineTo(x,y));
			
			System.out.println("x=" + x + " ||y=" + y);

			trace.getElements().add(new LineTo(x,y));
			trace.setStroke(Color.ORANGERED);

			object.update();
		}
		
		root.getChildren().add(trace);
		
		pathTransition = new PathTransition();
		pathTransition.setDuration(new Duration(duration));
		pathTransition.setPath(path);
		pathTransition.setNode(dot);
		pathTransition.setOrientation(PathTransition.OrientationType.NONE);
		pathTransition.setCycleCount(Timeline.INDEFINITE);
		pathTransition.setAutoReverse(false);
	}
	
	private void setupField() {
		// 2d field
		field = new Field(2);
		data.getPoly(field);
		object = new FieldObject(field);
		
		object.setPosition(initial_position);
		object.setVelocity(initial_velocity);
		object.setStep(step);
	}
	
	public void run() {
		pathTransition.play();
	}
	
	private void extract() {
		initial_position = data.getInitialPosition();
		initial_velocity = data.getInitialVelocity();
		step = data.getStepSize();
		num_steps = data.getNumStep();
		max_velocity = data.getMaxVelocity();
		duration = data.getDuration();
	}

}
