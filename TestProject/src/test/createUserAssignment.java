package test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
import pojo.address;
import pojo.createUser;
import pojo.geo;


public class createUserAssignment {
	
	
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;

	@BeforeClass
	public void beforeClass() throws FileNotFoundException {

		PrintStream fos = new PrintStream(new File("restAssured.log"));
		RequestSpecBuilder specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri("https://jsonplaceholder.typicode.com");
		specBuilder.setContentType(ContentType.JSON);
		

		specBuilder.addFilter(new RequestLoggingFilter(LogDetail.ALL, fos));
		specBuilder.addFilter(new ResponseLoggingFilter(LogDetail.ALL, fos));

		requestSpecification = specBuilder.build();

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(201).expectContentType(ContentType.JSON);

		responseSpecification = responseSpecBuilder.build();

	}

	@Test
	public void test01() {
		
		geo g = new geo("-37.3159", "81.1496");
		address add = new address(g, "Kulas Light", "Apt. 556", "Gwenborough", "92998-3874");
		createUser cu1 = new createUser("Leanne Graham", "Bret", "Sincere@april.biz", add, 11);
		
		 createUser cu2 = given().spec(requestSpecification).body(cu1).post("users").then()
				.spec(responseSpecification).extract().response().as(createUser.class);
		 
		
		assertThat(cu2.getId(),is(equalTo(cu1.getId())));

	}

}
