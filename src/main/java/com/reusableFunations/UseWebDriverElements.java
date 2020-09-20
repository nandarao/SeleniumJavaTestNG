package com.reusableFunations;

import java.util.List;
import java.util.Set;

public interface UseWebDriverElements {

	String useWebElement(LocatorsList locator, String Locator_Value, Action action, String action_values,String logValue);

	List<String> useWebElements(LocateBy By, String LocatorsList_Value, Action action, String action_values, String logValue);

	String useWebWindow();

	Set<String> useWebWindows();

	void frame();

}
