package UserInfo;

import java.util.Stack;
/**
 * This class implements the Dijkstras algorithm .
 * Works cited: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/DijkstraSP.java.html
 * @author Alessandra Guerinoni
 *@version 1.0 
 */
public class DijkstraSP {
    private double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
    private DirectedEdge[] eTo; 
    private IndexMinPQ<Double> pq;    // priority queue of vertices
    private String[] res; 
    private GasStationADT[] gasArray;
    private VehicleADT car; 
    private int s; 
    private int dest; 
    private double distance;
   
	/**
	 * 
	 *@param Weighted Digraph G, the graph to find a path 
	 *@param the cities array, to represent the  source vertex and destination vertex as a city.  
	 */
    public DijkstraSP(WeightedDigraph G, int s, int dest, double distance, VehicleADT car, GasStationADT[] gasarray) {

    		this.s = s;
    		this.dest = dest; 
    		this.distance = distance; 
    		this.car = car;
    		this.gasArray = gasarray;
    		res = new String[G.V()];
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        eTo = new DirectedEdge[G.V()];
        validateVertex(s);
        // initializes the distance as positive infinity 
        // created a seperate array to keep track of the meals from previous edges 
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
            res[v] = null; 
        }  		
        distTo[s] = 0.0;
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
        
    }
    
    private void relax(WeightedDigraph G, int v) {
    		for(DirectedEdge e: G.adj(v))
    		{
    			int w = e.to();
    			if(distTo[w] > distTo[v] + e.weight()) {
    				eTo[w] = e;
    			}
    			if(e.weight() > car.milesInTank(car.getCurrentGas())) continue; 
    			if(distTo[w] > distTo[v] + e.weight()) {
	    				distTo[w] = distTo[v] + e.weight(); 
	    				edgeTo[w] = e;
	    				if(pq.contains(w)) pq.changeKey(w, distTo[w]);
	    				else 	pq.insert(w, distTo[w]);
	    				
    			}
        }
    }

    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
 // The shortest path 
    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        //if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = eTo[v]; e != null; e = eTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
	/**
	 * 
	 * For a given vertex, determines whether it is valid, if its within the number of vertices given from the constructor.  
	 * @param int v, if the vertex v is a valid vertex. 
	 */
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    public String toString() {
    		
    		String str ="";
    		if(car.milesInTank(car.getCurrentGas()) > distance) {
    			str += "You can get to your destination without going to a gas station!";
    			return str; 
    		} 
           // str += .name()+ " to" + " " + CitiesArray[21].name() +  " " + distTo(21) + ": ";
            for (DirectedEdge e : pathTo(dest)) {
                str += "Path: "+ e + "| ";
            }
            str += "\n";

        return str; 
    }	
}
