package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

public class GetPayload {
	
	@Test
	public static HashMap<String,Object> getPayload() {
		
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
		
//		System.out.println(colorsMap.toString());
		return colorsMap;
	}

}
