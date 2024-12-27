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

//delete user
public class class4 {
	@Test
	void deleteUser(ITestContext context) {
		 //we need bearer token auth
		   String token = "sdgasgfgisgifsigfsigf";
		   
		   int id=(Integer) context.getAttribute("user_id");
			
		   given()
		      .headers("Authorization","Bearer "+token)
	   	      .contentType("application/json")
	   		  .pathParam("id", id)  //add path para
	   		
		   .when()
		        .delete("https://gorest.co.in/public/v2/users/{id}")
	   		
		   
		   .then()
		      .statusCode(204)
		   	  .log().all();		   
		   
	}

}
