package testscript;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.RadioButtonPage;

public class RadioButtonTest extends BaseTest {
	
	RadioButtonPage radio;
	
	@BeforeClass
	public void gotoButtons() {
		driver.get("https://demoqa.com/radio-button");
		radio = new RadioButtonPage(driver);
	}
	
	@Test
	public void checkYesRadio() {
		String expectedValue = "Yes";
		radio.clickYesRadio();
		String actualValue = radio.getMsg();
		Assert.assertEquals(actualValue, expectedValue);
	}
	
	@Test
	public void checkImpressiveRadio() {
		String expectedValue = "Impressive";
		radio.clickImpressiveRadio();
		String actualValue = radio.getMsg();
		Assert.assertEquals(actualValue, expectedValue);
	}
	
	@Test
	public void checkNoRadio() {
		boolean expectedValue = false;
		radio.clickYesRadio();
		boolean actualValue = radio.checkNoRadioEnabled();
		Assert.assertEquals(actualValue, expectedValue);
	}
	
}
