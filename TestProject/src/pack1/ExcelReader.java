package pack1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	static Map<String, String> testcaseData;
	public static enum Platforms {
		API;
	}

	public static Map<String, String> readExcelData(String sheetname, String rowKeyVal) throws IOException {
		XSSFWorkbook workbook = null;

		try {
//			log.info("Reading data from " + sheetname + "." + rowKeyVal);

			String path;
			FileInputStream file;
			String fileSep = File.separator;

			Sheet sheet;
			testcaseData = new HashMap<String, String>();
			path = System.getProperty("user.dir") + fileSep + "src" + fileSep + "test" + fileSep + "java" + fileSep
					+ "testdata" + fileSep + Platforms.API.toString().toLowerCase() + fileSep + "Testdata.xlsx";
//			log.debug(path);
			file = new FileInputStream(path);
			workbook = new XSSFWorkbook(file);
//			log.info("Workbook read successfully.");
			sheet = workbook.getSheet(sheetname);
			Row row = null;
			Cell cell;
			int rowId = 0;

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				cell = row.getCell(0);
				if (cell.getStringCellValue().trim().equalsIgnoreCase(rowKeyVal)) {
					rowId = i;
//					log.info("Test data found.");
					break;
				}
			}

			if (rowId == 0) {
//				log.warn("No testdata exists.");
			} else {
				row = sheet.getRow(rowId);
				for (int i = 1; i < row.getLastCellNum(); i++) {
					cell = row.getCell(i);
					String cellValue = cell.getStringCellValue();
					String[] arr = cellValue.split("=", 2);
					testcaseData.put(arr[0], arr[1]);
				}
			}
		} catch (Exception e) {
//			log.fatal("Excel data sheet cannot be read.");
			e.printStackTrace();
		} finally {
			workbook.close();
		}
//		log.info("FUNCTION readExcelData FINISHED.");
		return testcaseData;
	}

}
