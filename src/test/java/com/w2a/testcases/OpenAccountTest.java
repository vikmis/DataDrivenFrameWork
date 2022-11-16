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


public class OpenAccountTest extends TestBase {
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	
	public void openAccountTest(Hashtable<String , String> data) throws InterruptedException {
		
		if(!TestUtil.isTestRunnable("openAccountTest", excel)) {
			
			throw new SkipException("Skipping the test"+"openAccountTest".toUpperCase()+" as runmode in NO");
		}
		
		click("openAccount_xpath");
		select("customer_xpath",data.get("customer"));
		select("curency_xpath",data.get("currency"));		
		click("process_xpath");	
		
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
	}
		
	

}
