package files;

public class payLoad {
	
	
	public static String AddPlace() {
		
		return "{\n"
				+ "  \"location\": {\n"
				+ "    \"lat\": -38.383494,\n"
				+ "    \"lng\": 33.427362\n"
				+ "  },\n"
				+ "  \"accuracy\": 50,\n"
				+ "  \"name\": \"Frontline house\",\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\n"
				+ "  \"types\": [\n"
				+ "    \"shoe park\",\n"
				+ "    \"shop\"\n"
				+ "  ],\n"
				+ "  \"website\": \"http://google.com\",\n"
				+ "  \"language\": \"French-IN\"\n"
				+ "}\n"
				+ "";
	}

	public static String updatePlace(String placeId,String address) {
		// TODO Auto-generated method stub
		return "{\n"
				+ "\"place_id\":\""+placeId+"\",\n"
				+ "\"address\":\""+address+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}\n"
				+ "";
	}
	
	public static String complexJson() {
		
		return "{\n"
				+ "\"dashboard\": {\n"
				+ "\"purchaseAmount\": 910,\n"
				+ "\"website\": \"rahulshettyacademy.com\"\n"
				+ "},\n"
				+ "\"courses\": [\n"
				+ "{\n"
				+ "\"title\": \"Selenium Python\",\n"
				+ "\"price\": 50,\n"
				+ "\"copies\": 6\n"
				+ "},\n"
				+ "{\n"
				+ "\"title\": \"Cypress\",\n"
				+ "\"price\": 40,\n"
				+ "\"copies\": 4\n"
				+ "},\n"
				+ "{\n"
				+ "\"title\": \"RPA\",\n"
				+ "\"price\": 45,\n"
				+ "\"copies\": 10\n"
				+ "}\n"
				+ "]\n"
				+ "}";
	}

	public static String Addbook(String isbn, int aisle) {
		// TODO Auto-generated method stub
		String body = "{\n"
				+ "\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\n"
				+ "\"isbn\":\""+isbn+"\",\n"
				+ "\"aisle\":\""+aisle+"\",\n"
				+ "\"author\":\"John foe\"\n"
				+ "}\n"
				+ "";
		return body;
	}

	public static String Deletebook(String book_id) {
		// TODO Auto-generated method stub
		String body = "{\n"
				+ " \n"
				+ "\"ID\" : \""+book_id+"\"\n"
				+ " \n"
				+ "} \n"
				+ "";
		return body;
	}

}
