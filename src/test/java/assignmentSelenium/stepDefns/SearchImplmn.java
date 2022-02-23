package assignmentSelenium.stepDefns;

import static assignmentSelenium.reporting.ExtentTestManager.infoLogs;
import static assignmentSelenium.reporting.ExtentTestManager.startTest;

import org.openqa.selenium.WebDriver;

import assignmentSelenium.pageFactory.PurchasePage;
import assignmentSelenium.pageFactory.SearchPage;
import assignmentSelenium.testCases.BaseTest;
import assignmentSelenium.utility.ReadingTestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class SearchImplmn {

	WebDriver driver;
	SearchPage searchPage;
	PurchasePage purchasePage;


	@Given("Navigate to {string}")
	public void navigate_to(String appUrl) {
		driver=CucumberBase.getDriver();
		searchPage= new SearchPage(driver);
		purchasePage=new PurchasePage(driver);
		driver.navigate().to(appUrl);
		searchPage.clickProvinceDropDown();  
	}
	
	@When("Select {string} Province")
	public void select_province(String province) {
		searchPage.selectProvice(province);
	}

	@When("Filter {string} vehicles using MakeModel filter")
	public void filter_vehicles_using_make_model_filter(String makeModel) throws InterruptedException {
		searchPage.selectMakeAndModel(makeModel.trim());
	}
	
	@When("Sort by Price {string}")
	public void sort_by_price(String priority) throws InterruptedException {
		searchPage.selectSortBy(priority);
	}
	
	@When("Favourite 3 RAM 1500 vehicles")
	public void favourite_ram_vehicles() {
		searchPage.choosefavourite3Cars();
	}
	
	@When("Pick an available RAM 1500 vehicle")
	public void pick_an_available_ram_vehicle() {
		searchPage.selectACar();
	}

	@When("Click on Get Started")
	public void click_on_get_started() {
		purchasePage.clickPurchase();
	}

	@When("In Calculate delivery, Enter Toronto Address")
	public void in_calculate_delivery_enter_toronto_address() throws InterruptedException {
		purchasePage.clickCalculateDelivery();
		purchasePage.enterStreetAddress("2-35 Valleywood Dr, Markham ON, L3R 5L9");
		String EstimatedDliverycost =purchasePage.getDeliveryCost();
		infoLogs("Estimated deliveryCost is"+EstimatedDliverycost);


	}
	@When("Select {string} warranty")
	public void select_warranty(String warrantyPeriod) throws InterruptedException {
		purchasePage.clickSaveAndConfirm();
		purchasePage.doSelectWarranty(warrantyPeriod);;
	}

}
