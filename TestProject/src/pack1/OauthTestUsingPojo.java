package pack1;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.API;
import pojo.Courses;
import pojo.GetCourse;
import pojo.WebAutomation;
import utilities.reusableMethods;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class OauthTestUsingPojo {
	
public static void main(String[] args) {
	
	ArrayList<String> expectedList = new ArrayList<>();
	expectedList.add("Selenium Webdriver Java");
	expectedList.add("Cypress");
	expectedList.add("Protractor");
	
	String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWiy7xchuSFEI-s-ZCa0Loo6pu-vqkzuSHHV3hqOSDpTZ1iZrLmxCDx8N54Z1x5iIw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
	String partialcode=url.split("code=")[1];
	String code=partialcode.split("&scope")[0];
	System.out.println(code);
	String response = given().urlEncodingEnabled(false)
					.queryParams("code",code)
                    .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                    .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                    .queryParams("grant_type", "authorization_code")
                    .queryParams("state", "verifyfjdss")
                    //.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
	                     // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                   .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").expect().defaultParser(Parser.JSON)
          .when()
	 .post("https://www.googleapis.com/oauth2/v4/token").asString();

	// System.out.println(response);
	JsonPath jsonPath = new JsonPath(response);
	String accessToken = jsonPath.getString("access_token");
	System.out.println(accessToken);
	
	
	GetCourse gc= given()
		.queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);


	
	System.out.println(gc.getLinkedIn());
	System.out.println(gc.getInstructor());
	
	List<API> apiCourses = gc.getCourses().getAPI();
	for (int i = 0; i < apiCourses.size(); i++) {
		if (apiCourses.get(i).getCourseTitle().contains("Soap")) {
			System.out.println(apiCourses.get(i).getPrice());
		}
	}
	
	ArrayList<String> actualList = new ArrayList<>();
	
	List<WebAutomation> webCourses = gc.getCourses().getWebAutomation();
	for (int i = 0; i < webCourses.size(); i++) {
		
			actualList.add(webCourses.get(i).getCourseTitle());
		}
	
	Assert.assertEquals(actualList, expectedList);
	}
	
	
	

	}

	


