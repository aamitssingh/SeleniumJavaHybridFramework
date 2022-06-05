package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accountsPageSetUp()
	{
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void accountsPageTitleTests()
	{
		String pageTitle = accountsPage.getAccountsPageTitle();
		System.out.println("Accounts Page Title = "+pageTitle);
		Assert.assertEquals(pageTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accountsPageLogoutLinkTest()
	{
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	@Test
	public void accountsPageSearchFieldTest()
	{
		Assert.assertTrue(accountsPage.isSearchFieldExist());
	}
	
	@Test
	public void accountsPageSecHeaderTest()
	{
		List<String> actuaAccSecHeaderList = accountsPage.getAccountsHeadersList();
		System.out.println(actuaAccSecHeaderList);
		Assert.assertEquals(actuaAccSecHeaderList, Constants.EXP_ACCOUNTS_SECTIONS_LIST);
	}
	
	@DataProvider
	public Object[][] productData()
	{
		return new Object[][] {
			{"macbook"},
			{"iMac"},
			{"Apple"}
		};
	}
	
	@Test(dataProvider= "productData")
	public void searchTest(String productName)
	{
		searchResultsPage = accountsPage.doSearch(productName);
		Assert.assertTrue(searchResultsPage.getSearchProductListCount() > 0);
	}
	
	
	@DataProvider
	public Object[][] productSelectData()
	{
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""}
		};
	}
	
	@Test(dataProvider= "productSelectData")
	public void selectAndClickOnProduct(String productName, String mainProductName)
	{
		searchResultsPage = accountsPage.doSearch(productName);
		searchResultsPage.selectAndClickOnProduct(mainProductName);
	}
	
	
	
	
	
	
	
	
	
}

