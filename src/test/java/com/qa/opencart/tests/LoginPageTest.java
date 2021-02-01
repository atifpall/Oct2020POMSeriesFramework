package com.qa.opencart.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//high level feature, list of all user stories wrt to a feature
@Epic("Epic - 100: Feature name: Login Page for Demo Shop Application")
@Story("User Story - 300: Design Login Page for application with different test cases")
//how to add the listener at the class level - just for getting reports for loginPage class.
//@Listeners(ExtentReportListener.class)
public class LoginPageTest extends BaseTest{

	@Test
	@Description("login Page Title Test...")
	@Severity(SeverityLevel.MINOR)
	public void loginPageTitleTest() {
		
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is : " + title);
		AssertJUnit.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	@Description("forgot PwdLink Test...")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkTest() {
		AssertJUnit.assertTrue(loginPage.isForgotPwdLinkPresent());
	}
	
	
	@Test
	@Description("login Test...")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		AccountsPage accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		AssertJUnit.assertEquals(accountsPage.getAccountsPageTitle(),Constants.ACCOUNTS_PAGE_TITLE);
	
	}
	
}


