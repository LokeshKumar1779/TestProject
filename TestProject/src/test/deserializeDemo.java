package test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import static org.hamcrest.MatcherAssert.assertThat; 
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.WorkspaceRoot;

import pojo.workspace;

public class deserializeDemo {
	
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;

	@BeforeClass
	public void beforeClass() throws FileNotFoundException {

		PrintStream fos = new PrintStream(new File("restAssured.log"));
		RequestSpecBuilder specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri("https://api.postman.com");
		specBuilder.setContentType(ContentType.JSON);
		specBuilder.addHeader("x-api-key","PMAK-62243fc9b0bc19005424d6d3-8a4371809912f4b8a6fddb785a51bc1196");

		specBuilder.addFilter(new RequestLoggingFilter(LogDetail.ALL, fos));
		specBuilder.addFilter(new ResponseLoggingFilter(LogDetail.ALL, fos));

		requestSpecification = specBuilder.build();

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON);

		responseSpecification = responseSpecBuilder.build();

	}

	@Test(dataProvider="workspaceData")
	public void test01(String name, String type, String desc) {

		workspace ws = new workspace(name, type, desc);
		WorkspaceRoot wr = new WorkspaceRoot(ws);
		
		WorkspaceRoot wr1 = given().spec(requestSpecification).body(wr).post("/workspaces").then()
				.spec(responseSpecification).extract().response().as(WorkspaceRoot.class);
//		wr1.getWorkspace().getName();
		
		assertThat(wr1.getWorkspace().getName(), is(equalTo(wr.getWorkspace().getName())));

	}
	
	@DataProvider(name="workspaceData")
	public Object[][] getdata() {
		
		return new Object[][] {
			{"my workspace7", "personal", "this is created using rest assured"},
			{"my workspace8", "personal", "this is created using rest assured"},
			{"my workspace9", "personal", "this is created using rest assured"}
		};
	}

}
