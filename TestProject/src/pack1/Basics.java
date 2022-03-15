package pack1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utilities.reusableMethods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.payLoad;

public class Basics {
	
	public static void main(String[] args) throws IOException {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//Add Place
		/*
		 * String rsp = given().log().all().queryParam("key",
		 * "qaclick123").header("Content-Type", "application/json")
		 * .body(payLoad.AddPlace())
		 * .when().post("/maps/api/place/add/json").then().log().all().assertThat().
		 * statusCode(200) .body("scope", equalTo("APP")).header("server",
		 * "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		 * 
		 * JsonPath js = new JsonPath(rsp); String placeId = js.getString("place_id");
		 * System.out.println("Place Id : "+placeId);
		 */
		
		
//		Read json from a file
//		first convert the json file to bytes -> then convert bytes to String -> the pass the String to body
	
				String rsp = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("/Users/lokeshkumar/addPlace.json"))))
				.when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
				
				JsonPath js = new JsonPath(rsp);
				String placeId = js.getString("place_id");
				System.out.println("Place Id : "+placeId);
		
//		Update Place
		
		String expectedAddress = "72 Summer walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payLoad.updatePlace(placeId,expectedAddress))
				.when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));
		
//		Get Place
		
		String rsp1 = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).header("Content-Type", "application/json")
		.when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = reusableMethods.rawToJson(rsp1);
		String actualAddress = js1.getString("address");
		System.out.println("Address : "+actualAddress);
		
		Assert.assertEquals(actualAddress, expectedAddress);
		
		
		
		
	}

}
