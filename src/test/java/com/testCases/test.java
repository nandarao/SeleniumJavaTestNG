package com.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utilitys.ConfigScreenshot;
import com.utilitys.ReadConfigFile;
import com.utilitys.ReadWriteExcelData;

public class test {
	private ReadConfigFile readConfigFile;
	public static ExtentReports extent =new ExtentReports();
	public ExtentSparkReporter spark = null;
	public ExtentTest logger;
	public ConfigScreenshot screenShot;

	
	@BeforeTest
	public void befroeTest() {
		this.readConfigFile = new ReadConfigFile();
		spark = new ExtentSparkReporter(this.readConfigFile.getScreenshotPath());
		extent.attachReporter(this.spark);
		
	}

	@BeforeMethod
	public void beforeTest() {
		
		
		
		logger = extent.createTest("Test Loing Page Titel");
		logger.log(Status.INFO, "Bronser Open");

		

	}

	@Test(priority = 0)
	public void checkLoginTilte() {
		logger.log(Status.INFO, "matching");
		

	}
/*
	@Test(priority = 1)
	public void checkLoginPage() {
	
	}*/
	@AfterMethod
	public void afterMathod() {

	}
	
	@AfterClass
	public void aterSuite() {
		spark = new ExtentSparkReporter(this.readConfigFile.getScreenshotPath());
		extent.attachReporter(this.spark);
		extent.flush();

	}
	

}