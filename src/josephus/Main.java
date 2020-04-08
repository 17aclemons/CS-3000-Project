package josephus;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent> {
	//window 	stage
	//scene 	content inside stage 

	Button predict;
	Button animate;
	int numPeople;
	Person[] people;
	

	public static void main(String[] args) {	//Nikki, Andrew, Matt
		launch(args); //sets everything up and runs start
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Josephus Problem");
		Pane layout = new Pane();

		makeDashboard(layout, numPeople, people);
		makeSliders(layout, numPeople);
		makeCircle(layout, numPeople);

		Scene scene =  new Scene(layout, 1000, 600);		//set dimensions of scene
		primaryStage.setScene(scene);
		primaryStage.show();


	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println("event! nice.");
	}

	public void makeDashboard(Pane layout, int n, Person[] p) {
		predict = new Button();
		predict.setText("Generate Prediction");
		predict.setLayoutX(800);
		predict.setLayoutY(150);
		predict.setOnAction(new EventHandler<ActionEvent>() { public void
			handle1(ActionEvent e) {}

		@Override
		public void handle(ActionEvent arg0) {
			System.out.println("generated");
			generate(p, n);
		} } );

		//*************************

		animate = new Button();
		animate.setText("Run Animation");
		animate.setLayoutX(800);
		animate.setLayoutY(250);
		animate.setOnAction(new EventHandler<ActionEvent>() { public void
			handle1(ActionEvent e) {}

		@Override
		public void handle(ActionEvent arg0) {
			System.out.println("animated");

		} } );

		//****************************
		Line line = new Line(700, 0, 701, 600);
		line.setStroke(Color.BLACK);
		line.setStrokeWidth(1);

		layout.getChildren().add(predict);
		layout.getChildren().add(animate);
		layout.getChildren().add(line);
	}

	public void makeSliders(Pane layout, int nn) {
		//****************************
		Slider nPeople = new Slider(2, 30, 15);
		nPeople.setTranslateX(800);
		nPeople.setTranslateY(500);
		nPeople.setShowTickLabels(true);
		nPeople.setShowTickMarks(true);
		nPeople.setBlockIncrement(1);
		//TODO: uh fix the tick thingerbops
		nPeople.setMajorTickUnit(5);
		nPeople.setMinorTickCount(23);
		nPeople.setSnapToTicks(true);
		nPeople.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue, 
					Number oldValue, 
					Number newValue) { 
				nPeople.accessibleTextProperty().setValue(
						String.valueOf(newValue.intValue()));
			}
		});

		//****************************
		Slider kSkips = new Slider(0, 5, 2);
		kSkips.setTranslateX(800);
		kSkips.setTranslateY(400);
		
		Label nLabel = new Label("People in circle: "+ Integer.toString((int) nPeople.getValue()));
		nPeople.setOnMouseReleased(event -> {
			System.out.println((int) nPeople.getValue());
			nLabel.setText("People in circle: " + Integer.toString((int) nPeople.getValue()));

			int n =(int) nPeople.getValue();
			makeCircle(layout, n);
		});
		nLabel.setTranslateX(800);
		nLabel.setTranslateY(450);
		
		layout.getChildren().add(kSkips);
		layout.getChildren().addAll(nPeople, nLabel);
		nLabel.accessibleTextProperty().setValue("n");
	}

	public void makeCircle(Pane layout, int n) {

		int centerX = 350;
		int centerY = 300;
		int radius = 200;

		Circle circle = new Circle(centerX, centerY, radius, Color.LIGHTGRAY);

		layout.getChildren().add(circle);

		Person[] people = new Person[n];
		for(int i = 0; i < n; i++) {
			int x = (int) findChairX(radius, i, n) + centerX;
			int y = (int) findChairY(radius, i, n) + centerY;
			System.out.println("("+x+","+y+")");
			people[i] = new Person(x, y);
			layout.getChildren().add(people[i].iView);
			people[i].iView.relocate(x-25, y-25);
		}

	}

	public double findChairX(int r, int currentPoint, int totalPoints) { //we used two algorithms for this hoe
		double theta = ((Math.PI*2) / totalPoints);
		double angle = (theta * currentPoint);

		return (r * Math.cos(angle));
	}

	public double findChairY(int r, int currentPoint, int totalPoints) { //we used two algorithms for this hoe
		double theta = ((Math.PI*2) / totalPoints);
		double angle = (theta * currentPoint);

		return (r * Math.sin(angle));
	}

	public void generate(Person[] p, int n) {
		for(int i = 0; i < n ; i++) {
			p[i].kill();
		}
	}
}


