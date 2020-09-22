package com.utilitys;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigFile {

	private final String filePath = "src\\main\\java\\com\\configration\\Configuration.properties";
	private Properties properties;

	public ReadConfigFile() {

		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(this.filePath));
			this.properties = new Properties();

			try {
				this.properties.load(bufferedReader);
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public String getScreenshotPath() {
		String path = this.properties.getProperty("screenShotFoldarPath");

		if (path != null) {
			return path;
		} else {
			throw new RuntimeException("screenShotFoldarPath is not specified in Configuration.properties file");
		}
	}

	public String getReportFoldarPath() {
		String path = this.properties.getProperty("reportFoldarPath");

		if (path != null) {
			return path;
		} else {
			throw new RuntimeException("reportFoldarPath is not specified in Configuration.properties file");
		}
	}

	public String getTestDataPath() {
		String path = this.properties.getProperty("testDataFilePath");

		if (path != null) {
			return path;
		} else {
			throw new RuntimeException("testDataFilePath is not specified in Configuration.properties file");
		}
	}

	public String getFireFOXDriverPath() {
		String path = this.properties.getProperty("broserDriverFireFox");

		if (path != null) {
			return path;
		} else {
			throw new RuntimeException("broserDriverFireFox is not specified in Configuration.properties file");
		}
	}

	public String getChromeDriverPath() {
		String path = this.properties.getProperty("broserDriverCrome");

		if (path != null) {
			return path;
		} else {
			throw new RuntimeException("broserDriverCrome is not specified in Configuration.properties file");
		}
	}

	public String getDriverType() {
		String path = this.properties.getProperty("browserType");

		if (path != null) {
			return path;
		} else {
			throw new RuntimeException("browserType is not specified in Configuration.properties file");
		}
	}

	public long getImplicitlyWait() {
		String path = this.properties.getProperty("implicitlyWait");
		if (path != null) {
			return Integer.parseInt(path);
		} else {

			throw new RuntimeException("implicitlyWait is not specified in Configuration.properties file");
		}
	}

	public String getApplicationURL() {
		String path = this.properties.getProperty("applicationURL");

		if (path != null) {
			return path;
		} else {
			throw new RuntimeException("ApplicationURL is not specified in Configuration.properties file");

		}

	}

}
