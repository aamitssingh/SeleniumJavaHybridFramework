package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

/*
 * 1. Check Search bar/option is there or not
 * 2. Check LogOut link/option is there or not
 * 3. Check My Account, My Order, My Affiliate Account and Newsletter is there or not
 * 
 */


public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By search = By.name("search");
	private By searchBtn = By.xpath("//div[@id='search']//span");
	private By logOut = By.linkText("Logout");
	//private By accSecHeaders = By.xpath("//div[@id='content']//h2");
	private By accSecHeaders = By.cssSelector("div#content h2");
	
	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getAccountsPageTitle()
	{
		return elementUtil.waitForTitleToBe(Constants.DEAULT_TIME_OUT, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	public boolean isLogoutLinkExist()
	{
		return elementUtil.doIsDiplayed(logOut);
	}
	
	public boolean isSearchFieldExist()
	{
		return elementUtil.doIsDiplayed(search);
	}
	
	public List<String> getAccountsHeadersList()
	{
		List<WebElement> headersList = elementUtil.getElements(accSecHeaders);
		List<String> actual_headersList = new ArrayList<String>();
		for(WebElement e : headersList)
		{
			actual_headersList.add(e.getText());
		}
		
		return actual_headersList;
	}
	
	/*	1. login will land into AccountsPage
		2. From AccountsPage use Search
		3. Clicking on Search button will give SearchResultPage
		4. In SearchResultPage verify:
			1. it is showing the same product given in search field
			2. total product
		5. From SearchResultPage Click on any product it will give ProductInfoPage. */
	
	public SearchResultsPage doSearch(String productName)
	{
		System.out.println("Searched Product Name = "+productName);
		elementUtil.doSendKeys(search, productName);
		elementUtil.doClick(searchBtn);
		return new SearchResultsPage(driver);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
