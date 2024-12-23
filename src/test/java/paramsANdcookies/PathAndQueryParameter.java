package paramsANdcookies;
import org.testng.annotations.Test;
import  io.restassured.RestAssured;
import  io.restassured.matcher.ResponseAwareMatcher;
//import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;

/*
 * 
 * https://reqres.in/api/users?page=2  = endpoint
   https://reqres.in/  = domain
   /api/users   = path
   ?page=2       = query param
 * 
 */
public class PathAndQueryParameter {
	@Test
	
//https://reqres.in/api/users?page=2&id=5
	
  void testQueryAndPathParams() {
	  given()
	  .pathParam("mypath", "users")
	  .queryParam("page", 2)
	  .queryParam("id", 2)
	  
	  //specify just path param below since query param pass automatically to req
	  .when()
	     .get("https://reqres.in/api/{mypath}")
	  
	  .then().statusCode(200).log().all();
	  
	  
  }
	
	
}
