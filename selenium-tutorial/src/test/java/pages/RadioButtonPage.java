package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RadioButtonPage {

	WebDriver driver;
	By yesRadio = By.id("yesRadio");
	By impressiveRadio = By.id("impressiveRadio");
	By noRadio = By.id("noRadio");
	By msg = By.className("text-success");
	
	public RadioButtonPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickYesRadio() {
		WebElement btn = driver.findElement(yesRadio);
		btn.sendKeys(Keys.SPACE);
	}
	
	public void clickImpressiveRadio() {
		WebElement btn = driver.findElement(impressiveRadio);
		btn.sendKeys(Keys.SPACE);
	}
	
	public boolean checkNoRadioEnabled() {
		WebElement btn = driver.findElement(noRadio);
		return btn.isEnabled();
	}
	
	public String getMsg() {
		return driver.findElement(msg).getText();
	}
	
	
	
}
