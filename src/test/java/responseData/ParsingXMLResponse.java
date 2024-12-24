package responseData;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import  io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import  io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.xml.XmlPath;

//import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;

public class ParsingXMLResponse {

	
	@Test
	//Method 1 - then().body()
	void testXMLResponse() {
		
		given()
		
		.when().get("https://mocktarget.apigee.net/xml")
		
		.then().statusCode(200)
		.header("Content-Type", "application/xml; charset=utf-8")
		
		/*
		.body("root.firstName", equalTo("John"));  //xpath of tag u want to check
		*/
		
		//if xml has many record info for firstname
		//above command will fetch all first names
		
		//for single record - we need to pass index, index starts with 0 
		
		
		.body("root.firstName[0]", equalTo("John"));  //xpath of tag u want to check
		
	}
	
	@Test
	//Method 2 - response stored in a variable
	void testXMLResponse1() {
		Response res= given()
		
		.when().get("https://mocktarget.apigee.net/xml");
		
		//Assert
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getHeader("Content-Type"), "application/xml; charset=utf-8");
		
		//assertbody
		String valueoffirstName= res.xmlPath().get("root.firstName[0]").toString();
		Assert.assertEquals(valueoffirstName, "John");
		
		//capture res let us add more vaidations
		
	}
	
	@Test
	//Method 3 - response stored in a variable - more validations
	void testXMLResponse2() {
		Response res= given()
				
		.when().get("https://mocktarget.apigee.net/xml");
		
		//validations 
		//similar to jsonPath obj 
		//pass response
		XmlPath obj= new XmlPath(res.asString());
		
		//difference between toString and asString
		//toString = convert data to string
		//asString = convert response to string
		
		// we use List as we have child nodes nested in parent node
		
		List <String>firstnames=obj.getList("root.firstName");
		firstnames.size();
		
		//assert number of records
		Assert.assertEquals(firstnames.size(), 1);
		
		
		//check when order of records are changed
		//verify if one specific name exists in response
		List <String>allnames=obj.getList("root.firstName");
		boolean status=false;
		for (String name :allnames) {
			System.out.println(name);
			if(name.equals("John")) {
				status = true;
				break;
			}
		}
		
		
	}
}
