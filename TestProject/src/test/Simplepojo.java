package test;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.simplepojo;

public class Simplepojo {

	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;

	@BeforeClass
	public void beforeClass() throws FileNotFoundException {

		PrintStream fos = new PrintStream(new File("restAssured.log"));
		RequestSpecBuilder specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri("https://f64626b4-4ca3-4004-b6dd-cce5a9b10b77.mock.pstmn.io");
		specBuilder.setContentType(ContentType.JSON);

//      specBuilder.addFilter(new RequestLoggingFilter(LogDetail.ALL));
//		specBuilder.addFilter(new ResponseLoggingFilter(LogDetail.ALL));
//		specBuilder.addFilter(new RequestLoggingFilter(LogDetail.BODY));
//		specBuilder.addFilter(new ResponseLoggingFilter(LogDetail.STATUS));

		specBuilder.addFilter(new RequestLoggingFilter(LogDetail.ALL, fos));
		specBuilder.addFilter(new ResponseLoggingFilter(LogDetail.ALL, fos));

		requestSpecification = specBuilder.build();

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON);

		responseSpecification = responseSpecBuilder.build();

	}

	@Test
	public void test01() {

		simplepojo sp = new simplepojo("value1","value2");
		
		given().spec(requestSpecification).body(sp).post("/postSimplepojo").then()
				.spec(responseSpecification).assertThat().body("key1", is(equalTo(sp.getKey1())));

	}
}
