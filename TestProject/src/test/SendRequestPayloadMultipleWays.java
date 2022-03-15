package test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;

import org.hamcrest.text.MatchesPattern;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class SendRequestPayloadMultipleWays extends PostRequest{
	
//	sending payload as a file
	@Test(enabled=false)
	public void test01() {
		
//		System.out.println(System.getProperty("user.dir"));
		
		File file = new File(System.getProperty("user.dir")+"/src/resources/createWorkspacePayload.json");
		Response response = with().spec(requestSpecification).body(file).post("/workspaces").then().spec(responseSpecification).extract().response();
		
		assertThat(response.path("workspace.name"), is(equalTo("My Workspace4")));
		assertThat(response.path("workspace.id"), MatchesPattern.matchesPattern("^[a-z0-9-]{36}$"));
	}
	
//	sending payload as a json object	
	@Test
	public void test02() {
		
		HashMap<String, Object> mainObject = new HashMap<String,Object>();
		
		HashMap<String, String> nestedObject = new HashMap<String,String>();
		nestedObject.put("name", "My Workspace5");
		nestedObject.put("type", "personal");
		nestedObject.put("description", "restassured created this");
		
		mainObject.put("workspace", nestedObject);
		
		Response response = with().spec(requestSpecification).body(mainObject).post("/workspaces").then().spec(responseSpecification).extract().response();
		
		assertThat(response.path("workspace.name"), is(equalTo("My Workspace5")));
		assertThat(response.path("workspace.id"), MatchesPattern.matchesPattern("^[a-z0-9-]{36}$"));
	}
	


}
