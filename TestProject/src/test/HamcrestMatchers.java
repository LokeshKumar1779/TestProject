package test;

import org.testng.annotations.Test;

import io.restassured.config.LogConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Collections;
import java.util.HashMap;

public class HamcrestMatchers {
	
	@Test(enabled=false)
	public void test1() {
		
		given().baseUri("https://api.postman.com").
		header("x-api-key","PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196").
		when().get("/workspaces").
		then().assertThat().statusCode(200)
		
		.body("workspaces.name",  hasItem("AutomationTesting"),
				"workspaces.name",  not(hasItem("AutomationTesting1")),
				"workspaces.name", hasItems("My Workspace","AutomationTesting"),
				"workspaces.name", hasItems("AutomationTesting","My Workspace"),
				"workspaces[0].name",equalTo("My Workspace"),
				"workspaces[1].name",is(equalTo("AutomationTesting")),
				"workspaces.size()",equalTo(2),
				"workspaces.name", contains("My Workspace","AutomationTesting"),
				"workspaces.name", containsInAnyOrder("AutomationTesting","My Workspace"),
				"workspaces.name", is(not(empty())),
				"workspaces", not(emptyArray()),
				"workspaces.name",hasSize(2),
				"workspaces[0]",hasKey("id"),
				"workspaces[0]",hasValue("60790dbb-bbbc-4fd7-8849-64bda5ae31cd"),
				"workspaces[0]",hasEntry("id", "60790dbb-bbbc-4fd7-8849-64bda5ae31cd"),
				"workspaces[0]",not(equalTo(Collections.EMPTY_MAP)));
	
	}
//  Logging only when there is validation fail	
	@Test(enabled = false)
	public void test2() {
		
		given().baseUri("https://api.postman.com").
		header("x-api-key","PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196").
		log().all().
		when().get("/workspaces").
		then().log().ifValidationFails().assertThat().statusCode(200);
	
	}
	
//	Loggin when there is validation fail in response 
	@Test(enabled=false)
	public void test3() {
		
		given().baseUri("https://api.postman.com").
		header("x-api-key","PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196").
		config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
		when().get("/workspaces").
		then().assertThat().statusCode(200);
	
	}
	
//	Blacklist header/headers
	@Test(enabled=false)
	public void test4() {
		
		given().baseUri("https://api.postman.com").
		header("x-api-key","PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196").
		config(config.logConfig(LogConfig.logConfig().blacklistHeader("x-api-key").enableLoggingOfRequestAndResponseIfValidationFails())).
		when().get("/workspaces").
		then().assertThat().statusCode(201);
	
	}
	
	
//	Passing multiple headers in request
	@Test(enabled=false)
	public void test5() {
		
		Header header1 = new Header("x-api-key", "PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196");
		Header header2 = new Header("", "");
		
		given().baseUri("https://api.postman.com").
		header(header1).header(header2).
		config(config.logConfig(LogConfig.logConfig().blacklistHeader("x-api-key").enableLoggingOfRequestAndResponseIfValidationFails())).
		when().get("/workspaces").
		then().assertThat().statusCode(200);
	
	}
	
//	Passing multiple headers in request
	@Test(enabled=false)
	public void test6() {
		
		Header header1 = new Header("x-api-key", "PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196");
		Header header2 = new Header("", "");
		Headers headers = new Headers(header1,header2);
		given().baseUri("https://api.postman.com").
		headers(headers).
		log().all().
		when().get("/workspaces").
		then().assertThat().statusCode(200);
	
	}
	
//	Passing multiple headers in request
	@Test(enabled=false)
	public void test7() {
		
		HashMap<String,String> header = new HashMap<>();
		header.put("x-api-key", "PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196");
		header.put("", "");
		given().baseUri("https://api.postman.com").
		headers(header).
		log().all().
		when().get("/workspaces").
		then().assertThat().statusCode(200);
	
	}
	
//	Response Header , assert
	@Test(enabled=false)
	public void test8() {
		
		HashMap<String,String> header = new HashMap<>();
		header.put("x-api-key", "PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196");
		header.put("", "");
		given().baseUri("https://api.postman.com").
		headers(header).
		log().all().
		when().get("/workspaces").
		then().log().all().assertThat().statusCode(200).headers("Content-Type", "application/json; charset=utf-8");
	
	}
	
//	Extracting Response Header 
	@Test
	public void test9() {
		
		HashMap<String,String> header = new HashMap<>();
		header.put("x-api-key", "PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196");
	
		Headers Rspheaders = given().baseUri("https://api.postman.com").
		headers(header).
		log().all().
		when().get("/workspaces").
		then().log().all().assertThat().statusCode(200).extract().headers();
		
		System.out.println("Header Name: " +Rspheaders.get("Content-Type").getName());
		System.out.println("Header value: " +Rspheaders.get("Content-Type").getValue());
		
		
		for(Header header1: Rspheaders) {
			System.out.print("Header name :  " +header1.getName());
	
			System.out.println("Header value :  " +header1.getValue());
		}
	
	}
	

}
