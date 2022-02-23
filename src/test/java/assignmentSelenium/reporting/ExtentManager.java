package assignmentSelenium.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	public static final ExtentReports extentReports= new ExtentReports();
	
	public synchronized static ExtentReports createExtentReports()
	{
		ExtentSparkReporter reporter=new ExtentSparkReporter("./target/extent-report.html");
		reporter.config().setReportName("Regression Execution Results");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Author", "Vijay");
		return extentReports;
	}

}
