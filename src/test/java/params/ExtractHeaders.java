package params;
import org.testng.annotations.Test;
import  io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import  io.restassured.matcher.ResponseAwareMatcher;
//import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;


//we can find headers also same way- content , server name
//headers are not dynamic except some like - cache
		

public class ExtractHeaders {

	@Test
	void testHeader() {
	given()
	
	
	.when()
	    .get("https://www.google.com/")
	
	    //these are assertions if value dont match, script fails 
	.then().header("Content-Type","text/html; charset=ISO-8859-1")
	.and()  //optional to use- suggest we are checking multiple headers
	.header("Content-Encoding","gzip");
	
	
}
	@Test
	void CaptureHeaders() {
		//no assertions 
		Response res=given()
		
		
		.when()
		    .get("https://www.google.com/");
		
		
		 //get single header
		 String header=res.getHeader("Content-Type");
		 System.out.println("value of content type header"+header);
		
		//get multiple= headers
		Headers  allheaders=res.getHeaders();
		
		/*
		 * getHeader - fetch one header - key+value
		 * getHeaders - fetch multiple headers each with <header name> +< header value>
		 * datatype of each is Headers
		 * 
		 */
		
		//this is not hashmap
		//very less used because then().log().headers() to the same thing
		for(Header hd : allheaders) {
				System.out.println(hd.getName()+"  "+hd.getValue());
		}
		
		
	
	}
	
	
	
}
