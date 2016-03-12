package resources;

import resources.Controller;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.*;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;

/**
 * The View class handles all the JavaFX controls and simulations. It communicates with the Controller Class.
 * 
 * @author Natasha DeCoste
 * @version 1.0
 * @since 2016-02-28
 */


public class View extends Application {
	private String origin = "";
	private String destination = "";
	private ComboBox comboDest;
	private ArrayList<String[]> options;
	
	

	
/**
 * Mandatory JavaFX application launch function.
 * 
 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			inputHandler test2 = new inputHandler("info/2XB3_AirplaneData.csv");
			options = test2.run();
			
			
			
			
			
			///////////////////////////////////////////////////////

			HashSet<String> hash = new HashSet<>();
			for(int i=0;i<options.size();i++){
				hash.add(options.get(i)[0]);
			}
			//list = (ObservableList<String>) hash;
			ObservableList<String> list;
			list = FXCollections.observableArrayList(hash);
			
			
			//origin combo box
			final ComboBox comboBox = new ComboBox(list);
			comboBox.setLayoutX(420);
			comboBox.setLayoutY(430);
			comboBox.setPrefWidth(100);
			comboBox.setPromptText("Origin");
			
			comboBox.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() { 
				//public void changed(ObservableValue<? extends String> ov,  String old_val, String new_val) { }

				@Override
				public void changed(ObservableValue observable, Object oldValue, Object newValue) {
					destination = null;
					origin = (String) newValue;
					comboDest.getItems().clear();
					Controller.setOrigin(origin);
					getDestinations();
				} });
			
			
			////////////////////////////////////////////////////////
			comboDest = new ComboBox();
			comboDest.setLayoutX(420);
			comboDest.setLayoutY(515);
			comboDest.setPrefWidth(100);
			comboDest.setPromptText("Destination");

			
			/////////////////////////////////////////////////////////
			
			
			
			Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
			Pane pane = new Pane();
			primaryStage.setTitle("Flight Delay");
			

			
			// The Plane Image
			ImageView plane = new ImageView();
		    plane.setImage(new Image("file:src/plane.png"));
		    plane.setX(-150);
		    plane.setY(185);
		    plane.setFitHeight(90);
		    plane.setPreserveRatio(true);
		    

			//Animating the plane 
			Timeline timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
			final KeyValue kv = new KeyValue(plane.xProperty(), 900);
			final KeyFrame kf = new KeyFrame(Duration.millis(15000), kv);
			timeline.getKeyFrames().add(kf);
			timeline.play();

			
			
			//setting the scene & stage
		    pane.getChildren().addAll(root,plane,comboBox,comboDest);
		    Scene scene = new Scene(pane, 800, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();


			} 
		catch (Exception e) {
			e.printStackTrace();
			}
		
	}
	
	/**
	 * To get the origin that the user selected
	 * @return A string of the Origin airport.
	 */
	public String getOrigin(){
		return origin;
	}
	
	/**
	 * Gets the destination options for the origin.
	 * 
	 */
	public void getDestinations(){
		
		HashSet<String> hashDest = new HashSet<>();
		for(int i=0;i<options.size();i++){
			if (options.get(i)[0].equals(origin)){
				hashDest.add(options.get(i)[3]);
			}
			
		}
		//list = (ObservableList<String>) hash;
		ObservableList<String> Destlist;
		Destlist = FXCollections.observableArrayList(hashDest);
		
		comboDest.getItems().clear();
		comboDest.getItems().addAll(Destlist);

		
		comboDest.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() { 
			//public void changed(ObservableValue<? extends String> ov,  String old_val, String new_val) { }

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				destination = (String) newValue;
				Controller.setDestination(destination);
			} });
		
		}
		

	
	public static void main(String[] args) {
		launch(args);
	}


}
