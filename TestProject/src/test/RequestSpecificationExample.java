package test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestSpecificationExample {

//	RequestSpecification requestSpecification;
//	ResponseSpecification responseSpecification;

	@BeforeClass
	public void beforeClass() {
		/*
		 * requestSpecification =
		 * given().baseUri("https://api.postman.com").header("x-api-key",
		 * "PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196");
		 */
		RequestSpecBuilder specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri("https://api.postman.com");
		specBuilder.addHeader("x-api-key",
		 "PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196");
		specBuilder.log(LogDetail.ALL);
		
		RestAssured.requestSpecification =  specBuilder.build();  // default request specification
		
//		responseSpecification = RestAssured.expect().statusCode(200).contentType(ContentType.JSON).log().all();
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
//		responseSpecification = responseSpecBuilder.build();
		
//		With default response specification
		RestAssured.responseSpecification = responseSpecBuilder.build();
		
		
	}

	@Test
	public void validate_status_code() {

//		given().spec(requestSpecification).get("/workspaces").then().log().all().assertThat().statusCode(200);
//		get("/workspaces").then().spec(responseSpecification);
		get("/workspaces");   // with default request and response specification 
	}
	
	@Test
	public void validate_response_body() {

		//given().spec(requestSpecification).get("/workspaces").then().log().all().assertThat().body("workspaces[0].name",is(equalTo("My Workspace")));
//		get("/workspaces").then().spec(responseSpecification).assertThat().body("workspaces[0].name",is(equalTo("My Workspace")));
		get("/workspaces").then().assertThat().body("workspaces[0].name",is(equalTo("My Workspace"))); // with default request and response specification
		
	}


}

