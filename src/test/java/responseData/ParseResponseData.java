package responseData;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import  io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import  io.restassured.matcher.ResponseAwareMatcher;
//import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;

public class ParseResponseData {


	@Test
	void ParseJSONResponse() {
		 
		//REFER JSON = BOOKS.JSON
		
		//METHOD1 - 
		given().contentType(ContentType.JSON)
		.when().get("http://localhost:3000/books")
		.then()
			.statusCode(200)
			///.header("Content-Type", "")
			//find jsonpath from jsonPath finder- 
			.body("books[2].title", equalTo("Mobile Cart"));
		
		
		
		//validations in complex json is done by json object
		//METHOD2 useful for complex json- store response in variable
		Response res= 
		given().contentType(ContentType.JSON)
		.when().get("http://localhost:3000/books");
		
		//add TESTNG assertions with res using all methods - getstatus, getheader, getcookie
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.getHeader("Content-Type"),"application/json; charset=utf-8");
		
		//fields 
		String bookname=res.jsonPath().get("jsonpath").toString();
		Assert.assertEquals(bookname, "The Lord of the rings");
		
		
		//METHOD3- we are not using soecific json path
		//if we want to capture title of all books and print them
		//and then we can check if another title is present or not
		//helps if order of items in changed in res, still we can validate 
		Response res1= 
				given().contentType(ContentType.JSON)
				.when().get("http://localhost:3000/books");
				
		
		//convert res to string , and pass in josn obj
		String res11=res1.toString();
		JSONObject obj= new JSONObject(res11);
		
		//json array derived from array res
		for (int i=0 ;i <obj.getJSONArray("books").length(); i++) {
			
			//extract title of every book - every element of this array is object in any size
			String booktitle= obj.getJSONArray("books").getJSONObject(i).get("Title").toString();
			System.out.println(booktitle);
		}
		
		//validation 1- for checking 1 title
		boolean status =false;
	for (int i=0 ;i <obj.getJSONArray("books").length(); i++) {
			
			//extract title of every book - every element of this array is object in any size
			String booktitle= obj.getJSONArray("books").getJSONObject(i).get("Title").toString();
			if(booktitle.equals("Lord of the Rings")) {
				status = true;
				break;   //for breaking i for loop
			}
		}
		
		Assert.assertEquals(status, true);
		
		
		//validation 2- we can also find - total price by summing all prices
		//validate total price of books
		double  totalprice=0;
		for (int i=0 ;i <obj.getJSONArray("books").length(); i++) {
			
	        //convert to string and then double
			String price= obj.getJSONArray("books").getJSONObject(i).get("price").toString();
			double price1=Double.parseDouble(price);
			totalprice=totalprice+price1;
		}
		
		Assert.assertEquals(totalprice, 788.80);
		
		
		
		
}}

