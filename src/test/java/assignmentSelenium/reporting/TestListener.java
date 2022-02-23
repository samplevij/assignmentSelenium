package assignmentSelenium.reporting;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import static assignmentSelenium.reporting.ExtentTestManager.getTest;
import assignmentSelenium.testCases.BaseTest;

import java.util.Objects;



public class TestListener extends BaseTest implements ITestListener {
	
	private static String getTestMethodName(ITestResult iTestResult)
	{
		return iTestResult.getMethod().getConstructorOrMethod().getName();
		
	}
	
	@Override
	public void onStart(ITestContext iTestContext)
	{
		iTestContext.setAttribute("WebDriver", BaseTest.getDriver());
	}
	
	@Override
	public void onFinish(ITestContext iTestContext)
	{
		ExtentManager.extentReports.flush();
	}
	
	@Override
	public void onTestStart(ITestResult iTestResult)
	{

		System.out.println("Starting the Test"+getTestMethodName(iTestResult));
	}
	
	@Override
	public void onTestSuccess(ITestResult iTestResult)
	{
	
		getTest().log(Status.PASS, "Test Pass");
	}
	
	@Override
	public void onTestFailure(ITestResult iTestResult)
	{
		System.err.println("Test Failed for Method:"+getTestMethodName(iTestResult));
		
		 Object testClass =iTestResult.getInstance(); 
		 WebDriver webdriver=BaseTest.getDriver(); 
		 String base64Screenshot="data:image/png;base64,"+((TakesScreenshot)Objects.requireNonNull(webdriver)).getScreenshotAs(OutputType.BASE64);
		 getTest().log(Status.FAIL,"Failed",getTest().addScreenCaptureFromBase64String(base64Screenshot).
		getModel().getMedia().get(0));
		}

}
