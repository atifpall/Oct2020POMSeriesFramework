package com.qa.opencart.tests;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic - 200: Feature name: Accounts Page for Demo Shop Application")
@Story("User Story - 301: Design Accounts Page for application with different test cases")
public class AccountsPageTest extends BaseTest {
	
	private static final Logger LOGGER = Logger.getLogger(String.valueOf(AccountsPageTest.class));
	
	@BeforeClass
	public void accountsPageSetup() {	
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
    @Description("accounts Page Title Test...")
    @Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void accountsPageTitleTest() {
		String title = accountsPage.getAccountsPageTitle();
		//System.out.println("title of the page is : " + title);
		LOGGER.info("title of the page is : " + title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test(priority=2)
	@Description("verify Accounts Page Header Test...")
    @Severity(SeverityLevel.NORMAL)
	public void verifyAccountsPageHeaderTest() {
		String header = accountsPage.getHeaderValue();
		System.out.println("account page header is.. " + header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Test(priority=3)
	@Description("verify Acc Page Sections Count Test...")
    @Severity(SeverityLevel.NORMAL)
	public void verifyAccPageSectionsCountTest() {
		Assert.assertTrue(accountsPage.getAccountSectionsCount() == Constants.ACCOUNTS_PAGE_SECTIONS_COUNT);
	}
	
	@Test(priority=4)
	@Description("verify Acc Sections List Test...")
    @Severity(SeverityLevel.NORMAL)
	public void verifyAccSectionsListTest() {
		List<String> accSectionList = accountsPage.getAccountSectionsList();
		System.out.println(accSectionList);
	}
	
	@Description("product search with iMac...")
    @Severity(SeverityLevel.CRITICAL)
	@Test(priority=5)
	public void searchTest_iMac() {
		Assert.assertTrue(accountsPage.doSearch("iMac"));
	}
	
	@Test(priority=6)
	@Description("product search with MacBook Air...")
    @Severity(SeverityLevel.CRITICAL)
	public void searchTest_MacBook() {
		Assert.assertTrue(accountsPage.doSearch("MacBook Air"));
	}
	
	@Test(priority=7)
	@Description("verify Product Results Test...")
    @Severity(SeverityLevel.CRITICAL)
	public void verifyProductResultsTest() {
		accountsPage.doSearch("iMac");
		accountsPage.selectProductFromResults("iMac");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
