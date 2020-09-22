package com.testCases;

import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.functions.Replace;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pageObjects.LoginPage;
import com.reusableFunations.Action;
import com.reusableFunations.Log;
import com.reusableFunations.MethodsImplementation;
import com.reusableFunations.UseWebDriverElements;
import com.utilitys.ConfigScreenshot;
import com.utilitys.ReadConfigFile;
import com.utilitys.ReadWriteExcelData;

public class Test_001_Login {

	private WebDriver driver;
	private LoginPage login;
	// private UseWebElement useWebDriverElements;
	private UseWebDriverElements useWebDriverElements;
	public ConfigScreenshot screenShot;
	// private String url = "https://admin-demo.nopcommerce.com/";
	private String userID; // = "admin@yourstore.com";
	private String password;// = "admin";
	private ReadConfigFile readConfigFile;
	private ReadWriteExcelData readExcelData;
	static Log log = new Log(Test_001_Login.class.getName());
	public ExtentReports extent = new ExtentReports();
	public ExtentSparkReporter spark = null;
	ExtentTest logger = null;
	String projectFoldar =System.getProperty("user.dir");

	
	@BeforeMethod
	public void beforeTest() {
		
		this.readConfigFile = new ReadConfigFile();
		this.readExcelData = new ReadWriteExcelData(this.readConfigFile.getTestDataPath());
		log.startTestCase(Test_001_Login.class.getName());
		spark = new ExtentSparkReporter(projectFoldar+this.readConfigFile.getScreenshotPath());
		extent.attachReporter(this.spark);
		

		if (readConfigFile.getDriverType().contains("FF")) {
			System.setProperty("webdriver.gecko.driver", this.readConfigFile.getFireFOXDriverPath());
			this.driver = new FirefoxDriver();
			log.info("FireFox Browser Initiated");
			//extent.createTest("Test Loing Page Titel")//.log(Status.PASS, "Bronzer Open");
		

		}

		else if (readConfigFile.getDriverType().contains("CC")) {
			System.setProperty("webdriver.chrome.driver", this.readConfigFile.getChromeDriverPath());
			this.driver = new ChromeDriver();
			log.info("Chrome Browser Initiated");
			logger.log(Status.INFO, "Bronzer Open");
		}

		this.driver.manage().timeouts().implicitlyWait(this.readConfigFile.getImplicitlyWait(), TimeUnit.SECONDS);
		// this.useWebDriverElements = new UseWebElement(driver);
		this.screenShot = new ConfigScreenshot(this.driver);
		this.login = new LoginPage(driver);
		this.useWebDriverElements = new MethodsImplementation(driver);
		this.useWebDriverElements.useWebElement(null, null, Action.get, this.readConfigFile.getApplicationURL(),
				"URL entered");

	}

	@Test(priority = 0)
	public void checkLoginTilte() {
		
		try {
			logger = extent.createTest("Test Loing Page Titel");
			logger.log(Status.INFO, "Bronzer Open");
			AssertJUnit.assertEquals(useWebDriverElements.useWebElement(null, null, Action.getTitle, null,
					"Login page title matching with expected"), "Your store. Login");
			logger.log(Status.FAIL, "Login page title matching with expected");
			logger.log(Status.INFO, "Login page title matching with expected", MediaEntityBuilder.createScreenCaptureFromPath( this.screenShot.takeScreenshot("Pass_loginPage123")).build());
			//this.screenShot.takeScreenshot("Pass_loginPage");
			
		}

		catch (Exception e) {
			log.error(e.getMessage());
			logger.log(Status.FAIL, "Login page title matching with expected", MediaEntityBuilder.createScreenCaptureFromPath(this.screenShot.takeScreenshot("Fail_loginPage")).build());
		//	this.screenShot.takeScreenshot("Fail_loginPage");
			e.printStackTrace();
			System.out.println(e);

		}

	}

	@Test(priority = 1)
	public void checkLoginPage() {
		try {
			logger = extent.createTest("Test Loing Page");
			this.userID = this.readExcelData.getCellData("LoginPageData", 1, 0);
			this.password = this.readExcelData.getCellData("LoginPageData", 1, 1);
			this.login.enterUserName(userID);
			this.login.enterPassword(password);
			this.screenShot.takeScreenshot("Pass_loginPage");
			this.login.clickLogin();
			this.screenShot.takeScreenshot("Pass_HomePage");
			this.login.clickLogout();
		} catch (Exception e) {
			this.screenShot.takeScreenshot("Fail_loginPage");
			this.driver.close();
			log.error(e.getMessage());
		}

	}

	@AfterMethod
	public void afterMathod() {
		log.endTestCase(Test_001_Login.class.getName());
		this.driver.quit();
		extent.flush();
	}
	
	@AfterClass
	public void aterSuite() {
	
		
		log.info("******************************************************************************");

	}

}
