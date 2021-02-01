package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void registrationPageSetup() {	
		registrationPage = loginPage.doClickOnRegisterLink();
	}
	
	
	@DataProvider
	public Object[][] getRegistrationData() {
		Object data[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;
	}
		
	
	@Test(dataProvider = "getRegistrationData")
	public void userRegistrationTest(String fn, String ln, String eId, String tel, String pwd, String subscribe) {
		Assert.assertTrue(registrationPage.doAccountRegistration(fn, ln, eId, tel, pwd, subscribe));
	}
	
}	
	


