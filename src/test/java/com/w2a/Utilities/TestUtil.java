package com.w2a.Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.w2a.Base.TestBase;

public class TestUtil extends TestBase {
	
	public static String screenshotPath;
	public static String screenshotname;
	
	public static void captureScreenShot() throws IOException {
		
		Date d =new Date();
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 screenshotname =d.toString().replace(":", "_").replace(" ","_");
		 
		FileUtils.copyFile(srcFile, 
				new File(System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots\\"+screenshotname+".jpg"));
		
		FileUtils.copyFile(srcFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotname));
		
		
		
		
	}
	
	@DataProvider(name="dp")
		public Object[][] getData(Method m ){
		
		String sheetName= m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][1];
		
		Hashtable<String,String> table = null;
		
		for(int rowNum=2; rowNum<=rows; rowNum++){
			
			table = new Hashtable<String,String>();
			
			for(int colNum=0; colNum<cols; colNum++){
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
			//data[rowNum-2][colNum]=	excel.getCellData(sheetName, colNum, rowNum);
			data[rowNum-2][0]=table;	
		
			//table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));	
			//data[rowNum-2][0]=table;	
				
			}
			
		}
		
		
		return data;
		
		
		
	}
	
public static boolean isTestRunnable(String testname,ExcelReader excel) {
	
	String sheetName="test_suit";
	int rows = excel.getRowCount(sheetName);
	
	for(int rNum=2;rNum<=rows;rNum++) {
		
		String testcase = excel.getCellData(sheetName, "TestID", rNum);
		
		if(testcase.equalsIgnoreCase(testname)) {
			
			String runmode = excel.getCellData(sheetName, "RunMode", rNum);
			
			if(runmode.equalsIgnoreCase("Y"))
				return true;
			else
				return false;
		}
	}
	return false;
	}

}
