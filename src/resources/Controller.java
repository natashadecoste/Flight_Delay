package resources;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
	public static String origin;
	public static String destination;
	
	@FXML
	private static Label result;


	
	
	public static void setOrigin(String choice){
		origin = choice;
		System.out.println("Origin changed to " + origin);
		
	}
	
	public static void setDestination(String choice){
		destination = choice;
		System.out.println("Destination choosen is " + destination);
	}
}
