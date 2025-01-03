package permission;
import org.testng.annotations.Test;
import  io.restassured.RestAssured;
import  io.restassured.matcher.ResponseAwareMatcher;
//import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;
public class Authentication {
	
	@Test(priority=1)
	//basic , digest, preemptive almost similar - internally alogorithms are different 
	void BasicAuthentication() {
		
		given()
				.auth().basic("postman","password")
				//.auth().digest("postman","password")
				//.auth().preemptive.basic("postman","password")
		.when()
				.get("http://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	

	@Test(priority=2)
	void DigestAuthentication() {
		
		given()
				.auth().digest("postman","password")
				//.auth().preemptive.basic("postman","password")
		.when()
				.get("http://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	
	@Test(priority=3)
	void PreemptiveAuthentication() {
		
		given()
				.auth().preemptive().basic("postman","password")
		.when()
				.get("http://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	
	//@Test(priority=4)
	//bearer token go with headers
	void BearerTokenAuthentication() {
		
		String token="abc";
		given()
				.headers("Authorisation", "Bearer "+token)
		.when()
				.get("http://github.api/user/repos")
		.then()
			.statusCode(200)
			.log().all();
		
	}

	   //@Test(priority=5)
	   //aouth 1 is old and auth 2 is advanced 
		void test_OAuth1() {
		
		   //need 4 values
		given()
				.auth().oauth("consumerKey", "consumerSecret", "AccessToken","TokenSecret")
				
		.when().get("http://github.api/user/repos")
		.then()
			.statusCode(200)
			.log().all();
		
	}

		//oauth2 - token manually generated by postman
		//step 1- we pass client id
		//step 2 - once client id matched, pass clienid, clientsecret, code-> response will send Oauth2_token
		//@Test(priority=6)
		void test_OAuth2() {
		
			given()
					.auth().oauth2("value of oauth2_token")
					
			.when().get("http://github.api/user/repos")
			.then()
				.statusCode(200)
				.log().all();
			
		}
		
		//@Test(priority=7)
		//we use- key and value
		//passed with query param
		void test_ApiKey() {
					
			
				given()
					.queryParam("appid","valueshvjygdewy" )
					.pathParam("mypath", "data/2.5/forecast/daily")		
					.queryParam("q", "Delhi")
					.queryParam("units", "metric")
					.queryParam("cnt", "7")
					
				//.when().get("http://api.openweathermap.org/data/{mypath}?q=Delhi&units=metric&cnt=7")
				.when().get("http://api.openweathermap.org/data/{mypath}")
				
				.then()
					.statusCode(200)
					.log().all();
					
				}
	
	
}
