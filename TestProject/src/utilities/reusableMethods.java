package utilities;

import io.restassured.path.json.JsonPath;

public class reusableMethods {
	
	
	public static JsonPath rawToJson(String response) {
		
		JsonPath js = new JsonPath(response);
		return js;
	}

}
