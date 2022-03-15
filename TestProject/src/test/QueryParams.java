package test;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class QueryParams {
	
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	@BeforeClass
	public void beforeClass() {
	
		RequestSpecBuilder specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri("https://postman-echo.com");
		specBuilder.log(LogDetail.ALL);
		//specBuilder.setContentType(ContentType.JSON);
		
		requestSpecification =  specBuilder.build();  
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		responseSpecification = responseSpecBuilder.build();
			
	}
	
	//query param
	@Test(enabled=false)
	public void test01() {
		
		given().spec(requestSpecification).queryParam("foo1", "bar1;bar2") //multivalue parameter
				.get("/get")
				.then().spec(responseSpecification);
			
	}
	
	//query param
	@Test(enabled=false)
	public void test02() {
		
		given().spec(requestSpecification).baseUri("https://reqres.in").pathParam("userId","1") 
				.get("/api/users/{userId}")
				.then().spec(responseSpecification);
		
	}
	
	//multipart form data 
	@Test(enabled=false)
	public void test03() {
		
		given().spec(requestSpecification).multiPart("foo1", "bar1")
			.contentType(ContentType.MULTIPART)
				.post("/post")
				.then().spec(responseSpecification);
		
	}
	
	//file upload using multipart 
	@Test(enabled=false)
	public void test04() {
		
		given().spec(requestSpecification).multiPart("file","./TestProject/data.txt")
				.contentType(ContentType.MULTIPART)
				.post("/post")
				.then().spec(responseSpecification);
		
	}
	
	//file download using multipart 
	@Test
	public void test05() throws IOException {
		
		/*
		 * byte [] bytes =
		 * given().spec(requestSpecification).baseUri("https://github.com")
		 * .contentType(ContentType.MULTIPART)
		 * .post("/appium/appium/raw/master/sample-code/apps/ApiDemos-debug.apk")
		 * .then().extract().response().asByteArray(); FileOutputStream fos = new
		 * FileOutputStream(new File("ApiDemos-debug.apk")); fos.write(bytes);
		 * 
		 * fos.close();
		 */
		
		InputStream is = given().spec(requestSpecification).baseUri("https://raw.githubusercontent.com")
				
				.post("/appium/appium/master/sample-code/apps/ApiDemos-debug.apk").then().extract().response()
				.asInputStream();
		OutputStream fos = new FileOutputStream(new File("ApiDemos-debug.apk"));
		byte[] bytes = new byte[is.available()];
		is.read(bytes);
		fos.write(bytes);
		fos.close();
		 
		
		
	}

}
