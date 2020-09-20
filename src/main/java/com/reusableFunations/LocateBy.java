package com.reusableFunations;

import org.openqa.selenium.By;



class LocateBy {

	private Log log = new Log(LocateBy.class.getName());
	
	public By getLocater(LocatorsList locator, String LocatorsList_Value) {

		switch (locator) {
		case Id:
			return By.id(LocatorsList_Value);
		case Name:
			return By.name(LocatorsList_Value);
		case ClassName:
			return By.className(LocatorsList_Value);
		case LinkText:
			return By.linkText(LocatorsList_Value);
		case partialLinkText:
			return By.partialLinkText(LocatorsList_Value);
		case TagName:
			return By.tagName(LocatorsList_Value);
		case CssSelector:
			return By.cssSelector(LocatorsList_Value);
		case XPath:
			return By.xpath(LocatorsList_Value);
		default:
			log.error("Class Name : "+LocateBy.class.getName() +" values of -> locator : "+locator+",  LocatorsList_Value : "+LocatorsList_Value );
			return null;

		}

	}

}
