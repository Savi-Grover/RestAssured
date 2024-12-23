package params;
import org.testng.annotations.Test;
import  io.restassured.RestAssured;
import  io.restassured.matcher.ResponseAwareMatcher;
//import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;


/*
 * sometimes - cookie and headers are sent in api response
 * how do we capture these since they are dynamic on every run
 */
public class CookiesAndHeader {

	//@Test
	void testCookies() {
		given()
		
		.when()
		  .get("https://www.google.com/")
		  
		.then()
		
		//hard coding cookie value will fail as cookie is dynamic
		.cookie("AEC","AZ6Zc-Vnkj4c9xIyxiMCLFcBO9MCEX6YFysxf-3jZxc0Gydh37F7Y2-OIUI")
		.log().all();
		
	}
	@Test
	void testCookieInfo() {
		
		//we will save whole response to a variable
		Response res= (Response) given()
		
		.when()
		  .get("https://www.google.com/");
		  
		//.then()
		
		//we can capture single cookie info from res
		String cookie_variable=res.getCookie("AEC");
		System.out.println("value of cookie" +cookie_variable);
		
		//get all cookie info- always in key-value pair
		Map<String,String> cookie_variables=res.getCookies();
		
		//only key names
		System.out.println(cookie_variables.keySet());
		
		//once you know all keys, we can extract any key value - from getcookie method
	
		for (String val : cookie_variables.keySet()) 
		{
			String all_cookie_values=res.getCookie(val);
			System.out.println(val+"  " +all_cookie_values);
		}
		
		
		
	}
}
