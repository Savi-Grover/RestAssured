package chaining;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import  io.restassured.RestAssured;
import  io.restassured.matcher.ResponseAwareMatcher;
//import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;

//get user
public class class2 {

	//@Test
	void getUser(ITestContext context) {
		String token = "sdgasgfgisgifsigfsigf";
		
		//capture from xml file
		int id=(Integer) context.getAttribute("user_id");
		
		given()
   		.headers("Authorization","Bearer "+token)
   		.contentType("application/json")
   		.pathParam("id", id)
   		
		.when()
		//now passing get endpoint here consist of path parameter having id
		  .get("https://gorest.co.in/public/v2/users/{id}")
   		
		
		.then()
		   .statusCode(200)
		   .log().all();
		
		
		
	}
	
	
}
