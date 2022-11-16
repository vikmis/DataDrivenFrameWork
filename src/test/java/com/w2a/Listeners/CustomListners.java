package com.w2a.Listeners;

import java.io.IOException;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.Base.TestBase;
import com.w2a.Utilities.TestUtil;

public class CustomListners extends TestBase implements ITestListener,ISuiteListener {

	public void onTestStart(ITestResult result) {
		
		//test=rep.startTest(result.getName().toUpperCase());
		test = rep.startTest(result.getName().toUpperCase());
		
		
		
	}

	public void onTestSuccess(ITestResult result) {
		
		test.log(LogStatus.PASS,result.getName().toUpperCase()+ "Pass");
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestFailure(ITestResult result) {
		
		try {
			TestUtil.captureScreenShot();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL,result.getName().toUpperCase()+ "Failed with Exception :"+result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotname));
		rep.endTest(test);
		rep.flush();
	}

	public void onTestSkipped(ITestResult result) {
		
		test.log(LogStatus.SKIP, result.getName()+"Skipped the test case as run mode is NO");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}
	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		
	}
	
	

}
