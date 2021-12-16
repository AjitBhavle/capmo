package com.capmo.swaglab.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.capmo.swaglab.helper.Helper;

public class ConfirmationOverviewPage {

	private WebDriver driver;
	
	private By firstItemText = By.cssSelector("#item_4_title_link > div");
	private By firstItemPrice = By.xpath("//*[@id='checkout_summary_container']/div/div[1]/div[3]/div[2]/div[2]/div");
	private By secondItemText = By.cssSelector("#item_0_title_link > div");
	private By secondItemPrice = By.xpath("//*[@id='checkout_summary_container']/div/div[1]/div[4]/div[2]/div[2]/div");
	private By finishButton = By.cssSelector("#finish");
	private By checkoutOverviewTitle = By.cssSelector(".title");
	private By checkoutCartSubTotal = By.cssSelector(".summary_subtotal_label");
	private By checkoutTax = By.cssSelector(".summary_tax_label");
	private By checkoutTotal = By.cssSelector(".summary_total_label");
	
	public ConfirmationOverviewPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	protected WebElement finishButton() {
		return driver.findElement(finishButton);
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
	protected WebElement checkoutOverviewTitle() {
		return driver.findElement(checkoutOverviewTitle);
	}
	
	protected WebElement checkoutCartSubTotal() {
		return driver.findElement(checkoutCartSubTotal);
	}
	protected WebElement checkoutTax() {
		return driver.findElement(checkoutTax);
	}
	protected WebElement checkoutTotal() {
		return driver.findElement(checkoutTotal);
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
	
	public double getActualCheckoutSubTotal() {
		
		String checkoutCartSubTotal = this.checkoutCartSubTotal().getText();
		checkoutCartSubTotal = checkoutCartSubTotal.substring(13);
		System.out.println("Actual checkoutCartSubTotal is: "+Double.parseDouble(checkoutCartSubTotal));
		return Double.parseDouble(checkoutCartSubTotal);
	}
	
	public double getCheckoutTax() {
		
		String checkoutTax = this.checkoutTax().getText();
		checkoutTax = checkoutTax.substring(6);
		System.out.println("checkoutTax is: "+Double.parseDouble(checkoutTax));
		return Double.parseDouble(checkoutTax);
	}
	
	public double getActualCheckoutTotal() {
		
		String checkoutTotal = this.checkoutTotal().getText();
		checkoutTotal = checkoutTotal.substring(8);
		System.out.println("checkoutTotal is: "+Double.parseDouble(checkoutTotal));
		return Double.parseDouble(checkoutTotal);
	}
	
	public double getExpectedSubTotal()
	{
		double avg;
		avg = this.getFirstItemPrice() + this.getSecondItemPrice();
		System.out.println("item actualSubTotalAvg:" +avg);
		
		return avg;
	}
	
	public double getExpectedTotalAvg()
	{
		double subTotal;
		double tax;
		double total;
		subTotal = this.getFirstItemPrice() + this.getSecondItemPrice();
		tax = this.getCheckoutTax();
		
		total= subTotal+tax;
		
		System.out.println("item expected Total:" +total);
		
		return total;
	}
	
	//Boolean checkout overview page methods
	
	public boolean isFinishButtonDisplayed() {
		return this.finishButton().isDisplayed();
	}
	public boolean isCheckoutOverviewTitleDisplayed() {
		return this.checkoutOverviewTitle().isDisplayed();
	}
	public void waitUntilCheckoutOverviewPageToLoaded() {
		Helper.explicitWait(driver, this.finishButton(), 2000);
	}

	
	//Click methods
	public void clickOnFinishButton() {
		if (this.isFinishButtonDisplayed())
			this.finishButton().click();
	}
	
}
