package post;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import  io.restassured.RestAssured;
import  io.restassured.matcher.ResponseAwareMatcher;
//import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;

/*
 * 1. Hashmap - used for small set of data
 * 2. using org.json 
 * 3. POJO classes
 * 4. external json file data
 */
public class DiffWaysToCreatePOSTRequestBody {
	
	@SuppressWarnings("rawtypes")
	//@Test(priority=1)
	void testPostUsingHashMap() {
		HashMap data = new HashMap();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[]= {"C","C++"};
		data.put("courses", courseArr);  //when data is in form of array, create array first
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when().post("http://localhost:3000/students")
		
		.then().statusCode(201)
		.body("name", is("Scott"))
		.body("location", is("France"))
		.body("phone",is("123456"))
		.body("courses[0]",is("C"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}
	
	//@Test(priority=2)
	void testDelete() {
		given()
		
		.when().delete("http://localhost:300/students/66")
		
		
		.then().statusCode(200)
		.log().all();
	}
	
	//@Test(priority=3)
	void testPostUsingOrgJson() {
		//add dependency of json in pom.xml
		JSONObject data = new JSONObject();
		
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[]= {"C","C++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())  //need to convert json data to string
			
		.when().post("http://localhost:3000/students")
		
		.then().statusCode(201)
		.body("name", is("Scott"))
		.body("location", is("France"))
		.body("phone",is("123456"))
		.body("courses[0]",is("C"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}
	
	
	//@Test(priority=4)
	void testPostUsingPOJO() {
	//create a class = POJO class and take data from there
		
		Pojo_PostRequest obj  = new Pojo_PostRequest();
		
		obj.setName("Scott");
		obj.setLocation("Germany");
		obj.setPhone("14852369");
		String courseArr[]= {"English","Science"};
		obj.setCourses(courseArr);
		
		given()
			.contentType("application/json")
			.body(obj)  //need to convert json data to string
			
		.when().post("http://localhost:3000/students")
		
		.then().statusCode(201)
		.body("name", is("Scott"))
		.body("location", is("France"))
		.body("phone",is("123456"))
		.body("courses[0]",is("C"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}
	
	
	@Test(priority=5)
	void testPostUsingExternalJSONFile() throws FileNotFoundException {
	//create a file = body.json which has json data 
		
		File f= new File(".\\body.json");
		FileReader fr= new FileReader(f);
		JSONTokener jt = new JSONTokener(fr); // extract data from json file
		JSONObject data = new JSONObject(jt);
		
		
		given()
			.contentType("application/json")
			.body(data.toString())  //need to convert json data to string
			
		.when().post("http://localhost:3000/students")
		
		.then().statusCode(201)
		.body("name", is("Scott"))
		.body("location", is("France"))
		.body("phone",is("123456"))
		.body("courses[0]",is("C"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}
	
	
	
	
	
	
}
