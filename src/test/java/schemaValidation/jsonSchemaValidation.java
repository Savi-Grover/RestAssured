package schemaValidation;
import org.testng.annotations.Test;
import  io.restassured.RestAssured;
import io.restassured.http.ContentType;
import  io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

//import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class jsonSchemaValidation {
	
	//@Test
	
	//for validateing json response , we shud have json schema file ( .json)
	void jsonSchemaValidate() {
		//File file = new File("C:\\Users\\savig\\eclipse-workspace_Ford1\\testing\\src\\test\\resources\\booksSchema.json");
		//maintain a schema file with.json extn in resource folder
		given().contentType("application/json")
		
		//get endpoint form json server
		.when().get("http://localhost:3000/books")
		
		
		//String expectedJson = FileUtils.readFileToString(file));
	    //response.then().assertThat().body(matchesJsonSchema(expectedJson));
		
	    .then()
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaValidation.json"));
	}
	
	
	
	//for validateing xml response , we shud have .xsd schema file ( .xsd)
	//@Test
	// online coversion for xml to xsd
	void xmlSchemaValidate() {
		
		given()
		
		
		.when().get("http://localhost:3000/accounting")
		
		.then().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("output.xsd"));
		
}

}
