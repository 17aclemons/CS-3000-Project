package josephus;

import java.math.BigInteger;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {
	// window stage
	// scene content inside stage


	
	
	Button predict;
	Button animate;
	int numPeople;
	Person[] people;
	int defaultSliderNum = 15;

	public static void main(String[] args) { // Nikki, Andrew, Matt
		
		//test(9);
		
		launch(args); // sets everything up and runs start

	}
/*
	public static void test(int testlength) {	//when testing, make josephus method static.
		Person[] test = new Person[testlength];

		for(int i = 0; i < testlength; i++) {
			test[i] = new Person(0, 1, i+1);
		}
		
		Pane p = new Pane();
		
		Person winner = josephus(p, test, 0);
		System.out.println(winner.id +" is the winner");
		
		
		boolean[] b = numToBinary(testlength);
		binaryToNum(b);
		swap(b);
		int prediction = binaryToNum(b);
		System.out.println("our prediction was "+ prediction);
	}
*/

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Josephus Problem");
		Pane layout = new Pane();

		makeSliders(layout, people, defaultSliderNum);
		makeCircle(layout, numPeople);
		makeDashboard(layout, numPeople, people);
		Scene scene = new Scene(layout, 1050, 600); // set dimensions of scene
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println("event! nice.");
	}
	
	public void sleep() throws InterruptedException {
		Thread.sleep(0);
	}

	public Person josephus(Pane layout, Person[] p, Person[] orig, int firstIndex) throws InterruptedException { //recursive josephus method
		Person lastAlive = p[0];
		//TODO: grab the index of the last alive to pass into recursive method.
		int newArrayLength = p.length;
		
		if(p.length == 1) {
			return p[0];
		}else {
			
			if (firstIndex == 0) { //if we start killing at spot 0
				System.out.println("start spot 0");
				for(int i = 0; i < p.length; i++) {
					if (p[i].isAlive()) {
						if(i+1 < p.length) {
							p[i+1].kill();
							
							newArrayLength--;
							updateCircle(layout, orig.length, orig);
	sleep();
						}else { lastAlive = p[i]; }
					}
				}
			} else { //the first spot to kill is NOT at the beginning of our array...
				p[0].kill();
				
				updateCircle(layout, orig.length, orig);
	sleep();
				System.out.println("not");
				newArrayLength--;
				for(int i = 1; i < p.length; i++) {
					if (p[i].isAlive()) {
						if(i+1 < p.length) {
							p[i+1].kill();
							
							updateCircle(layout, orig.length, orig);
	sleep();
							newArrayLength--;
						}else { lastAlive = p[i]; }
					}
				}
			}
			
			//build new array with only alive people
			Person[] nextRound = new Person[newArrayLength];
			int index = 0;
			for(int i = 0; i < p.length; i++) {
				if(p[i].isAlive()) {
					nextRound[index] = p[i];
					index++;
					System.out.print(p[i].id + " ");

				}
				if(p[i].id == lastAlive.id) {
					firstIndex = i;
				}
				
			}
			System.out.println();
			return josephus(layout, nextRound, orig, firstIndex);
			
		}
	}

	public void clearCircle(Pane layout, int n, Person[] p) { // remember to makecircle or update circle
		layout.getChildren().clear();
		makeSliders(layout, p, n);
		makeDashboard(layout, n, p);
	}

	public void makeDashboard(Pane layout, int n, Person[] p) { // Buttons, line

		ImageView sickLogo = new ImageView("niceLogo.png");
		sickLogo.setX(725);
		sickLogo.setY(50);
		layout.getChildren().add(sickLogo);
		
		


		ImageView runImage = new ImageView("run.png");
		animate = new Button("Run Animation", runImage);
		animate.setFont(new Font("Arial", 14));
		animate.setLayoutX(800);
		animate.setLayoutY(250);
		animate.setOnAction(new EventHandler<ActionEvent>() {
			public void handle1(ActionEvent e) { }

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("animated");
				try {
					josephus(layout, people, people, 0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// ****************************
		Line line = new Line(700, 0, 701, 600);
		line.setStroke(Color.BLACK);
		line.setStrokeWidth(1);

		layout.getChildren().add(animate);
		layout.getChildren().add(line);

	}

	public void makeSliders(Pane layout, Person[] p, int current) {
		// ****************************
		Slider nPeople = new Slider(2, 30, current);
		nPeople.setTranslateX(800);
		nPeople.setTranslateY(200);
		nPeople.setShowTickLabels(true);
		nPeople.setShowTickMarks(true);
		nPeople.setBlockIncrement(1);
		nPeople.setMajorTickUnit(5);
		nPeople.setMinorTickCount(23);
		nPeople.setSnapToTicks(true);
		nPeople.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				nPeople.accessibleTextProperty().setValue(String.valueOf(newValue.intValue()));
			}
		});

		// ****************************

		Label nLabel = new Label("People in circle: " + Integer.toString((int) nPeople.getValue()));
		nPeople.setOnMouseReleased(event -> {

			int n = (int) nPeople.getValue();
			clearCircle(layout, n, p);
			makeCircle(layout, n);
			generate(layout, 2, n); // k = 2
		});

		nPeople.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				nLabel.textProperty().setValue("People in circle: " + String.valueOf(newValue.intValue()));
			}
		});
		nLabel.setFont(new Font("Arial", 14));
		nLabel.setTranslateX(808);
		nLabel.setTranslateY(170);
		layout.getChildren().addAll(nPeople, nLabel);
		nLabel.accessibleTextProperty().setValue("n");

		numPeople = (int) nPeople.getValue();

	}

	public void makeCircle(Pane layout, int n) { // initialize people[]

		int centerX = 350;
		int centerY = 300;
		int radius = 200;

		Circle circle = new Circle(centerX, centerY, radius, Color.LIGHTGRAY);

		layout.getChildren().add(circle);

		this.people = new Person[n];
		Text[] circleLabels = new Text[n];
		for (int i = 0; i < n; i++) {
			int x = (int) findChairX(radius, i, n) + centerX;
			int y = (int) findChairY(radius, i, n) + centerY;
			//System.out.println("(" + x + "," + y + ")");
			people[i] = new Person(x, y, i+1);
			layout.getChildren().add(people[i].iView);
			people[i].iView.relocate(x - 25, y - 25);
			
			//janky circle labels
			circleLabels[i] = new Text(String.valueOf((people[i].id)));
			circleLabels[i].setX(x);
			circleLabels[i].setY(y);
			layout.getChildren().add(circleLabels[i]);
		}

	}

	public void updateCircle(Pane layout, int n, Person[] people) {
		
		clearCircle(layout, n, people);

		int centerX = 350;
		int centerY = 300;
		int radius = 200;

		Circle circle = new Circle(centerX, centerY, radius, Color.LIGHTGRAY);

		layout.getChildren().add(circle);
		Text[] circleLabels = new Text[n];
		for (int i = 0; i < n; i++) {
			int x = (int) findChairX(radius, i, n) + centerX;
			int y = (int) findChairY(radius, i, n) + centerY;
			//System.out.println("(" + x + "," + y + ")");
			layout.getChildren().add(people[i].iView);
			people[i].iView.relocate(x - 25, y - 25);
			
			//janky circle labels
			circleLabels[i] = new Text(String.valueOf((people[i].id)));
			circleLabels[i].setX(x);
			circleLabels[i].setY(y);
			layout.getChildren().add(circleLabels[i]);
		}

	}

	public double findChairX(int r, int currentPoint, int totalPoints) { // we used two algorithms for this hoe
		double theta = ((Math.PI * 2) / totalPoints);
		double angle = (theta * currentPoint);

		return (r * Math.cos(angle));
	}

	public double findChairY(int r, int currentPoint, int totalPoints) { // we used two algorithms for this hoe
		double theta = ((Math.PI * 2) / totalPoints);
		double angle = (theta * currentPoint);

		return (r * Math.sin(angle));
	}

	public void generate(Pane layout, int k, int n) {
		Label predictionLabel;
		boolean[] b = numToBinary(n);
		binaryToNum(b);
		swap(b);
		int prediction = binaryToNum(b);

		predictionLabel = new Label("Prediction: Winning chair = " + String.valueOf(prediction));
		System.out.println("Prediction: Chair "+ String.valueOf(prediction));
		predictionLabel.setTranslateX(800);
		predictionLabel.setTranslateY(200);

		layout.getChildren().add(predictionLabel);

	}

	public static boolean[] numToBinary(int n) { // Nikki
		BigInteger bi = BigInteger.valueOf(n);
		String s = bi.toString(2);
		//System.out.println(s);
		boolean b[] = new boolean[s.length()];

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				b[i] = true;
			} else {
				b[i] = false;
			}
			//System.out.print(b[i] + " ");
		}
		System.out.println();
		return b;
	}

	public static void swap(boolean[] b) { // Nikki
		boolean temp = b[0];
		for (int i = 0; i < b.length - 1; i++) {
			b[i] = b[i + 1];
		}
		b[b.length - 1] = temp;

		for (int i = 0; i < b.length; i++) {
			if (b[i]) {
				//System.out.print("1 ");
			} else {
				//System.out.print("0 ");
			}
		}

		//System.out.println();
		//System.out.println("Swapped... ");
	}

	public static int binaryToNum(boolean[] b) { // Nikki
		int result = 0;
		int p = 1;
		for (int i = b.length - 1; i >= 0; i--) {
			// System.out.println(p); //testing
			if (b[i]) {
				result += p;
			}
			p *= 2;
		}
		//System.out.println(result);
		return result;
	}

	public static void print(int x) {
		System.out.println(x);
	}

}
