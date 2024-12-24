package uploads;
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

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;

public class uploadsandDownloads {

	
	@Test
	
	void singleFileUpload() {
		
		File file=new File("path of file");
		
		//both multipart and content type mandatory to upload
		given()
			.multiPart(file)
			.contentType("multipart/form-data")
		
		.when()
			.post("http://localhost:8080/uploads")
		
		.then().statusCode(200)
		.body("fileName", equalTo("Test.txt"))
		.log().all();
		
	}

	
	void multipleFileUpload() {
		
		File file1=new File("path of file");
		File file2=new File("path of file");
		//manually specify all paths 
		
		//can optionally make array
		// file[] array={myfile1, myfile2};
		
		given()
			.multiPart("files", file1)
			.multiPart("files", file2)
			.contentType("multipart/form-data")
		
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		
		.then().statusCode(200)
		
		//since outputs in form of array
		.body("[0].fileName", equalTo("Test1.txt"))
		.body("[1].fileName", equalTo("Test2.txt"))
		
		.log().all();
		
		//to check successfully downlaoded
		
	}
	
	void fileDownload() {
		
		given()
		
		//multiple have to write multi get stmt
		.when().get("http://localhost:8080/downloadFile/Test1.txt")
		
		.then().statusCode(200)
		.log().all();
	}

	
}