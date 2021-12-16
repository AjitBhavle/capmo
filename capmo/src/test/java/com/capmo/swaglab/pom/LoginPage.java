package com.capmo.swaglab.pom;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;
	public Properties prop;
	
	private By uname = By.cssSelector("#user-name");
	private By pass = By.cssSelector("#password");
	private By loginButton = By.cssSelector("#login-button");
	private By inventoryList = By.cssSelector(".inventory_list");
	private By productTitle = By.cssSelector(".title");
	private By icon = By.cssSelector(".peek");
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	protected WebElement username() {
		return driver.findElement(uname);
	}

	protected WebElement password() {
		return driver.findElement(pass);
	}
	
	protected WebElement inventoryList() {
		return driver.findElement(inventoryList);
	}
	
	protected WebElement productTitle() {
		return driver.findElement(productTitle);
	}
	
	protected WebElement icon() {
		return driver.findElement(icon);
	}


	public void enterUsername(String uname) {
		if (this.isLoginPageDisplayed()) {
			this.username().clear();
			this.username().sendKeys(uname);
		}
	}

	public void enterPassword(String pass) {
		if (this.isLoginPageDisplayed()) {
			this.password().clear();
			this.password().sendKeys(pass);
		}
	}

	//Boolean methods
	public boolean isLoginPageDisplayed() {
		return driver.findElement(uname).isDisplayed() && driver.findElement(pass).isDisplayed()
				&& driver.findElement(loginButton).isDisplayed();
	}

	public boolean isInventoryListDisplayed() {
		return this.inventoryList().isDisplayed() && this.isProductTitleDisplayed() && isIconDisplayed();
	}
	
	public boolean isProductTitleDisplayed() {
		return this.productTitle().isDisplayed();
	}
	
	public boolean isIconDisplayed() {
		return this.icon().isDisplayed();
	}
	
	
	//Click methods
	
	public void clickOnLoginButton() {
		if (this.isLoginPageDisplayed())
			driver.findElement(loginButton).click();
	}

}
