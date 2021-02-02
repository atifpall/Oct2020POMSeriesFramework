package com.qa.opencart.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
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
public class LoginPageTest extends BaseTest {

	private static final Logger LOGGER = Logger.getLogger(String.valueOf(LoginPageTest.class));

	@Test
	@Description("login Page Title Test...")
	@Severity(SeverityLevel.MINOR)
	public void loginPageTitleTest() {

		String title = loginPage.getLoginPageTitle();
		// System.out.println("login page title is : " + title);
		LOGGER.info("getting page title : " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test
	@Description("forgot PwdLink Test...")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkTest() {
		LOGGER.info("checking forgot password link");
		Assert.assertTrue(loginPage.isForgotPwdLinkPresent());
	}

	@Test
	@Description("login Test...")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		LOGGER.info("Login with :" +prop.getProperty("username") + " " + prop.getProperty("password"));
		AccountsPage accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountsPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);

	}

}
