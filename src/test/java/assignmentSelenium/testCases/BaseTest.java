package assignmentSelenium.testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import assignmentSelenium.utility.ReadingTestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import static assignmentSelenium.reporting.ExtentTestManager.startTest;
import static assignmentSelenium.reporting.ExtentTestManager.infoLogs;


public class BaseTest {
	
	static WebDriverWait wait;
	static WebDriver driver;
	
	
	@BeforeSuite
	public void initDriver()
	{
				startTest("BaseTest case", "Starting the case");
				String browser=ReadingTestData.getProps("browser");
				infoLogs("User chose to run with :"+browser);
				switch(browser)
				{
				case "chrome":
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					break;
				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					break;
				case "edge":
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
					break;					
				}
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				driver.navigate().to(ReadingTestData.getProps("appUrl"));
				infoLogs("Driver launched successfully with url"+ReadingTestData.getProps("appUrl"));
	}
	
	@AfterSuite
	public void quitDriver()
	{
		driver.quit();
	}
	
	public static WebDriver getDriver()
	{
		return driver;
	}

}
