package resources;


import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedDirectedCycle {
    private Stack<DirectedEdge> cycle;    // directed cycle (or null if no such cycle)
    private Graph g;
    
	EdgeWeightedDirectedCycle(Graph g){
		this.g = g;
		Iterable<Vertex> t = g.allVertices();
		for (Vertex x: t){
			if(!x.isMarked()){
				dfs(g,x);}
				
			}
		
		assert check(g);


		
	}
	
	private void dfs(Graph g, Vertex v) {
		v.push();
		v.mark();
		

		for (DirectedEdge e : v.getAdj()) {
			Vertex w = e.finish();
			
			if(cycle!=null) {return;}
			
			else if(!w.isMarked()){
				w.edgeTo(e.start());
				dfs(g,w);
			}
			
			else if(w.onStack()){
				cycle = new Stack<DirectedEdge>();
				while(e.start() != w){
					cycle.push(e);
					//reassigning e to the edge before this edge
					for(DirectedEdge t : e.start().getEdgeTo().getAdj()){
						if(t.finish() == e.start()){
							e = t;
						}
					}
					cycle.push(e);
					return;
					
				}
				
			}
			

		}
		v.pop();

	}
	
	
    public boolean hasCycle() {
        return cycle != null;
    }
    
    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
    
    private boolean check(Graph G){
    	if(hasCycle()){
    		DirectedEdge first = null, last = null;
    		for(DirectedEdge e : cycle()){
    			if(first == null) first = e;
    			if(last!= null){
    				if(last.finish() != e.start()){
    					System.err.print("Error");
    					return false;
    				}
    			}
    			last = e;
    		}
    		if(last.finish() != first.start()){
    			System.err.print("Error 2");
    			return false;
    		}
    	}
    	return true;
    }
    

}
