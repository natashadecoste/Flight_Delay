package resources;

import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.Queue;



public class Graph {
	private int E;	//the number of edges
	private int V;	//the number of vertices
	private Vertex root;

	Graph(){	//graph constructor
		this.E = 0;
		this.V = 0;
		this.root = null;
		
	}
	


	public Graph(Graph g){
		Iterable<Vertex> t = g.allVertices();
		for(Vertex x: t){
			if(x.getEdgeTo()!=null){
				put((g.getVertex(x.airport)).airport(),g.getVertex(x.airport).city());
				//add the edge
				//from x to x's getEdge to
				//need to find the directed edge 
				for(DirectedEdge e: g.getVertex(x.airport).adj){
					if(e.finish() == x.getEdgeTo()){
						(this.getVertex(x.airport)).addAdj(e);
					}
				}
				
			}
			
		}
		//bellmanGraph();
	}
	
/*	public Graph bellmanGraph(){
		return this;
	}
*/
	
	
	
	
	
	
	
	public void put(String airport,String city){
		Vertex temp = new Vertex(airport, city);
		this.root = put(root, temp);
	}
	
	private Vertex put(Vertex n, Vertex t){
			if(n == null) {
				this.V += 1;
				return t;
			}
			//System.out.println("ver: "+ n.airport + " string: "+ t.airport);
			//System.out.println("compare " + n.compareTo(t));
			if(t.compareTo(n) < 0){ n.left = put(n.left, t);}
			else if(t.compareTo(n) > 0){ n.right = put(n.right, t);}
			else{ n.airport = t.airport; n.city = t.city;}
			n.N = size(n.left) + size(n.right)+ 1;
			return n;
	}
	
	public void addEdges(ArrayList<String []> x){
		//need to read from connectedCities and add directed weighted edges from each city to the other and the same the other direction
		for(int i = 1; i< x.size();i++){
			//System.out.println(x.get(i).length);
			String c = (x.get(i)[1].concat(x.get(i)[2])).replace("\"", "");
			String d = (x.get(i)[4].concat(x.get(i)[5])).replace("\"", "");
			//System.out.println(x.get(i)[0]+ "  " + c);
			//System.out.println(x.get(i)[3]+ "  " + d);

			


			if(x.get(i).length >= 10){
				put(x.get(i)[0],c);
				put(x.get(i)[3],d);
				getVertex(x.get(i)[0]).addAdj(getVertex(x.get(i)[3]),x.get(i)[7],x.get(i)[9]);
				E+=1;
			}
		}
	}
	
	
	public int size(Vertex x){
		if (x==null){return 0;}
		else return x.size();
	}
	
	
	public Vertex getVertex(String airport){
		return getVertex(root,airport);
		
	}
	
	
	private Vertex getVertex(Vertex x, String airport){
		//if(x == null) {return null;}
		if (airport.compareTo(x.airport()) < 0)  { return getVertex(x.left, airport);}
		if (airport.compareTo(x.airport()) > 0) { return getVertex(x.right,airport);}
		else return x;
	}
	
	public Iterable<Vertex> allVertices(){
		Queue<Vertex> queue = new Queue<Vertex>();
		allVertices(root, queue);
		return queue;
	}
	
	private void allVertices(Vertex x, Queue<Vertex> queue){
		if(x == null){ return;}
		allVertices(x.left,queue);
		allVertices(x.right,queue);
		
	}
	
	public int V(){
		return this.V;
	}
	
	public int E(){
		return this.E;
	}

/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		inputHandler i = new inputHandler("info/2XB3_AirplaneData.csv");
		ArrayList<String[]> information = i.run();
		Graph g = new Graph();
		g.addEdges(information);

		System.out.println("******************************");
		g.getVertex("JFK").printAdj();


		

	} */

}
