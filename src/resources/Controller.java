package resources;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Stack;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
	public static String origin;
	public static String destination;
	
	@FXML
	private static Label result;


	
	
	public static void setOrigin(String choice){
		origin = choice;
		
		//to test 
		//System.out.println("Origin changed to " + origin);
		
	}
	
	public static void setDestination(String choice){
		destination = choice;
		//System.out.println("Destination choosen is " + destination);
	}
	
	//creates the graph and runs the back end as well
	public void start(){
		inputHandler i = new inputHandler("info/2XB3_AirplaneData.csv");
		ArrayList<String[]> information = i.run();
		Graph g = new Graph();
		g.addEdges(information);
		//DijkstraAlgorithm m = new DijkstraAlgorithm(g,g.getVertex(origin));
		
		BellmanFord m = new BellmanFord(g,g.getVertex(origin));
		//System.out.println(n.hasPathTo(g.getVertex(destination)));
		if(m.hasPathTo(g.getVertex(destination))){
			Stack<Vertex> path = m.pathTo(g.getVertex(destination));
			View.printPath(path);
		}
		else{
			View.printErr();
		}	
		
		//to test front end is working
/*		Stack<Vertex> path = new Stack<Vertex>();
		path.push(g.getVertex("CLE"));
		path.push(g.getVertex("MDW"));
		path.push(g.getVertex("LAX"));
		
		View.printPath(path);*/
		
		
	}

}
