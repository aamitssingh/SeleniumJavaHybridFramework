package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	
	//1. By loactor -- OR:
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@type='submit' and @value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	//private By registerLink = By.xpath("//a[text()='Register' and @class='list-group-item']");
	//private By registerLink = By.linkText("Register");
	
	By myAccount_PM = By.xpath("//span[text()='My Account']");
	By register_CM = By.xpath("//ul[@class='list-inline']//li[@class='dropdown open']//ul//li//a[text()='Register']");
	
	
	//2. Page Constructor:
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//3. Page Actions/Methods:
	
	public String getLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public String getLoginPageUrl()
	{
		return driver.getCurrentUrl();
	}
	
	public boolean isForgotPwdLinkExist()
	{
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return elementUtil.doIsDiplayed(forgotPwdLink);
	}
	
//	public boolean isRegisterLinkExist()
//	{
//		
//		return driver.findElement(registerLink).isDisplayed();
//	}
	
	public AccountsPage doLogin(String un, String pwd)
	{
//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();
		
		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new AccountsPage(driver);
		
	}
	
	public RegistrationPage navigateToRegisterPage_FromTopMenu()
	{
		elementUtil.parentChild_MenuHandle(myAccount_PM,register_CM );
		return new RegistrationPage(driver);
	}
	
	
	
	
	
	
	

}





