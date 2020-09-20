package com.reusableFunations;

import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.WebDriver;

public class MethodsImplementation implements UseWebDriverElements {
	
	
	private WebDriver driver;
	private LocateBy by;
	private Log log = new Log(MethodsImplementation.class.getName());


	public MethodsImplementation(WebDriver driver) {
		this.driver = driver;
		this.by = new LocateBy();
	}



	public String useWebElement(LocatorsList locator, String Locator_Value, Action action, String action_values,
			String logValue) {
		if ( (driver==null || by==null)  ) {
			log.error("Class Name : " + UseWebElement.class.getName() 
					
					+ " values of -> Driver : " + driver + ","
					+ "   By : " + by 
					);
			throw new RuntimeErrorException(null, "Driver & By locater are null");
		}

		try {
			switch (action) {
			case clear:
				this.driver.findElement(this.by.getLocater(locator, Locator_Value)).clear();
				log.info(logValue);
				break;
			case click:
				this.driver.findElement(this.by.getLocater(locator, Locator_Value)).click();
				log.info(logValue);
				break;
			case sendKeys:
				this.driver.findElement(this.by.getLocater(locator, Locator_Value)).sendKeys(action_values);
				log.info(logValue);
				break;
			case getAttribute:
				log.info(logValue);
				return this.driver.findElement(this.by.getLocater(locator, Locator_Value)).getAttribute(action_values);
			case getText:
				log.info(logValue);
				return this.driver.findElement(this.by.getLocater(locator, Locator_Value)).getText();
			case get:
				log.info(logValue);
				this.driver.get(action_values);
				break;
			case getTitle:
				log.info(logValue);
				return this.driver.getTitle();
			default:
				log.warn("Class Name : " + UseWebElement.class.getName() + " values of -> locator : " + locator + ","
						+ "   Locator_Value : " + Locator_Value + ","
						+ "   Action : " + action + ","
						+ "   Action_values : " + action_values + ","
						+ "   LogValue : " + logValue 
						);

				return null;
			}

		} catch (Exception e) {
			log.error("Class Name : " + UseWebElement.class.getName() + " values of -> locator : " + locator + ","
					+ "   Locator_Value : " + Locator_Value + ","
					+ "   Action : " + action + ","
					+ "   Action_values : " + action_values + ","
					+ "   LogValue : " + logValue + ","
					+ "   Exception Msg : "
					+ e.getMessage());
		}

		return null;

	}



	public List<String> useWebElements(LocateBy By, String LocatorsList_Value, Action action, String action_values,
			String logValue) {
		// TODO Auto-generated method stub
		return null;
	}



	public String useWebWindow() {
		// TODO Auto-generated method stub
		return null;
	}



	public Set<String> useWebWindows() {
		// TODO Auto-generated method stub
		return null;
	}



	public void frame() {
		// TODO Auto-generated method stub
		
	}

	
	

	
	
}
