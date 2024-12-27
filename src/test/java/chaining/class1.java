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

import org.testng.annotations.Test;

//create user & store generated id
public class class1 {
   //@Test
	void test_createUser(ITestContext context) {
	   Faker faker= new Faker();
	   JSONObject data = new JSONObject();
	   data.put("name", faker.name().fullName());
	   data.put("gender", "Male");
	   data.put("email", faker.internet().emailAddress());
	   data.put("status", "inactive");
	   
	   //we need bearer token auth
	   String token = "sdgasgfgisgifsigfsigf";
	   
	   //post request to create user with above data
	   int id=given()
	   		.headers("Authorization","Bearer "+token)
	   		.contentType("application/json")
	   		.body(data.toString())
	   		
	   .when()
	   		.post("https://gorest.co.in/public/v2/users")
	   		.jsonPath().getInt("id");
	   
	   
	   //save everything in response res, we need only id so we use jsonPath().getInt("id")
	   System.out.println("Generated id" +id);
	   
	   context.setAttribute("user_id", id);
	   
	
	}
}
