package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage {
	
  public WebDriver driver;

  
  public LogInPage(WebDriver driver) {
	  this.driver= driver; 
  }
  
  By loginUsername = By.id("userName");
  By loginPassword = By.id("password");
  By loginBtn = By.id("login");
  By afterLoginText = By.className("main-header");
  
  public void login(String username, String password) {
	  driver.findElement(loginUsername).sendKeys(username);
	  driver.findElement(loginPassword).sendKeys(password);
	  driver.findElement(loginBtn).click();
  }
  
  public String getURL() {
	  return driver.getCurrentUrl();
  }

}
