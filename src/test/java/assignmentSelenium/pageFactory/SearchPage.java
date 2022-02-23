package assignmentSelenium.pageFactory;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static assignmentSelenium.reporting.ExtentTestManager.infoLogs;

public class SearchPage {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	public SearchPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, Duration.ofSeconds(15));
		js= (JavascriptExecutor)driver;
	}

	@FindBy(how=How.XPATH,using="//*[contains(@class,'province-dropdown__province-menu') and @data-icon='chevron-down']")
	private WebElement provinceDropDown;

	@FindBy(how=How.CSS,using="ul.province-dropdown__list")
	private WebElement provinceDropDownList;

	@FindBys(@FindBy(how=How.CSS,using="ul.province-dropdown__list>li"))
	private List<WebElement> provinceList;

	@FindBy(how=How.XPATH,using="//span[text()='Make & Model']")
	private WebElement makeAndModelOption;

	@FindBy(how=How.XPATH,using="//div[contains(@class,'sort-by search-filters')]")
	private WebElement sortByDropDown;

	@FindBys(@FindBy(how=How.XPATH,using="//div[@class='vehicle-card__title font-weight-medium text-body-1 text-h6-card']"))
	private List<WebElement> listOfCars;

	@FindBys(@FindBy(how=How.XPATH,using="//span[contains(@class,'fav-icon')]"))
	private List<WebElement>favIcon;

	public void clickProvinceDropDown()
	{
		try {
		wait.until(ExpectedConditions.elementToBeClickable(provinceDropDown));
		provinceDropDown.click();
		}catch(ElementClickInterceptedException e)
		{
			js.executeScript("arguments[0].click();", provinceDropDown);
		}catch(TimeoutException e)
		{
			infoLogs("Exception occured on clicking the Element :provinceDropDown"+e.getLocalizedMessage());
		}
	}

	public  void selectProvice(String province)
	{
		try {
		wait.until(ExpectedConditions.visibilityOf(provinceDropDownList));
		for(WebElement provinceToSelect:provinceList)
		{
			if(provinceToSelect.getText().equals(province))
			{
				provinceToSelect.click();
				break;
			}
		}
		}catch(TimeoutException |ElementNotVisibleException e)
		{
			infoLogs("Exception occured on visibility of the Element :provinceDropDownList"+e.getLocalizedMessage());
		}
	}

	public void selectMakeAndModel(String makeModel) throws InterruptedException
	{
		try {
		wait.until(ExpectedConditions.visibilityOf(makeAndModelOption));
		makeAndModelOption.click();
		String [] detailsOfMakeModel=makeModel.split(" ");
		String xpathForMake ="//span[text()='"+detailsOfMakeModel[0]+"']";
		String xpathForModel ="//span[text()='"+detailsOfMakeModel[1]+"']";
		boolean flag=false;
		int count=0;
		while(count<3 && flag==false)
		{
			try {
				js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpathForMake)));
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath(xpathForMake)));
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathForModel))));
				js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpathForModel)));
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath(xpathForModel)));
				Thread.sleep(5000);
				flag=true;
			}catch(StaleElementReferenceException e)
			{
				Thread.sleep(1000);
				count++;
			} 
		}
		}catch(TimeoutException e)
		{
			infoLogs("Exception occured on visibility of the Element :makeAndModelOption"+e.getLocalizedMessage());
		}
	}

	public void selectSortBy(String sortOption) throws InterruptedException
	{
		try {
		wait.until(ExpectedConditions.visibilityOf(sortByDropDown));
		sortByDropDown.click();
		String xpathSort="//div[contains(text(),'"+sortOption+"')]";
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathSort))));
		driver.findElement(By.xpath(xpathSort)).click();
		Thread.sleep(5000);
		}catch(TimeoutException e)
		{
			infoLogs("Exception due to visibility of the element:sortByDropDown"+e.getLocalizedMessage());
		}
	}

	public void choosefavourite3Cars()
	{
		try {
		int count=0;
		wait.until(ExpectedConditions.visibilityOfAllElements(listOfCars));
		for(WebElement fav:favIcon)
			{
				fav.click();
				count++;
				if(count==3)
				{
					break;
				}
			}
		}catch(TimeoutException e)
		{
			infoLogs("Exception occured due to visibility of the element:listOfCars"+e.getLocalizedMessage());
		}
		}
	
	public void selectACar()
	{
		try {
		wait.until(ExpectedConditions.visibilityOfAllElements(listOfCars));
		for(WebElement cars:listOfCars)
			{
			wait.until(ExpectedConditions.elementToBeClickable(cars));
			cars.click();
			break;
			}
		}catch(TimeoutException e)
		{
			infoLogs("Exception occured due to visibility of the element:listOfCars"+e.getLocalizedMessage());
		}
	}
}
