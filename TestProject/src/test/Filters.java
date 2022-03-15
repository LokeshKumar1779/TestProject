package test;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Filters {

	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	@BeforeClass
	public void beforeClass() throws FileNotFoundException {
	
		PrintStream fos = new PrintStream(new File("restAssured.log"));
		RequestSpecBuilder specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri("https://postman-echo.com");
		specBuilder.setContentType(ContentType.JSON);

//      specBuilder.addFilter(new RequestLoggingFilter(LogDetail.ALL));
//		specBuilder.addFilter(new ResponseLoggingFilter(LogDetail.ALL));
//		specBuilder.addFilter(new RequestLoggingFilter(LogDetail.BODY));
//		specBuilder.addFilter(new ResponseLoggingFilter(LogDetail.STATUS));
		
		specBuilder.addFilter(new RequestLoggingFilter(LogDetail.ALL,fos));
		specBuilder.addFilter(new ResponseLoggingFilter(LogDetail.ALL,fos));
		
		requestSpecification =  specBuilder.build();  
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON);
	
		responseSpecification = responseSpecBuilder.build();
		
			
	}
	

	@Test
	public void test01() {
		
		given().spec(requestSpecification).queryParam("foo1", "bar1;bar2") 
				.get("/get")
				.then().spec(responseSpecification);
			
	}
}
