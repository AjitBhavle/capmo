package com.capmo.swaglab.pom;

import java.awt.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductHomePage {
	
	private WebDriver driver;
	
	private By firstItemAddToCart = By.cssSelector("#add-to-cart-sauce-labs-backpack");
	private By firstItemText = By.cssSelector("#item_4_title_link > div");
	private By firstItemPrice = By.xpath("//*[@id='inventory_container']/div/div[1]/div[2]/div[2]/div");
	private By secondItemAddToCart = By.cssSelector("#add-to-cart-sauce-labs-bike-light");
	private By secondItemText = By.cssSelector("#item_0_title_link > div");
	private By secondItemPrice = By.xpath("//*[@id='inventory_container']/div/div[2]/div[2]/div[2]/div");
	private By inventoryList = By.cssSelector(".inventory_list");
	private By cartIcon = By.cssSelector(".shopping_cart_link");
	private By filterDropDown = By.cssSelector(".product_sort_container");

	public ProductHomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	protected WebElement firstItemAddToCart() {
		return driver.findElement(firstItemAddToCart);
	}
	protected WebElement firstItemText() {
		return driver.findElement(firstItemText);
	}
	protected WebElement firstItemPrice() {
		return driver.findElement(firstItemPrice);
	}
	protected WebElement secondItemAddToCart() {
		return driver.findElement(secondItemAddToCart);
	}
	protected WebElement secondItemText() {
		return driver.findElement(secondItemText);
	}
	protected WebElement secondItemPrice() {
		return driver.findElement(secondItemPrice);
	}
	protected WebElement inventoryList() {
		return driver.findElement(inventoryList);
	}	
	protected WebElement cartIcon() {
		return driver.findElement(cartIcon);
	}
	protected WebElement filterDropDown() {
		return driver.findElement(filterDropDown);
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
	
	public String[] getFilterDropdowValues(){
		ArrayList<String> list=new ArrayList<String>();
		Select filter = new Select(filterDropDown());
	      // getting the list of options in the dropdown with getOptions()
	      java.util.List<WebElement> op = filter.getOptions();
	      int size = op.size();
	      for(int i =0; i<size ; i++){
	    	  list.add(op.get(i).getText());
	      }
	      String[] options = new String[list.size()];
	      options = list.toArray(options);
	      return options;	
	}
	
	//Boolean methods
	public boolean isfirstItemAddToCartDisplayed() {
		return this.firstItemAddToCart().isDisplayed();
	}

	public boolean isSecondItemAddToCartDisplayed() {
		return this.secondItemAddToCart().isDisplayed();
	}

	public boolean isCartIconDisplayed() {
		return this.cartIcon().isDisplayed();
	}
	public boolean isFilterDropDownDisplayed() {
		return this.filterDropDown().isDisplayed();
	}
		
	//Click methods
	public void clickOnFirstItemAddToCartButton() {
		if (this.isfirstItemAddToCartDisplayed())
			this.firstItemAddToCart().click();
	}
	
	public void clickOnSecondItemAddToCartButton() {
		if (this.isSecondItemAddToCartDisplayed())
			this.secondItemAddToCart().click();
	}
	
	public void clickOnCartIconButton() {
		if (this.isCartIconDisplayed())
			this.cartIcon().click();
	}
	
	public void clickOnFilterDropDownIcon() {
		if (this.isFilterDropDownDisplayed())
			this.filterDropDown().click();
	}


}
