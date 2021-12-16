package com.capmo.swaglab.main;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.capmo.swaglab.base.Base;
import com.capmo.swaglab.pom.CheckoutPage;
import com.capmo.swaglab.pom.ConfirmationOverviewPage;
import com.capmo.swaglab.pom.ConfirmationPage;
import com.capmo.swaglab.pom.LoginPage;
import com.capmo.swaglab.pom.ProductHomePage;
import com.relevantcodes.extentreports.LogStatus;

public class Checkout extends Base {

	ProductHomePage product;
	CheckoutPage checkout;
	ConfirmationOverviewPage confirmationPreview;
	ConfirmationPage confirmation;
	LoginPage login;
	
	// common method
	public void loginToSwagLab(WebDriver driver) throws InterruptedException, IOException {
		login = new LoginPage(driver);
		if (login.isLoginPageDisplayed()) {
			login.enterUsername(prop.getProperty("uname"));
			login.enterPassword(prop.getProperty("pass"));
			login.clickOnLoginButton();
			Assert.assertEquals(login.isInventoryListDisplayed(), true);
			extentTest.get().log(LogStatus.PASS, "User has logged in successfully");
		}
	}

	// Smoke test case
	@Test
	public void validateFilterOrder() throws InterruptedException, IOException {
		WebDriver driver = webdriver.get();
		product = new ProductHomePage(driver);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Test Case One with Thread Id:- " + Thread.currentThread().getId());

		// Land on Login page
		this.loginToSwagLab(driver);

		// Filter Order
		product.clickOnFilterDropDownIcon();
		String[] values = prop.get("filderOrder").toString().split("#");
		System.out.println("Actual filter order is: " + Arrays.toString(product.getFilterDropdowValues())
				+ " Expected filter order is: " + Arrays.toString(values));
		extentTest.get().log(LogStatus.INFO,
				"Actual filter order is: " + Arrays.toString(product.getFilterDropdowValues())
						+ " Expected filter order is: " + Arrays.toString(values));
		Assert.assertTrue(Arrays.toString(product.getFilterDropdowValues()).equals(Arrays.toString(values)));
	}

	// End to End test case
	@Test
	public void bookItemEnd2End() throws InterruptedException, IOException {
		WebDriver driver = webdriver.get();
		product = new ProductHomePage(driver);
		checkout = new CheckoutPage(driver);
		confirmationPreview = new ConfirmationOverviewPage(driver);
		confirmation = new ConfirmationPage(driver);
		login = new LoginPage(driver);
		HashMap<Double, String> productMap = new HashMap<Double, String>();
		HashMap<Double, String> checkoutMap = new HashMap<Double, String>();
		System.out.println("Test Case two with Thread Id:- " + Thread.currentThread().getId());

		// Land on Login page
		this.loginToSwagLab(driver);

		// Product home page
		productMap.put(product.getFirstItemPrice(), product.getFirstItemText());
		productMap.put(product.getSecondItemPrice(), product.getSecondItemText());
		System.out.println("Product items and its price: " + Arrays.asList(productMap));
		product.clickOnFirstItemAddToCartButton();
		product.clickOnSecondItemAddToCartButton();
		extentTest.get().log(LogStatus.INFO, "Items added in to cart are: " + Arrays.asList(productMap));
		product.clickOnCartIconButton();

		// checkout Cart page
		checkout.waitUntilCheckoutPageIsLoaded();
		checkout.isCheckoutPageDisplayed();
		checkoutMap.put(checkout.getFirstItemPrice(), checkout.getFirstItemText());
		checkoutMap.put(checkout.getSecondItemPrice(), checkout.getSecondItemText());
		System.out.println("Checkout cart items and its price: " + Arrays.asList(checkoutMap));
		extentTest.get().log(LogStatus.INFO, "Items added in to cart are: " + Arrays.asList(checkoutMap));
		Assert.assertTrue(productMap.equals(checkoutMap));
		checkout.clickOnCheckoutButton();

		// checkout information page
		checkout.waitUntilConfirmationIsLoaded();
		checkout.enterFirstName(prop.getProperty("uname"));
		checkout.enterLastName(prop.getProperty("lname"));
		checkout.enterZipCode(prop.getProperty("zipcode"));
		checkout.clickOnContinueButton();

		// checkout confirmation page
		confirmationPreview.waitUntilCheckoutOverviewPageToLoaded();
		extentTest.get().log(LogStatus.INFO,
				"Items actual subTotal is: " + confirmationPreview.getActualCheckoutSubTotal()
						+ " Items expected subTotal is: " + confirmationPreview.getExpectedSubTotal());
		Assert.assertEquals(confirmationPreview.getActualCheckoutSubTotal(), confirmationPreview.getExpectedSubTotal());

		extentTest.get().log(LogStatus.INFO, "Items actual total is: " + confirmationPreview.getActualCheckoutTotal()
				+ " Items expected total is: " + confirmationPreview.getExpectedTotalAvg());
		Assert.assertEquals(confirmationPreview.getActualCheckoutTotal(), confirmationPreview.getExpectedTotalAvg());
		confirmationPreview.clickOnFinishButton();

		// Confirmation page
		confirmation.waitUntilConfirmationPageToLoaded();
		extentTest.get().log(LogStatus.INFO, " Order placed successfully...");
		Assert.assertEquals(confirmation.getConfirmationMsgText(), prop.getProperty("confirmationMessage"),
				" Order confirmation message displayed and order booked sucessfully");
	}


}
