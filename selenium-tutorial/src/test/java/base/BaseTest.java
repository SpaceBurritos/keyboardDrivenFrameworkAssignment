package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import pages.LogInPage;

public class BaseTest {
	
	String driverPath = "src\\test\\resources\\chromedriver.exe";
	private String username = "andressaurez";
	private String password = "Andres123.#";
	public WebDriver driver ;
	LogInPage login;

	@BeforeSuite(alwaysRun = true)
	public void startUp() {
    	System.setProperty("webdriver.chrome.driver", driverPath);
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	login = new LogInPage(driver);
    	System.out.println("Chrome driver initialized");
	}
	
	@BeforeTest
	public void login(){
		  driver.get("https://demoqa.com/login");
		  login.login(username, password);
	}
	
	@AfterSuite(alwaysRun = true)
	public void exit() {
        driver.close();
        System.out.println("Chrome driver closed");
	}
}
