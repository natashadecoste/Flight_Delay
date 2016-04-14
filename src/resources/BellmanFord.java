package resources;


import java.util.ArrayList;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BellmanFord {
	private Queue<Vertex> queue;
	private Graph g;
	Iterable<DirectedEdge> cycle;
	private int cost;
	private Vertex source;
	
	BellmanFord(Graph g, Vertex s){
		//System.out.println("created");
		this.source = s;
		this.g = g;
		queue = new Queue<Vertex>();
		
		s.dist(0.0);

		//s.dist(0.0);
		queue.enqueue(s);
		s.add2Queue();
		//System.out.println("HAS NEG CYCLE:" + hasNegativeCycle());
		while(!queue.isEmpty() && !hasNegativeCycle()){
			Vertex v = queue.dequeue();
			v.removeFromQueue();
			relax(g,v);
		}
		assert check(g,s);
		//System.out.println("relaxed");
	}
	
	//relaxing the whole graph
	private void relax(Graph g, Vertex v){
		//System.out.println("RELAZ FUNC");
			for(DirectedEdge e: v.getAdj()){
				//System.out.println("HERE");
				Vertex w = e.finish();
				if(w.distTo() > v.distTo() + e.weight()){
					w.dist(v.distTo()+e.weight());
					w.edgeTo(e.start());
					//System.out.println("Relaxing: "+ e);
					if(!w.onQ()){
						queue.enqueue(w);
						w.add2Queue();
					}
				}
				if(cost++ % g.V() == 0){
					//System.out.println("LOOKING 4 NEG CYCLE");
					findNegativeCycle();
					if(hasNegativeCycle()) {
						//System.out.println("has neg cycle :(");
						return;}
				}
			}

	
			
	}
	
	private void findNegativeCycle(){
		Graph spt = new Graph(g);
		EdgeWeightedDirectedCycle cf;
		cf = new EdgeWeightedDirectedCycle(spt);
		
		cycle = cf.cycle();
	}
	
	public boolean hasNegativeCycle(){
		return cycle != null;
	}
	
	public Iterable<DirectedEdge> negativeCycle(){
		return cycle;
	}
	
	//updating the distTo (it is within the given vertex)
	public double distTo(Vertex v){
		return v.distTo();

	}
	
	//if a path exists to a vertex v
	public boolean hasPathTo(Vertex v){
		return distTo(v) < Double.POSITIVE_INFINITY;
	}
	
	//to get the path to a vertex v
	public Stack<Vertex> pathTo(Vertex v){
		if(hasNegativeCycle()) throw new UnsupportedOperationException("Negative Cost cycle exists");
		if(!hasPathTo(v)){ return null;}

		
		Stack<Vertex> path = new Stack<Vertex>();
		for(Vertex x = v; x != source; x = x.getEdgeTo()){
			path.push(x);
		}

		//path.push(source);
		return path;
	}
	
	private boolean check(Graph g, Vertex s){
		if(hasNegativeCycle()){
			double weight = 0.0;
			for(DirectedEdge e: negativeCycle()){
				weight += e.weight();
			}
			if(weight>=0.0){
				System.err.println("error: weight of negative cycle: "+ weight );
				return false;
			}
		}
		
		else{
			if(s.distTo() != 0.0 || s.getEdgeTo() != null){
				 System.err.println("distanceTo[s] and edgeTo[s] inconsistent");
	             return false;
				
			}
			for(Vertex x: g.allVertices()){
				if(x == s) continue;
				if(x.getEdgeTo() == null && x.distTo() != Double.POSITIVE_INFINITY){
                    System.err.println("distTo[] and edgeTo[] inconsistent");
                    return false;
				}
			}
			
			for(Vertex v: g.allVertices()){
				for(DirectedEdge e: v.getAdj()){
					Vertex w = e.finish();
					if(v.distTo()+e.weight() < w.distTo()){
                        System.err.println("edge " + e + " not relaxed");
                        return false;
					}
				}
			}
			for(Vertex w : g.allVertices()){
				if(w.getEdgeTo() == null ) continue;
				DirectedEdge e = null;
				for(DirectedEdge t: w.getEdgeTo().getAdj()){
					if(t.finish() == w) e =t;
				}
				Vertex v = e.start();
				if(w!= e.finish()) return false;
				if(v.distTo() + e.weight() != w.distTo()){
                    System.err.println("edge " + e + " on shortest path not tight");
                    return false;
				}
			}
		}
        StdOut.println("Satisfies optimality conditions");
        StdOut.println();
        return true;
	}
/*	
	public static void main(String[] args) {
		inputHandler i = new inputHandler("info/2XB3_AirplaneData.csv");
		ArrayList<String[]> information = i.run();
		Graph g = new Graph();
		g.addEdges(information);
		System.out.println(g.V());
		System.out.println(g.E());
		BellmanFord n = new BellmanFord(g,g.getVertex("ABE"));
		System.out.println(n.hasPathTo(g.getVertex("ATL")));
		*/
/*		if(n.hasPathTo(g.getVertex("LAX"))){
			Iterable<Vertex> path = n.pathTo(g.getVertex("LAX"));
			View.printPath(path);
			for(Vertex x : path){
				System.out.println(x);
			}
		}
		else{
			System.err.println("ERRRRRRRR");
		}

	} */

}
