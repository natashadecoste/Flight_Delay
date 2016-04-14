package resources;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.Stack;

public class JUnitTest {
	// tested with origin JFK and destination LAX

	@Before
	public void setUp() throws Exception {
		Graph g = new Graph();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testBFS(){
		Graph p = new Graph();
		DijkstraAlgorithm r = new DijkstraAlgorithm(p, p.getVertex("JFK"));
		
		Iterable<Vertex> path = r.pathTo(p.getVertex("LAX"));
		
		Iterator<Vertex> iter = path.iterator();
		List<Vertex> copy = new ArrayList<Vertex>();
		while (iter.hasNext())
		    copy.add(iter.next());
		
		Vertex a;
		for(int i = 1; i<copy.size(); i++){
			a = copy.get(i-1);
			assert(copy.get(i).getEdgeWeight(a) != (Double) null);	//if there is a connection between the cities then the edge weight wont return as (Double) null
			}
			

	}
	
}
