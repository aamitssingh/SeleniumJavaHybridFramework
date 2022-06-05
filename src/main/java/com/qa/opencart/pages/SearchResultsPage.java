package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By searchHeaderText = By.xpath("//div[@id='content']//h1");
	private By count_SearchProductResult = By.xpath("//div[@class='caption']//a");
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
//	public void getSearchResultPageTitle()
//	{
//		elementUtil.waitForTitleToBe(Constants.DEAULT_TIME_OUT, title)
//	}
	
	public String getSearchHeaderText()
	{
		return elementUtil.doGetText(searchHeaderText);
	}
	
	public int getSearchProductListCount()
	{
		return elementUtil.waitForElementsVisible(count_SearchProductResult, Constants.DEAULT_TIME_OUT).size();
	}
	
	public ProductInfoPage selectAndClickOnProduct(String mainProductName)
	{
		List<WebElement> productList = elementUtil.waitForElementsVisible(count_SearchProductResult, Constants.DEAULT_TIME_OUT);
		
		for(WebElement e : productList)
		{
			String productListText = e.getText();
			//System.out.println(productListText);
			if(productListText.equals(mainProductName))
			{
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
