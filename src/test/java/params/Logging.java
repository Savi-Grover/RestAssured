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
public class Logging {

	@Test
	void testLog() {
		given()
		
		.when().get("https://reqres.in/api/users?page=2")
		
		//methods to log
		.then()
		//.log().all();
		
		//to print only  body
		//.log().body();
		
		//to print cookies
		//log().cookies();
		
		
		//only headers
		.log().headers();
		
		
		
	}
}
