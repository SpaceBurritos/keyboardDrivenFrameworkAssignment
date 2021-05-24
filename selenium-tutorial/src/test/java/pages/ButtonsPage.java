package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ButtonsPage {

	  public WebDriver driver;
	  
	  By doubleClickBtn = By.id("doubleClickBtn");
	  By rightClickBtn = By.id("rightClickBtn");
	  By leftClickBtn = By.xpath("//button[text() = 'Click Me']");
	  
	  By doubleClickMsg = By.id("doubleClickMessage");
	  By rightClickMsg = By.id("rightClickMessage");
	  By leftClickMsg = By.id("dynamicClickMessage");
	  
	  public ButtonsPage(WebDriver driver) {
		  this.driver= driver; 
	  }
	  
	  public void leftClickBtn() {
		  driver.findElement(leftClickBtn).click();
	  }
	  
	  public void doubleClickBtn() {
		  Actions actions = new Actions(driver);
		  WebElement btn = driver.findElement(doubleClickBtn);
		  actions.doubleClick(btn).perform();
	  }
	  
	  public void rightClickBtn() {
		  Actions actions = new Actions(driver);
		  WebElement btn = driver.findElement(rightClickBtn);
		  actions.contextClick(btn).perform();
	  }
	  
	  public String getRightClickMsg() {
		  return driver.findElement(rightClickMsg).getText();
	  }
	  
	  public String getLeftClickMsg() {
		  return driver.findElement(leftClickMsg).getText();
	  }
	  
	  public String getDoubleClickMsg() {
		  return driver.findElement(doubleClickMsg).getText();
	  }
	
}
