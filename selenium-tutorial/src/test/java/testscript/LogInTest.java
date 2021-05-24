package testscript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.ExcelUtils;
import pages.LogInPage;

public class LogInTest {
	
	WebDriver driver;
	WebDriverWait wait;
	LogInPage login;
	String driverPath = "src\\test\\resources\\chromedriver.exe";
	private String sheetName = "loginData";
	
	
	@DataProvider()
	public Object[][] loginData() throws Exception{
		return ExcelUtils.getTableArray(sheetName);
	}
	
	@BeforeMethod()
	public void startUp() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/login");
		login = new LogInPage(driver);
		wait = new WebDriverWait(driver, 5);
		System.out.println("Chrome driver initialized");
	}
	
	  
	@AfterMethod
	public void closeDriver() {
	    driver.close();
	    System.out.println("Chrome driver closed");
	}
		
	@Test(dataProvider = "loginData")
	public void loginTest(String username, String password, String expectedValue) { 
		login.login(username, password);
		wait.until(ExpectedConditions.urlToBe(expectedValue));		
		String actualValue = driver.getCurrentUrl();
		Assert.assertEquals(actualValue, expectedValue);
	}
}
