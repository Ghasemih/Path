package UserInfo;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Compiler {
	private GasStationADT[] gasarray;
	private String place1;
	private String place2; 
	private double fromto; 
	
	public Compiler(String place1, String place2, GasStationADT[] gasarray) {
		this.place1 = place1; 
		this.place2 = place2; 
		this.gasarray = gasarray; 
	}
	// gas array will be the array with gas station locations in sorted order ? 
    public WeightedDigraph getWeightedDigraph() throws Exception {
    		int count = 0; 
    		int vertices = 0;  
            Map<String, Double> coords;
            coords = OpenStreetMapUtils.getInstance().getCoordinates(place1);
            double latitude = coords.get("lat");
            double longitude = coords.get("lon");
            Map<String, Double> coords2;
            coords2 = OpenStreetMapUtils.getInstance().getCoordinates(place2);
            double latitude2 = coords2.get("lat");
            double longitude2 = coords2.get("lon");
    		for(int i = 0; i < gasarray.length; i++) {
    			if(gasarray[i].getLatitude() < latitude2 + 1 && gasarray[i].getLatitude() > latitude2 -1 && gasarray[i].getLatitude() < latitude + 1  && gasarray[i].getLatitude() > latitude - 1 && gasarray[i].getLongitude() < longitude2 + 1  && gasarray[i].getLongitude() > longitude2-1 && gasarray[i].getLongitude() < longitude + 1&& gasarray[i].getLongitude() > longitude - 1) {
    				vertices++;		
    			}
    		}
    		System.out.println("number:" + vertices);
    		GasStationADT[] newarray = new GasStationADT[vertices]; 
    		int a = 0;
    		int b =0; 
    		while(true) {
    			if(gasarray[a].getLatitude() < latitude2 + 1 && gasarray[a].getLatitude() > latitude2 -1 && gasarray[a].getLatitude() < latitude + 1  && gasarray[a].getLatitude() > latitude - 1 && gasarray[a].getLongitude() < longitude2 + 1  && gasarray[a].getLongitude() > longitude2-1 && gasarray[a].getLongitude() < longitude + 1&& gasarray[a].getLongitude() > longitude - 1) { 
    				 newarray[b]= gasarray[a]; 
    				 b++;
    			}
    			a++; 
    			if(a == gasarray.length) {break;} 
    			}
    		WeightedDigraph G = new WeightedDigraph(vertices + 2); 
    		//creates an edge from source to destination 

    		for(int i = 0; i < newarray.length; i++) {
    			for (int j = i+1;  j < newarray.length;  j++) {
    				double distance1 = DistanceGoogle.distance(newarray[i].getAddress(), newarray[j].getAddress());
    				DirectedEdge ed1 = new DirectedEdge(newarray[i].getStationName(), newarray[j].getStationName(), distance1, newarray); 
    				G.addEdge(ed1);
    			}
        		double distance = DistanceGoogle.distance(place1, newarray[0].getAddress());
        		DirectedEdge ed = new DirectedEdge(place1, newarray[i].getStationName(), distance, newarray); 
        		G.addEdge(ed);
    			double distdest = DistanceGoogle.distance(newarray[i].getAddress(), place2);
    			DirectedEdge edge = new DirectedEdge(newarray[i].getStationName(), place2, distdest, newarray); 
				G.addEdge(edge);
    			
    		}

    		System.out.println(G);
    		System.out.println("digraph");
    return G; 
}
    public String getsource() {
    		return this.place1; 
    }
    public String getdestination() {
		return this.place2; 
}
    public double getdistance() throws IOException {
		return DistanceGoogle.distance(place1, place2);
}
    public int efrom() {
		double newdistance = 0;
		DirectedEdge edges = new DirectedEdge(place1, place2, newdistance, gasarray);
		return edges.from();
    }
    public int eto() {
		double newdistance = 0;
		DirectedEdge edges = new DirectedEdge(place1, place2, newdistance, gasarray);
		return edges.to();
    }
    public static void main(String[] args) throws Exception {
	  	GasStationADT ar1 = new GasStationADT("station1", 1.4, "subs", "269 Potts Terrace",1.5, "cool",true,43.486756,-79.877208); 
	  	GasStationADT ar2 = new GasStationADT("station2", 1.4, "subs", "281 Potts Terrace",2.5, "cool",true,43.486793, -79.876889); 
	  	GasStationADT ar3 = new GasStationADT("station3", 1.4, "subs", "689 Savoline Blvd",2.5, "cool",true,43.487091, -79.878294); 
	  	GasStationADT[] array = {ar1,ar2,ar3}; 
	  	VehicleADT car = new VehicleADT(2017,"bmw","kk","premium",4,0.06,18);
	  	Compiler graph = new Compiler("6520 Derry Rd", "735 Reece Ct", array);
		WeightedDigraph G = graph.getWeightedDigraph(); 
		System.out.println(graph.getdistance());
		System.out.println(car.milesInTank(car.getCurrentGas())); 
		DijkstraSP path = new DijkstraSP(G, graph.efrom(), graph.eto(), graph.getdistance(),car,array);
		System.out.println(path);
    }
} 