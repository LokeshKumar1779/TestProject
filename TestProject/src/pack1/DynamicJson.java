package pack1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.reusableMethods;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

public class DynamicJson {
	
	ArrayList<String> book_id = new ArrayList<String>();
	
	@Test(dataProvider="BooksData")
	public void addBook(String aisle, int isbn) {
		
		RestAssured.baseURI="http://216.10.245.166";
		Response response = given().log().all().header("Content-Type","application/json").body(payLoad.Addbook(aisle,isbn)).
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response();
		
		JsonPath js = reusableMethods.rawToJson(response.asString());
		
		book_id.add(js.get("ID"));
		System.out.println(book_id);
		
		
		
	}
	
	@Test(dataProvider="BookIds")
	public void deleteBook(String bookId) {
		
		RestAssured.baseURI="http://216.10.245.166";
		Response response = given().log().all().header("Content-Type","application/json").body(payLoad.Deletebook(bookId)).
		when().post("/Library/DeleteBook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response();
		
		JsonPath js = reusableMethods.rawToJson(response.asString());
		String msg = js.get("msg");
		System.out.println(msg);

	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		
		Object[][] data = new Object[2][2];
		data[0][0] = "sswaadf";
		data[0][1] = 234;
		data[1][0] = "sdawaxWf";
		data[1][1] = 4565;
		
		return data;
	
	}
	
	@DataProvider(name="BookIds")
	public Object[] deleteBooks() {
		
		ArrayList<String> list = new ArrayList<>();
		list.addAll(book_id);
		
		return list.toArray();
	
	}

}
