package com.qa.opencart.factory;

import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private static final Logger LOGGER =Logger.getLogger(String.valueOf(OptionsManager.class));

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		
		LOGGER.info("Getting chrome options");
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless")))  co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		LOGGER.info("Getting firefox options");
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless")))  fo.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) fo.addArguments("--incognito");
		return fo;
	}
	
	
	
}
