package assesmentProject_1.Core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

	private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
	private static WebDriver driver = null;

	//===================method to get webdriver from browser=================

	public static WebDriver webdriverForBrowser(String browser) {

		switch(browser.toLowerCase()) {

		case "chrome":
			driver = new ChromeDriver();
			logger.info("Chrome browser is invoked");
			break;

		case "firefox":
			driver = new FirefoxDriver();
			logger.info("firefox browser is invoked");
			break;

		default:
			logger.fatal("No such browser is implemented, browser name is: "+ browser);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		logger.info("browser window maximized and set timeout is 20 second");

		return driver;
	}

	//====================navigating URL===========================================

	public static void navigateToURL(String url) {

		driver.get(url);
		logger.info("navigate to url");
	}

	//==================Quit the browser============================================

	public static void quitTheBrowser() {

		driver.quit();
		logger.info("Browser is closed");
	}

	//=================Switch to new window=========================================

	public static void switchToNewWindow() {

		Set <String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		itr.next();
		String newWindowId = itr.next();
		driver.switchTo().window(newWindowId);

		//		ArrayList <String> arr= new ArrayList<String> (driver.getWindowHandles());
		//		String childWindow=	arr.get(1);
		//		driver.switchTo().window(childWindow);

		logger.info("Switch to new Window, its id is: "+ newWindowId);

	}

	//===================Browsername from commandline==================================

	public static String getBrowserName() {

		String defaultBrowser = "chrome";
		String browserSentFromCmd = System.getProperty("browser");

		if(browserSentFromCmd == null) {

			return defaultBrowser;
		}
		else {

			return browserSentFromCmd;
		}

	}

}
