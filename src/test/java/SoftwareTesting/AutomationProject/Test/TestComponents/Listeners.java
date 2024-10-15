package SoftwareTesting.AutomationProject.Test.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SoftwareTesting.AutomationProject.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReporterNG obj=new ExtentReporterNG();
	ExtentReports extent=obj.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
	ExtentTest test;
	public void onTestStart(ITestResult result) {
		   
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		  }

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test pass");
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		String filepath =null;
		try {
			 filepath=GetScreenshot(result.getMethod().getMethodName(), driver);
			System.out.println(filepath);
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());	
		
	}

	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	public void onStart(ITestContext context) {
		// not implemented
	}

	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
