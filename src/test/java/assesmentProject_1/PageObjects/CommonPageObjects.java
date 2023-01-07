package assesmentProject_1.PageObjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class CommonPageObjects {
	
	private static final Logger logger= LogManager.getLogger(CommonPageObjects.class);
	WebDriver driver;
	Scenario scn;
	WebDriverWait wait;
	
	//========Locaters on landing page===================================
	
	private By logoImage = By.xpath("//div[@id = 'site-logo']/a/img[@alt = 'Automation Practice Site']");
	private By prodCategory= By.xpath("//div[@id='woocommerce_product_categories-2']/ul/li");
	
	//============= Expected Results =====================================
	
		String expCurrentURL = "https://practice.automationtesting.in/shop/";
		
	//==========Constructor===============================================
		
	public CommonPageObjects(WebDriver driver, Scenario scn) {
		
		this.driver = driver;
		this.scn    = scn;
	}
	
	//============ Method to validate page URL =============================
		public void validatePageURL()
		{
			wait= new WebDriverWait(driver,20);
			boolean check = wait.until(ExpectedConditions.urlToBe(expCurrentURL));
			Assert.assertEquals(true,check);
			logger.info("validate current URL of page ,so URL is: "+ driver.getCurrentUrl());
			
		}
		
	//==============Validate page title====================================
		public void validatePageTitle(String pageTitle) {
			
			String actPageTitle= driver.getTitle();
	    	Assert.assertEquals(pageTitle, actPageTitle);
	        logger.info("Validate title of page, title is:" + actPageTitle);
			
		}
	
	
	//===============Method to display logo================================
	
	public void displayLogo() {
		
		WebElement logo = driver.findElement(logoImage);
		Assert.assertEquals(true, logo.isDisplayed());
    	logger.info("Display the logo on landing page");
	}
	
   //==============Method to set product category===============================
	
	public void setProdCategory() {
		
		List <WebElement> prodCatList = driver.findElements(prodCategory);
		Assert.assertEquals(false, prodCatList.isEmpty());
		logger.info("Display the product category list, size of list is: "+ prodCatList.size());
    	scn.log("Product category is displayed on page with size is: "+ prodCatList.size());	
	}
	
	//===============Validate product category==================================
	
	public void validateProdCat(List<String> prodcat) {
		
		List<WebElement> prodList = driver.findElements(prodCategory);
		
		for(int i = 0; i<prodList.size(); i++) {
			
			if(prodList.get(i).getText().equals(prodcat.get(i).toString())) {
				
				Assert.assertTrue(true);
				logger.info(prodList.get(i).getText()+ " is matched with expected "+ prodcat.get(i).toString()+" product name in datatable");
    		}
			else {
				
				Assert.fail();
    			logger.fatal(prodList.get(i).getText()+ " is not matched with expected "+ prodcat.get(i).toString()+" product name in datatable");
			}
			logger.info("Validate the product category with expected datatable");
    	}
    }

	//==============Size of product category======================================
	
	 public void sizeOfProdCategory(int prodCount)
	    {
	 	   List <WebElement> prodCategoryList =driver.findElements(prodCategory);
	 	   Assert.assertEquals(prodCount, prodCategoryList.size());
	 	   logger.info("validate the Size of product category, size is: "+ prodCategoryList.size());
	 	   scn.log("validate the Size of product category, size is: "+ prodCategoryList.size());
	    }
	   

}
