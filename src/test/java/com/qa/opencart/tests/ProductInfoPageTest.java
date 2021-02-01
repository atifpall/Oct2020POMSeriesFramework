package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPagesetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	public void goToProductInfoPage(String productName) {
		accountsPage.doSearch(productName);
		productInfoPage = accountsPage.selectProductFromResults(productName);
	}
	
	
	
	@Test
	public void productInfoPageTitleTest_iMac() {
		String productName = "iMac"; 
		goToProductInfoPage(productName);
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle(productName),productName);

	}
	

	@Test
	public void productInfoPageTitleTest_MacBookAir() {
		String productName = "MacBook Air"; 
		goToProductInfoPage(productName);
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle(productName),productName);

	}
	
	@Test
	public void verifyProductImageTest() {
		String productName = "iMac";
		goToProductInfoPage(productName);
		Assert.assertTrue(productInfoPage.getProductImages()==3);

	}
	
	@Test
	public void verifyProductInfoTest() {
		String productName = "iMac";
		goToProductInfoPage(productName);
		Map<String, String> productInfoMap = productInfoPage.getProductInformation();
		
		System.out.println(productInfoMap);
		productInfoMap.forEach((k,v) -> System.out.println(k + " : " + v));
		
		SoftAssert softArrest = new SoftAssert();
		softArrest.assertEquals(productInfoMap.get("name"), productName);
		softArrest.assertEquals(productInfoMap.get("Brand"), "Apple");
		softArrest.assertEquals(productInfoMap.get("price"), "$100.00");
		softArrest.assertEquals(productInfoMap.get("Product Code"), "Product 14");
		softArrest.assertAll();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
