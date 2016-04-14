package resources;


import edu.princeton.cs.algs4.Bag;

public class Vertex {
	String airport;	//the airport identifier 
	String city;	//the full city and state of the airport
	private Vertex edgeTo;		//for BFS and DFS and Dijkstra's Algorithm
	Bag<DirectedEdge> adj = new Bag<DirectedEdge>(); 	//to hold the adjacent vertices
	public Vertex left,right;	//to hold them in a binary search tree
	public int N;
	private double distTo;
	private boolean onQ;
	private boolean onStack;
	private boolean marked;
	
	/**
	 * The Constructor for the Vertex Object.
	 * @param airport	The 3 letter airport identifier.
	 * @param city		The City and State the airport is located in.
	 */
	Vertex(String airport, String city){		//constructing the Vertex Object
		this.airport = airport;
		this.city = city;
		this.edgeTo = null;
		this.N = 1;
		this.left = null;
		this.right = null;
		this.distTo = Double.POSITIVE_INFINITY;
		this.onQ = false; 	//for bellford algorithm
		this.onStack = false; // for Negative cycles
		this.marked = false;
		//System.out.println("Object created!");	
	}
	
	
	/**
	 * Assigning the Vertex edgeTo reference for finding the path.
	 * @param x The Vertex that is pointing to this edge (with a directed edge).
	 */
	public void edgeTo(Vertex x){	//assigning the edge that is pointing to this edge
		this.edgeTo = x;
	}
	
	public boolean onQ(){
		return this.onQ;
	}
	
	public void add2Queue(){
		this.onQ = true;
	}
	
	public void removeFromQueue(){
		this.onQ = false;
	}
	
	public void push(){
		this.onStack = true;
	}
	
	public void pop(){
		this.onStack = false;
	}
	
	public boolean onStack(){
		return this.onStack;
	}
	
	public boolean isMarked(){
		return this.marked;
	}
	
	public void mark(){
		this.marked = true;
	}
	
	public void unmark(){
		this.marked = false;
	}
	public double getEdgeWeight(Vertex p){	//getting the edge between two vertices 
		//System.out.println("getEdgeBetween "+ this + " ADJ SIZE: "+ this.adj.size());
		for(DirectedEdge v: p.adj){
			//System.out.println(v.start().getEdgeTo());
			if(v.finish().equals(this)){
				return v.weight();
			}
			
		}
		return (Double) null;
	}
	
	public String airport(){
		return this.airport;
	}
	
	public String city(){
		return this.city;
	}
	
	public double distTo(){		//used to retrieve the variable distTo (used in finding the shortest path)
		return this.distTo;
	}
	
	public void dist(double s){	//to assign the distTo variable for the shortest path algorithms
		this.distTo = s;
	}
	public int size(){
		return N;
	}
	
	/**
	 * Returns the Vertex that is pointing to this one.
	 * @return A Vertex object.
	 */
	public Vertex getEdgeTo(){		//to return the Vertex that points to this vertex
		return this.edgeTo;
	}
	
	public Bag<DirectedEdge> getAdj(){
		return this.adj;
	}
	
	/**
	 * Printing out the Adjacent Edges to the console.
	 */
	public void printAdj(){		//to print out the adjacent edges from this city.
		System.out.println("Adjacent to "+ this.airport + ": ");
		for(DirectedEdge s: this.adj){
			System.out.println(s);
		}
	}
	
	public int addAdj(Vertex b, String delay, String delay2){	//adding an adjacent edge connecting this vertex to another
		DirectedEdge temp = new DirectedEdge(this, b, delay, delay2);
		for(DirectedEdge x : this.adj){
			if (x.finish().equals(b)){
				//System.out.println("updating");
				x.weightUpdate(delay, delay2);
				return 1;
			}
		}
		adj.add(temp);
		//System.out.println("Just added edge from "+ this + " to "+ b);
		return 1;
		//printAdj();
		
	}
	
	public void addAdj(DirectedEdge e){
		adj.add(e);
	}
	
	/**
	 * To override the compareTo in order to sort the cities in a tree.
	 * @param x	The Vertex to compare it to.
	 * @return	An integer based on the relationship between this Vertex and Vertex x.
	 */
	public int compareTo(Vertex x){
		int compare = this.airport.compareTo(x.airport);
		if (compare < 0){
			return -1;
		}
		else if(compare > 0){
			return 1;
		}
		else {
			return 0;
		}
	}	
	
	/**
	 * The String representation of the Vertex Object.
	 */
	public String toString(){					//defining the string representation of the Vertex object
		return "Airport: " + this.airport + " located @ " + this.city;
	}
	
/*	public static void main(String[] args) {
		Vertex x = new Vertex("HFK","New York City");
		Vertex y = new Vertex("JFK", "Boston");
		System.out.println(x.compareTo(y));
		System.out.println(y.compareTo(x));
		
		

	}*/

}
