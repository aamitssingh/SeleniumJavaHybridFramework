package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetUp()
	{
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void productHeaderTest()
	{
		searchResultsPage = accountsPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectAndClickOnProduct("MacBook Pro");
		String accHeader = productInfoPage.getProductHeaderText();
		Assert.assertEquals(accHeader, "MacBook Pro");
		
	}
	
	@DataProvider
	public Object[][] getImageData()
	{
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"iMac", "iMac", 3},
			{"Apple", "Apple Cinema 30\"", 6}
		};
	}
	
	@Test(dataProvider = "getImageData")
	public void productImageCountTest(String productName, String mainProductName, int imageCount)
	{
		searchResultsPage = accountsPage.doSearch(productName);
		productInfoPage = searchResultsPage.selectAndClickOnProduct(mainProductName);
		//Assert.assertTrue(productInfoPage.getProductImageCount() > 0);
		//Assert.assertEquals(productInfoPage.getProductImageCount(), Constants.IMAC_IMAGE_COUNT);
		Assert.assertEquals(productInfoPage.getProductImageCount(), imageCount);
	}
	
//	Brand: Apple
//	Availability: In Stock
//	ExTaxPrice: $2,000.00
//	price: $2,000.00
//	Product Code: Product 18
//	productname: MacBook Pro
//	Reward Points: 800
	
	@Test
	public void productMetaDataTest()
	{
		searchResultsPage = accountsPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectAndClickOnProduct("MacBook Pro");
		Map<String, String> actualProductMap = productInfoPage.getProductMetaData();
		actualProductMap.forEach((k,v)->System.out.println(k +": " + v));
		
		//Hard Assetion:
			//Assert.assertEquals(actualProductMap.get("productname"),"MacBook Pro");
			//Assert.assertEquals(actualProductMap.get("Brand"),"Apple");
			//Assert.assertEquals(actualProductMap.get("price"),"$2,000.00");
		
		//Soft Assertion: 
			softAssert.assertEquals(actualProductMap.get("productname"),"MacBook Pro11");
			softAssert.assertEquals(actualProductMap.get("Brand"),"Apple");
			softAssert.assertEquals(actualProductMap.get("price"),"$2,000.00");
			softAssert.assertAll();
	}
	

}
