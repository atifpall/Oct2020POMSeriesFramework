package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private static final Logger LOGGER = Logger.getLogger(String.valueOf(LoginPage.class));

	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1. By locators / OR
	//should be created with private keyword (encapsulation) 
	// it should be encapsulated so that it cannot be used outside the class
	
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.cssSelector("input[value='Login']");
	private By forgotPwdLink = By.cssSelector("div.form-group a");
	private By registerLink = By.linkText("Register");
	
	
	//2. Constructor of the class
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3. Page Actions/ Methods in the page class
	
	@Step("get Login Page Title")
	public String getLoginPageTitle() {
		//return driver.getTitle();
		LOGGER.info("getting login page title..");
		return elementUtil.waitForPageTitlePresent(Constants.LOGIN_PAGE_TITLE, 5);
		
	}
	
	@Step("checking is Forgot Pwd Link is Present")
	public boolean isForgotPwdLinkPresent() {
		//return driver.findElement(forgotPwdLink).isDisplayed();
		LOGGER.info("checking forgot password link is present..");
		return elementUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("Login with username : {0} and password : {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Logging with : " + un + " " + pwd);
		
//		driver.findElement(username).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();
		
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new AccountsPage(driver);
	}
	
	@Step("navigate to register page..")
	public RegistrationPage doClickOnRegisterLink() {
		System.out.println("Navigate to Registration Page");
		elementUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	
	
	
}
