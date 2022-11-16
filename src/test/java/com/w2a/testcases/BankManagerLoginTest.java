package com.w2a.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.sun.tools.sjavac.Log;
import com.w2a.Base.TestBase;

public class BankManagerLoginTest extends TestBase {
	
	@Test
	
	public void loginAsBankManager() throws IOException {
		
		
		verifyEquals("xyz", "abc");
		/*log.debug("Welcome in log in page ");
		Reporter.log("Log in succesully ");*/
		//driver.findElement(By.xpath(OR.getProperty("BnkMngLg"))).click();
		
		click("BnkMngLg_xpath");
		
		/*log.debug("Bank manager buton clicked  ");
		Reporter.log("Bank manger button clicked");*/
		
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Addcutomer_xpath"))),"Login is not succesfull");
		//Thread.sleep(3000);
		
		
		
	}

}
