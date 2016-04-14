package resources;



public class DirectedEdge {
	private Vertex start;
	private Vertex finish;
	private double weight;
	private double n;
	
	/**
	 * The Constructor for the Directed Edge Object
	 * @param start 	The Vertex that is is starting from.
	 * @param finish	The Vertex that is the destination.
	 * @param arrDelay	The arrival delay from the start to the finish.
	 * @param depDelay	The departure delay from this start airport to the finish.
	 */
	DirectedEdge(Vertex start, Vertex finish, String arrDelay, String depDelay){
		//System.out.println("new edge");
		this.start = start;
		this.finish = finish;
		this.weight = (Double.parseDouble(arrDelay) + Double.parseDouble(depDelay))/2;	//the weight is the average of the departure delay and the arrival delay
	}
	
	/**
	 * Returns the Vertex that this directed edge starts at.
	 * @return A Vertex object.
	 */
	public Vertex start(){
		return this.start;
	}
	
	/**
	 * Returns the Vertex that this directed edge ends at.
	 * @return A Vertex object.
	 */
	public Vertex finish(){
		return this.finish;
	}
	
	/**
	 * 
	 * @return
	 */
	public double weight(){
		return this.weight;
	}
	
	public String toString(){
		return this.start + " ---> " + this.finish + " with weight: " + this.weight;
	}
	
	public void weightUpdate(String x, String y){
		n ++;
		this.weight += (((Double.parseDouble(x)+Double.parseDouble(y)) - this.weight)/this.n);
	}

/*	public static void main(String[] args) {
		Vertex x = new Vertex("JFK","New York City");
		Vertex y = new Vertex("AYZ","Arizona");
		DirectedEdge r = new DirectedEdge(x,y,"66", "-8");
		System.out.println(r);
		
		


	}*/

}
