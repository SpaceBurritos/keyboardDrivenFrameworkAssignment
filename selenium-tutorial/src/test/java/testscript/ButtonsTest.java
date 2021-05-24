package testscript;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ButtonsPage;

public class ButtonsTest extends BaseTest {

	ButtonsPage buttons;
	
	  @BeforeClass
	  public void gotoButtons() {
		  driver.get("https://demoqa.com/buttons");
		  buttons = new ButtonsPage(driver);
	  }
	  
	  @Test
	  public void checkRightClick() {
		  String expectedValue = "You have done a right click";
		  buttons.rightClickBtn();
		  String actualValue = buttons.getRightClickMsg();
		  Assert.assertEquals(actualValue, expectedValue);
		  
	  }
	  
	  @Test
	  public void checkLeftClick() {
		  String expectedValue = "You have done a dynamic click";
		  buttons.leftClickBtn();
		  String actualValue = buttons.getLeftClickMsg();
		  Assert.assertEquals(actualValue, expectedValue);
		  
	  }
	  
	  @Test
	  public void checkDoubleClick() {
		  String expectedValue = "You have done a double click";
		  buttons.doubleClickBtn();
		  String actualValue = buttons.getDoubleClickMsg();
		  Assert.assertEquals(actualValue, expectedValue);
		  
	  }
	
}
