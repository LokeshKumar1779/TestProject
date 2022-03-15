package test;

import static io.restassured.RestAssured.with;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utilities.GetPayload;

public class ParseComplexJsonAssignment {
	
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	@BeforeClass
	public void beforeClass() {
	
		RequestSpecBuilder specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri("https://f64626b4-4ca3-4004-b6dd-cce5a9b10b77.mock.pstmn.io");
		specBuilder.addHeader("x-mock-match-request-body", "true");

		
		specBuilder.log(LogDetail.ALL);
		specBuilder.setContentType(ContentType.JSON);
		
		requestSpecification =  specBuilder.build();  
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		responseSpecification = responseSpecBuilder.build();
			
	}
	
	@Test
	public void test001() {
		
		List<Integer> rgbaList1 = new ArrayList<Integer>();
		rgbaList1.add(0);
		rgbaList1.add(0);
		rgbaList1.add(0);
		rgbaList1.add(1);
		
		HashMap<String,Object> codeMap1 = new HashMap<String,Object>();
		codeMap1.put("rgba", rgbaList1);
		codeMap1.put("hex", "#FFF");
		
		HashMap<String,Object> colorsMap1 = new HashMap<String,Object>();
		colorsMap1.put("color", "white");
		colorsMap1.put("category", "value");
		colorsMap1.put("code", codeMap1);
		
		List<Integer> rgbaList0 = new ArrayList<Integer>();
		rgbaList0.add(255);
		rgbaList0.add(255);
		rgbaList0.add(255);
		rgbaList0.add(1);
		
		HashMap<String,Object> codeMap0 = new HashMap<String,Object>();
		codeMap0.put("rgba", rgbaList0);
		codeMap0.put("hex", "#000");
		
		HashMap<String,Object> colorsMap0 = new HashMap<String,Object>();
		colorsMap0.put("color", "black");
		colorsMap0.put("category", "hue");
		colorsMap0.put("type", "primary");
		colorsMap0.put("code", codeMap0);
		
		List<HashMap<String,Object>> colorsList = new ArrayList<HashMap<String,Object>>();
		colorsList.add(colorsMap0);
		colorsList.add(colorsMap1);
		
		HashMap<String, Object> colorsMap = new HashMap<String, Object>();
		colorsMap.put("colors", colorsList);
		
		with().spec(requestSpecification).body(colorsMap).post("/postComplexJsonAssignment").then().spec(responseSpecification);
	}
}