package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import keyword.Keywords;

public class BaseTest {
	
	String driverPath = "src\\test\\resources\\chromedriver.exe";
	WebDriver driver;
	Keywords kw;

	@BeforeSuite(alwaysRun = true)
	public void startUp() {
    	System.setProperty("webdriver.chrome.driver", driverPath);
    	driver = new ChromeDriver();
    	System.out.println("Chrome initialized");
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);				
    	driver.manage().window().maximize();
    	kw = new Keywords();

	}
	
	
	@AfterSuite(alwaysRun = true)
	public void exit() {
        driver.close();
        System.out.println("Chrome driver closed");
	}
	
	public Keywords getKw() {
		return kw;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

}
