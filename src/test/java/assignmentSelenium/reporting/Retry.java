package assignmentSelenium.reporting;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import static assignmentSelenium.reporting.ExtentTestManager.getTest;
import com.aventstack.extentreports.Status;

import assignmentSelenium.testCases.BaseTest;



public class Retry implements IRetryAnalyzer{

	private int count=0;
	private static int maxTry=3;
	
	@Override
	public boolean retry(ITestResult iTestResult) {
		if(!iTestResult.isSuccess())
		{
			if(count<maxTry)
			{
				count++;
				iTestResult.setStatus(ITestResult.FAILURE);
				extendReportsFailOperations(iTestResult);
				return true;
			}
		}
			else {
				iTestResult.setStatus(ITestResult.SUCCESS);
			}
		return false;
	}
	
	public void extendReportsFailOperations(ITestResult iTestResult)
	{
		Object testClass =iTestResult.getInstance();
		WebDriver webdriver=((BaseTest)testClass).getDriver();
		String base64Screenshot ="data:image/png:base64,"+((TakesScreenshot)webdriver).getScreenshotAs(OutputType.BASE64);
		getTest().log(Status.FAIL,
				  "Failed",getTest().addScreenCaptureFromBase64String(base64Screenshot).
				  getModel().getMedia().get(0));
	}
	

	
	
}
