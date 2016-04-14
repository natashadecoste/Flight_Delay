package resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.princeton.cs.algs4.Stack;

public class DijkstraAlgorithm {
	private Vertex source;
	private ArrayList<Vertex> pq;
	private Graph g;
	private double totalTripCost;
	
	//constructor for the shortest path class
	DijkstraAlgorithm(Graph g, Vertex s){
		this.source = s;
		this.g= g;
		
		pq = new ArrayList<Vertex>();
		s.dist(0.0);
		pq.add(s);
		//relaxing all the edges in the graph
		while(!pq.isEmpty()){
			relax(g,delMin());
		}
	}
	
	 
	private Vertex delMin(){
		if(pq.size()>=1){
			Vertex temp = pq.get(0);
			for(int i = 1; i < pq.size(); i++){
				if(pq.get(i).distTo() < temp.distTo()){
					temp = pq.get(i);
				}
			}
			//System.out.println("temp : " + temp + "    dist to: " + temp.distTo());
			pq.remove(temp);
			//System.out.println("returning temp");
			return temp;
		}
		//System.out.println("returning null");
		return null;
		
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
		if(!hasPathTo(v)){ return null;}

		
		Stack<Vertex> path = new Stack<Vertex>();
		for(Vertex x = v; x != source; x = x.getEdgeTo()){
			path.push(x);
		}

		//path.push(source);
		return path;
	}
	
	
	//relaxing the whole graph
	private void relax(Graph g, Vertex v){
		//System.out.println("relaxing");
		for(DirectedEdge e: v.getAdj()){
			Vertex w = e.finish();
			if(w.distTo() > v.distTo() + e.weight()){
				w.dist(v.distTo()+e.weight());
				w.edgeTo(e.start());
				if(!pq.contains(w)){
					pq.add(w);
				}	
			}
		}

			

		
	}
	

/*	public static void main(String[] args) {
		System.out.println("Start");
		inputHandler i = new inputHandler("info/2XB3_AirplaneData.csv");
		ArrayList<String[]> information = i.run();
		Graph g = new Graph();
		g.addEdges(information);
		System.out.println(g.E());
		System.out.println(g.V());
		DijkstraAlgorithm d = new DijkstraAlgorithm(g, g.getVertex("ABE"));
		System.out.println("here");
		System.out.println(d.hasPathTo(g.getVertex("ATL")));
		
		Iterable<Vertex> path = d.pathTo(g.getVertex("ATL"));
		
		for(Vertex x: path){
			System.out.println(x);
		}

	}*/


}
