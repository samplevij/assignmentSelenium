package assignmentSelenium.testCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import assignmentSelenium.pageFactory.PurchasePage;
import assignmentSelenium.pageFactory.SearchPage;
import assignmentSelenium.utility.ReadingTestData;

import static assignmentSelenium.reporting.ExtentTestManager.startTest;
import static assignmentSelenium.reporting.ExtentTestManager.infoLogs;

public class SearchTest {
	
	WebDriver driver;
	SearchPage searchPage;
	PurchasePage purchasePage;
	
	@Test(priority=1,dataProvider = "contentsForBooking")
	public void searchCars(String province,String makeAndModel,String warrantyPeriod) throws InterruptedException
	{
		startTest("Search Cars", "Search cars on given criteria and select their warranty");
		driver=BaseTest.getDriver();
		searchPage= new SearchPage(driver);
		purchasePage=new PurchasePage(driver);
		searchPage.clickProvinceDropDown();
		searchPage.selectProvice(province);
		searchPage.selectMakeAndModel(makeAndModel.trim());
		infoLogs("Search based on make and Model :"+makeAndModel);
		searchPage.selectSortBy("Low to High");
		searchPage.choosefavourite3Cars();
		searchPage.selectACar();
		purchasePage.clickPurchase();
		purchasePage.clickCalculateDelivery();
		purchasePage.enterStreetAddress("2-35 Valleywood Dr, Markham ON, L3R 5L9");
		String EstimatedDliverycost =purchasePage.getDeliveryCost();
		infoLogs("Estimated deliveryCost is"+EstimatedDliverycost);
		assertTrue(Integer.parseInt(EstimatedDliverycost)<=0);
		purchasePage.clickSaveAndConfirm();
		purchasePage.doSelectWarranty(warrantyPeriod);
		infoLogs("Warranty Selects as :"+warrantyPeriod);
	}
	
	@DataProvider(name="contentsForBooking")
	public Object[][] getData()
	{
		return ReadingTestData.getData();
		
	}

}
