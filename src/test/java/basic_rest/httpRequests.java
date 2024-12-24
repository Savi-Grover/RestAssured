package basic_rest;

import org.testng.annotations.Test;
import  io.restassured.RestAssured;
import  io.restassured.matcher.ResponseAwareMatcher;
//import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;

//REST ASSURED- defaults to BDD framework in Gerkin Language

//GIVEN - check all pre-req- header, auth, parameter, req body, cookie, content type
//WHEN - specify method to use.
//THEN- response checks and validations/assertions
//use static imports to import these keywords

public class httpRequests {
 
	int id;
	@Test(priority =1 )
	//use priority to run all testcases together
	void getUsers() {
		
		//to get userList - endpoint - https://reqres.in/api/users/?page=2
		
		 given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
		    	.statusCode(200)
		    	.body("page",is(2))
		    	.log().all();   //console log whole response
	}
	
	
	@SuppressWarnings("unchecked")
	@Test(priority =2)
	void postUser() {
		
		//we can use hashmap to store key value and convert that 
		//to json to create data
		HashMap hm = new HashMap();
		hm.put("name", "xyz");
		hm.put("job", "trainer");
		
		id=given().contentType("application/json").body(hm)
	    .when().post("https://reqres.in/api/users")   //to store id
		.jsonPath().getInt("id");
		
		//.then().statusCode(201)
			//.log().all();  - to verify 201 and print response
	}
	
	@Test(priority =3, dependsOnMethods= {"postUser"})
	void updateUser() {
		HashMap hm = new HashMap();
		//change values 
		hm.put("name", "john");
		hm.put("job", "teacher");
		
		given().contentType("application/json").body(hm)
	    .when().put("https://reqres.in/api/users/"+id)   //to use id produced by above id
		
		.then()
		.statusCode(200).log().all(); 
	}
	
	@Test(priority =4)
	void deleteUser() {
		given()
		
		.when().delete("https://reqres.in/api/users/"+id)
		
		.then().statusCode(204).log().all();
	}
	
}
