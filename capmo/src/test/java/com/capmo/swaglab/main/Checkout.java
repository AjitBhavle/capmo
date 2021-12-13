package com.capmo.swaglab.main;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.capmo.swaglab.base.Base;
import com.capmo.swaglab.pom.Products;
import com.capmo.swaglab.pom.CheckoutPage;
import com.capmo.swaglab.pom.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class Checkout extends Base {

	LoginPage login;
	@Test
	public void loginToSwagLab() throws InterruptedException {
		login = new LoginPage(driver);
		System.out.println("Test Case One with Thread Id:- " + Thread.currentThread().getId());
		if (login.isLoginPageDisplayed()) {
			login.enterUsername(prop.getProperty("uname"));
			login.enterPassword(prop.getProperty("pass"));
			login.clickOnLoginButton();
			Assert.assertEquals(login.isInventoryListDisplayed(), true);
			extentTest.get().log(LogStatus.PASS, "User has logged in successfully");
		}
	}

	/*@Test
	public void sunscreensE2E() throws InterruptedException {

		LoginPage temprature = new LoginPage(driver);
		Products addToCart = new Products(driver);
		CheckoutPage checkout = new CheckoutPage(driver);
		System.out.println("Test Case One with Thread Id:- " + Thread.currentThread().getId());
		if (temprature.getTemperature() > 34) {

			extentTest.get().log(LogStatus.INFO, "Current temprature is:" + temprature.getTemperature());
			System.out.println("Current temprature is:" + temprature.getTemperature());
			temprature.clickOnBuySunscreens();
			addToCart.addLeasExpensiveMoisturizerContainSPF30AndSPF50();
			addToCart.clickOnGoToCartButton();
			extentTest.get().log(LogStatus.INFO, "Card item count is: " + checkout.getCartCount()
					+ "Cart items Displayed as: " + checkout.getCartItem() + "");
			Assert.assertEquals(checkout.isCartCountDisplayed(), true);
			int actualCartTotal = checkout.getCartTotal();
			int expectedCartTotal = checkout.calculateCartTotal();
			Assert.assertEquals(actualCartTotal, expectedCartTotal, "Actual items total is: " + actualCartTotal
					+ " and expected items total is:" + expectedCartTotal + "  on checkout page");
			extentTest.get().log(LogStatus.INFO, "Actual items total is: " + "" + actualCartTotal
					+ " and expected items total is:" + expectedCartTotal + "  on checkout page");
			checkout.clickOnPayWithCardButton();
			extentTest.get().log(LogStatus.INFO, "Clicked on payWithCardButton");
			checkout.switchToiframe();
			checkout.enterCartDetails(prop.getProperty("email"), prop.getProperty("month"), prop.getProperty("year"),
					prop.getProperty("cvv"), prop.getProperty("zipCode"));
			extentTest.get().log(LogStatus.INFO, "Entered card details and click on submit button");
			Assert.assertEquals(checkout.getConfirmationMessage(), prop.getProperty("confirmationMessage"),
					"Order confirmation message displayed and order booked sucessfully");
			extentTest.get().log(LogStatus.INFO, "Confirmation message is displayed");
		} else {
			extentTest.get().log(LogStatus.FAIL,
					"sunscreens E2E test is not executed because current temprature is less than 34. Actual temprature is: "
							+ temprature.getTemperature());
		}
	}
*/
	@Test
	public void testE2E() throws InterruptedException {

		System.out.println("Test Case One with Thread Id:- " + Thread.currentThread().getId());

		extentTest.get().log(LogStatus.PASS, "Third TC" + Thread.currentThread().getId());
	}

}
