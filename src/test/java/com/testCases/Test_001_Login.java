package com.testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

	@BeforeMethod
	public void beforeTest() {
		log.startTestCase(Test_001_Login.class.getName());

		this.readConfigFile = new ReadConfigFile();
		this.readExcelData = new ReadWriteExcelData(this.readConfigFile.getTestDataPath());

		if (readConfigFile.getDriverType().contains("FF")) {
			System.setProperty("webdriver.gecko.driver", this.readConfigFile.getFireFOXDriverPath());
			this.driver = new FirefoxDriver();
			log.info("FireFox Browser Initiated");
		}

		else if (readConfigFile.getDriverType().contains("CC")) {
			System.setProperty("webdriver.chrome.driver", this.readConfigFile.getChromeDriverPath());
			this.driver = new ChromeDriver();
			log.info("Chrome Browser Initiated");
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

		// this.screenShot = new ConfigScreenshot(this.driver);
		try {
			AssertJUnit.assertEquals(useWebDriverElements.useWebElement(null, null, Action.getTitle, null,
					"Login page title matching with expected"), "Your store. Login");
			this.screenShot.takeScreenshot("Pass_loginPage");
		}

		catch (Exception e) {
			log.error(e.getMessage());
			this.screenShot.takeScreenshot("Fail_loginPage");
			e.printStackTrace();
			System.out.println(e);

		}

	}

	@Test(priority = 1)
	public void checkLoginPage() {
		try {
			// this.screenShot = new ConfigScreenshot(this.driver);

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

	}

}
