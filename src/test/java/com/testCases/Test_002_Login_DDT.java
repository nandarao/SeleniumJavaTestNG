package com.testCases;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pageObjects.LoginPage;
import com.reusableFunations.Action;
import com.reusableFunations.Log;
import com.reusableFunations.MethodsImplementation;
import com.reusableFunations.UseWebDriverElements;
import com.utilitys.ConfigScreenshot;
import com.utilitys.ReadConfigFile;
import com.utilitys.ReadWriteExcelData;

public class Test_002_Login_DDT {

	private WebDriver driver;
	private LoginPage login;
//	private UseWebElement useWebDriverElements;
	private UseWebDriverElements useWebDriverElements;
	private String url;// = "https://admin-demo.nopcommerce.com/";
	private String userID; // = "admin@yourstore.com44444";
	private String password; // = "admin";
	private String expected;
	private ReadWriteExcelData readExcelData;
	private SoftAssert softAssert = new SoftAssert();
	private ConfigScreenshot screenShot;
	private ReadConfigFile readConfigFile;
	static Log log = new Log(Test_002_Login_DDT.class.getName());

	@BeforeMethod
	public void beforeTest() {
		log.startTestCase(Test_002_Login_DDT.class.getName());
		
		this.readConfigFile = new ReadConfigFile();
		this.readExcelData = new ReadWriteExcelData(this.readConfigFile.getTestDataPath());
		this.url = this.readConfigFile.getApplicationURL();


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
//		this.useWebDriverElements = new UseWebElement(driver);
		this.useWebDriverElements = new MethodsImplementation(driver);
		this.screenShot = new ConfigScreenshot(this.driver);
		this.login = new LoginPage(driver);
		this.useWebDriverElements.useWebElement(null, null, Action.get, url, "URL entered");


	}

	@Test(priority = 0)
	public void checkLoginPage() {


		ArrayList<String> status = new ArrayList<String>();

		for (int i = 1; i < this.readExcelData.getRowCont("LoginPageData") + 1; i++) {
			log.info("Testing with data set : "+i);
			try {
				
				this.userID = this.readExcelData.getCellData("LoginPageData", i, 0);
				this.password = this.readExcelData.getCellData("LoginPageData", i, 1);
				this.expected = this.readExcelData.getCellData("LoginPageData", i, 2);
				this.login.enterUserName(userID);
				this.login.enterPassword(password);
				this.login.clickLogin();
				AssertJUnit.assertTrue(true);
				Thread.sleep(5000);

				if (this.useWebDriverElements.useWebElement(null, null, Action.getTitle, null, "Home page title matching with expected")
						.contentEquals("Dashboard / nopCommerce administration")) {
					System.out.println("match");

					if (this.expected.contains("Pass")) {

						this.screenShot.takeScreenshot("Pass_HomePage");
						AssertJUnit.assertTrue(true);
						this.login.clickLogout();
						this.readExcelData.writeData("LoginPageData", i, 3, "Pass");
						status.add("Pass");

					} else if (this.expected.contains("Fail")) {

						this.screenShot.takeScreenshot("Fail_HomePage");
						this.login.clickLogout();
						AssertJUnit.assertFalse(false);
						this.readExcelData.writeData("LoginPageData", i, 3, "Fail");
						status.add("Fail");
					}

				}

				else if (!this.useWebDriverElements.useWebElement(null, null, Action.getTitle, null, "Login page title matching with expected")
						.contentEquals("Dashboard / nopCommerce administration")) {

					if (this.expected.contains("Pass")) {

						this.screenShot.takeScreenshot("Fail_LoginPage_Negative_Testing");
						this.readExcelData.writeData("LoginPageData", i, 3, "Fail");
						AssertJUnit.assertFalse(true);
						status.add("Fail");

					} else if (this.expected.contains("Fail")) {

						this.screenShot.takeScreenshot("Pass_LoginPage_Negative_Testing");
						this.readExcelData.writeData("LoginPageData", i, 3, "Pass");
						AssertJUnit.assertTrue(true);
						status.add("Pass");
					}

				}

			} catch (Exception e) {
				log.error(e.getMessage());
				this.screenShot.takeScreenshot("Fail_loginPage");
				AssertJUnit.assertFalse(true);
				e.printStackTrace();
			}

		}

		if (status.contains("Fail")) {

			AssertJUnit.assertFalse(true);
			this.driver.quit();

		} else {

			AssertJUnit.assertTrue(true);
			this.driver.quit();
		}

	}

	@AfterMethod
	public void afterMathod() {
		log.endTestCase(Test_002_Login_DDT.class.getName());
		log.info(this.softAssert.toString());
		this.softAssert.assertAll();
		System.out.println("@AfterMethod");

	}

}
