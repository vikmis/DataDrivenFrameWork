package com.w2a.Rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestPropeties {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Properties config = new Properties();
		WebDriver driver;
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Config.properties");
		config.load(fis);
		System.out.println(config.getProperty("browser"));
		
		if(config.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.out.println("in chrome");
			
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Executable\\chromedriver.exe");
			
			driver=new ChromeDriver();
			driver.get(config.getProperty("testSiteUrl"));
			Thread.sleep(3000);
			System.out.println("out chrome");
			driver.quit();
			
			
		
		}
		
		//System.out.println(System.getProperty("user.dir"));
		
		/*Properties config = new Properties();		
		Properties OR = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Config.properties");
		config.load(fis);
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\OR.properties");
		OR.load(fis);
		System.out.println(config.getProperty("browser"));
		System.out.println(OR.getProperty("BnkMngLg"));
		*/
		
		
		
		
	}

}
