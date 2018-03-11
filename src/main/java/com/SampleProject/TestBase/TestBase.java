package com.SampleProject.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.SampleProject.ExcelReader.ExcelUtils;
import com.SampleProject.Helper.LoggerHelper;
import com.SampleProject.Helper.ResourceHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	private final Logger log = LoggerHelper.getLogger(TestBase.class);
	public WebDriver driver;
	public Properties OR = new Properties();
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "\\src\\main\\java\\com\\SampleProject\\Reports\\"
				+ formater.format(calendar.getTime()) + "_Sample_Report.html", false);
	}

	public void loadData() throws IOException {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\SampleProject\\Config\\config.properties");
		FileInputStream f = new FileInputStream(file);
		OR.load(f);

	}

	public void init() throws IOException {
		loadData();
		/*
		 * String log4jConfPath = "log4j.properties";
		 * PropertyConfigurator.configure(log4jConfPath);
		 */
		selectBrowser(OR.getProperty("browser"));
		getUrl(OR.getProperty("url"));
	}

	/*
	 * To Perform Browser selection Operation
	 * 
	 * @Param browser
	 * 
	 */
	public void selectBrowser(String browser) {
		System.out.println(System.getProperty("os.name"));
		if (System.getProperty("os.name").contains("Window")) {
			if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
				// Create object of ChromeOptions class
				ChromeOptions options = new ChromeOptions();
				// add parameter which will disable the extension
				options.addArguments("--disable-extensions");
				driver = new ChromeDriver(options);
				log.info("navigating to :-" + browser + " browser");
			} else if (browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				log.info("navigating to :-" + browser + " browser");
			}
		} else if (System.getProperty("os.name").contains("Mac")) {
			if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver");
				driver = new ChromeDriver();
				log.info("navigating to :-" + browser + " browser");
			} else if (browser.equals("firefox")) {
				System.setProperty("webdriver.firefox.marionette",
						System.getProperty("user.dir") + "/Drivers/geckodriver");
				driver = new FirefoxDriver();
				log.info("navigating to :-" + browser + " browser");
			}
		}
	}

	/*
	 * To launch URL Operation
	 * 
	 * @Param url
	 * 
	 */
	public void getUrl(String url) {
		log.info("navigating to :-" + url);
		driver.get(url);
		driver.manage().window().maximize();
	}

	/*
	 * To Perform Explicit Wait Operation
	 * 
	 * @Param driver
	 * 
	 * @Param timeOutInSeconds
	 * 
	 * @Param element
	 * 
	 */
	public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/*
	 * To Perform Implicit Wait Operation
	 * 
	 * @Param timeOutInSeconds
	 * 
	 */
	public void waitForWebPage(int timeOutInSeconds) {
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
		test.log(LogStatus.INFO, data);
	}

	/*
	 * To capture ScreenShot operation
	 * 
	 * @param fileName
	 */
	public String captureScreen(String fileName) {
		if (fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "\\src\\main\\java\\com\\SampleProject\\ScreenShots\\";
			destFile = new File(
					(String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}
	/*
	 * This method can be used for to get the status of test case
	 * 
	 * @param result
	 * 
	 */

	public void getresult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP,
					result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
			String screen = captureScreen("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}

	/**
	 * This method will give excel data in 2D array based on sheet Name
	 * 
	 * @param excellocation
	 * @param sheetName
	 * @return
	 */
	public String[][] getExcelData(String excelName, String sheetName, String firstCellValue) {
		String excellocation = ResourceHelper.getResourcePath("\\src\\main\\java\\com\\SampleProject\\TestData\\") + excelName;
		ExcelUtils excelUtils = new ExcelUtils();
		return excelUtils.getExcelData(excellocation, sheetName, firstCellValue);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		closeBrowser();
	}

	/*
	 * To Perform Browser close Operation
	 * 
	 */
	public void closeBrowser() {
		driver.quit();
		log.info("browser closed");
		extent.endTest(test);
		extent.flush();
	}

}
