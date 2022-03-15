package pack1;

import org.testng.Assert;

import files.payLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJson {
	
	
	public static void main(String[] args) {
		
			JsonPath js = new JsonPath(payLoad.complexJson());
			//Print the number of courses
			System.out.println("No. of courses : "+js.getInt("courses.size()"));
//			Print purchase amount
			System.out.println("purchaseAmount : "+js.getInt("dashboard.purchaseAmount"));
//			Title of first course 
			System.out.println("Title of first course : "+js.getString("courses[0].title"));
//			4. Print All course titles and their respective Prices
			for (int i = 0; i < js.getInt("courses.size()"); i++) {
				System.out.println("Title of course : "+js.getString("courses["+i+"].title"));
				System.out.println("Price of course : "+js.getInt("courses["+i+"].price"));
			}
//			5. Print no of copies sold by RPA Course
			for (int i = 0; i < js.getInt("courses.size()"); i++) {
				if((js.getString("courses["+i+"].title").equalsIgnoreCase("RPA"))){
				System.out.println("Copies sold of RPA course : "+js.getString("courses["+i+"].copies"));
				break;
				}
			}
//			6. Verify if Sum of all Course prices matches with Purchase Amount
			int sum = 0;
			for (int i = 0; i < js.getInt("courses.size()"); i++) {
				 sum = sum + js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");
			}
			Assert.assertEquals(sum, js.getInt("dashboard.purchaseAmount"));
	}

}
