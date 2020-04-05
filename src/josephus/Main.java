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
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent> {
	//window 	stage
	//scene 	content inside stage 

	Button predict;
	Button animate;
	
	public static void main(String[] args) {	//Nikki, Andrew, Matt
		launch(args); //sets everything up and runs start
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Josephus Problem");
		
		predict = new Button();
		predict.setText("Generate Prediction");
		predict.setLayoutX(800);
		predict.setLayoutY(150);
		predict.setOnAction(new EventHandler<ActionEvent>() { public void
			handle1(ActionEvent e) {}

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("generated");
				
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
		
		Label nLabel = new Label("People in circle: "+ Double.toString(nPeople.getValue()));
		nLabel.setTranslateX(800);
		nLabel.setTranslateY(450);
		
		
		//****************************
		Slider kSkips = new Slider(0, 5, 2);
		kSkips.setTranslateX(800);
		kSkips.setTranslateY(400);
		//need a label that reads the value
		//****************************
		
		//****************************
		
		//****************************
		
		//stackpane: set location button.setTranslateX(#)
		//Pane: 	 set location butotn.setLayoutX(#);	
				
		Pane layout = new Pane(); 			//simple layout		... can be borderpane if we decide to be cool?
		layout.getChildren().add(predict);
		layout.getChildren().add(animate);
		layout.getChildren().add(line);
		//layout.getChildren().add(nPeople);
		//layout.getChildren().add(nLabel);
		layout.getChildren().add(kSkips);
		layout.getChildren().addAll(nPeople, nLabel);
		nLabel.accessibleTextProperty().setValue("n");
		
		
		
		Scene scene =  new Scene(layout, 1000, 600);		//set dimensions of scene
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		System.out.println("event! nice.");
	}
	
	
	
}


