package UserInfo;
import java.util.Map;
import java.util.Scanner;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
public class DistanceGoogle {

	  private static final String API_KEY = "AIzaSyBDKTv6rqlnRPePpq4glG0ZOH8_Zpzwbwc";

	  OkHttpClient client = new OkHttpClient();

	  public String run(String url) throws IOException {
	    Request request = new Request.Builder()
	        .url(url)
	        .build();

	    Response response = client.newCall(request).execute();
	    return response.body().string(); }

	  public static double distance(String origin, String destination) throws IOException{
	    DistanceGoogle request = new DistanceGoogle();
	    String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins="+origin+"&destinations=" +destination+"&key=" + API_KEY;
	    String response = request.run(url_request);
	    int index = response.indexOf("value") + 9;
	    String strdis = response.substring(index, index+5); 
	    String dis1 = ""; 
	    for(int i =0; i < strdis.length(); i++) 
	    		if(Character.isDigit(strdis.charAt(i))) dis1 = dis1 + strdis.charAt(i); 

	    double dis = Double.parseDouble(dis1) /1000; 
	    return dis;
	  
	  }
	  
	  public static void main(String[] args) throws Exception {

		  GasStationADT[] array = Load.LoadGasStations();
		//  String Stationname, double price, String suburb, String Address, double distance, String gasTypes, boolean rewards, double latitude, double longitude
			Scanner original = new Scanner(System.in);
			System.out.print("Enter starting point: ");
		    String origin = original.nextLine();
		    UsingDistance.getArray(array, origin); 
			for (int i = 0; i < 157; i++)
			{
				//System.out.println(array[i]);
			}
//43.4919402,-79.882548,15.79 // 43.4855811,-79.8798548
	  }
	  }
