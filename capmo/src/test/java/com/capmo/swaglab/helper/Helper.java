package com.capmo.swaglab.helper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capmo.swaglab.base.Base;


public class Helper extends Base {

	public static String takeScreenShot(WebDriver driver, String method_name) throws IOException {
		String imageLocation = System.getProperty("user.dir")+"\\htmlReportsAndScreenshots\\screenshots\\";
		String image_path = imageLocation + method_name + ".png";		
		// generate screenshot as a file object
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(image_path));
		return image_path;

	}
//	public void waintUntil(WebDriver driver, By element) {
//		WebDriverWait wait = new WebDriverWait(driver,20);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
//	}
	
	public static void explicitWait(WebDriver driver, WebElement locator, int timeout)
	 {
	        new WebDriverWait(driver,timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(locator));
	        locator.click();

	 }
	
	public void switchWindow() throws InterruptedException {

		// Storing parent window reference into a String Variable
		// Switching from parent window to child window
		for (String Child_Window : driver.getWindowHandles()) {
			driver.switchTo().window(Child_Window);
		}
		// // Switching back to Parent Window
		// driver.switchTo().window(Parent_Window);
		driver.manage().window().maximize();
		Thread.sleep(5000);

	}
	
}