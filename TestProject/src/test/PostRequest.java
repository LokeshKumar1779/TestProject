package test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;


import org.hamcrest.text.MatchesPattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PostRequest {
	
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	
	@BeforeClass
	public void beforeClass() {
	
		RequestSpecBuilder specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri("https://api.postman.com");
		specBuilder.addHeader("x-api-key",
		 "PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196");
		specBuilder.log(LogDetail.ALL);
		specBuilder.setContentType(ContentType.JSON);
		
		requestSpecification =  specBuilder.build();  
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		responseSpecification = responseSpecBuilder.build();
		
		
	}


	
	@Test(enabled=false)
	public void validate_post_request() {
		
	String payLoad = "{\n"
			+ "    \"workspace\": {\n"
			+ "        \"name\": \"My Workspace3\",\n"
			+ "        \"type\": \"personal\",\n"
			+ "        \"description\":\"rest assured created this\"\n"
			+ "    }\n"
			+ "}";
	/*given().spec(requestSpecification).body(payLoad).post("/workspaces")
	.then().assertThat()
	.body("workspace.name",equalTo("My Workspace2"),"workspace.id",MatchesPattern.matchesPattern("^[a-z0-9-]{36}$"));*/
			
	
	Response response = with().spec(requestSpecification).body(payLoad).post("/workspaces");
	
	assertThat(response.path("workspace.name"), is(equalTo("My Workspace3")));
	assertThat(response.path("workspace.id"), MatchesPattern.matchesPattern("^[a-z0-9-]{36}$"));

		
	}
	
	@Test(enabled=false)
	public void validate_put_request() {
		
		String workspace_id = "378ab1e2-3e4f-4a40-b129-8ad80513bf26";
	String payLoad = "{\n"
			+ "    \"workspace\": {\n"
			+ "        \"name\": \"My Workspace3\",\n"
			+ "        \"type\": \"personal\",\n"
			+ "        \"description\":\"rest assured created this\"\n"
			+ "    }\n"
			+ "}";
	/*given().spec(requestSpecification).body(payLoad).post("/workspaces")
	.then().assertThat()
	.body("workspace.name",equalTo("My Workspace2"),"workspace.id",MatchesPattern.matchesPattern("^[a-z0-9-]{36}$"));*/
			
	
	//Response response = given().spec(requestSpecification).pathParam("workspace_id", workspace_id).body(payLoad).put("/workspaces/{workspace_id}");
	//non bdd style 
	Response response = with().spec(requestSpecification).pathParam("workspace_id", workspace_id).body(payLoad).put("/workspaces/{workspace_id}");
	assertThat(response.path("workspace.name"), is(equalTo("My Workspace3")));
	assertThat(response.path("workspace.id"), MatchesPattern.matchesPattern("^[a-z0-9-]{36}$"));
	assertThat(response.path("workspace.id"), is(equalTo(workspace_id)));

		
	}
	
	@Test(enabled=false)
	public void validate_delete_request() {
		
	String workspace_id = "fcc1669b-90cf-4255-8b68-891b9c2b8124";

	/*given().spec(requestSpecification).body(payLoad).post("/workspaces")
	.then().assertThat()
	.body("workspace.name",equalTo("My Workspace2"),"workspace.id",MatchesPattern.matchesPattern("^[a-z0-9-]{36}$"));*/
			
	
	//Response response = given().spec(requestSpecification).pathParam("workspace_id", workspace_id).body(payLoad).put("/workspaces/{workspace_id}");
	//non bdd style 
	Response response = with().spec(requestSpecification).pathParam("workspace_id", workspace_id).delete("/workspaces/{workspace_id}");

	response.then().assertThat().body("workspace.id", is(equalTo(workspace_id)));
//	assertThat(response.path("workspace.id"), is(equalTo(workspace_id)));

		
	}

}
