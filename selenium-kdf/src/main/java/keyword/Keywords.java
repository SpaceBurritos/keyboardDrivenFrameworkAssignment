package keyword;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import bsh.This;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Keywords {

	//WebDriver driver;
	String text;
	final String CHROMEPATH = "src\\test\\resources\\chromedriver.exe";
	final String FIREFOXPATH = "src\\test\\resources\\geckodriver.exe";
	boolean assert_value;
	
	public Keywords() {
		text = "";
		//this.driver = driver;
	}
	
	By getObject(Properties prop, String objectName, String locatorType) throws IOException {
		
		switch(locatorType) {
			case "XPATH":
				return By.xpath(prop.getProperty(objectName));
			case "ID":
				return By.id(prop.getProperty(objectName));
			case "CLASSNAME":
				return By.className(prop.getProperty(objectName));
			case "CSS":
				return By.cssSelector(prop.getProperty(objectName));
			case "LINK":
				return By.linkText(prop.getProperty(objectName));
			case "PARTIALLINK":
				return By.partialLinkText(prop.getProperty(objectName));
			default:
				System.out.println("Element not found");
				return null;
		}
	}
	
	public void perform(WebDriver driver, Properties p, String operation, String locatorType, String objectName, String value) throws IOException, InterruptedException{
		Actions actions = new Actions(driver);

		switch (operation) {
			case "CLICK":
				driver.findElement(getObject(p, objectName, locatorType)).click();
				break;
				
			case "SETTEXT":
				driver.findElement(getObject(p, objectName, locatorType)).sendKeys(value);
				break;	
				
			case "CLEARTEXT":
				driver.findElement(getObject(p, objectName, locatorType)).clear();
				break;
				
			case "GETTEXT":
				this.text = driver.findElement(getObject(p, objectName, locatorType)).getText();
				System.out.println(this.text);
				break;
				
			case "GETSELECTEDTEXT":
				Select selected = new Select(driver.findElement(getObject(p, objectName, locatorType)));
				this.text = selected.getFirstSelectedOption().getText();
				break;
				
			case "SEARCHTABLEGETTEXT":
				this.text = driver.findElements(getObject(p, objectName, locatorType)).get(Integer.parseInt(value)).getText();
				System.out.println(this.text);
				break;
				 
			case "SEARCHTABLECLICK":
				driver.findElements(getObject(p, objectName, locatorType)).get(Integer.parseInt(value)).click();
				break;
				
			case "SEARCHTABLEVALUECLICK":
				for (WebElement tableValue:driver.findElements(getObject(p, objectName, locatorType))) {
					if (value.equals(tableValue.getText())) {
						tableValue.click();
					}
				}
				break;
				
			case "ASSERTMULTIPLEVALUES":
				for (WebElement tableValue:driver.findElements(getObject(p, objectName, locatorType))) {
					if (value.length() > 0 && value != null) {
						Assert.assertEquals(tableValue.getText(), value);
					} else {
						Assert.assertEquals(tableValue.getText(), this.text);
					}
				}

				break;
				
			case "ASSERTVALUEINLIST":
				//boolean isIn = false;
				for (WebElement listValue:driver.findElements(getObject(p, objectName, locatorType))) {
					if (listValue.getText().equals(value)) {
						Assert.assertEquals(listValue.getText(), value);
					//	isIn = true;
					} 
				}
				//if (!isIn) {
				//	Assert.assertEquals("", value);
				//}
				break;
				
			case "ASSERTNOTVALUEINLIST":
				boolean isIn2 = false;
				for (WebElement listValue:driver.findElements(getObject(p, objectName, locatorType))) {
					if (listValue.getText().equals(value)) {
						Assert.assertNotEquals(listValue.getText(), value);
						isIn2 = true;
					} 
				}
				if (!isIn2) {
					Assert.assertNotEquals(null, value);	
				}
				break;
				
			case "GETSIZE":
				this.text = String.valueOf(driver.findElements(getObject(p, objectName, locatorType)).size());
				break;
			
			case "GOTOURL":
				driver.get(p.getProperty(value));
				break;
				
			case "GETURL":
				this.text = driver.getCurrentUrl();
				break;
				
			case "REFRESH":
				driver.navigate().refresh();
				break;
				
			case "WAIT":
				Thread.sleep(Integer.parseInt(value) * 1000);
				break;
				
			case "IMPLICIT":
				driver.manage().timeouts().implicitlyWait(Integer.parseInt(value), TimeUnit.SECONDS);
				break;
			
			case "EXPLICIT":
				WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(value));
				wait.until(ExpectedConditions.visibilityOfElementLocated(getObject(p, objectName, locatorType)));
				break;
				
			case "FLUENTWAIT":
				Duration timeout = Duration.ofSeconds(Integer.parseInt(value));
				Duration polling = Duration.ofSeconds(Integer.parseInt("4"));
				Wait<WebDriver> fluentWait = new FluentWait(driver).withTimeout(timeout).pollingEvery(polling);
				break;
				
			case "SELECTBYINDEX":
				Select listbox_index = new Select(driver.findElement(getObject(p, objectName, locatorType)));
				listbox_index.selectByIndex(Integer.parseInt(value));
				break;
				
			case "SELECTBYVALUE":
				Select listbox_value = new Select(driver.findElement(getObject(p, objectName, locatorType)));
				listbox_value.selectByValue(value);
				break;
				
			case "SELECTBYVISIBLETEXT":
				Select visible_text = new Select(driver.findElement(getObject(p, objectName, locatorType)));
				visible_text.selectByVisibleText(value);
				break;
				
			case "SETSAVEDVALUE":
				driver.findElement(getObject(p, objectName, locatorType)).sendKeys(this.text);
				break;
				
			case "HOVEROVER":
				WebElement ho_target = driver.findElement(getObject(p, objectName, locatorType));
				actions = new Actions(driver);
				actions.moveToElement(ho_target).perform();
				break;
				
			case "OPENCHROME":
		    	System.setProperty("webdriver.chrome.driver", CHROMEPATH);
		    	driver = new ChromeDriver();
				break;
				
			case "OPENFF":
		    	System.setProperty("webdriver.chrome.driver", FIREFOXPATH);
		    	driver = new FirefoxDriver();
				break;
				
			case "JSCLICK":
				break;
				
			case "CLICKANDHOLD":
				actions = new Actions(driver);
				WebElement cnh_target = driver.findElement(getObject(p, objectName, locatorType));
				
				actions.clickAndHold(cnh_target).perform();
				break;
				
			case "MAXIMIZE":
				driver.manage().window().maximize();
				break;
				
			case "SETWINDOWSIZE":
				List<String> dimList = Arrays.asList(value.split(","));
				Dimension dim = new Dimension(Integer.parseInt(dimList.get(0)), Integer.parseInt(dimList.get(1)));
				driver.manage().window().setSize(dim);
				break;
				
			case "SWITCHWINDOW":
				driver.switchTo().window(value); 
				break;
				
			case "ACCEPTALERT":
				System.out.println(driver.switchTo().alert().getText());
				driver.switchTo().alert().accept();
				break;
				
			case "DISMISSALERT":
				driver.switchTo().alert().dismiss();
				break;
				
			case "DRAG":
				WebElement source = driver.findElement(getObject(p, objectName, locatorType));
				List<String> pos = Arrays.asList(value.split(","));
				actions = new Actions(driver);

				if (pos.size() > 1) {
					actions.dragAndDropBy(source, Integer.parseInt(pos.get(0)), Integer.parseInt(pos.get(1)));
				} else {
					WebElement destination = driver.findElement(getObject(p, value, locatorType)); //assuming it has the same locator type
					actions.dragAndDrop(source, destination).perform();
				}
				break;
				
			case "ASSERT":
				//if there is no webElement to search use the value gotten with getText before
				if (getObject(p, objectName, locatorType) == null) {
					System.out.println(value + " " + this.text);
					this.assert_value = this.text.equals(value);
					Assert.assertEquals(this.text, value);
				} else if (value.equals("")) {
					String val = driver.findElement(getObject(p, objectName, locatorType)).getText();
					Assert.assertEquals(val, this.text);
				} else {
					String actual_value = driver.findElement(getObject(p, objectName, locatorType)).getText();
					System.out.println(actual_value + " " + value);
					this.assert_value = actual_value.equals(value);
					Assert.assertEquals(actual_value, value);
				}
				break;
				
			case "CLOSEBROWSER":
		        driver.close();
				break;
				
				
			default:
				System.out.println("Action not found");
				break;
		}
	}
	
	public boolean getAssertValue() {
		return this.assert_value;
	}
	
	
}
