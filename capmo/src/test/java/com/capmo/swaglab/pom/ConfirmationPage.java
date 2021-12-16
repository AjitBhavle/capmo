package com.capmo.swaglab.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.capmo.swaglab.helper.Helper;

public class ConfirmationPage {

	private WebDriver driver;
	
	private By confirmationMsg = By.cssSelector(".complete-header");
	private By confirmationTitle = By.cssSelector(".title");

	public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	protected WebElement confirmationMsg() {
		return driver.findElement(confirmationMsg);
	}
	
	protected WebElement confirmationTitle() {
		return driver.findElement(confirmationTitle);
	}
	
	//Boolean displayed methods
	
	protected boolean isConfirmationMsg() {
		return this.confirmationMsg().isDisplayed();
	}
	
	public void waitUntilConfirmationPageToLoaded() {
		Helper.explicitWait(driver, this.confirmationMsg(), 2000);
	}
	
// getText for elements
	
	public String getConfirmationMsgText() {
			return this.confirmationMsg().getText().trim();
	}
	public String getConfirmationTitleText() {
		return this.confirmationTitle().getText().trim();
	}
}
