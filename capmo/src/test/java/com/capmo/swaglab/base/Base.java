package com.capmo.swaglab.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.capmo.swaglab.helper.Helper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Base {

	public WebDriver driver;
	protected ExtentReports extent;
	public ExtentTest logger;
	WebDriverWait wait;
	public Properties prop;
	protected ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	protected ThreadLocal<WebDriver> webdriver = new ThreadLocal<WebDriver>();

	@BeforeSuite()
	public WebDriver openBrowser() throws IOException {

		prop = new Properties();
		// System.getProperty("user.dir")
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\capmo\\swaglab\\resources\\data.properties");

		prop.load(fis);

		// mvn test -Dbrowser=chrome

		 String browserName=System.getProperty("browser"); // Uncomment this line if
		// you are sending parameter from Maven
		//String browserName = prop.getProperty("browser");// comment this line if you are sending parameter from Maven
		System.out.println(browserName);

		if (browserName.contains("chrome")) {
			// execute in chrome driver
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\driver\\chromedriver\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			webdriver.set(new ChromeDriver(options));

		} else if (browserName.equals("firefox")) {
			// firefox code
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\driver\\geckodriver\\chromedriver.exe");
			webdriver.set(new FirefoxDriver());

		} else if (browserName.equals("IE")) {
			// IE code
		}
		return webdriver.get();
	}

	@BeforeTest
	public void startReport() throws IOException {
		// extent.setTestRunnerLogs(Thread.currentThread());

		// ExtentReports(String filePath,Boolean replaceExisting)
		// filepath - path of the file, in .htm or .html format - path where your report
		// needs to generate.
		// replaceExisting - Setting to overwrite (TRUE) the existing file or append to
		// it
		// True (default): the file will be replaced with brand new markup, and all
		// existing data will be lost. Use this option to create a brand new report
		// False: existing data will remain, new tests will be appended to the existing
		// report. If the the supplied path does not exist, a new file will be created.
		extent = new ExtentReports(
				System.getProperty("user.dir") + "//htmlReportsAndScreenshots//reports//SeleniumAutomation.html");
		// extent.addSystemInfo("Environment","Environment Name")
		extent.addSystemInfo("Host Name", "Selenium").addSystemInfo("Environment", "Capmo Automation Testing")
				.addSystemInfo("User Name", "Ajit Bhavle");
		// loading the external xml file (i.e., extent-config.xml) which was placed
		// under the base directory
		// You could find the xml file below. Create xml file in your project and copy
		// past the code mentioned below
		extent.loadConfig(new File(
				System.getProperty("user.dir") + "//src//test//java//com//capmo//swaglab//report//extent-config.xml"));

	}

	@BeforeMethod
	public void testLogger(ITestResult result) throws IOException {
		webdriver.get().manage().window().maximize();
		webdriver.get().get(prop.getProperty("url"));
		webdriver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger = extent.startTest(this.getClass().getSimpleName() + " :: " + result.getMethod().getMethodName());
		extentTest.set(logger);
		extentTest.get().assignAuthor("Ajit Bhavle");
		extentTest.get().assignCategory("Smoke Test");

	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.get().log(LogStatus.FAIL, "Test Case Failed is: " + result.getMethod().getMethodName());
			extentTest.get().log(LogStatus.FAIL,
					"Log trace is: " + result.getThrowable().getMessage() + result.getThrowable().getCause());
			String imagePath = Helper.takeScreenShot(webdriver.get(), "method_name");
			extentTest.get().log(LogStatus.FAIL,
					"ScreenShots Attached is : " + extentTest.get().addScreenCapture(imagePath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.get().log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
			throw new SkipException("Skipping this test-method with exception");
		} else {
			extentTest.get().log(LogStatus.PASS, "Test Case Passed is " + result.getName());
		}
		// ending test
		// endTest(logger) : It ends the current test and prepares to create HTML report

	}

	@AfterTest
	public void endReport() {
		extent.endTest(extentTest.get());
		extent.flush();
		// writing everything to document
		// flush() - to write or update test information to your report.
		// extent.flush();
		// Call close() at the very end of your session to clear all resources.
		// If any of your test ended abruptly causing any side-affects (not all logs
		// sent to ExtentReports, information missing), this method will ensure that the
		// test is still appended to the report with a warning message.
		// You should call close() only once, at the very end (in @AfterSuite for
		// example) as it closes the underlying stream.
		// Once this method is called, calling any Extent method will throw an error.
		// close() - To close all the operation
		// extent.close();

		
	}

	@AfterSuite
	public void afterClass() {
		webdriver.get().quit();
	}

	public void reportLog(String message) {
		logger.log(LogStatus.INFO, message);// For extentTest HTML report
		Reporter.log(message);

	}

}
