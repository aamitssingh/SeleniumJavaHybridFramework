package com.qa.opencart.pages;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By productNameHeader = By.xpath("//div[@id='content']//h1");
	//private By productImages = By.xpath("//ul[@class='thumbnails']//img");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By quantity = By.id("input-quantity");
	private By addToCartbtn = By.id("button-cart");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]//li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]//li");
	
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getProductHeaderText()
	{
		elementUtil.waitForElementsVisible(productNameHeader, Constants.DEAULT_TIME_OUT);
		return elementUtil.doGetText(productNameHeader);
	}

	public int getProductImageCount()
	{
		return elementUtil.waitForElementsVisible(productImages, Constants.DEAULT_TIME_OUT).size();
	}
	
	public Map<String, String> getProductMetaData()
	{
		Map<String, String> prodMap = new HashMap<String,String>();
		String productName = elementUtil.doGetText(productNameHeader);
		prodMap.put("productname", productName);
		getProdMetaData(prodMap);
		getProdPriceData(prodMap);
		return prodMap;
	}
	
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
	private void getProdMetaData(Map<String, String> prodMap)
	{
		List<WebElement> metaList = elementUtil.getElements(productMetaData);
		for(WebElement e : metaList)
		{
			String metaText = e.getText();
			String metaKey = metaText.split(":")[0].trim();
			String metaValue = metaText.split(":")[1].trim();
			prodMap.put(metaKey, metaValue);
		}
	}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	
	private void getProdPriceData(Map<String, String> prodMap)
	{
		List<WebElement> metaList = elementUtil.getElements(productPriceData);
		String actualPrice = metaList.get(0).getText().trim();
		String exTaxPrice = metaList.get(1).getText().trim();
		prodMap.put("price", actualPrice);
		prodMap.put("ExTaxPrice", exTaxPrice.split(":")[1].trim());
	}
}
