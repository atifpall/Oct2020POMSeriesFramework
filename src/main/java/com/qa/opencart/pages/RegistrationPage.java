package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1. Object Repositories
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailID = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirm_pwd = By.id("input-confirm");
	
	private By subscribe_yes = By.xpath("//label[@class='radio-inline'][position()=1]/input");
	private By subscribe_no = By.xpath("//label[@class='radio-inline'][position()=2]/input");
	
	private By pp_radioButton = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By submitBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By success_msg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	
	//2. Constructor
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	
	//3. page actions and methods
	public boolean doAccountRegistration(String fn, String ln, String eId, String tel, 
			String pwd, String subscribe ) {
		
		
		elementUtil.doSendKeys(firstName, fn);
		elementUtil.doSendKeys(lastName, ln);
		elementUtil.doSendKeys(emailID, eId);
		elementUtil.doSendKeys(telephone, tel);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doSendKeys(confirm_pwd, pwd);
		
//		elementUtil.doSendKeys(this.firstName, firstName);
//		elementUtil.doSendKeys(this.lastName, lastName);
//		elementUtil.doSendKeys(this.emailID, emailID);
//		elementUtil.doSendKeys(this.telephone, telephone);
//		elementUtil.doSendKeys(this.password, password);
//		elementUtil.doSendKeys(this.confirm_pwd, password);
		
		if(subscribe.equals("yes")) {
			elementUtil.doClick(subscribe_yes);
		}else {
			elementUtil.doClick(subscribe_no);
		}
		
		elementUtil.doClick(pp_radioButton);
		elementUtil.doClick(submitBtn);
		
		String text = elementUtil.doGetText(success_msg);
		if(text.contains(Constants.ACCOUNT_SUCCESS_MESG)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;
		
		
		
	}
	
	
	
	
	

}
