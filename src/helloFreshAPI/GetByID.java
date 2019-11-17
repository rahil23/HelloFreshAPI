package helloFreshAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetByID 
{
	
public static String  retrieveBookingID()
{
	String bookingId,fname,lname,roomid;

	ArrayList<String> xMediaTypeList = new ArrayList<>();
	ArrayList<String> bookingIdCollection = new ArrayList<String>(); 
		
		HttpResponse response = null;
		 HashMap<String,ArrayList<String>> resourceMap = new HashMap();
		
		
		String URL = "https://automationintesting.online/booking/";

		try {
			response = Unirest.get(URL).header("accept","application/json").asString();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	JSONObject responseA = new JSONObject(response.getBody().toString());
		
	    System.out.println(responseA);
	    int size = responseA.length();
	    JSONParser parse = new JSONParser(); 
	    JSONArray jsonarr_1 = (JSONArray) responseA.get("bookings");
	    System.out.println(jsonarr_1);

	for (int i = 0; i < jsonarr_1.length(); i++) {
		JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
		System.out.println("\nPlace id: " +jsonobj_1.get("bookingid"));
		bookingId = jsonobj_1.get("bookingid").toString();
		bookingIdCollection.add(bookingId);
	}
	return bookingIdCollection.get(0);
	}
public static void main(String a[])
{
	String bookingid = retrieveBookingID();
	
	System.out.println(bookingid);
	String URL = "https://automationintesting.online/booking/"+bookingid;
	HttpResponse response = null;

	try {
		response = Unirest.get(URL).header("accept","application/json").asString();
	} catch (UnirestException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
JSONObject responseA = new JSONObject(response.getBody().toString());
System.out.println("Status is"+response.getStatus());
System.out.println(response.getStatusText());
org.testng.Assert.assertEquals(response.getStatus(),200,"API is returning 200 status code");

}
}
