package assignmentSelenium.pageFactory;

import static assignmentSelenium.reporting.ExtentTestManager.infoLogs;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PurchasePage {
	
	WebDriver driver;
	WebDriverWait wait;
	Actions act;
	JavascriptExecutor js;

	public PurchasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, Duration.ofSeconds(15));
		js= (JavascriptExecutor)driver;
		act=new Actions(driver);
	}
	
	@FindBy(how=How.XPATH,using="//div[contains(@class,'desktop-start-purchase')]//span[contains(text(),'Start Purchase')]")
	private WebElement startPurchase;
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'Calculate Delivery')]")
	private WebElement calculateDelivery;
	
	@FindBy(how=How.XPATH,using="//input[@id='street_address']")
	private WebElement streetAddress;
	
	@FindBys(@FindBy(how=How.XPATH,using="//div[contains(@class,'pcaitem')]//span"))
	private List<WebElement> deliverableCities;
	
	@FindBy(how=How.XPATH,using="//h4[text()='Your delivery cost is:']/following-sibling::h2")
	private WebElement deliveryCost;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Save and Confirm')]")
	private WebElement saveAndConfirm;
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'Select Warranty')]")
	private WebElement selectWarranty;
	
	public void clickPurchase()
	{
		try {
		wait.until(ExpectedConditions.elementToBeClickable(startPurchase));
		startPurchase.click();
		}catch(TimeoutException | ElementClickInterceptedException e)
		{
			if(e.getLocalizedMessage().contains("Intercepted"))
			{
				js.executeScript("arguments[0].click();", startPurchase);
			}
			
		}
	}
	
	public void clickCalculateDelivery()
	{
		try {
		wait.until(ExpectedConditions.elementToBeClickable(calculateDelivery));
		calculateDelivery.click();
		}catch(TimeoutException | ElementClickInterceptedException e)
		{
			if(e.getLocalizedMessage().contains("Intercepted"))
			{
				js.executeScript("arguments[0].click();", calculateDelivery);
			}
		}
	}
	
	public void enterStreetAddress(String address) throws InterruptedException
	{
		try {
		wait.until(ExpectedConditions.visibilityOf(streetAddress));
		act.sendKeys(streetAddress,address).build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElements(deliverableCities));
		if(deliverableCities.size()<=0)
		{
			streetAddress.clear();
			act.sendKeys(streetAddress,address).build().perform();
			Thread.sleep(3000);
		}
		for(WebElement ele:deliverableCities)
		{
			if(ele.getText().contains("ON"))
			{
				ele.click();
				Thread.sleep(2000);
				break;
			}
		}
		}catch(TimeoutException e)
		{
			infoLogs("Exception occured on clicking the Element :streetAddress"+e.getLocalizedMessage());
		}
	}
	
	public String getDeliveryCost()
	{
		String EstimatedDeliveryCost=null;
		try {
		wait.until(ExpectedConditions.visibilityOf(deliveryCost));
		 EstimatedDeliveryCost=deliveryCost.getText();
		}catch(TimeoutException e)
		{
			infoLogs("Exception occured on clicking the Element :deliveryCost"+e.getLocalizedMessage());
		}
		return EstimatedDeliveryCost;
	}
	
	public void clickSaveAndConfirm() throws InterruptedException
	{
		try {
		wait.until(ExpectedConditions.elementToBeClickable(saveAndConfirm));
		saveAndConfirm.click();
		}catch(TimeoutException | ElementClickInterceptedException e)
		{
			if(e.getLocalizedMessage().contains("Intercepted"))
			{
				js.executeScript("arguments[0].click();", saveAndConfirm);
			}else {
				infoLogs("Exception occured on clicking the Element :saveAndConfirm"+e.getLocalizedMessage());
			}
			
		}
	}
	
	public void doSelectWarranty(String warranty) throws InterruptedException
	{
		try {
		wait.until(ExpectedConditions.elementToBeClickable(selectWarranty));
		selectWarranty.click();
		String warrantyPeriod="//div[contains(text(),'"+warranty+"')]";
		driver.findElement(By.xpath(warrantyPeriod)).click();
		Thread.sleep(3000);
		}catch(ElementClickInterceptedException e)
		{
			infoLogs("Exception occured on clicking the Element :selectWarranty"+e.getLocalizedMessage());
		}
	}

}
