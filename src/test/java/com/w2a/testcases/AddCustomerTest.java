package com.w2a.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.Base.TestBase;
import com.w2a.Utilities.TestUtil;


public class AddCustomerTest extends TestBase {
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	
	public void addCustomerTest(Hashtable<String , String> data) throws InterruptedException {
		
		if(data.get("runmode").equalsIgnoreCase("Y")) {
			
			throw new SkipException("skipping the test cases as runmode of data set is NO");
		}
		
		//driver.findElement(By.xpath(OR.getProperty("Addcutomer"))).click();
	//driver.findElement(By.xpath(OR.getProperty("firstName"))).sendKeys(firstName);
		//driver.findElement(By.xpath(OR.getProperty("lastName"))).sendKeys(lastName);
		//driver.findElement(By.xpath(OR.getProperty("postCode"))).sendKeys(postCode);
		//driver.findElement(By.xpath(OR.getProperty("AddBtn"))).click();
		click("Addcutomer_xpath");
		type("firstName_xpath",data.get("firstName"));
		type("lastName_xpath",data.get("lastName"));
		type("postCode_xpath",data.get("postCode"));
		click("AddBtn_xpath");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alertText")));
		alert.accept();
		
	}
	
	
		
		
		
	}


