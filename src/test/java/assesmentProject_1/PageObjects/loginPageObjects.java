package assesmentProject_1.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class loginPageObjects {
	
	private static final Logger logger = LogManager.getLogger(loginPageObjects.class);
	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;
	
	//===========Locaters for login====================================================
	
		private By myAccount = By.xpath("//a[contains(text(), 'My Account')]");
		private By userName = By.id("username"); ////input[@id = 'username']
		private By userpassword = By.xpath("//input[@id = 'password']");
		private By loginBtn = By.xpath("//input[@name = 'login']");
		
	
	//====================Constructer====================================================
	
	public loginPageObjects(WebDriver driver, Scenario scn) {
		
		this.driver = driver;
		this.scn = scn;
	}
	
	//=================Method to validate Myaccount btn=================================
	
	public void validateMyAccount() {
		
		WebElement myAccountBtn = driver.findElement(myAccount);
		
		Assert.assertEquals(true, myAccountBtn.isDisplayed());
    	logger.info("Validate the myAccount Button");
    	scn.log("Validate the myAccount Button");
	}
	
	//=================Method to validate Myaccount btn=================================
	
	public void clickMyAccount() {
		
		WebElement myAccountBtn = driver.findElement(myAccount);
		
		wait= new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(myAccountBtn));
    	myAccountBtn.click();
    	logger.info("Click on the myAccount Button");
    	scn.log("Click on the myAccount Button");
	}
	
	//==================Method to log into the account with multiple id====================
	
	public void loginWithCredentials(String email,String password) {
		
		WebElement userId = driver.findElement(userName);
		userId.sendKeys(email);
		WebElement password1 = driver.findElement(userpassword);
		password1.sendKeys(password);
		WebElement LogIn = driver.findElement(loginBtn);
		LogIn.click();
		
	}
	
	
}
