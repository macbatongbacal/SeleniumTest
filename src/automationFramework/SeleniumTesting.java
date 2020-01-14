package automationFramework;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationFramework.MyConstants;

public class SeleniumTesting {

 	@Before
 	public void setDriverProperties() {
 		this.setProperties();
 	}
 	
 	@Test
 	public void loginUrlTest() {
		 WebDriver chromeDriver = new ChromeDriver();
		 chromeDriver.manage().window().maximize();
		 chromeDriver.get(MyConstants.LOGIN_URL);
		 login(chromeDriver);
 		 
		 WebDriverWait wait = new WebDriverWait(chromeDriver, 50);
		 // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserProfile")));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("avanced_search")));
		 assertThat(chromeDriver.getCurrentUrl(), is(MyConstants.EXPECTED_LANDING_URL));
		 chromeDriver.close();
 	}
 	
 	private static void login(WebDriver myDriver){
 		String URL = "https://" + MyConstants.EPUBLISH_USERNAME 
			  + ":" 
			  + MyConstants.EPUBLISH_PASSWORD 
			  + "@" + MyConstants.LOGIN_URL_SHORT;
 		myDriver.get(URL);
 		// wait for the page to load
 		WebDriverWait wait = new WebDriverWait(myDriver, 6000);
 		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("capture_signIn_signInEmailAddress")));
 		testMsdLogin(myDriver);
	}
 	
 	private void setProperties() {
 		// call the GeckoDriver for Firefox
 		 System.setProperty("webdriver.gecko.driver","C:\\SeleniumDrivers\\geckodriver-v0.26.0-win64\\geckodriver.exe");
 		 // call the ChromeDriver for Google Chrome
 		 System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver_win32\\chromedriver.exe");
 		 // call the IEDriver for Internet Explorer
 		 System.setProperty("webdriver.ie.driver", "C:\\SeleniumDrivers\\IEDriverServer_x64_3.9.0\\IEDriverServer.exe");
 	}
 	
 	private static void testMsdLogin(WebDriver myDriver) {
 		// get the web elements
 		WebElement msd_username = myDriver.findElement(By.id("capture_signIn_signInEmailAddress"));
 		WebElement msd_password = myDriver.findElement(By.id("capture_signIn_currentPassword"));
 		WebElement msd_login_btn = myDriver.findElement(By.className("capture_secondary"));
 		
 		// add inputs to the fields
 		msd_username.sendKeys(MyConstants.MSD_PREVIEW_USERNAME);
 		msd_password.sendKeys(MyConstants.MSD_PREVIEW_PASSWORD);
 		msd_login_btn.click();
 	}
}