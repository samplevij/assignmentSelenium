package assignmentSelenium.stepDefns;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import assignmentSelenium.utility.ReadingTestData;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import static assignmentSelenium.reporting.ExtentTestManager.startTest;
import static assignmentSelenium.reporting.ExtentTestManager.infoLogs;


public class CucumberBase {
	
	static WebDriverWait wait;
	static WebDriver driver;
	
	
	@Before
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
				infoLogs("Driver launched successfully with url"+ReadingTestData.getProps("appUrl"));
	}
	
	@After
	public void quitDriver()
	{
		driver.quit();
	}
	
	public static WebDriver getDriver()
	{
		return driver;
	}

}
