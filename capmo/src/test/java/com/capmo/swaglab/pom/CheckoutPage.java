package com.capmo.swaglab.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.capmo.swaglab.helper.Helper;

public class CheckoutPage {

	private WebDriver driver;
	
	private By firstItemText = By.cssSelector("#item_4_title_link > div");
	private By firstItemPrice = By.xpath("//*[@id='cart_contents_container']/div/div[1]/div[3]/div[2]/div[2]/div");
	private By secondItemText = By.cssSelector("#item_0_title_link > div");
	private By secondItemPrice = By.xpath("//*[@id='cart_contents_container']/div/div[1]/div[4]/div[2]/div[2]/div");
	private By checkoutRemoveButton = By.cssSelector("##remove-sauce-labs-backpack");
	private By checkoutButton = By.cssSelector("#checkout");
	private By continueShoppingButton = By.cssSelector("#continue-shopping");
	private By firstName = By.cssSelector("#first-name");
	private By lastName = By.cssSelector("#last-name");
	private By zipCode = By.cssSelector("#postal-code");
	private By continueButton = By.cssSelector("#continue");
	
	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	protected WebElement checkoutButton() {
		return driver.findElement(checkoutButton);
	}
	protected WebElement checkoutRemoveButton() {
		return driver.findElement(checkoutRemoveButton);
	}
	protected WebElement continueShoppingButton() {
		return driver.findElement(continueShoppingButton);
	}
	protected WebElement firstItemText() {
		return driver.findElement(firstItemText);
	}
	protected WebElement firstItemPrice() {
		return driver.findElement(firstItemPrice);
	}
	protected WebElement secondItemText() {
		return driver.findElement(secondItemText);
	}
	protected WebElement secondItemPrice() {
		return driver.findElement(secondItemPrice);
	}
	protected WebElement firstName() {
		return driver.findElement(firstName);
	}
	protected WebElement lastName() {
		return driver.findElement(lastName);
	}
	protected WebElement zipCode() {
		return driver.findElement(zipCode);
	}
	protected WebElement continueButton() {
		return driver.findElement(continueButton);
	}
	

// getText for elements
	
	public String getFirstItemText() {
			return this.firstItemText().getText();
	}
	public String getSecondItemText() {
		return this.secondItemText().getText();
	}
	public double getSecondItemPrice() {
		String secondItemPrice = this.secondItemPrice().getText();
		secondItemPrice = secondItemPrice.substring(1);
		System.out.println("secondItemPrice is: "+Double.parseDouble(secondItemPrice));
		return Double.parseDouble(secondItemPrice);
	}
	public double getFirstItemPrice() {
		
		String firstItemPrice = this.firstItemPrice().getText();
		firstItemPrice = firstItemPrice.substring(1);
		System.out.println("firstItemPrice is: "+Double.parseDouble(firstItemPrice));
		return Double.parseDouble(firstItemPrice);
	}
	
	//Boolean methods
	public boolean isFirstItemTextDisplayed() {
		return this.firstItemText().isDisplayed();
	}
	public boolean isSecondItemTextDisplayed() {
		return this.firstItemText().isDisplayed();
	}
	public boolean isCheckoutRemoveButtonDisplayed() {
		return this.checkoutRemoveButton().isDisplayed();
	}
	public boolean ischeckoutButtonDisplayed() {
		return this.checkoutButton().isDisplayed();
	}
	public boolean isContinueShoppingButtonDisplayed() {
		return this.continueShoppingButton().isDisplayed();
	}
	public boolean isCheckoutPageDisplayed() {
		return this.continueShoppingButton().isDisplayed() && this.ischeckoutButtonDisplayed() && isFirstItemTextDisplayed() && isSecondItemTextDisplayed();
	}
	public void waitUntilCheckoutPageIsLoaded() {

		Helper.explicitWait(driver, this.checkoutButton(), 2000);
	}

	// confirmation page details
	public boolean isFirstNameDisplayed() {
		return this.firstName().isDisplayed();
	}
	public boolean isLastNameDisplayed() {
		return this.lastName().isDisplayed();
	}
	public boolean isZipCodeDisplayed() {
		return this.zipCode().isDisplayed();
	}
	public boolean isContinueButtonDisplayed() {
		return this.continueButton().isDisplayed();
	}
	public void waitUntilConfirmationIsLoaded() {
		Helper.explicitWait(driver, this.firstName(), 2000);
	}
	
	//Enter text to input box
	
	public void enterFirstName(String uname) {
		if (this.isFirstNameDisplayed()) {
			this.firstName().clear();
			this.firstName().sendKeys(uname);
		}
	}
	public void enterLastName(String lname) {
		if (this.isFirstNameDisplayed()) {
			this.lastName().clear();
			this.lastName().sendKeys(lname);
		}
	}
	public void enterZipCode(String zipCode) {
		if (this.isZipCodeDisplayed()) {
			this.zipCode().clear();
			this.zipCode().sendKeys(zipCode);
		}
	}
		
	//Click methods
	public void clickOnCheckoutButton() {
		if (this.ischeckoutButtonDisplayed())
			this.checkoutButton().click();
	}
	
	public void clickOnContinueButton() {
		if (this.isContinueButtonDisplayed())
			this.continueButton().click();
	}
	
	public void clickOnCheckoutRemoveButton() {
		if (this.isCheckoutRemoveButtonDisplayed())
			this.checkoutRemoveButton().click();
	}
	
	public void clickOnContinueShoppingButton() {
		if (this.isContinueShoppingButtonDisplayed())
			this.continueShoppingButton().click();
	}
}
