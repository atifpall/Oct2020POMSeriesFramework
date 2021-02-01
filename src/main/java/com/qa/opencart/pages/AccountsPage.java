package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. By locators / OR
	private By header = By.cssSelector("div#logo a");
	private By accountSectionsHeaders = By.cssSelector("div#content h2");
	
	//Locators for search page
	private By searchText = By.cssSelector("div#search input[name='search']");
	private By searchButton = By.cssSelector("div#search button[type='button']");
	private By searchItemResult = By.cssSelector("div.product-layout div.product-thumb");
	private By resultItems = By.cssSelector("div.product-thumb h4 a");
	
	
	

	// 2. Create the constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// 3. Page Actions/Methods
	public String getAccountsPageTitle() {
		//return driver.getTitle();
		return elementUtil.waitForPageTitlePresent(Constants.ACCOUNTS_PAGE_TITLE, 5);
	}

	public String getHeaderValue() {
		//if (driver.findElement(header).isDisplayed()) {
			if(elementUtil.doIsDisplayed(header)) {
			
			//return driver.findElement(header).getText();
				return elementUtil.doGetText(header);
			 
		}
		return null;
	}
	
	
	public int getAccountSectionsCount() {
		//return driver.findElements(accountSectionsHeaders).size();
		return elementUtil.getElements(accountSectionsHeaders).size();
	}

	public List<String> getAccountSectionsList() {

		List<String> accountsList = new ArrayList<String>();
		//List<WebElement> accSectionList = driver.findElements(accountSectionsHeaders);
		List<WebElement> accSectionList = elementUtil.getElements(accountSectionsHeaders);
		for (WebElement e : accSectionList) {
			String text = e.getText();

			accountsList.add(text);
			
		}
		return accountsList;

	}
	
	//Search features page actions
	public boolean doSearch(String productName) {
		elementUtil.doSendKeys(searchText, productName);
		elementUtil.doClick(searchButton);
		if(elementUtil.getElements(searchItemResult).size()>0) {
			return true;
		}
		return false;
	}
	
	
	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> resultItemsList = elementUtil.getElements(resultItems);
		System.out.println("total no. of items displayed :" + resultItemsList.size());
		
		for(WebElement e: resultItemsList) {
			if(e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
