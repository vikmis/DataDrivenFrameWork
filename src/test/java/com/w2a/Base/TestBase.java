package com.w2a.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/*import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;*/
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.Utilities.ExcelReader;
import com.w2a.Utilities.ExtendManager;
import com.w2a.Utilities.TestUtil;


public class TestBase {
	
	
	
	/*
	 * Webdriver- done 
	 * Properties- done 
	 * Logs- log 4j jar file, . log file,log4j.properties,logger class,
	 * Extends report
	 * DB
	 * Excel
	 * Mail
	 * ReportNg , Extend report 
	 * Jenkins
	 * 
	 */
	
	public static WebDriver driver;
	public static Properties config = new Properties();		
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger(TestBase.class);
	//public static org.apache.logging.log4j.Logger log = LogManager.getLogger(TestBase.class);
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\Excel\\testdata.xlsx");
	
	public static WebDriverWait wait;
	
	public ExtentReports rep =ExtendManager.getInstance();
	
	public static ExtentTest test;
	
	@BeforeSuite
	public void setUp() {
		if(driver==null) {
			
			try {
			
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Config.properties");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("loaded");
				System.out.println("config file loaded !!!");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\OR.properties");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			if(config.getProperty("browser").equalsIgnoreCase("firefox")) {
				
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Executable\\geckodriver.exe");
				driver = new FirefoxDriver();
				
				
			}else if(config.getProperty("browser").equalsIgnoreCase("chrome")) {
				
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Executable\\chromedriver.exe");
				driver=new ChromeDriver();
				log.debug("Chrome launched !!!");
							
			}else if(config.getProperty("browser").equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Executable\\msedgedriver.exe");
				driver = new EdgeDriver();
				
			}
			
			driver.get(config.getProperty("testSiteUrl"));
			log.debug("navigatoed to the url "+config.getProperty("testSiteUrl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,5);
		}
		
	}
	
	public void click(String locator) {
		
		if(locator.endsWith("_xpath")) {
		
		driver.findElement(By.xpath(OR.getProperty("locator"))).click();
		
		}else if(locator.endsWith("_css")) {
			driver.findElement(By.cssSelector(OR.getProperty("locator"))).click();
		}else if(locator.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty("locator"))).click();
		}
		test.log(LogStatus.INFO, "Clicking on :"+locator);
		
	}
	
	public void type(String locator,String value) {
		
		if(locator.endsWith("_xpath")) {
		
		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys("value");
		}else if(locator.endsWith("_css")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys("value");
		}else if(locator.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys("value");
		}
		test.log(LogStatus.INFO, "Typing in :"+locator+"entered the value :"+value);
	}
	
	static WebElement dropDown;
	
	public void select(String locator,String value) {
		
		if(locator.endsWith("_xpath")) {
			
			dropDown =driver.findElement(By.xpath(OR.getProperty(locator)));
			}else if(locator.endsWith("_css")) {
				dropDown =driver.findElement(By.cssSelector(OR.getProperty(locator)));
			}else if(locator.endsWith("_id")) {
				dropDown =driver.findElement(By.id(OR.getProperty(locator)));
			}
		Select select=new Select(dropDown);
		select.deselectByVisibleText(value);
		test.log(LogStatus.INFO, "Selecting from dropdown :"+locator+" as value :"+value);
		
	}
	
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
			
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	
	public static void verifyEquals(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual, expected);

		} catch (Throwable t) {

			TestUtil.captureScreenShot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotname + "><img src=" + TestUtil.screenshotname
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			/*CustomListeners.testReport.get().log(Status.FAIL, " Verification failed with exception : " + t.getMessage());
			CustomListeners.testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.screenshotName)
					.build());*/
			
			test.log(LogStatus.FAIL," Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotname));

		}

	}
	
	@AfterSuite
	public void tearUp() {
		if(driver!=null) {
			driver.quit();
		}
		log.debug("Test execution completed");
	}
	
}
