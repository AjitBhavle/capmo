package com.capmo.swaglab.helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactoryStaticThreadLocal
{

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static  void setDriver() {
		// execute in chrome driver
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "\\driver\\chromedriver\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
	}
	
	public static WebDriver getDriver()
	{
		return driver.get();
	}
	
	public static void closeBrowser()
	{
		driver.get().close();
		driver.remove();
	}
}