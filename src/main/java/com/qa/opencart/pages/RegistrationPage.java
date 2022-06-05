package com.qa.opencart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

/*
 * 1. From LoginPage(without login) click on 'My Account' menu and the 'Register' sub-menu
 * 2. Check you landed in 'Register Account' page.
 * 3. In 'Register Account' page, fill First Name, Last Name, E-Mail, Telephone, Password, Confirm Password and click on Continue button
 * 4. Check the Error msg
 * 5. Select the checkbox 'Privacy Policy' and click on Continue Button
 * 6. Verify the success message 
 */

public class RegistrationPage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telph = By.id("input-telephone");
	private By password = By.id("input-password");
	private By cnfPassword = By.id("input-confirm");
	private By agreeCkBox = By.xpath("//input[@type='checkbox' and @name='agree'] ");
	private By continueBtn = By.xpath("//input[@type='submit']");
	
	private By warning = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	private By success = By.xpath("//div[@id='content']//h1");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegistrationPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		
	}
	
	public String getRegistrationPageTitle()
	{
		return elementUtil.waitForTitleToBe(Constants.DEAULT_TIME_OUT, Constants.REGISTER_PAGE_TITLE);
	}
	
	public boolean doRegistration(String fname, String lname, String e_mail, String ph, String pwd, String cpwd)
	{
		fillRegForm(fname, lname, e_mail, ph, pwd, cpwd);
		selectAgreeAndContinue();
		return getRegistrationStatus();
	}
	
	private void fillRegForm(String fname, String lname, String e_mail, String ph, String pwd, String cpwd)
	{
		elementUtil.doSendKeys(this.firstName, fname);
		elementUtil.doSendKeys(this.lastName, lname);
		elementUtil.doSendKeys(this.email, e_mail);
		elementUtil.doSendKeys(this.telph, ph);
		elementUtil.doSendKeys(this.password, pwd);
		elementUtil.doSendKeys(this.cnfPassword, cpwd);
		elementUtil.doClick(continueBtn);
	}
	
	private void selectAgreeAndContinue()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(warning));
		
		String warningMsg = elementUtil.doGetText(warning);
		
		if(warningMsg.equals(Constants.REGISTER_PAGE_WARNING))
		{
			elementUtil.doClick(agreeCkBox);
			elementUtil.doClick(continueBtn);	
		}
	}
	
	private boolean getRegistrationStatus()
	{
		String successMsg = elementUtil.doGetText(success);
		if(successMsg.equals(Constants.REGISTER_SUCCESS_MESSAGE))
		{
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

}
