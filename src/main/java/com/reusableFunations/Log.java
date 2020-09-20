package com.reusableFunations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

//	private String className;
	private Logger log;
	
	public Log(String className) {
		log=LogManager.getLogger(className);
//		this.className=className;
	}

	public void startTestCase(String sTestCaseName) {

//		className = sTestCaseName;

		log.info("****************************************************************************************");

		log.info("****************************************************************************************");

		log.info("$$$$$$$$$$$$$$$$$$$$$$$         " + sTestCaseName + "          $$$$$$$$$$$$$$$$$$$$$$$$$");

		log.info("****************************************************************************************");

		log.info("****************************************************************************************");

	}

	public void endTestCase(String sTestCaseName) {

		log.info("XXXXXXXXXXXXXXXXXXXXXXX           " + "-E---N---D-" + "            XXXXXXXXXXXXXXXXXXXXXX");

		log.info("X");

		log.info("X");

		log.info("X");

		log.info("X");

	}

	public void info(String message) {

		log.info("  MSG  :  " + message);

	}

	public void warn(String message) {

		log.warn("  MSG  :  " + message);

	}

	public void error(String message) {

		log.error("  MSG  :  " + message);

	}

	public void fatal(String message) {

		log.fatal("  MSG  :  " + message);

	}

	public void debug(String message) {

		log.debug("  MSG  :  " + message);

	}

	public void trace(String message) {

		log.trace("  MSG  :  " + message);

	}

}