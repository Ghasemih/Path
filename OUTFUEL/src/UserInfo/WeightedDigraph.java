package UserInfo;

//Work cited: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/EdgeWeightedGraph.java.html
public class WeightedDigraph {
private final int V;                
private int E;                      
private Bag<DirectedEdge>[] adj;    
private int[] indegree;             
//Parameter , the number of vertices in the graph. 
public WeightedDigraph(int V) {
   this.V = V;
   this.E = 0;
   this.indegree = new int[V];
   adj = (Bag<DirectedEdge>[]) new Bag[V];
   for (int v = 0; v < V; v++)
       adj[v] = new Bag<DirectedEdge>();
}
public int V() {
   return V;
}

public int E() {
   return E;
}
private void validateVertex(int v) {
   if (v < 0 || v >= V)
       throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
}

public void addEdge(DirectedEdge e) {
   int v = e.from();
   int w = e.to();
   validateVertex(v);
   validateVertex(w);
   adj[v].add(e);
   indegree[w]++;
   E++;
}

public Iterable<DirectedEdge> adj(int v) {
   validateVertex(v);
   return adj[v];
}
public Bag<DirectedEdge>[] getadj(){
		return adj; 
}
public int outdegree(int v) {
   validateVertex(v);
   return adj[v].size();
}

public int indegree(int v) {
   validateVertex(v);
   return indegree[v];
}

public Iterable<DirectedEdge> edges() {
   Bag<DirectedEdge> list = new Bag<DirectedEdge>();
   for (int v = 0; v < V; v++) {
       for (DirectedEdge e : adj(v)) {
           list.add(e);
       }
   }
   return list;
} 

public String toString() {
   String s = "";
   s += (V + " " + E + "\n");
   for (int v = 0; v < V; v++) {
       for (DirectedEdge e : adj[v]) {
           s += e + "  ";
       }
       s += "\n";
   }
   return s.toString();
}

}