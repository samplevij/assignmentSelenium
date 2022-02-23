package assignmentSelenium.reporting;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentTestManager {
	
	static Map<Integer,ExtentTest>extentTestMap=new HashMap<>();
	
	static ExtentReports extent= ExtentManager.createExtentReports();
	static ExtentTest test;
	
	public static synchronized ExtentTest getTest()
	{
		return extentTestMap.get((int)Thread.currentThread().getId());
	}
	
	public static synchronized ExtentTest startTest(String testName,String desc)
	{
		test=extent.createTest(testName,desc);
		 extentTestMap.put((int)Thread.currentThread().getId(), test);
		 System.out.println("Test name:"+test);
		 return test;
	}
	
	public static synchronized ExtentTest infoLogs(String desc)
	{
		
		 return test.log(Status.INFO, desc);
	}
	
	public static synchronized ExtentTest successInfo(String desc)
	{
		
		 return test.log(Status.PASS, desc);
	}

}
