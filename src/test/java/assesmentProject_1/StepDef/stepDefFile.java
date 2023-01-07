package assesmentProject_1.StepDef;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import assesmentProject_1.Core.WebDriverFactory;
import assesmentProject_1.PageObjects.CommonPageObjects;
import assesmentProject_1.PageObjects.loginPageObjects;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class stepDefFile {

	private static final Logger logger = LogManager.getLogger(stepDefFile.class);

	WebDriver driver;

	Scenario scn;
	
	WebDriverWait wait;

	String base_url = "https://practice.automationtesting.in/shop/";

	//============== Declare object reference of pageObjects classes =============================

	CommonPageObjects cmnPageObject;

	loginPageObjects logPageObject;


	//====================== Before Hook =========================================================	

	
	@Before(order=2)

	public void setUp(Scenario scn) {

		this.scn = scn;

		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.webdriverForBrowser(browserName);
		scn.log("Browser is invoked");

		//Initialize object of page objects classes 

		cmnPageObject = new CommonPageObjects(driver, scn);

		logPageObject = new loginPageObjects(driver, scn);


	}

	//====================== After Hook =========================================================

	@After(order=2) //Capture screenshot if testcase got failed

	public void captureScreenshot(Scenario scn) {

		if(scn.isFailed()) {

			TakesScreenshot scnShot = ((TakesScreenshot)driver);
			byte[] data = scnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Name of failed step is: "+ scn.getName());
			scn.log("Attach a screenshot as step get failed");
		}
		else {

			scn.log("Test case get passed, no screenshot is captured");
		}

	}

	@After(order=1) //Quit the browser

	public void tearDown(Scenario scn){

		WebDriverFactory.quitTheBrowser();
		scn.log("Browser is quit");
	}

	//========================Background=======================================
	@Given("User navigate to URL and open the landing page")
	public void user_navigate_to_url_and_open_the_landing_page() {
		
		WebDriverFactory.navigateToURL(base_url);
	}

	//=====================================================================================
	@When("User is on landing page")
	public void user_is_on_landing_page() {
		
		logger.info("User is on landing page after navigating to base URL");
	    scn.log("User is on landing page after navigating to base URL");
	}
	
	@Then("User see the logo of application")
	public void user_see_the_logo_of_application() {
		
		cmnPageObject.displayLogo();
		scn.log("Display the application logo on landing page");
	}

	//===========================================================================================
	@When("User see the product category")
	public void user_see_the_product_category() {

		cmnPageObject.setProdCategory();		
	}

	@Then("Validate product category as per expected product category listed below")
	public void validate_product_category_as_per_expected_product_category_listed_below(List<String> prodcat) {
		
		cmnPageObject.validateProdCat(prodcat);
		scn.log("Validate the product category with expected datatable");
		
	}
	@Then("Size of product category should be {int}")
	public void size_of_product_category_should_be(Integer prodCount) {
		
		cmnPageObject.sizeOfProdCategory(prodCount);
	}

	//========================================================================================

	@Given("user check the MyAccount tab")
	public void user_check_the_my_account_tab() {
		
		logPageObject.validateMyAccount();
		scn.log("My account button is displayed");
	}


	@When("User clicks on MyAccount")
	public void user_clicks_on_my_account() {
		
		logPageObject.clickMyAccount();
	}
	@Then("User is on MyAccount page where the title is {string}")
	public void user_is_on_my_account_page_where_the_title_is(String pagetitle) {
		
		//cmnPageObject.validatePageTitle(pagetitle);
		scn.log("user is on my account page and the title is: "+ driver.getTitle());
	}
	@Then("Redirect to landing page")
	public void redirect_to_landing_page() {
		
		driver.navigate().to(base_url);
		
		logger.info("user is redirected to landing page");
		scn.log("user is redirected to landing page");
	}

	//======================================================================================================
	@Given("User click on MyAccount button from home page")
	public void user_click_on_my_account_button_from_home_page() {
		
		logPageObject.clickMyAccount();
	}


	@When("User redirected to login page of the application where title is {string}")
	public void user_redirected_to_login_page_of_the_application_where_title_is(String pagetitle) {
		
	//	cmnPageObject.validatePageTitle(pagetitle);
		
	}
	@When("User enters {string} and {string} and click on signin button")
	public void user_enters_and_and_click_on_signin_button(String email, String password) {
		
		logPageObject.loginWithCredentials(email, password);		
		scn.log("User got successfully logged in");		
		
	}
	@Then("User successfully directed to next page")
	public void user_successfully_directed_to_next_page() {

		scn.log("user is on Login page");
	}

	//=========================================================================================================
	@When("User click on signin button from home page")
	public void user_click_on_signin_button_from_home_page() {
		
		logPageObject.clickMyAccount();
	}

	@Then("User is unable to login with an error message {string}")
	public void user_is_unable_to_login_with_an_error_message(String errorMessage) {
	    
		WebElement failedMsg = driver.findElement(By.xpath("//ul[@class = 'woocommerce-error']/li"));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class = 'woocommerce-error']/li")));
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(errorMessage, failedMsg.getText());

	}

	//=================================================================================

}
