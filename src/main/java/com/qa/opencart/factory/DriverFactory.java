package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	private static final Logger LOGGER =Logger.getLogger(String.valueOf(DriverFactory.class));
	
	WebDriver driver;
	Properties prop;
	public static String highlight;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<>();

	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		//System.out.println("browser name is : " + browserName);
		LOGGER.info("browser name is : " + browserName);
		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);
		
		if (browserName.equals("chrome")) {
			LOGGER.info("set up chrome driver");
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if (browserName.equals("firefox")) {
			LOGGER.info("set up firefox driver");
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if (browserName.equals("safari")) {
			LOGGER.info("set up safari driver");
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			//System.out.println("please pass the correct browser name : " + browserName);
			LOGGER.info("please pass the correct browser name : " + browserName);
		}

		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();

		return getDriver();

	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_prop() {

		prop = new Properties();

		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}
	
	public String getScreenshot() {
		
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+ "/screenshots"+ System.currentTimeMillis()+ ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
//	public String getScreenshot() {
//		
//		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
//		String path = System.getProperty("user.dir")+ "/screenshots"+ System.currentTimeMillis()+ ".png";
//		File destination = new File(path);
//		try {
//			FileUtils.copyFile(src, destination);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return path;
//	}
	
	
	
	
	
}
