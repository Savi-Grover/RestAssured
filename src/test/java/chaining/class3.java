package chaining;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import  io.restassured.RestAssured;
import  io.restassured.matcher.ResponseAwareMatcher;
//import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;

//update user
public class class3 {
	//@Test
	void updateUser(ITestContext context) {
		 Faker faker= new Faker();
		   JSONObject data = new JSONObject();
		   data.put("name", faker.name().fullName());
		   data.put("gender", "Female");  //changing from male to female
		   data.put("email", faker.internet().emailAddress());
		   data.put("status", "active");  //updating from inactive to active
		   
		   //we need bearer token auth
		   String token = "sdgasgfgisgifsigfsigf";
		   int id=(Integer) context.getAttribute("user_id");
			
		   //post request to create user with above data
		   given()
		   		.headers("Authorization","Bearer "+token)
		   		.contentType("application/json")
		   		.pathParam("id", id)  //add path para
		   		.body(data.toString())
		   		
		   .when()
		   		.put("https://gorest.co.in/public/v2/users/{id}")
		   		
		   
		   //then also
		   .then()
		      .statusCode(200).log().all();
		   
		   //save everything in response res, we need only id so we use jsonPath().getInt("id")
		   System.out.println("Generated id" +id);
	   
		
		
	}
}
