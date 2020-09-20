package com.DelatePackage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.DelatePackage.UseWebDriverElements;
import com.DelatePackage.extOfInterface;

interface InterTest{
	
	public void interTest_1();
	
	
}


class InterClass implements InterTest {

	public void interTest_1() {
System.err.println("Test InterTest_1");		
	}
	
}

public class Test1{
	
//	public static void main(String[] args){
//	InterTest intt = new InterClass();
//	UseWebDriverElements usew = new Test();
//	
//	intt.interTest_1();
//	usew.useWebElement();
//	
//	
//	}
	
@Test	
public void check(){

InterTest intt = new InterClass();
UseWebDriverElements usew = new extOfInterface();
usew.useWebElement();
intt.interTest_1();	
}

}