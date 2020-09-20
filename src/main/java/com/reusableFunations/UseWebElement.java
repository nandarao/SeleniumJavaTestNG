package com.reusableFunations;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.WebDriver;

public class UseWebElement {

	private WebDriver driver;
	private LocateBy by;
	static Log log = new Log(UseWebElement.class.getName());

	public UseWebElement(WebDriver driver) {
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

		// Driver.findElement(getLocater(LocatorsList, LocatorsList_Value));

	}
	/*
	 * private By getLocater(LocatorsList LocatorsList, String LocatorsList_Value) {
	 * 
	 * switch (LocatorsList) { case Id: return By.id(LocatorsList_Value); case Name:
	 * return By.name(LocatorsList_Value); case ClassName: return
	 * By.className(LocatorsList_Value); case LinkText: return
	 * By.linkText(LocatorsList_Value); case partialLinkText: return
	 * By.partialLinkText(LocatorsList_Value); case TagName: return
	 * By.tagName(LocatorsList_Value); case CssSelector: return
	 * By.cssSelector(LocatorsList_Value); case XPath: return
	 * By.xpath(LocatorsList_Value); default: return null; } }
	 */
	//
	//
	// if (LocatorsList.toUpperCase().equals("ID")) {
	// return By.id(LocatorsList_Value);
	// }
	// else if (LocatorsList.toUpperCase().equals("NAME")) {
	// return By.name(LocatorsList_Value);
	// }
	// else if (LocatorsList.toUpperCase().equals("CLASSNAME")) {
	// return By.className(LocatorsList_Value);
	// }
	// else if (LocatorsList.toUpperCase().equals("LINKTEST")) {
	// return By.linkText(LocatorsList_Value);
	// }
	// else if (LocatorsList.toUpperCase().equals("PARTIALLINKTEXT")) {
	// return By.partialLinkText(LocatorsList_Value);
	// }
	// else if (LocatorsList.toUpperCase().equals("TAGNAME")) {
	// return By.tagName(LocatorsList_Value);
	// }
	// else if (LocatorsList.toUpperCase().equals("CSSSELECTOR")) {
	// return By.cssSelector(LocatorsList_Value);
	// }
	// else if (LocatorsList.toUpperCase().equals("XPATH")) {
	// return By.xpath(LocatorsList_Value);
	// }
	// else {
	// throw new Exception("Please Check LocatorsList we do not find such
	// LocatorsList");
	// }
	//

}
