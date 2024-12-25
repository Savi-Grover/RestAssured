package serializer;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import  io.restassured.RestAssured;
import  io.restassured.matcher.ResponseAwareMatcher;
//import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;
import post.Pojo_PostRequest;

//POJO request --serialize---> JSON object -----deserialize------> POJO response
//refer to POJO students class
public class serializationDeserialization {

	//this is just programming implementation done inbuilt in Rest Assured to automatically perform 
	//serialization and deserialization
	@Test
	void convertPojo2Json() throws JsonProcessingException {
		
		
		Pojo_Students obj  = new Pojo_Students();
		
		obj.setName("Scott");
		obj.setLocation("Germany");
		obj.setPhone("14852369");
		String courseArr[]= {"English","Science"};
		obj.setCourses(courseArr);
		
		//need to convert java object = onj to JSON object
		
		ObjectMapper objMapper=new ObjectMapper();
		
		//it is jackson databind object
		String JSONdata= objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		System.out.println(JSONdata);
	}
	
	
	@Test
	void convertJson2Pojo() throws JsonProcessingException {
		
		//copy the response from above
		/*
		 * 
		 * {
			  "name" : "Scott",
			  "location" : "Germany",
			  "phone" : "14852369",
			  "courses" : [ "English", "Science" ]
			}
		 * 
		 * 
		 */
		
		String JSONResponse="{\r\n"
				+ "			  \"name\" : \"Scott\",\r\n"
				+ "			  \"location\" : \"Germany\",\r\n"
				+ "			  \"phone\" : \"14852369\",\r\n"
				+ "			  \"courses\" : [ \"English\", \"Science\" ]\r\n"
				+ "			}";
		
		//convert back to pojo  using type of class Pojo_Students
		ObjectMapper objmapper= new ObjectMapper();
		Pojo_Students stu=objmapper.readValue(JSONResponse, Pojo_Students.class);
		
		//get data from above stu - all data can be fetched by this
		System.out.println(stu.getName());
		System.out.println(stu.getLocation());
		System.out.println(stu.getPhone());
		System.out.println(stu.getCourses()[0]);
		
		/*output 
		 * Scott
			Germany
			14852369
			English
		 */
	
	}
	
}
