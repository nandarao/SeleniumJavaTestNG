package com.DelatePackage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;


public class Log4j2HelloWorldExample 
{
	
   
     static Logger log = LogManager.getLogger(Log4j2HelloWorldExample.class.getName());
     
     
     @Test
    public void test() 
    {
        
        log.info("Info Message Logged !!!");
        log.error("Error Message Logged !!!");
        log.debug("Debug Message Logged !!!");
        log.warn("Warn Message Logged !!!");
    }
}