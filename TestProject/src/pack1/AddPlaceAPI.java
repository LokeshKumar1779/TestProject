package pack1;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import static io.restassured.RestAssured.given;

public class AddPlaceAPI {
	
	
	public static RequestSpecification requestSpecBuilder() {
		
		
		RequestSpecBuilder reqBuild = new RequestSpecBuilder();
		reqBuild.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").setBaseUri("https://rahulshettyacademy.com");
		RequestSpecification reqSpec = reqBuild.build();
		return reqSpec;
	}
	
	public static ResponseSpecification responseSpecBuilder() {
		
		
		ResponseSpecBuilder resBuild = new ResponseSpecBuilder();
		resBuild.expectContentType(ContentType.JSON).expectStatusCode(200);
		ResponseSpecification resSpec = resBuild.build();
		return resSpec;
	}
	
	public static AddPlace addPlacePayLoad() {
		
		String expAddress = "29, side layout, cohen 09";
		AddPlace ap = new AddPlace();

		Location lc = new Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);

		List<String> ty = new ArrayList<String>();
		ty.add("shoe park");
		ty.add("shop");

		ap.setAccuracy(100);
		ap.setAddress(expAddress);
		ap.setLanguage("French-IN");
		ap.setLocation(lc);
		ap.setName("Frontline house");
		ap.setPhone_number("8234287348");
		ap.setTypes(ty);
		ap.setWebsite("http://google.com");
		
		return ap;
	}
	


}
