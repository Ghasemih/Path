package UserInfo;

//Works cited: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/DirectedEdge.java.html
public class DirectedEdge { 
 private int v;
 private  int w;
 private final double weight;
 private final GasStationADT[] gasarray;  
 private final String place1; 
 private final String place2; 
//parameters - starting city, final city, the menu item of the edge, the weight of the edge, 
 public DirectedEdge(String place1, String place2, double weight, GasStationADT[] gasarray) {
 		this.v = (int) Double.POSITIVE_INFINITY; 
 		this.w = (int) Double.POSITIVE_INFINITY;
 		this.place1 = place1; 
 		this.place2 = place2; 
 		this.weight = weight; 
 		this.gasarray = gasarray; 
 		for(int i =0 ; i < gasarray.length; i++) {
 			if(place1.toLowerCase().contains(gasarray[i].getStationName().toLowerCase())) {
 				this.v = i;
 				break; 
 			}
 		}
 		for(int i =0 ; i < gasarray.length; i++) {
 			if(place2.toLowerCase().contains(gasarray[i].getStationName().toLowerCase())) {
 				this.w = i;
 				break; 
 			}
 		}
 		if(this.v == (int) Double.POSITIVE_INFINITY) { this.v = gasarray.length; }
 		if(this.w == (int) Double.POSITIVE_INFINITY) { this.w = gasarray.length + 1; }
 
 }
 public String fromplace() {
 	return this.place1; 
 }
 public String toplace() {
 	return this.place2; 
 }

 /**
  * Returns the tail vertex of the directed edge.
  * @return the tail vertex of the directed edge
  */
 public int from() {
     return v;
 }

 /**
  * Returns the head vertex of the directed edge.
  * @return the head vertex of the directed edge
  */
 public int to() {
     return w;
 }

 public double weight() {
     return weight;
 }

 public String toString() {
     return fromplace() + "->" + toplace() + " Cost:" + String.format("%5.2f", weight);
 		//return v + "->" + w + String.format("%5.2f", weight);
 }

}