package pack1;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.AddPlaceRsp;
import pojo.Location;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SerializationDemo {

	@Test
	public void addPlace() {

//		RestAssured.baseURI = "https://rahulshettyacademy.com";

		/*
		 * AddPlaceRsp rsp =
		 * given().log().all().header("Content-Type","application/json").queryParam(
		 * "key", "qaclick123").body(ap)
		 * .expect().defaultParser(Parser.JSON).when().post("/maps/api/place/add/json")
		 * .then().log().all().assertThat().statusCode(200).extract().response().as(
		 * AddPlaceRsp.class);
		 * 
		 * Assert.assertNotNull(rsp.getId(), "Id is null");
		 * Assert.assertEquals(rsp.getStatus(), "OK");
		 * Assert.assertEquals(rsp.getScope(), "APP");
		 */

		/*
		 * given().log().all().header("Content-Type",
		 * "application/json").queryParam("key", "qaclick123").body(ap).expect()
		 * .defaultParser(Parser.JSON).when().post("/maps/api/place/add/json").then().
		 * log().all().assertThat() .statusCode(200).body("status", equalTo("OK"));
		 */

		/*
		 * String rsp = given().log().all().header("Content-Type",
		 * "application/json").queryParam("key", "qaclick123")
		 * .body(ap).expect().defaultParser(Parser.JSON).when().post(
		 * "/maps/api/place/add/json").then().extract() .response().asString();
		 * 
		 * JsonPath jp = new JsonPath(rsp); Assert.assertEquals(jp.getString("status"),
		 * "OK");
		 */

		/*
		 * Response rsp = given().log().all().header("Content-Type",
		 * "application/json").queryParam("key", "qaclick123")
		 * .body(AddPlaceAPI.addPlacePayLoad()).when().post("/maps/api/place/add/json").
		 * then().extract() .response();
		 * 
		 */

		RequestSpecification req = given().spec(AddPlaceAPI.requestSpecBuilder()).body(AddPlaceAPI.addPlacePayLoad());
		Response rsp = req.when().post("/maps/api/place/add/json").then().spec(AddPlaceAPI.responseSpecBuilder()).extract().response();

		rsp.prettyPeek();

		JSONObject jo = new JSONObject(rsp.asString());

		Assert.assertTrue(jo.has("id"));
		Assert.assertNotNull(jo.get("id"));

	}

}
